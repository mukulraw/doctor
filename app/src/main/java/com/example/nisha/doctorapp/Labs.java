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
import android.widget.TextView;
import android.widget.Toast;

import com.example.nisha.doctorapp.DoctorsPOJO.DoctorBean;
import com.example.nisha.doctorapp.LabDetailsPOJO.Datum;
import com.example.nisha.doctorapp.LabDetailsPOJO.LabDetailBean;
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

public class Labs extends Fragment {

    RecyclerView grid;

    GridLayoutManager manager;

    LabAdapter adapter;

    ProgressBar bar;

    List<Datum> list;

    ConnectionDetector cd;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.labs, container, false);

        cd = new ConnectionDetector(getContext());

        list = new ArrayList<>();

        grid = view.findViewById(R.id.grid);

        manager = new GridLayoutManager(getContext(), 1);

        adapter = new LabAdapter(getContext(), list);

        grid.setAdapter(adapter);

        grid.setLayoutManager(manager);

        bar = view.findViewById(R.id.progress);


        if (cd.isConnectingToInternet()) {

            bar.setVisibility(View.VISIBLE);

            Bean b = (Bean) getContext().getApplicationContext();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(b.BaseUrl)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            AllApiInterface cr = retrofit.create(AllApiInterface.class);

            Call<LabDetailBean> call = cr.labdetail();

            call.enqueue(new Callback<LabDetailBean>() {
                @Override
                public void onResponse(Call<LabDetailBean> call, Response<LabDetailBean> response) {


                    if (Objects.equals(response.body().getStatus(), "1")) {


                        adapter.setgrid(response.body().getData());


                    } else {

                        Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    bar.setVisibility(View.GONE);
                }

                @Override
                public void onFailure(Call<LabDetailBean> call, Throwable t) {


                    bar.setVisibility(View.GONE);

                }
            });

        } else {
            Toast.makeText(getContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
        }

        return view;
    }


    public class LabAdapter extends RecyclerView.Adapter<LabAdapter.Myviewholder> {

        Context context;
        List<Datum> list = new ArrayList<>();

        public LabAdapter(Context context, List<Datum> list) {

            this.context = context;
            this.list = list;
        }


        @NonNull
        @Override
        public LabAdapter.Myviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            View view = LayoutInflater.from(context).inflate(R.layout.grid_list_model, viewGroup, false);
            return new Myviewholder(view);


        }

        @Override
        public void onBindViewHolder(@NonNull LabAdapter.Myviewholder myviewholder, int i) {

            final Datum item = list.get(i);

            myviewholder.name.setText(item.getName());

            myviewholder.timing.setText("Open hour : " + item.getStartTime() + " to " + item.getEndTime());

            myviewholder.name.setText(item.getName());

            DisplayImageOptions options = new DisplayImageOptions.Builder().cacheOnDisk(true).cacheInMemory(true).resetViewBeforeLoading(false).build();

            ImageLoader loader = ImageLoader.getInstance();

            loader.displayImage(item.getImage(), myviewholder.circle, options);

            myviewholder.mor.setText(item.getCity());

            final StringBuilder spe = new StringBuilder();

            for (int j = 0; j < item.getSpeciality().size(); j++) {


                spe.append(", ").append(item.getSpeciality().get(j).getName());
            }


            myviewholder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent i = new Intent(context, LabDetails.class);
                    i.putExtra("name", item.getName());
                    i.putExtra("image", item.getImage());
                    i.putExtra("timing", item.getStartTime() + "-" + item.getEndTime());
                    i.putExtra("fee", item.getFee());
                    i.putExtra("address", item.getAddress() + "," + item.getCity() + "," + item.getState());
                    i.putExtra("spec", spe.toString());
                    i.putExtra("id", item.getId());
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


            TextView name, yrs, timing, mor;

            CircleImageView circle;


            public Myviewholder(@NonNull final View itemView) {
                super(itemView);

                name = itemView.findViewById(R.id.name);

                yrs = itemView.findViewById(R.id.years);

                timing = itemView.findViewById(R.id.timing);

                mor = itemView.findViewById(R.id.mor);

                circle = itemView.findViewById(R.id.circle);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                    }
                });
            }
        }
    }


}
