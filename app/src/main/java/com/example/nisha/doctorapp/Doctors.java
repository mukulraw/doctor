package com.example.nisha.doctorapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class Doctors extends Fragment {

    RecyclerView grid;

    GridLayoutManager manager;

    GridAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.doctors , container ,false);


        grid = view.findViewById(R.id.grid);
        manager = new GridLayoutManager(getContext() , 1);
        adapter = new GridAdapter(getContext());
        grid.setAdapter(adapter);
        grid.setLayoutManager(manager);
        return view;
    }

    public class GridAdapter extends RecyclerView.Adapter<GridAdapter.Myviewholder>{

        Context context;

        public GridAdapter (Context context){

            this.context = context;
        }


        @NonNull
        @Override
        public GridAdapter.Myviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            View view = LayoutInflater.from(context).inflate(R.layout.grid_list_model , viewGroup , false);
            return new Myviewholder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull GridAdapter.Myviewholder myviewholder, int i) {

        }

        @Override
        public int getItemCount() {
            return 10;
        }

        public class Myviewholder extends RecyclerView.ViewHolder {

            CircleImageView circleImageView;
            TextView name , hour , years;

            public Myviewholder(@NonNull View itemView) {
                super(itemView);

                name = itemView.findViewById(R.id.name);
                hour = itemView.findViewById(R.id.hour);
                years = itemView.findViewById(R.id.years);

                circleImageView = itemView.findViewById(R.id.circle);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        Intent i = new Intent(context , DoctorProfile.class);
                        context.startActivity(i);
                    }
                });
            }
        }
    }
}
