package com.example.jsouptest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.MyViewHolder> {
    private List<String> period = new ArrayList<>();
    private List<String>  tDrawnList = new ArrayList<>();
    private List<String>  lastDrawnList = new ArrayList<>();

    public DataAdapter(List<String> period, List<String> tDrawnList) {
        this.period = period;
        this.tDrawnList = tDrawnList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_powerball_stat, parent, false);
        return new MyViewHolder(itemView);
    }

    @NonNull

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.number.setText(period.get(position));
        holder.tDrawnList.setText(tDrawnList.get(position));

    }


    @Override
    public int getItemCount() {
        return period.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView number;
        TextView tDrawnList;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            number = itemView.findViewById(R.id.textView);
            tDrawnList = itemView.findViewById(R.id.textView2);
        }
    }
}
