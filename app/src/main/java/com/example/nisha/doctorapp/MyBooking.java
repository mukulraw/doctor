package com.example.nisha.doctorapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MyBooking extends AppCompatActivity {

    RecyclerView grid;

    GridLayoutManager manager;

    BookingAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_booking);

        grid = findViewById(R.id.grid);
        manager = new GridLayoutManager(getApplicationContext() , 1);
        adapter = new BookingAdapter(this);
        grid.setAdapter(adapter);
        grid.setLayoutManager(manager);



    }
    public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.Myviewholder>{

        Context context;

        public BookingAdapter(Context context){

            this.context = context;
        }


        @NonNull
        @Override
        public BookingAdapter.Myviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            View view = LayoutInflater.from(context).inflate(R.layout.booking_list_model ,viewGroup , false);

            return new Myviewholder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull BookingAdapter.Myviewholder myviewholder, int i) {

        }

        @Override
        public int getItemCount() {
            return 10;
        }

        public class Myviewholder extends RecyclerView.ViewHolder {


            public Myviewholder(@NonNull View itemView) {
                super(itemView);
            }
        }
    }
}
