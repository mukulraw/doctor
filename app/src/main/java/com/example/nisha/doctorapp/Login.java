package com.example.nisha.doctorapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nisha.doctorapp.LoginPOJO.LoginBean;
import com.example.nisha.doctorapp.SignupPOJO.SignupBean;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Login extends AppCompatActivity {

    Button login;

    TextView signup , forget;

    EditText email , pass;

    ProgressBar bar;

    ConnectionDetector cd;

    SharedPreferences pref;

    SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pref = getSharedPreferences("pref", Context.MODE_PRIVATE);

        edit = pref.edit();

        cd = new ConnectionDetector(Login.this);

        forget = findViewById(R.id.textView10);

        signup = findViewById(R.id.textView6);

        login = findViewById(R.id.button3);

        email = findViewById(R.id.editText);

        pass = findViewById(R.id.editText2);

        bar = findViewById(R.id.progressBar2);
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(Login.this , Forget.class);
                startActivity(i);
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (cd.isConnectingToInternet()){

                    final String e = email.getText().toString();
                    final String p = pass.getText().toString();

                    if (Utils.isValidMail(e)){

                        if (p.length()>0){

                            bar.setVisibility(View.VISIBLE);

                            Bean b = (Bean) getApplicationContext();

                            Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl(b.BaseUrl)
                                    .addConverterFactory(ScalarsConverterFactory.create())
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build();

                            AllApiInterface cr = retrofit.create(AllApiInterface.class);

                            Call<LoginBean> call = cr.login(e , p);
                            call.enqueue(new Callback<LoginBean>() {
                                @Override
                                public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {

                                    if (Objects.equals(response.body().getStatus() , "1")){

                                        Bean b = (Bean) getApplicationContext();

                                        edit.putString("email", e);
                                        edit.putString("password", p);
                                        edit.apply();

                                        Toast.makeText(Login.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                                        Intent i = new Intent(Login.this , SetProfileIAmge.class);
                                        startActivity(i);


                                    }else {

                                        Toast.makeText(Login.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                    }


                                    bar.setVisibility(View.GONE);
                                }

                                @Override
                                public void onFailure(Call<LoginBean> call, Throwable t) {

                                    bar.setVisibility(View.GONE);
                                }
                            });


                        } else if (DataValidation.isNotValidPassword(p)) {
                            Toast.makeText(Login.this, "Invalid Password", Toast.LENGTH_SHORT).show();
                        }

                    }else {

                        Toast.makeText(Login.this, "Invalid Email", Toast.LENGTH_SHORT).show();
                    }


                }else {

                    Toast.makeText(Login.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
                }






            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(Login.this , Signup.class);
                startActivity(i);
            }
        });
















    }
}
