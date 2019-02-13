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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.nisha.doctorapp.DoctorInsertPOJO.DoctorInsertBean;
import com.example.nisha.doctorapp.DoctorsPOJO.DoctorBean;
import com.example.nisha.doctorapp.FreeSlotLabsPOJO.FreeLAbsBean;
import com.example.nisha.doctorapp.FreeSlotPOJO.FreeslotBean;
import com.example.nisha.doctorapp.LabDetailsPOJO.LabDetailBean;
import com.example.nisha.doctorapp.ScheduleLabPOJO.SchedulelabBean;
import com.example.nisha.doctorapp.TestPOJO.TestBean;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class LabDetails extends AppCompatActivity {

    Toolbar toolbar;

    Button book;

    TextView name, address, special, timing, free;

    CircleImageView circleImageView;

    ProgressBar bar;

    String imag, nam, timin, fe, add, sp , id;

    String lab = "";

    String test = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_details);

        bar = findViewById(R.id.progress);

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

        circleImageView = findViewById(R.id.circle);

        address = findViewById(R.id.address);

        special = findViewById(R.id.special);

        timing = findViewById(R.id.timing);

        free = findViewById(R.id.free);
        name = findViewById(R.id.name);

        nam = getIntent().getStringExtra("name");
        timin = getIntent().getStringExtra("timing");
        fe = getIntent().getStringExtra("fee");
        add = getIntent().getStringExtra("address");
        imag = getIntent().getStringExtra("image");
        sp = getIntent().getStringExtra("spec");
        id = getIntent().getStringExtra("id");

        name.setText(nam);
        timing.setText(timin);
        free.setText(fe);
        address.setText(add);
        special.setText(sp);

        DisplayImageOptions options = new DisplayImageOptions.Builder().cacheOnDisk(true).cacheInMemory(true).resetViewBeforeLoading(false).build();
        ImageLoader loader = ImageLoader.getInstance();
        loader.displayImage(imag, circleImageView, options);


        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(LabDetails.this);
                dialog.setContentView(R.layout.dialog);
                dialog.setCancelable(true);
                dialog.show();


                final TextView date = dialog.findViewById(R.id.date);
                final Spinner spinner = dialog.findViewById(R.id.spinner);
                final Spinner spinner1 = dialog.findViewById(R.id.spinner1);
                ImageView close = dialog.findViewById(R.id.close);
                Button ok = dialog.findViewById(R.id.ok);
                final ProgressBar progress = dialog.findViewById(R.id.progress);


                //progress.setVisibility(View.VISIBLE);

                final List<String>ss = new ArrayList<>();
                final List<String>lb = new ArrayList<>();

                Bean b = (Bean) getApplicationContext();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(b.BaseUrl)
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                AllApiInterface cr = retrofit.create(AllApiInterface.class);

                Call<TestBean> call = cr.test();
                call.enqueue(new Callback<TestBean>() {
                    @Override
                    public void onResponse(Call<TestBean> call, Response<TestBean> response) {


                        if (Objects.equals(response.body().getStatus() , "1")){

                            for (int i = 0; i < response.body().getData().size(); i++) {


                                ss.add(response.body().getData().get(i).getTestName());
                                lb.add(response.body().getData().get(i).getId());
                            }



                            ArrayAdapter dataAdapter1 = new ArrayAdapter(LabDetails.this, android.R.layout.simple_spinner_item, ss);

                            dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                            spinner1.setAdapter(dataAdapter1);

                         //   Toast.makeText(LabDetails.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                        }else {

                            Toast.makeText(LabDetails.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<TestBean> call, Throwable t) {

                    }
                });


                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                        test = lb.get(position);

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {


                    }
                });


                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        progress.setVisibility(View.VISIBLE);

                        Bean b = (Bean) getApplicationContext();

                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(b.BaseUrl)
                                .addConverterFactory(ScalarsConverterFactory.create())
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

                        AllApiInterface cr = retrofit.create(AllApiInterface.class);

                        Call<SchedulelabBean> call = cr.schedule( id , b.userid , date.getText().toString() , lab);
                        call.enqueue(new Callback<SchedulelabBean>() {
                            @Override
                            public void onResponse(Call<SchedulelabBean> call, Response<SchedulelabBean> response) {


                                if (Objects.equals(response.body().getStatus() , "1")){

                                    Toast.makeText(LabDetails.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();


                                }else {

                                    Toast.makeText(LabDetails.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                }

                               progress .setVisibility(View.GONE);
                                dialog.dismiss();
                            }

                            @Override
                            public void onFailure(Call<SchedulelabBean> call, Throwable t) {

                                progress.setVisibility(View.GONE);

                            }
                        });


                    }
                });

               /* time.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        final Dialog dialog = new Dialog(LabDetails.this);
                        dialog.setContentView(R.layout.timepickerdialog);
                        dialog.setCancelable(true);
                        dialog.show();

                        final TimePicker timePicker = dialog.findViewById(R.id.timepicker);
                        Button ok = dialog.findViewById(R.id.ok);

                        ok.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {


                                Calendar mcurrentTime = Calendar.getInstance();
                                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                                int minute = mcurrentTime.get(Calendar.MINUTE);

                                time.setText("" + hour + " : " + minute);
                                dialog.dismiss();


                            }
                        });
                    }
                });*/

                date.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        final Dialog dialog = new Dialog(LabDetails.this);
                        dialog.setContentView(R.layout.datepickerdialog);
                        dialog.setCancelable(true);
                        dialog.show();

                        final DatePicker picker = dialog.findViewById(R.id.date);
                        picker.setMinDate(System.currentTimeMillis());
                        Button ok = dialog.findViewById(R.id.ok);

                        final ProgressBar progres = dialog.findViewById(R.id.progress);

                        final List<String> lii = new ArrayList<>();
                        final List<String> list = new ArrayList<>();

                        ok.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {


                                String day = String.valueOf(picker.getDayOfMonth());
                                String month = String.valueOf(picker.getMonth() + 1);
                                String year = String.valueOf(picker.getYear());

                                String f = year + "-" + month + "-" + day;

                                date.setText(f);
                                dialog.dismiss();

                                progres.setVisibility(View.VISIBLE);

                                Bean b = (Bean) getApplicationContext();

                                Retrofit retrofit = new Retrofit.Builder()
                                        .baseUrl(b.BaseUrl)
                                        .addConverterFactory(ScalarsConverterFactory.create())
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .build();

                                AllApiInterface cr = retrofit.create(AllApiInterface.class);

                                Call<FreeLAbsBean> call = cr.freelab(id, date.getText().toString());

                                call.enqueue(new Callback<FreeLAbsBean>() {
                                    @Override
                                    public void onResponse(Call<FreeLAbsBean> call, Response<FreeLAbsBean> response) {


                                        if (response.body().getStatus().equals("1")) {


                                            for (int i = 0; i < response.body().getData().size(); i++) {


                                                lii.add(response.body().getData().get(i).getTimeSlot());
                                                list.add(response.body().getData().get(i).getId());
                                            }



                                            ArrayAdapter dataAdapter = new ArrayAdapter(LabDetails.this, android.R.layout.simple_spinner_item, lii);

                                            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                                            spinner.setAdapter(dataAdapter);


                                        }

                                        progres.setVisibility(View.GONE);

                                        dialog.dismiss();
                                    }

                                    @Override
                                    public void onFailure(Call<FreeLAbsBean> call, Throwable t) {

                                        progres.setVisibility(View.GONE);

                                    }
                                });




                                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                                        lab = list.get(position);

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
