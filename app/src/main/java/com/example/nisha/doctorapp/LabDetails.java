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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.nisha.doctorapp.DoctorsPOJO.DoctorBean;
import com.example.nisha.doctorapp.LabDetailsPOJO.LabDetailBean;
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

    String imag , nam , timin , fe , add;

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


        name.setText(nam);
        timing.setText(timin);
        free.setText(fe);
        address.setText(add);
        //circleImageView.setImageBitmap(imag);

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(LabDetails.this);
                dialog.setContentView(R.layout.dialog);
                dialog.setCancelable(true);
                dialog.show();

                final TextView time = dialog.findViewById(R.id.time);
                final TextView date = dialog.findViewById(R.id.date);
                Spinner spinner = dialog.findViewById(R.id.spinner);
                ImageView close = dialog.findViewById(R.id.close);
                Button ok = dialog.findViewById(R.id.ok);


                List<String> li = new ArrayList<>();
                li = new ArrayList<>();

                li.add("Diabetes");
                li.add("Blood Test");
                li.add("uria");

                ArrayAdapter dataAdapter = new ArrayAdapter(LabDetails.this, android.R.layout.simple_spinner_item, li);

                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spinner.setAdapter(dataAdapter);
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dialog.dismiss();

                    }
                });
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dialog.dismiss();

                    }
                });

                time.setOnClickListener(new View.OnClickListener() {
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
                });

                date.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        final Dialog dialog = new Dialog(LabDetails.this);
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

        Bean b = (Bean)getApplicationContext();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.BaseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllApiInterface cr = retrofit.create(AllApiInterface.class);

        Call<LabDetailBean> call = cr.labdetail();

        call.enqueue(new Callback<LabDetailBean>() {
            @Override
            public void onResponse(Call<LabDetailBean> call, Response<LabDetailBean> response) {

                if (Objects.equals(response.body().getStatus() , "1")){


                    name.setText(response.body().getData().get(0).getName());

                    timing.setText(response.body().getData().get(0).getStartTime() + "-" + response.body().getData().get(0).getEndTime());

                    free.setText(response.body().getData().get(0).getFee());

                    DisplayImageOptions options = new DisplayImageOptions.Builder().cacheOnDisk(true).cacheInMemory(true).resetViewBeforeLoading(false).build();
                    ImageLoader loader = ImageLoader.getInstance();

                    loader.displayImage(response.body().getData().get(0).getImage() , circleImageView , options);

                    address.setText(response.body().getData().get(0).getAddress() + "," + response.body().getData().get(0).getCity() + "," + response.body().getData().get(0).getState());



                }else {

                    Toast.makeText(LabDetails.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }

                bar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<LabDetailBean> call, Throwable t) {

                bar.setVisibility(View.GONE);

            }
        });

*/
    }
}
