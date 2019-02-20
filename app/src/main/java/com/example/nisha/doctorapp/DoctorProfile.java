package com.example.nisha.doctorapp;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nisha.doctorapp.DoctorInsertPOJO.DoctorInsertBean;
import com.example.nisha.doctorapp.DoctorsPOJO.DoctorBean;
import com.example.nisha.doctorapp.EditProfilePOJO.EditProfileBean;
import com.example.nisha.doctorapp.FreeSlotPOJO.Datum;
import com.example.nisha.doctorapp.FreeSlotPOJO.FreeslotBean;
import com.example.nisha.doctorapp.GetFeePOJO.GetBean;
import com.example.nisha.doctorapp.LocationPOJO.LocationBean;
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

    String nam, des, exp, sess, add, hospi, timin , imag , rat , sp , id;

    String bb = "";


    String l = "";

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
        sp = getIntent().getStringExtra("spec");
        id = getIntent().getStringExtra("id");


        name.setText(nam);
        yr.setText(exp);
        genral.setText(des);
        timing.setText(timin);
        session.setText(sess);
        address.setText(add);
        hospital.setText(hospi);
        special.setText(sp);
        ratingBar.setRating(Float.parseFloat(rat));
       // circleImageView.setImageBitmap(imag);



        DisplayImageOptions options = new DisplayImageOptions.Builder().cacheOnDisk(true).cacheInMemory(true).resetViewBeforeLoading(false).build();
        ImageLoader loader = ImageLoader.getInstance();
        loader.displayImage(imag, circleImageView, options);



        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(DoctorProfile.this);
                dialog.setContentView(R.layout.dialogdialog);
                dialog.setCancelable(true);
                dialog.show();

                final TextView date = dialog.findViewById(R.id.date);

                final ImageView close = dialog.findViewById(R.id.close);

                final Spinner spinner = dialog.findViewById(R.id.spinner);

                final ProgressBar progress = dialog.findViewById(R.id.progress);

                Button ok = dialog.findViewById(R.id.ok);


                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dialog.dismiss();
                    }
                });



                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        final Dialog dialog = new Dialog(DoctorProfile.this);
                        dialog.setContentView(R.layout.getfeedialog);
                        dialog.setCancelable(true);
                        dialog.show();

                        final TextView fee = dialog.findViewById(R.id.fee);
                        final Button play = dialog.findViewById(R.id.pay);
                        final Button later = dialog.findViewById(R.id.later);
                        final ImageView close = dialog.findViewById(R.id.close);

                        final ProgressBar progress = dialog.findViewById(R.id.progress);



                        later.setOnClickListener(new View.OnClickListener() {
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


                        progress.setVisibility(View.VISIBLE);

                        Bean b = (Bean) getApplicationContext();

                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(b.BaseUrl)
                                .addConverterFactory(ScalarsConverterFactory.create())
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

                        AllApiInterface cr = retrofit.create(AllApiInterface.class);

                        Call<GetBean> call = cr.fees( id , bb);

                        call.enqueue(new Callback<GetBean>() {
                            @Override
                            public void onResponse(Call<GetBean> call, Response<GetBean> response) {

                                if (Objects.equals(response.body().getStatus() , "1")){

                                    fee.setText(response.body().getData().getEstimatedFee());

                                }else {

                                    Toast.makeText(DoctorProfile.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                }

                                progress.setVisibility(View.GONE);

                            }

                            @Override
                            public void onFailure(Call<GetBean> call, Throwable t) {


                                progress.setVisibility(View.GONE);


                            }
                        });



                        play.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                if (cd.isConnectingToInternet()){

                                    progress.setVisibility(View.VISIBLE);

                                    Bean b = (Bean) getApplicationContext();

                                    Retrofit retrofit = new Retrofit.Builder()
                                            .baseUrl(b.BaseUrl)
                                            .addConverterFactory(ScalarsConverterFactory.create())
                                            .addConverterFactory(GsonConverterFactory.create())
                                            .build();

                                    AllApiInterface cr = retrofit.create(AllApiInterface.class);

                                    Call<DoctorInsertBean> call = cr.insert( id , b.userid , date.getText().toString() , bb ,fee.getText().toString() );
                                    call.enqueue(new Callback<DoctorInsertBean>() {
                                        @Override
                                        public void onResponse(Call<DoctorInsertBean> call, Response<DoctorInsertBean> response) {


                                            if (Objects.equals(response.body().getStatus() , "1")){

                                                Toast.makeText(DoctorProfile.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                                            }else {

                                                Toast.makeText(DoctorProfile.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                            }

                                            progress.setVisibility(View.GONE);
                                            dialog.dismiss();
                                        }

                                        @Override
                                        public void onFailure(Call<DoctorInsertBean> call, Throwable t) {

                                            progress.setVisibility(View.GONE);

                                        }
                                    });
                                }else {
                                    Toast.makeText(DoctorProfile.this,"No Internet Connection" ,  Toast.LENGTH_SHORT).show();
                                }


                            }
                        });

                    }
                });




                date.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        final Dialog dialog = new Dialog(DoctorProfile.this);
                        dialog.setContentView(R.layout.datepickerdialog);
                        dialog.setCancelable(true);
                        dialog.show();

                        List<String> lis = new ArrayList<>();
                        List<String> li = new ArrayList<>();


                        li = new ArrayList<>();
                        lis = new ArrayList<>();


                        final DatePicker picker = dialog.findViewById(R.id.date);
                        picker.setMinDate(System.currentTimeMillis());


                        Button ok = dialog.findViewById(R.id.ok);
                        final ProgressBar progress = dialog.findViewById(R.id.progress);

                        final List<String> finalLi = li;
                        final List<String> finalLis = lis;

                        ok.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                if (cd.isConnectingToInternet()){

                                    String day = String.valueOf(picker.getDayOfMonth());
                                    String month = String.valueOf(picker.getMonth() + 1);
                                    String year = String.valueOf(picker.getYear());

                                    String f = year + "-" + month + "-" + day;

                                    date.setText(f);


                                    progress.setVisibility(View.VISIBLE);

                                    Bean b = (Bean) getApplicationContext();

                                    Retrofit retrofit = new Retrofit.Builder()
                                            .baseUrl(b.BaseUrl)
                                            .addConverterFactory(ScalarsConverterFactory.create())
                                            .addConverterFactory(GsonConverterFactory.create())
                                            .build();

                                    AllApiInterface cr = retrofit.create(AllApiInterface.class);

                                    Call<FreeslotBean> call = cr.free( id,date.getText().toString());

                                    call.enqueue(new Callback<FreeslotBean>() {
                                        @Override
                                        public void onResponse(Call<FreeslotBean> call, Response<FreeslotBean> response) {


                                            if (response.body().getStatus().equals("1")) {


                                                for (int i = 0; i < response.body().getData().size(); i++) {


                                                    finalLi.add(response.body().getData().get(i).getTimeSlot());
                                                    finalLis.add(response.body().getData().get(i).getId());
                                                }

                                                //list.clear();
                                                //li.clear();

                                                ArrayAdapter dataAdapter = new ArrayAdapter(DoctorProfile.this, android.R.layout.simple_spinner_item, finalLi);

                                                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                                                spinner.setAdapter(dataAdapter);


                                            }

                                            progress.setVisibility(View.GONE);

                                            dialog.dismiss();
                                        }

                                        @Override
                                        public void onFailure(Call<FreeslotBean> call, Throwable t) {

                                            progress.setVisibility(View.GONE);

                                        }
                                    });


                                }else {

                                    Toast.makeText(DoctorProfile.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
                                }




                                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                                      bb = finalLis.get(position);

                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {



                                    }
                                });




                            }
                        });

                    }
                });


            }
        });

    }
}
