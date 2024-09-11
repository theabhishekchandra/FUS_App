package com.example.fingerprint;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    public List<ResponseModel> log_data;
    public interface OnItemClickListener {
        void onItemButtonClick(String status);
    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public MyAdapter(List<ResponseModel> log_data) {
        this.log_data = log_data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ResponseModel currentItem = log_data.get(position);
        holder.itemTitle.setText(currentItem.getTitle());
        holder.itemUrl.setText(currentItem.getUrl());
        holder.itemDate.setText(currentItem.getDate());
        holder.itemIp.setText(currentItem.getIp());
        holder.itemStatus.setText(currentItem.getStatus());

        holder.item_btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    holder.itemStatus.setText("Activated");
                    if (listener != null) {
                        listener.onItemButtonClick("Activated");
                    }
                } else {
                    holder.itemStatus.setText("Deactivated");
                    if (listener != null) {
                        listener.onItemButtonClick("Deactivated");
                    }
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return log_data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView itemTitle, itemUrl, itemDate, itemIp, itemStatus;
        ToggleButton item_btn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemTitle = itemView.findViewById(R.id.item_title);
            itemUrl = itemView.findViewById(R.id.item_url);
            itemDate = itemView.findViewById(R.id.item_date);
            itemIp = itemView.findViewById(R.id.item_ip);
            itemStatus = itemView.findViewById(R.id.item_status);
            item_btn = itemView.findViewById(R.id.item_btn);

        }
    }
}
