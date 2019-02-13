package com.example.nisha.doctorapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.nisha.doctorapp.ChangePasswordPOJO.ChangeBean;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ChangePassword extends AppCompatActivity {

    Button submit;

    ImageView close;

    EditText np , cp;

    ProgressBar bar;

    ConnectionDetector cd;
    SharedPreferences pref;
    SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        pref = getSharedPreferences("pref" , Context. MODE_PRIVATE);

        edit = pref.edit();

        cd = new ConnectionDetector(ChangePassword.this);

        np = findViewById(R.id.np);
        cp = findViewById(R.id.cp);
        bar = findViewById(R.id.progress);
        submit = findViewById(R.id.submit);


                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String n = np.getText().toString().trim();
                        String c = cp.getText().toString().trim();

                        if (n.length()>3){

                            if (n.equals(c)){

                                bar.setVisibility(View.VISIBLE);

                                Bean b = (Bean)getApplicationContext();

                                Retrofit retrofit = new Retrofit.Builder()
                                        .baseUrl(b.BaseUrl)
                                        .addConverterFactory(ScalarsConverterFactory.create())
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .build();

                                AllApiInterface cr = retrofit.create(AllApiInterface.class);
                                Call<ChangeBean> call = cr.chanage(b.userid ,n );
                                call.enqueue(new Callback<ChangeBean>() {
                                    @Override
                                    public void onResponse(Call<ChangeBean> call, Response<ChangeBean> response) {

                                        if (Objects.equals(response.body().getStatus(),"1")){

                                            Toast.makeText(ChangePassword.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                                            edit.putString("password" , np.getText().toString());
                                            edit.apply();

                                            np.setText("");
                                            cp.setText("");
                                            finish();


                                        }else {
                                            Toast.makeText(ChangePassword.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                        }

                                        bar.setVisibility(View.GONE);
                                    }

                                    @Override
                                    public void onFailure(Call<ChangeBean> call, Throwable t) {

                                        bar.setVisibility(View.GONE);

                                    }
                                });


                            }else {
                                Toast.makeText(ChangePassword.this, "Password didn't match", Toast.LENGTH_SHORT).show();
                            }

                        }else {

                            Toast.makeText(ChangePassword.this, "Please enter atleat 4 digits password", Toast.LENGTH_SHORT).show();
                        }

                    }
                });



        close = findViewById(R.id.back);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }
}
