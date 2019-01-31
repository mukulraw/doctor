package com.example.nisha.doctorapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nisha.doctorapp.ProfilePOJO.ProfileBean;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Profile extends AppCompatActivity {

    Toolbar toolbar;

    ProgressBar bar;

    TextView name, age, email, mobile, dob, gender , edit;

    CircleImageView circleImageView;

    ConnectionDetector cd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        cd = new ConnectionDetector(Profile.this);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.setNavigationIcon(R.drawable.arrow);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });


        bar = findViewById(R.id.progress);

        name = findViewById(R.id.name);

        age = findViewById(R.id.age);

        mobile = findViewById(R.id.mobile);

        email = findViewById(R.id.email);

        dob = findViewById(R.id.dob);

        gender = findViewById(R.id.gender);

        circleImageView = findViewById(R.id.circle);
        edit = findViewById(R.id.edit);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Profile.this , EditProfile.class);
                startActivity(i);
            }
        });

        onResume();




    }

    @Override
    protected void onResume() {
        super.onResume();


        if (cd.isConnectingToInternet()){


            bar.setVisibility(View.VISIBLE);

            Bean b = (Bean)getApplicationContext();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(b.BaseUrl)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            AllApiInterface cr = retrofit.create(AllApiInterface.class);
            Call<ProfileBean> call = cr.profile(b.userid);
            call.enqueue(new Callback<ProfileBean>() {
                @Override
                public void onResponse(Call<ProfileBean> call, Response<ProfileBean> response) {

                    if (Objects.equals(response.body().getStatus() , "1")){

                        name.setText(response.body().getData().getName());
                        gender.setText(response.body().getData().getGender());
                        email.setText(response.body().getData().getEmail());
                        dob.setText(response.body().getData().getDob());
                        mobile.setText(response.body().getData().getPhone());


                        DisplayImageOptions options = new DisplayImageOptions.Builder().cacheOnDisk(true).cacheInMemory(true).resetViewBeforeLoading(false).build();
                        ImageLoader loader = ImageLoader.getInstance();

                        loader.displayImage(response.body().getData().getImage() , circleImageView , options);



                    }else {

                        Toast.makeText(Profile.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }


                    bar.setVisibility(View.GONE);
                }

                @Override
                public void onFailure(Call<ProfileBean> call, Throwable t) {


                    bar.setVisibility(View.GONE);
                }
            });


        }else {
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }


    }
}
