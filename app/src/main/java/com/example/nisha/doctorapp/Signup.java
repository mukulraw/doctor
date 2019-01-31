package com.example.nisha.doctorapp;

import android.app.Dialog;
import android.content.Intent;
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

import com.example.nisha.doctorapp.SignupPOJO.SignupBean;

import java.util.Objects;

import belka.us.androidtoggleswitch.widgets.ToggleSwitch;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Signup extends AppCompatActivity {

    EditText email, pass, ph, cp, name, dob;

    TextView login;

    Button signup;

    ImageView back;

    ProgressBar bar;

    ToggleSwitch toggleSwitchGender;

    int genderPositionToggle;

    String mGender, mClass = "";

    ConnectionDetector cd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        cd = new ConnectionDetector(Signup.this);

        signup = findViewById(R.id.button3);

        back = findViewById(R.id.imageView2);

        email = findViewById(R.id.editText);

        pass = findViewById(R.id.editText2);

        ph = findViewById(R.id.phone);

        name = findViewById(R.id.editText3);

        dob = findViewById(R.id.editText4);

        cp = findViewById(R.id.editText5);

        bar = findViewById(R.id.progressBar3);

        toggleSwitchGender = findViewById(R.id.textView17);

        back = findViewById(R.id.imageView2);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(Signup.this);
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

                        Log.d("dddd", dd);

                        dob.setText(dd);

                        dialog.dismiss();

                    }
                });

            }
        });


        genderPositionToggle = toggleSwitchGender.getCheckedTogglePosition();


        if (genderPositionToggle == 0) {
            mGender = "Male";
        }

        if (genderPositionToggle == 1) {
            mGender = "Female";
        }
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (cd.isConnectingToInternet()) {

                    String n = name.getText().toString();
                    String e = email.getText().toString();
                    String p = ph.getText().toString();
                    String d = dob.getText().toString();
                    String pp = pass.getText().toString();
                    String ccp = cp.getText().toString();

                    if (n.length() > 0) {

                        if (d.length() > 0) {

                            if (Utils.isValidMail(e)) {

                                if (Utils.isValidMobile(p)) {

                                    if (pp.length() > 0) {

                                        if (pp.equals(ccp)) {

                                           // bar.setVisibility(View.VISIBLE);

                                            Bean b = (Bean) getApplicationContext();

                                            Retrofit retrofit = new Retrofit.Builder()
                                                    .baseUrl(b.BaseUrl)
                                                    .addConverterFactory(ScalarsConverterFactory.create())
                                                    .addConverterFactory(GsonConverterFactory.create())
                                                    .build();

                                            AllApiInterface cr = retrofit.create(AllApiInterface.class);

                                            Call<SignupBean> call = cr.signup(n, e, p, mGender, d, pp);
                                            call.enqueue(new Callback<SignupBean>() {
                                                @Override
                                                public void onResponse(Call<SignupBean> call, Response<SignupBean> response) {


                                                    if (Objects.equals(response.body().getStatus(), "1")) {

                                                        Intent i = new Intent(Signup.this, Login.class);
                                                        startActivity(i);

                                                    } else {

                                                        Toast.makeText(Signup.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                                    }

                                                    bar.setVisibility(View.GONE);
                                                }

                                                @Override
                                                public void onFailure(Call<SignupBean> call, Throwable t) {

                                                    bar.setVisibility(View.GONE);

                                                }
                                            });


                                        } else {

                                            Toast.makeText(Signup.this, "Password didn't match", Toast.LENGTH_SHORT).show();
                                        }

                                    } else {

                                        Toast.makeText(Signup.this, "Please enter a Password", Toast.LENGTH_SHORT).show();
                                    }

                                } else {

                                    Toast.makeText(Signup.this, "Please enter a Email Address", Toast.LENGTH_SHORT).show();

                                }

                            } else {

                                Toast.makeText(Signup.this, "Please enter a Phone Number", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Toast.makeText(Signup.this, "Plesae select a DOB", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(Signup.this, "Please enter a UserName", Toast.LENGTH_SHORT).show();
                    }


                } else {
                    Toast.makeText(Signup.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
                }


            }
        });


    }
}
