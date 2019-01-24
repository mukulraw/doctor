package com.example.nisha.doctorapp;

import android.app.Dialog;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Toolbar  toolbar;

    DrawerLayout drawer;

    PagerAdapter adapter;

    ViewPager pager;

    TabLayout layout;

    TextView profile , booking , noti , setting;

    ImageView filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        drawer = findViewById(R.id.drawer);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.open, R.string.close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        pager = findViewById(R.id.pager);
        layout = findViewById(R.id.tablayout);

        layout.addTab(layout.newTab().setText("DOCTORS"));
        layout.addTab(layout.newTab().setText("LABS"));


        adapter = new PagerAdapter(getSupportFragmentManager() , 2);
        pager.setAdapter(adapter);
        layout.setupWithViewPager(pager);

        layout.getTabAt(0).setText("DOCTORS");
        layout.getTabAt(1).setText("LABS");


        profile = findViewById(R.id.profile);
        booking = findViewById(R.id.booking);
        filter = findViewById(R.id.filter);
        setting = findViewById(R.id.setting);
        noti = findViewById(R.id.notification);

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.filterdialog);
                dialog.setCancelable(true);
                dialog.show();

                ImageView close = dialog.findViewById(R.id.close);

                Button ok = dialog.findViewById(R.id.ok);

                Spinner spinner = dialog.findViewById(R.id.spinner);
                Spinner spinner1 = dialog.findViewById(R.id.spinner1);

                List<String> lit = new ArrayList<>();
                List<String> lit1 = new ArrayList<>();
                lit = new ArrayList<>();
                lit1 = new ArrayList<>();


                lit.add("Delhi");
                lit.add("lahore");
                lit.add("Bathinda");
                lit.add("Mansha");


                ArrayAdapter dataAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_spinner_item, lit);

                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spinner.setAdapter(dataAdapter);


                lit1.add("Hair");
                lit1.add("Skin");
                lit1.add("Ear");
                lit1.add("Teeth");

                ArrayAdapter dataAdapter1 = new ArrayAdapter(MainActivity.this, android.R.layout.simple_spinner_item, lit1);

                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spinner1.setAdapter(dataAdapter1);








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


            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent  i = new Intent(MainActivity.this , Profile.class);
                startActivity(i);
                drawer.closeDrawer(GravityCompat.START);
            }
        });

        booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent  i = new Intent(MainActivity.this , MyBooking.class);
                startActivity(i);
                drawer.closeDrawer(GravityCompat.START);
            }
        });



        noti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent  i = new Intent(MainActivity.this , Notification.class);
                startActivity(i);
                drawer.closeDrawer(GravityCompat.START);
            }
        });

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent  i = new Intent(MainActivity.this , Setting.class);
                startActivity(i);
                drawer.closeDrawer(GravityCompat.START);
            }
        });

    }


    public class PagerAdapter extends FragmentStatePagerAdapter {


        public PagerAdapter(FragmentManager fm, int list) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {

            if (i == 0) {

                return new Doctors();
            } else if (i == 1) {
                return new Labs();
            }

            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (drawer.isDrawerOpen(GravityCompat.START)) {

            drawer.closeDrawer(GravityCompat.START);

        } else {
            super.onBackPressed();
        }

    }
}
