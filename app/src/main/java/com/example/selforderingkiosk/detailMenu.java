package com.example.selforderingkiosk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import apiclient.APIClient;
import apiclient.Records;
import apiclient.RecordsInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class detailMenu extends AppCompatActivity {
    Button btnDetailCancelOrder, btnAddOrder;
    ImageView imgDetailItem2;
    TextView txtDetailNameItem2;
    TextView txtDetailPriceItem2;
    EditText edtQuantity;
    RecordsInterface recordsInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_menu);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_detail_menu);

        btnDetailCancelOrder = findViewById(R.id.btn_detail_cancel_order);
        btnDetailCancelOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(detailMenu.this, Menu.class);
                startActivity(intent);
            }
        });

        btnAddOrder = findViewById(R.id.btn_add_order);
        btnAddOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addOrder();
                Intent intent = new Intent(detailMenu.this, Menu.class);
                startActivity(intent);
            }
        });

        imgDetailItem2 = findViewById(R.id.img_detail_item);
        txtDetailNameItem2 = findViewById(R.id.txt_detail_name_item);
        txtDetailPriceItem2 = findViewById(R.id.txt_detail_price_item);
        edtQuantity = findViewById(R.id.edt_quantity);
        recordsInterface = APIClient.getClient().create(RecordsInterface.class);

        Intent intent = getIntent();

        int gambarMakanan = intent.getIntExtra("GAMBAR_DEFAULT", 0);
        String namaMakanan = intent.getStringExtra("NAMA_DEFAULT");
        String hargaMakanan = intent.getStringExtra("HARGA_DEFAULT");

        imgDetailItem2.setImageResource(gambarMakanan);
        txtDetailNameItem2.setText(namaMakanan);
        txtDetailPriceItem2.setText(rp(Integer.parseInt(hargaMakanan)));
    }

    public void addOrder(){
        String name = txtDetailNameItem2.getText().toString();
        int price = Integer.parseInt(txtDetailPriceItem2.getText().toString().replaceAll("[^0-9]", ""));
        int quantity = Integer.parseInt(edtQuantity.getText().toString());
        Call<Records> addOrder = recordsInterface.postRecords(name, price, quantity);

        addOrder.enqueue(new Callback<Records>() {
            @Override
            public void onResponse(Call<Records> call, Response<Records> response) {
                Log.d("post_records: ", response.toString());
            }

            @Override
            public void onFailure(Call<Records> call, Throwable t) {
                Log.d("error_curhat: ", t.getMessage());
            }
        });
    }

    public String rp(int txt){
        Locale locale = new Locale("in", "ID");
        NumberFormat format = NumberFormat.getCurrencyInstance(locale);
        format.setMaximumFractionDigits(0);
        return format.format(txt); // Integer.toString(total);
    }
}