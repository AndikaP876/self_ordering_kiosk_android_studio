package com.example.selforderingkiosk;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import apiclient.Records;

public class RecordsAdapter extends RecyclerView.Adapter<RecordsAdapter.ViewHolder> {
    ArrayList<Records> listRecords;

    public RecordsAdapter(ArrayList<Records> listRecords){
        this.listRecords = listRecords;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        ViewHolder holder = new ViewHolder(inflater.inflate(R.layout.item_record, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        Records records = listRecords.get(position);
        holder.txtName.setText(records.getName());
        holder.txtPrice.setText(rp(records.getTotalPrice()));
        holder.txtQuantity.setText(Integer.toString(records.getQuantity()));

        holder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = holder.itemLayout.getContext();
                Intent it = new Intent(context, editItemRecord.class);
                it.putExtra("current_records", records);
                context.startActivity(it);
            }
        });
    }

    @Override
    public int getItemCount(){
        return listRecords.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView txtName, txtPrice, txtQuantity;
        public ConstraintLayout itemLayout;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            txtName = itemView.findViewById(R.id.txt_records);
            txtPrice = itemView.findViewById(R.id.txt_records_price);
            txtQuantity = itemView.findViewById(R.id.txt_records_quantity);
            this.itemLayout = itemView.findViewById(R.id.item_layout_records);
        }
    }

    public String rp(int txt){
        Locale locale = new Locale("in", "ID");
        NumberFormat format = NumberFormat.getCurrencyInstance(locale);
        format.setMaximumFractionDigits(0);
        return format.format(txt); // Integer.toString(total);
    }
}

