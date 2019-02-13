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
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Notification extends AppCompatActivity {


    RecyclerView grid;

    GridLayoutManager manager;

    NotiAdapter adapter;

    Toolbar toolbar;

    ProgressBar bar;

  //  List<String> list;

    ConnectionDetector cd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

      //  list = new ArrayList<>();

        cd = new ConnectionDetector(this);

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

        bar = findViewById(R.id.progress);

        manager = new GridLayoutManager(getApplicationContext(), 1);

        adapter = new NotiAdapter(this );

        grid.setLayoutManager(manager);

        grid.setAdapter(adapter);

    }

    public class NotiAdapter extends RecyclerView.Adapter<NotiAdapter.Myviewholder> {

        Context context;

        //List<String>list = new ArrayList<>();

        public NotiAdapter(Context context) {
            this.context = context;
           // this.list = list;
        }


        @NonNull
        @Override
        public NotiAdapter.Myviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


            View view = LayoutInflater.from(context).inflate(R.layout.noti_list_model, viewGroup, false);
            return new Myviewholder(view);


        }

        @Override
        public void onBindViewHolder(@NonNull NotiAdapter.Myviewholder myviewholder, int i) {


          //  String item = list.get(i);
          //  myviewholder.name.setText("");
          //  myviewholder.timing.setText("");

        }

      /*  public void setgrid(List<String>list){
            this.list = list;
            notifyDataSetChanged();
        }
*/
        @Override
        public int getItemCount() {
            return 10;
        }

        public class Myviewholder extends RecyclerView.ViewHolder {

            TextView name, timing;

            public Myviewholder(@NonNull View itemView) {
                super(itemView);

                name = itemView.findViewById(R.id.name);
                timing = itemView.findViewById(R.id.timing);
            }
        }
    }
}
