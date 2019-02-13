package com.example.nisha.doctorapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nisha.doctorapp.DoctorsPOJO.DoctorBean;
import com.example.nisha.doctorapp.GetAppointmentPOJO.AppointmentBean;
import com.example.nisha.doctorapp.GetAppointmentPOJO.AppointmentHistory;
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

public class MyBooking extends AppCompatActivity {

    RecyclerView grid;

    GridLayoutManager manager;

    BookingAdapter adapter;

    Toolbar toolbar;

    List<AppointmentHistory> list;

    ProgressBar bar;

    ConnectionDetector cd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_booking);


        cd = new ConnectionDetector(MyBooking.this);

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


        grid = findViewById(R.id.grid);

        manager = new GridLayoutManager(getApplicationContext(), 1);

        list = new ArrayList<>();

        adapter = new BookingAdapter(this, list);

        grid.setAdapter(adapter);

        grid.setLayoutManager(manager);


        bar.setVisibility(View.VISIBLE);

        Bean b = (Bean) getApplicationContext();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.BaseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllApiInterface cr = retrofit.create(AllApiInterface.class);

        Call<AppointmentBean> call = cr.appointment(b.userid);

        call.enqueue(new Callback<AppointmentBean>() {
            @Override
            public void onResponse(Call<AppointmentBean> call, Response<AppointmentBean> response) {


                if (Objects.equals(response.body().getStatus(), "1")) {

                    adapter.setgrid(response.body().getData().getAppointmentHistory());


                } else {

                    Toast.makeText(MyBooking.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }

                bar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<AppointmentBean> call, Throwable t) {


                bar.setVisibility(View.GONE);

            }
        });


    }


    public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.Myviewholder> {

        Context context;

        List<AppointmentHistory> list = new ArrayList<>();

        public BookingAdapter(Context context, List<AppointmentHistory> list) {

            this.context = context;
            this.list = list;
        }


        @NonNull
        @Override
        public BookingAdapter.Myviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            View view = LayoutInflater.from(context).inflate(R.layout.booking_list_model, viewGroup, false);

            return new Myviewholder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull BookingAdapter.Myviewholder myviewholder, int i) {


            AppointmentHistory item = list.get(i);
            myviewholder.name.setText(item.getDoctorName());
            myviewholder.ratingBar.setRating(Float.parseFloat(item.getRating()));
            myviewholder.years.setText(item.getExperience() + " Years of Experience ");
            myviewholder.gen.setText(item.getDesignation());
            myviewholder.date.setText("Date :" + item.getDate());
            myviewholder.time.setText("Time : " + item.getTimeSlot());

            DisplayImageOptions options = new DisplayImageOptions.Builder().cacheOnDisk(true).cacheInMemory(true).resetViewBeforeLoading(false).build();
            ImageLoader loader = ImageLoader.getInstance();
            loader.displayImage(item.getImage(), myviewholder.circleImageView, options);


        }

        public void setgrid(List<AppointmentHistory> list) {

            this.list = list;
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class Myviewholder extends RecyclerView.ViewHolder {

            TextView years, gen, name, date, time;

            RatingBar ratingBar;

            CircleImageView circleImageView;

            TextView complete;


            public Myviewholder(@NonNull View itemView) {
                super(itemView);

                years = itemView.findViewById(R.id.years);

                gen = itemView.findViewById(R.id.genral);

                name = itemView.findViewById(R.id.name);

                date = itemView.findViewById(R.id.date);

                time = itemView.findViewById(R.id.time);

                circleImageView = itemView.findViewById(R.id.circle);

                complete = itemView.findViewById(R.id.complete);

                ratingBar = itemView.findViewById(R.id.ratingBar);

            }
        }
    }
}
