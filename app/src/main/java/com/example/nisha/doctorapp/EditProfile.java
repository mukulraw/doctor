package com.example.nisha.doctorapp;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nisha.doctorapp.EditProfilePOJO.EditProfileBean;

import java.util.Objects;

import belka.us.androidtoggleswitch.widgets.ToggleSwitch;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class EditProfile extends AppCompatActivity {

    TextView name , email , age;

    Button submit;

    ToggleSwitch toggleSwitch;

    int g;

    ImageView back;

    String gender;

    ProgressBar bar;

    ConnectionDetector cd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        cd = new ConnectionDetector(EditProfile.this);

        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        submit = findViewById(R.id.submit);
        toggleSwitch = findViewById(R.id.textView17);
        bar = findViewById(R.id.progress);
        back = findViewById(R.id.imageButton3);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                finish();
            }
        });

        age.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(EditProfile.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dob_popup);
                dialog.setCancelable(true);
                dialog.show();

                Button submit = dialog.findViewById(R.id.button11);
                final DatePicker dp = dialog.findViewById(R.id.view14);

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String dd = String.valueOf(dp.getDayOfMonth()) + "-" + String.valueOf(dp.getMonth() + 1) + "-" + dp.getYear();

                        Log.d("dddd" , dd);

                        age.setText(dd);

                        dialog.dismiss();

                    }
                });

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (cd.isConnectingToInternet()){

                    final String n = name.getText().toString();
                    final String a = age.getText().toString();
                    g = toggleSwitch.getCheckedTogglePosition();

                    if (g == 0) {
                        gender = "Male";
                    }

                    if (g == 1) {
                        gender = "Female";
                    }

                    bar.setVisibility(View.VISIBLE);

                    Bean b = (Bean)getApplicationContext();

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(b.BaseUrl)
                            .addConverterFactory(ScalarsConverterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    AllApiInterface cr = retrofit.create(AllApiInterface.class);

                    Call<EditProfileBean> call = cr.edit(b.userid ,n , gender,a);

                    call.enqueue(new Callback<EditProfileBean>() {
                        @Override
                        public void onResponse(Call<EditProfileBean> call, Response<EditProfileBean> response) {

                            if (Objects.equals(response.body().getStatus() , "1")){

                                Toast.makeText(EditProfile.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                                name.setText("");
                                age.setText("");
                                finish();


                            }else {

                                Toast.makeText(EditProfile.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            bar.setVisibility(View.GONE);
                        }

                        @Override
                        public void onFailure(Call<EditProfileBean> call, Throwable t) {

                            bar.setVisibility(View.GONE);

                        }
                    });


                }else {
                    Toast.makeText(EditProfile.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
                }



            }
        });


    }
}
