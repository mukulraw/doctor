package com.example.nisha.doctorapp;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nisha.doctorapp.DoctorsPOJO.DoctorBean;
import com.example.nisha.doctorapp.EditProfilePOJO.EditProfileBean;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class DoctorProfile extends AppCompatActivity {

    Toolbar toolbar;

    Button book;

    CircleImageView circleImageView;

    TextView name, yr, genral, timing, session, address, special, hospital;

    RatingBar ratingBar;

    ProgressBar bar;

    ConnectionDetector cd;

    String nam, des, exp, sess, add, hospi, timin , imag , rat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_profile);

        cd = new ConnectionDetector(DoctorProfile.this);

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

        book = findViewById(R.id.book);

        bar = findViewById(R.id.progress);

        name = findViewById(R.id.name);

        genral = findViewById(R.id.genral);

        yr = findViewById(R.id.year);

        ratingBar = findViewById(R.id.ratingBar);

        timing = findViewById(R.id.timing);

        session = findViewById(R.id.session);

        circleImageView = findViewById(R.id.circle);

        special = findViewById(R.id.special);

        address = findViewById(R.id.address);
        hospital = findViewById(R.id.hospital);


        nam = getIntent().getStringExtra("name");
        exp =  getIntent().getStringExtra("exp");
        des = getIntent().getStringExtra("desi");
        timin = getIntent().getStringExtra("timing");
        sess = getIntent().getStringExtra("fee");
        add = getIntent().getStringExtra("address");
        hospi = getIntent().getStringExtra("hopital");
        imag = getIntent().getStringExtra("image");
        rat = getIntent().getStringExtra("rating");
        imag = getIntent().getStringExtra("image");


        name.setText(nam);
        yr.setText(exp);
        genral.setText(des);
        timing.setText(timin);
        session.setText(sess);
        address.setText(add);
        hospital.setText(hospi);
        ratingBar.setRating(Float.parseFloat(rat));
     //   circleImageView.setImageBitmap(imag);



       /* DisplayImageOptions options = new DisplayImageOptions.Builder().cacheOnDisk(true).cacheInMemory(true).resetViewBeforeLoading(false).build();
        ImageLoader loader = ImageLoader.getInstance();
        loader.displayImage(imag.g, imag, options);
*/


        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(DoctorProfile.this);
                dialog.setContentView(R.layout.dialogdialog);
                dialog.setCancelable(true);
                dialog.show();


                final TextView date = dialog.findViewById(R.id.date);

                ImageView close = dialog.findViewById(R.id.close);

                Spinner spinner = dialog.findViewById(R.id.spinner);

                List<String> li = new ArrayList<>();
                li = new ArrayList<>();

                li.add("08:30m - 09:00pm");
                li.add("09:30m - 09:00pm");
                li.add("10:30m - 09:00pm");
                li.add("08:30m - 09:00pm");

                ArrayAdapter dataAdapter = new ArrayAdapter(DoctorProfile.this, android.R.layout.simple_spinner_item, li);

                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spinner.setAdapter(dataAdapter);

                Button ok = dialog.findViewById(R.id.ok);


                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dialog.dismiss();
                    }
                });
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dialog.dismiss();
                    }
                });

                date.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        final Dialog dialog = new Dialog(DoctorProfile.this);
                        dialog.setContentView(R.layout.datepickerdialog);
                        dialog.setCancelable(true);
                        dialog.show();

                        final DatePicker picker = dialog.findViewById(R.id.date);

                        Button ok = dialog.findViewById(R.id.ok);

                        ok.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {


                                String day = String.valueOf(picker.getDayOfMonth());
                                String month = String.valueOf(picker.getMonth() + 1);
                                String year = String.valueOf(picker.getYear());

                                String f = year + "-" + month + "-" + day;

                                date.setText(f);

                                dialog.dismiss();


                            }
                        });

                    }
                });


            }
        });
       /* bar.setVisibility(View.VISIBLE);

        Bean b = (Bean) getApplicationContext();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.BaseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllApiInterface cr = retrofit.create(AllApiInterface.class);

        Call<DoctorBean> call = cr.doctorprofile();

        call.enqueue(new Callback<DoctorBean>() {
            @Override
            public void onResponse(Call<DoctorBean> call, Response<DoctorBean> response) {

                if (Objects.equals(response.body().getStatus(), "1")) {


                    name.setText(response.body().getData().get(0).getName());
                    yr.setText(response.body().getData().get(0).getExperience());
                    genral.setText(response.body().getData().get(0).getDesignation());
                    timing.setText(response.body().getData().get(0).getStartTime() + "-" + response.body().getData().get(0).getEndTime());
                    session.setText(response.body().getData().get(0).getFee() + " / Session");

                    DisplayImageOptions options = new DisplayImageOptions.Builder().cacheOnDisk(true).cacheInMemory(true).resetViewBeforeLoading(false).build();
                    ImageLoader loader = ImageLoader.getInstance();

                    loader.displayImage(response.body().getData().get(0).getImage(), circleImageView, options);

                    address.setText(response.body().getData().get(0).getAddress() + response.body().getData().get(0).getCity() + response.body().getData().get(0).getState());

                    ratingBar.setRating(Float.parseFloat(response.body().getData().get(0).getRating()));

                    hospital.setText(response.body().getData().get(0).getHospital());


                } else {

                    Toast.makeText(DoctorProfile.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }

                bar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<DoctorBean> call, Throwable t) {

                bar.setVisibility(View.GONE);

            }
        });

*/
    }
}
