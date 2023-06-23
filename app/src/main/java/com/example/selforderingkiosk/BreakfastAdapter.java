package com.example.selforderingkiosk;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class BreakfastAdapter extends RecyclerView.Adapter<BreakfastAdapter.ViewHolder> {

    ViewHolder holder;

    public BreakfastAdapter(ArrayList<Breakfast> listBreakfast) {
        this.listBreakfast = listBreakfast;
    }

    private ArrayList<Breakfast> listBreakfast;

    @NonNull
    @Override
    public BreakfastAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        ViewHolder holder =  new ViewHolder(inflater.inflate(R.layout.item_menu, parent, false));

        return holder;
    }

    public String rp(int txt){
        Locale locale = new Locale("in", "ID");
        NumberFormat format = NumberFormat.getCurrencyInstance(locale);
        format.setMaximumFractionDigits(0);
        return format.format(txt); // Integer.toString(total);
    }

    @Override
    public void onBindViewHolder(@NonNull BreakfastAdapter.ViewHolder holder, int position) {
        Breakfast breakfast = listBreakfast.get(position);
        holder.txtNameBreakfast.setText(breakfast.getNameBreakfast());
        holder.txtPriceBreakfast.setText(rp(Integer.parseInt(breakfast.getPriceBreakfast())));
        holder.imgBreakfast.setImageResource(breakfast.getImgBreakfast());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listBreakfast.get(position).getNameBreakfast().equals("Egg Cheese Muffin")) {
                    Intent intent = new Intent(holder.itemView.getContext(), detailMenu.class);
                    intent.putExtra("GAMBAR_DEFAULT", R.drawable.egg_chesse_muffin);
                    intent.putExtra("NAMA_DEFAULT", "Egg Cheese Muffin");
                    intent.putExtra("HARGA_DEFAULT", "17000");
                    holder.itemView.getContext().startActivity(intent);
                }
                if (listBreakfast.get(position).getNameBreakfast().equals("Sausage Wrap")) {
                    Intent intent = new Intent(holder.itemView.getContext(), detailMenu.class);
                    intent.putExtra("GAMBAR_DEFAULT", R.drawable.sausage_wrap);
                    intent.putExtra("NAMA_DEFAULT", "Sausage Wrap");
                    intent.putExtra("HARGA_DEFAULT", "25000");
                    holder.itemView.getContext().startActivity(intent);
                }
                if (listBreakfast.get(position).getNameBreakfast().equals("Korean Soy Garlic Wings")) {
                    Intent intent = new Intent(holder.itemView.getContext(), detailMenu.class);
                    intent.putExtra("GAMBAR_DEFAULT", R.drawable.korean_soy_garlic_wings);
                    intent.putExtra("NAMA_DEFAULT", "Korean Soy Garlic Wings");
                    intent.putExtra("HARGA_DEFAULT", "34500");
                    holder.itemView.getContext().startActivity(intent);
                }
                if (listBreakfast.get(position).getNameBreakfast().equals("Big Breakfast")) {
                    Intent intent = new Intent(holder.itemView.getContext(), detailMenu.class);
                    intent.putExtra("GAMBAR_DEFAULT", R.drawable.big_breakfast);
                    intent.putExtra("NAMA_DEFAULT", "Big Breakfast");
                    intent.putExtra("HARGA_DEFAULT", "38000");
                    holder.itemView.getContext().startActivity(intent);
                }
                if (listBreakfast.get(position).getNameBreakfast().equals("Sausage McMuffin")) {
                    Intent intent = new Intent(holder.itemView.getContext(), detailMenu.class);
                    intent.putExtra("GAMBAR_DEFAULT", R.drawable.sausage_mcmuffin);
                    intent.putExtra("NAMA_DEFAULT", "Sausage McMuffin®");
                    intent.putExtra("HARGA_DEFAULT", "28000");
                    holder.itemView.getContext().startActivity(intent);
                }
                if (listBreakfast.get(position).getNameBreakfast().equals("Nasi Uduk McD with Coffee")) {
                    Intent intent = new Intent(holder.itemView.getContext(), detailMenu.class);
                    intent.putExtra("GAMBAR_DEFAULT", R.drawable.nasi_uduk_mcd_with_coffee);
                    intent.putExtra("NAMA_DEFAULT", "Nasi Uduk McD with Coffee");
                    intent.putExtra("HARGA_DEFAULT", "28000");
                    holder.itemView.getContext().startActivity(intent);
                }
                if (listBreakfast.get(position).getNameBreakfast().equals("PaNas 2 with Rice")) {
                    Intent intent = new Intent(holder.itemView.getContext(), detailMenu.class);
                    intent.putExtra("GAMBAR_DEFAULT", R.drawable.panas_2_with_rice);
                    intent.putExtra("NAMA_DEFAULT", "PaNas 2 with Rice");
                    intent.putExtra("HARGA_DEFAULT", "50000");
                    holder.itemView.getContext().startActivity(intent);
                }
                if (listBreakfast.get(position).getNameBreakfast().equals("Paket Big Order")) {
                    Intent intent = new Intent(holder.itemView.getContext(), detailMenu.class);
                    intent.putExtra("GAMBAR_DEFAULT", R.drawable.paket_big_order);
                    intent.putExtra("NAMA_DEFAULT", "Paket Big Order");
                    intent.putExtra("HARGA_DEFAULT", "562500");
                    holder.itemView.getContext().startActivity(intent);
                }
                if (listBreakfast.get(position).getNameBreakfast().equals("Hotcakes 3pcs")) {
                    Intent intent = new Intent(holder.itemView.getContext(), detailMenu.class);
                    intent.putExtra("GAMBAR_DEFAULT", R.drawable.hotcakes_3pcs);
                    intent.putExtra("NAMA_DEFAULT", "Hotcakes 3pcs");
                    intent.putExtra("HARGA_DEFAULT", "29500");
                    holder.itemView.getContext().startActivity(intent);
                }
                if (listBreakfast.get(position).getNameBreakfast().equals("Chicken Muffin with Egg")) {
                    Intent intent = new Intent(holder.itemView.getContext(), detailMenu.class);
                    intent.putExtra("GAMBAR_DEFAULT", R.drawable.chicken_muffin_with_egg);
                    intent.putExtra("NAMA_DEFAULT", "Chicken Muffin with Egg");
                    intent.putExtra("HARGA_DEFAULT", "30500");
                    holder.itemView.getContext().startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listBreakfast.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtNameBreakfast, txtPriceBreakfast;
        public ImageView imgBreakfast;
        public ConstraintLayout itemView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNameBreakfast = (TextView) itemView.findViewById(R.id.txt_item_name);
            txtPriceBreakfast = (TextView) itemView.findViewById(R.id.txt_item_price);
            imgBreakfast = (ImageView) itemView.findViewById(R.id.img_item);
            this.itemView = (ConstraintLayout) itemView.findViewById(R.id.itemLayout);
        }
    }
}