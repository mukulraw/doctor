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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class LabDetails extends AppCompatActivity {


    Toolbar toolbar;

    Button book;

    TextView name, address, special, timing, free;

    CircleImageView circleImageView;

    private Calendar calendar;
    private String format = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_details);

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
    }
}
