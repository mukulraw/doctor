package com.example.nisha.doctorapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyBooking extends AppCompatActivity {

    RecyclerView grid;

    GridLayoutManager manager;

    BookingAdapter adapter;

    Toolbar toolbar;

   // List<String>list;

    ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_booking);

     //   list = new ArrayList<>();

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

        manager = new GridLayoutManager(getApplicationContext() , 1);

        adapter = new BookingAdapter(this );

        grid.setAdapter(adapter);

        grid.setLayoutManager(manager);



    }


    public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.Myviewholder>{

        Context context;

      //  List<String>list = new ArrayList<>();

        public BookingAdapter(Context context ){

            this.context = context;
           // this.list = list;
        }


        @NonNull
        @Override
        public BookingAdapter.Myviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            View view = LayoutInflater.from(context).inflate(R.layout.booking_list_model ,viewGroup , false);

            return new Myviewholder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull BookingAdapter.Myviewholder myviewholder, int i) {


          //  String item = list.get(i);


        }

      /*  public void setgrid(List<String>list){

            this.list = list;
            notifyDataSetChanged();
        }*/

        @Override
        public int getItemCount() {
            return 10;
        }

        public class Myviewholder extends RecyclerView.ViewHolder {

            TextView years , genral , name , date , time ;

            RatingBar ratingBar;

            CircleImageView circleImageView;

            TextView complete;


            public Myviewholder(@NonNull View itemView) {
                super(itemView);

                years = itemView.findViewById(R.id.years);

                genral = itemView.findViewById(R.id.genral);

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
