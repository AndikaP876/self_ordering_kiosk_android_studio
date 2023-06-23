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

public class BeveragesAdapter extends RecyclerView.Adapter<BeveragesAdapter.ViewHolder> {

    ViewHolder holder;

    public BeveragesAdapter(ArrayList<Beverages> listBeverages) {
        this.listBeverages = listBeverages;
    }

    private ArrayList<Beverages> listBeverages;

    @NonNull
    @Override
    public BeveragesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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
    public void onBindViewHolder(@NonNull BeveragesAdapter.ViewHolder holder, int position) {
        Beverages breakfast = listBeverages.get(position);
        holder.txtNameBeverages.setText(breakfast.getNameBeverages());
        holder.txtPriceBeverages.setText(rp(Integer.parseInt(breakfast.getPriceBeverages())));
        holder.imgBeverages.setImageResource(breakfast.getImgBeverages());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listBeverages.get(position).getNameBeverages().equals("Teh Panas")) {
                    Intent intent = new Intent(holder.itemView.getContext(), detailMenu.class);
                    intent.putExtra("GAMBAR_DEFAULT", R.drawable.teh_panas);
                    intent.putExtra("NAMA_DEFAULT", "Teh Panas");
                    intent.putExtra("HARGA_DEFAULT", "12000");
                    holder.itemView.getContext().startActivity(intent);
                }
                if (listBeverages.get(position).getNameBeverages().equals("Air Mineral 600ml")) {
                    Intent intent = new Intent(holder.itemView.getContext(), detailMenu.class);
                    intent.putExtra("GAMBAR_DEFAULT", R.drawable.air_mineral_600ml);
                    intent.putExtra("NAMA_DEFAULT", "Air Mineral 600ml");
                    intent.putExtra("HARGA_DEFAULT", "11500");
                    holder.itemView.getContext().startActivity(intent);
                }
                if (listBeverages.get(position).getNameBeverages().equals("Milo")) {
                    Intent intent = new Intent(holder.itemView.getContext(), detailMenu.class);
                    intent.putExtra("GAMBAR_DEFAULT", R.drawable.milo);
                    intent.putExtra("NAMA_DEFAULT", "Milo");
                    intent.putExtra("HARGA_DEFAULT", "13000");
                    holder.itemView.getContext().startActivity(intent);
                }
                if (listBeverages.get(position).getNameBeverages().equals("Fruit Tea Lemon")) {
                    Intent intent = new Intent(holder.itemView.getContext(), detailMenu.class);
                    intent.putExtra("GAMBAR_DEFAULT", R.drawable.fruit_tea_lemon);
                    intent.putExtra("NAMA_DEFAULT", "Fruit Tea Lemon");
                    intent.putExtra("HARGA_DEFAULT", "9500");
                    holder.itemView.getContext().startActivity(intent);
                }
                if (listBeverages.get(position).getNameBeverages().equals("Fanta")) {
                    Intent intent = new Intent(holder.itemView.getContext(), detailMenu.class);
                    intent.putExtra("GAMBAR_DEFAULT", R.drawable.fanta);
                    intent.putExtra("NAMA_DEFAULT", "Fanta");
                    intent.putExtra("HARGA_DEFAULT", "9500");
                    holder.itemView.getContext().startActivity(intent);
                }
                if (listBeverages.get(position).getNameBeverages().equals("Coke McFloat")) {
                    Intent intent = new Intent(holder.itemView.getContext(), detailMenu.class);
                    intent.putExtra("GAMBAR_DEFAULT", R.drawable.coke_mcfloat);
                    intent.putExtra("NAMA_DEFAULT", "Coke McFloat");
                    intent.putExtra("HARGA_DEFAULT", "14000");
                    holder.itemView.getContext().startActivity(intent);
                }
                if (listBeverages.get(position).getNameBeverages().equals("Coca Cola")) {
                    Intent intent = new Intent(holder.itemView.getContext(), detailMenu.class);
                    intent.putExtra("GAMBAR_DEFAULT", R.drawable.coca_cola);
                    intent.putExtra("NAMA_DEFAULT", "Coca Cola");
                    intent.putExtra("HARGA_DEFAULT", "9500");
                    holder.itemView.getContext().startActivity(intent);
                }
                if (listBeverages.get(position).getNameBeverages().equals("Iced Lychee Tea")) {
                    Intent intent = new Intent(holder.itemView.getContext(), detailMenu.class);
                    intent.putExtra("GAMBAR_DEFAULT", R.drawable.iced_lychee_tea);
                    intent.putExtra("NAMA_DEFAULT", "Iced Lychee Tea");
                    intent.putExtra("HARGA_DEFAULT", "20000");
                    holder.itemView.getContext().startActivity(intent);
                }
                if (listBeverages.get(position).getNameBeverages().equals("Coca Cola X Strawberry McFloat")) {
                    Intent intent = new Intent(holder.itemView.getContext(), detailMenu.class);
                    intent.putExtra("GAMBAR_DEFAULT", R.drawable.coca_cola_x_strawberry_mcfloat);
                    intent.putExtra("NAMA_DEFAULT", "Coca Cola X Strawberry McFloat");
                    intent.putExtra("HARGA_DEFAULT", "17000");
                    holder.itemView.getContext().startActivity(intent);
                }
                if (listBeverages.get(position).getNameBeverages().equals("Sprite X Manggo McFloat")) {
                    Intent intent = new Intent(holder.itemView.getContext(), detailMenu.class);
                    intent.putExtra("GAMBAR_DEFAULT", R.drawable.sprite_x_manggo_mcfloat);
                    intent.putExtra("NAMA_DEFAULT", "Sprite X Manggo McFloat");
                    intent.putExtra("HARGA_DEFAULT", "17000");
                    holder.itemView.getContext().startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listBeverages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtNameBeverages, txtPriceBeverages;
        public ImageView imgBeverages;
        public ConstraintLayout itemView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNameBeverages = (TextView) itemView.findViewById(R.id.txt_item_name);
            txtPriceBeverages = (TextView) itemView.findViewById(R.id.txt_item_price);
            imgBeverages = (ImageView) itemView.findViewById(R.id.img_item);
            this.itemView = (ConstraintLayout) itemView.findViewById(R.id.itemLayout);
        }
    }
}