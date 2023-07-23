package com.example.covid_track_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    Context context;
    ArrayList<Model> modelArrayList;

    public Adapter(Context context, ArrayList<Model> modelArrayList) {
        this.context = context;
        this.modelArrayList = modelArrayList;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.testing,null,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {

        holder.state.setText(modelArrayList.get(position).getName());
        holder.active.setText(modelArrayList.get(position).getActive());
        holder.confirmed.setText(modelArrayList.get(position).getConfirmed());
        holder.recovered.setText(modelArrayList.get(position).getRecovered());
        holder.ddeceased.setText(modelArrayList.get(position).getDdeceased());
        holder.dconfirmed.setText(modelArrayList.get(position).getDconfirmed());
        holder.drecovered.setText(modelArrayList.get(position).getDrecovered());

    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView state,active,confirmed,deceased,recovered,dconfirmed,ddeceased,drecovered;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            state = itemView.findViewById(R.id.cityname);
            active = itemView.findViewById(R.id.active);
            confirmed = itemView.findViewById(R.id.confirm);
            deceased = itemView.findViewById(R.id.deceased);
            recovered = itemView.findViewById(R.id.recovered);
            dconfirmed = itemView.findViewById(R.id.dconfirm);
            ddeceased = itemView.findViewById(R.id.ddceased);
            drecovered = itemView.findViewById(R.id.drecovered);

        }
    }
}
