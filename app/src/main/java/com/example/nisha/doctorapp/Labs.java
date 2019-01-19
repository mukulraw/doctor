package com.example.nisha.doctorapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Labs extends Fragment {

    RecyclerView grid;

    GridLayoutManager manager;

    LabAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.doctors , container , false);


        grid = view.findViewById(R.id.grid);
        manager = new GridLayoutManager(getContext() , 1);
        adapter = new LabAdapter(getContext());
        grid.setAdapter(adapter);
        grid.setLayoutManager(manager);
        return view;
    }


    public class LabAdapter extends RecyclerView.Adapter<LabAdapter.Myviewholder>{

        Context context;

        public LabAdapter(Context context){
            this.context = context;
        }


        @NonNull
        @Override
        public LabAdapter.Myviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            View view = LayoutInflater.from(context).inflate(R.layout.grid_list_model , viewGroup , false);
            return new Myviewholder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull LabAdapter.Myviewholder myviewholder, int i) {

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
