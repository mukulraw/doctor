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

public class Notification extends AppCompatActivity {


    RecyclerView grid;

    GridLayoutManager manager;

    NotiAdapter adapter;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);



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

        adapter = new NotiAdapter(this);

        grid.setLayoutManager(manager);

        grid.setAdapter(adapter);

    }

    public class NotiAdapter extends RecyclerView.Adapter<NotiAdapter.Myviewholder>{

        Context context;

        public NotiAdapter(Context context){
            this.context = context;
        }


        @NonNull
        @Override
        public NotiAdapter.Myviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


            View view = LayoutInflater.from(context).inflate(R.layout.noti_list_model , viewGroup , false);
            return new Myviewholder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull NotiAdapter.Myviewholder myviewholder, int i) {

        }

        @Override
        public int getItemCount() {
            return 10;
        }

        public class Myviewholder extends RecyclerView.ViewHolder{

            public Myviewholder(@NonNull View itemView) {
                super(itemView);
            }
        }
    }
}
