package com.example.fingerprint;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

public class MyViewHolder extends RecyclerView.ViewHolder {

    TextView item_title, item_url,item_date,item_ip,item_status;
    ToggleButton item_btn;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);


        item_title = itemView.findViewById(R.id.item_title);
        item_url = itemView.findViewById(R.id.item_url);
        item_date = itemView.findViewById(R.id.item_date);
        item_ip = itemView.findViewById(R.id.item_ip);
        item_status = itemView.findViewById(R.id.item_status);
        item_btn = itemView.findViewById(R.id.item_btn);

    }


}
