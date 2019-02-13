package com.example.nisha.doctorapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nisha.doctorapp.DoctorsPOJO.Datum;
import com.example.nisha.doctorapp.DoctorsPOJO.DoctorBean;
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

public class Doctors extends Fragment {

    RecyclerView grid;

    GridLayoutManager manager;

    GridAdapter adapter;

    List<Datum> list;

    ProgressBar bar;

    String lname;

    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.doctors, container, false);

        list = new ArrayList<>();

        // lname = getArguments().getString("location");

        grid = view.findViewById(R.id.grid);

        manager = new GridLayoutManager(getContext(), 1);

        adapter = new GridAdapter(getContext(), list);

        grid.setAdapter(adapter);

        grid.setLayoutManager(manager);

        bar = view.findViewById(R.id.progress);

        bar.setVisibility(View.VISIBLE);

        Bean b = (Bean) getContext().getApplicationContext();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(b.BaseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllApiInterface cr = retrofit.create(AllApiInterface.class);

        Call<DoctorBean> call = cr.doctorprofile();

        call.enqueue(new Callback<DoctorBean>() {
            @Override
            public void onResponse(Call<DoctorBean> call, Response<DoctorBean> response) {


                if (Objects.equals(response.body().getStatus(), "1")) {

                    adapter.setgrid(response.body().getData());

                    Log.d("respponse", "res");

                } else {

                    Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }

                bar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<DoctorBean> call, Throwable t) {

                Log.d("failure", t.toString());

                bar.setVisibility(View.GONE);

            }
        });


        return view;


    }

    public class GridAdapter extends RecyclerView.Adapter<GridAdapter.Myviewholder> {

        Context context;
        List<Datum> list = new ArrayList<>();

        public GridAdapter(Context context, List<Datum> list) {

            this.context = context;
            this.list = list;
        }


        @NonNull
        @Override
        public GridAdapter.Myviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            View view = LayoutInflater.from(context).inflate(R.layout.dr_list_model, viewGroup, false);
            return new Myviewholder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull GridAdapter.Myviewholder myviewholder, int i) {

            final Datum item = list.get(i);

            myviewholder.name.setText(item.getName());
            myviewholder.gernal.setText(item.getDesignation());
            myviewholder.years.setText(item.getExperience() + " Years of Experience ");
            myviewholder.ratingBar.setRating(Float.parseFloat(item.getRating()));

            DisplayImageOptions options = new DisplayImageOptions.Builder().cacheOnDisk(true).cacheInMemory(true).resetViewBeforeLoading(false).build();
            ImageLoader loader = ImageLoader.getInstance();
            loader.displayImage(item.getImage(), myviewholder.circleImageView, options);

            myviewholder.mor.setText(item.getCity());

            final StringBuilder spe = new StringBuilder();

            for (int j = 0; j < item.getSpeciality().size(); j++) {


                spe.append(", ").append(item.getSpeciality().get(j).getName());
            }


            myviewholder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent i = new Intent(context, DoctorProfile.class);
                    i.putExtra("name", item.getName());
                    i.putExtra("desi", item.getDesignation());
                    i.putExtra("exp", item.getExperience() + " years of Experience ");
                    i.putExtra("rating", item.getRating());
                    i.putExtra("image", item.getImage());
                    i.putExtra("timing", item.getStartTime() + "-" + item.getEndTime());
                    i.putExtra("fee", item.getFee() + " / Seesion ");
                    i.putExtra("hopital", item.getHospital());
                    i.putExtra("address", item.getAddress() + "," + item.getCity() + "," + item.getState());
                    i.putExtra("spec", spe.toString());
                    i.putExtra("id" , item.getId());

                    context.startActivity(i);


                }
            });


        }

        public void setgrid(List<Datum> list) {

            this.list = list;
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class Myviewholder extends RecyclerView.ViewHolder {

            CircleImageView circleImageView;

            TextView name, eng, gernal, years, mor;

            RatingBar ratingBar;

            public Myviewholder(@NonNull View itemView) {
                super(itemView);

                name = itemView.findViewById(R.id.name);

                gernal = itemView.findViewById(R.id.genral);

                years = itemView.findViewById(R.id.years);

                eng = itemView.findViewById(R.id.eng);

                ratingBar = itemView.findViewById(R.id.ratingBar);

                mor = itemView.findViewById(R.id.mor);

                circleImageView = itemView.findViewById(R.id.circle);


            }
        }
    }
}
