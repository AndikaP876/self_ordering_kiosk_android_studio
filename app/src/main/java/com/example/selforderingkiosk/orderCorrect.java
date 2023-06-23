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
import java.util.List;
import java.util.Locale;

import apiclient.APIClient;
import apiclient.Records;
import apiclient.RecordsInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class orderCorrect extends AppCompatActivity {
    Button btnNoOrderCorrect, btnYesOrderCorrect;
    RecyclerView recRecordsOrderCorrect;
    TextView txtTotalOrderCorrect;
    RecordsInterface recordsInterface;
    RecordsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_correct);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_order_correct);

        btnNoOrderCorrect = findViewById(R.id.btn_no_order_correct);
        btnYesOrderCorrect = findViewById(R.id.btn_yes_order_correct);
        btnNoOrderCorrect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(orderCorrect.this, Menu.class);
                startActivity(intent);
            }
        });
        btnYesOrderCorrect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(orderCorrect.this, complete.class);
                startActivity(intent);
            }
        });

        txtTotalOrderCorrect = findViewById(R.id.txt_total_order_correct);
        recRecordsOrderCorrect = findViewById(R.id.rec_records_orcer_correct);
        recRecordsOrderCorrect.setLayoutManager(new LinearLayoutManager(this));

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
                recRecordsOrderCorrect.setAdapter(adapter);

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
                        txtTotalOrderCorrect.setText(rp(totalPrice));
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

    public String rp(int txt){
        Locale locale = new Locale("in", "ID");
        NumberFormat format = NumberFormat.getCurrencyInstance(locale);
        format.setMaximumFractionDigits(0);
        return format.format(txt); // Integer.toString(total);
    }
}