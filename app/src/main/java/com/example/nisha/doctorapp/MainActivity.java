package com.example.nisha.doctorapp;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
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
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.nisha.doctorapp.LocationPOJO.Datum;
import com.example.nisha.doctorapp.LocationPOJO.LocationBean;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;

    DrawerLayout drawer;

    PagerAdapter adapter;

    ViewPager pager;

    TabLayout layout;

    TextView profile, booking, noti, setting;

    ImageView filter;

    TextView location;

    String lname;

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
        //layout.addTab(layout.newTab().setText("HOSPITAl"));

        adapter = new PagerAdapter(getSupportFragmentManager(), 2);
        pager.setAdapter(adapter);
        layout.setupWithViewPager(pager);

        layout.getTabAt(0).setText("DOCTORS");
        layout.getTabAt(1).setText("LABS");
        //layout.getTabAt(1).setText("HOSPITAl");

        profile = findViewById(R.id.profile);
        booking = findViewById(R.id.booking);
        filter = findViewById(R.id.filter);
        setting = findViewById(R.id.setting);
        noti = findViewById(R.id.notification);
        location = findViewById(R.id.location);

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.locationdialog);
                dialog.setCancelable(true);
                dialog.show();


                final RecyclerView grid = dialog.findViewById(R.id.recyclerView);
                ProgressBar progress = dialog.findViewById(R.id.progressBar);

                progress = findViewById(R.id.progress);

                Bean b = (Bean) getApplicationContext();

                progress.setVisibility(View.VISIBLE);

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(b.BaseUrl)
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                AllApiInterface cr = retrofit.create(AllApiInterface.class);

                Call<LocationBean> call = cr.getLocations();

                final ProgressBar finalProgress = progress;
                call.enqueue(new Callback<LocationBean>() {
                    @Override
                    public void onResponse(Call<LocationBean> call, Response<LocationBean> response) {

                        LocationAdapter adapter = new LocationAdapter(MainActivity.this, response.body().getData(), dialog);
                        GridLayoutManager manager = new GridLayoutManager(MainActivity.this, 1);
                        grid.setAdapter(adapter);
                        grid.setLayoutManager(manager);

                        finalProgress.setVisibility(View.GONE);

                    }

                    @Override
                    public void onFailure(Call<LocationBean> call, Throwable t) {
                        finalProgress.setVisibility(View.GONE);
                    }
                });

            }
        });


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


                ArrayAdapter dataAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, lit);

                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spinner.setAdapter(dataAdapter);

                lit1.add("Hair");
                lit1.add("Skin");
                lit1.add("Ear");
                lit1.add("Teeth");

                ArrayAdapter dataAdapter1 = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, lit1);

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


                Intent i = new Intent(MainActivity.this, Profile.class);
                startActivity(i);
                drawer.closeDrawer(GravityCompat.START);
            }
        });

        booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, MyBooking.class);
                startActivity(i);
                drawer.closeDrawer(GravityCompat.START);


            }
        });


        noti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(MainActivity.this, Notification.class);
                startActivity(i);
                drawer.closeDrawer(GravityCompat.START);
            }
        });

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(MainActivity.this, Setting.class);
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
    protected void onResume() {
        super.onResume();

        lname = getIntent().getStringExtra("lname");
        location.setText(lname);

    }

    class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.ViewHolder> {

        Context context;
        List<Datum> list = new ArrayList<>();
        Dialog dialog;

        public LocationAdapter(Context context, List<Datum> list, Dialog dialog) {
            this.context = context;
            this.list = list;
            this.dialog = dialog;
        }


        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.location_list_model, viewGroup, false);
            return new ViewHolder(view);

        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

            final Datum item = list.get(i);

            viewHolder.text.setText(item.getName());

            viewHolder.text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    SharePreferenceUtils.getInstance().saveString("location", item.getId());
                    location.setText(item.getName());
                    dialog.dismiss();

                }
            });


        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            TextView text;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                text = itemView.findViewById(R.id.textView2);
            }
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
