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
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class DoctorProfile extends AppCompatActivity {

    Toolbar toolbar;

    Button book;

    CircleImageView circleImageView;

    TextView name , yr , genral , timing , session , address , special;

    RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_profile);

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

                List<String>li = new ArrayList<>();
                li = new ArrayList<>();

                li.add("08:30m - 09:00pm");
                li.add("09:301m - 09:00pm");
                li.add("10:30m - 09:00pm");
                li.add("08:301m - 09:00pm");

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


        name = findViewById(R.id.name);

        genral = findViewById(R.id.genral);

        yr = findViewById(R.id.year);

        ratingBar = findViewById(R.id.ratingBar);

        timing = findViewById(R.id.timing);

        session = findViewById(R.id.session);

        circleImageView = findViewById(R.id.circle);

        special = findViewById(R.id.special);

        address = findViewById(R.id.address);


















    }
}
