package com.example.selforderingkiosk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import apiclient.APIClient;
import apiclient.Records;
import apiclient.RecordsInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Menu extends AppCompatActivity {

    Button btnBack;
    Button btnCancelOrder;
    TextView txtTotal;
    private RecyclerView recBreakfast;
    private RecyclerView recBeverages;
    private ArrayList<Breakfast> listBreakfast;
    private ArrayList<Beverages> listBeverages;
    RecordsInterface recordsInterface;
    RecyclerView recRecords;
    RecordsAdapter adapter;
    Button btnDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_menu);

        btnBack = findViewById(R.id.btn_back);
        btnCancelOrder = findViewById(R.id.btn_cancel_order);
        btnDone = findViewById(R.id.btn_done);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, DiningLocation.class);
                startActivity(intent);
            }
        });
        btnCancelOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAllDataFromServer();
                Intent intent = new Intent(Menu.this, SplashScreen.class);
                startActivity(intent);
            }
        });
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, orderCorrect.class);
                startActivity(intent);
            }
        });

        txtTotal = findViewById(R.id.txt_total);
        recBreakfast = findViewById(R.id.rec_breakfast);
        recBeverages = findViewById(R.id.rec_beverages);
        recRecords = findViewById(R.id.rec_records);
        initDataBreakfast();
        initDataBeverages();

        recBreakfast.setAdapter(new BreakfastAdapter(listBreakfast));
        recBreakfast.setLayoutManager(new LinearLayoutManager(this));
        recBeverages.setAdapter(new BeveragesAdapter(listBeverages));
        recBeverages.setLayoutManager(new LinearLayoutManager(this));
        recRecords.setLayoutManager(new LinearLayoutManager(this));

        recordsInterface = APIClient.getClient().create(RecordsInterface.class);
        getAllRecords();
    }

    private void getAllRecords(){
        Call<List<Records>> getRecords = recordsInterface.getRecords();
        getRecords.enqueue(new Callback<List<Records>>() {
            @Override
            public void onResponse(Call<List<Records>> call, Response<List<Records>> response) {
                ArrayList<Records> listRecords = (ArrayList<Records>) response.body();
                Log.d("list_records: ", response.raw().toString());
                Log.d("list_records: ", listRecords.toString());

                adapter = new RecordsAdapter(listRecords);
                recRecords.setAdapter(adapter);

                if (response.isSuccessful()) {
                    List<Records> records = response.body();
                    if (records != null && !records.isEmpty()) {
                        // Lakukan perhitungan total_price di sini
                        int totalPrice = 0;
                        for (Records record : records) {
                            int price = record.getPrice();
                            int quantity = record.getQuantity();
                            totalPrice += price * quantity;
                        }
                        // Tampilkan total_price di TextView
                        txtTotal.setText(rp(totalPrice));
                    }
                } else {
                    // Tangani respons gagal
                }
            }

            @Override
            public void onFailure(Call<List<Records>> call, Throwable t) {
                Log.e("error_curhat: ", t.getMessage());
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
        getAllRecords();
    }

    private void initDataBreakfast(){
        this.listBreakfast =  new ArrayList<>();
        listBreakfast.add(new Breakfast("Nasi Uduk McD with Coffee", "28000", R.drawable.nasi_uduk_mcd_with_coffee));
        listBreakfast.add(new Breakfast("Korean Soy Garlic Wings", "34500", R.drawable.korean_soy_garlic_wings));
        listBreakfast.add(new Breakfast("Egg Cheese Muffin", "17000", R.drawable.egg_chesse_muffin));
        listBreakfast.add(new Breakfast("Sausage Wrap", "25000", R.drawable.sausage_wrap));
        listBreakfast.add(new Breakfast("Big Breakfast", "38000", R.drawable.big_breakfast));
        listBreakfast.add(new Breakfast("Paket Big Records", "562500", R.drawable.paket_big_order));
        listBreakfast.add(new Breakfast("Hotcakes 3pcs", "29500", R.drawable.hotcakes_3pcs));
        listBreakfast.add(new Breakfast("PaNas 2 with Rice", "50000", R.drawable.panas_2_with_rice));
        listBreakfast.add(new Breakfast("Sausage McMuffin", "28000", R.drawable.sausage_mcmuffin));
        listBreakfast.add(new Breakfast("Chicken Muffin with Egg", "30500", R.drawable.chicken_muffin_with_egg));
    }

    private void initDataBeverages(){
        this.listBeverages =  new ArrayList<>();
        listBeverages.add(new Beverages("Sprite X Manggo McFloat", "17000", R.drawable.sprite_x_manggo_mcfloat));
        listBeverages.add(new Beverages("Coca Cola X Strawberry McFloat", "17000", R.drawable.coca_cola_x_strawberry_mcfloat));
        listBeverages.add(new Beverages("Iced Lychee Tea", "20000", R.drawable.iced_lychee_tea));
        listBeverages.add(new Beverages("Coca Cola", "9500", R.drawable.coca_cola));
        listBeverages.add(new Beverages("Coke McFloat", "14000", R.drawable.coke_mcfloat));
        listBeverages.add(new Beverages("Fanta", "9500", R.drawable.fanta));
        listBeverages.add(new Beverages("Teh Panas", "12000", R.drawable.teh_panas));
        listBeverages.add(new Beverages("Fruit Tea Lemon", "9500", R.drawable.fruit_tea_lemon));
        listBeverages.add(new Beverages("Milo", "13000", R.drawable.milo));
        listBeverages.add(new Beverages("Air Mineral 600ml", "11500", R.drawable.air_mineral_600ml));
    }

    public String rp(int txt){
        Locale locale = new Locale("in", "ID");
        NumberFormat format = NumberFormat.getCurrencyInstance(locale);
        format.setMaximumFractionDigits(0);
        return format.format(txt); // Integer.toString(total);
    }

    private void deleteAllDataFromServer() {
        int x = 1;
        recordsInterface = APIClient.getClient().create(RecordsInterface.class);
        while ( x <= 1000){
            Call<Records> delRecords = recordsInterface.delRecords(x);
            delRecords.enqueue(new Callback<Records>() {
                @Override
                public void onResponse(Call<Records> call, Response<Records> response) {
                    Log.d("delete_records: ", response.toString());
                }

                @Override
                public void onFailure(Call<Records> call, Throwable t) {
                    Log.d("error_records: ", t.getMessage());
                }
            });
            x++;
        }
    }
}