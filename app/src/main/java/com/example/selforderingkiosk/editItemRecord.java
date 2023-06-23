package com.example.selforderingkiosk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import apiclient.APIClient;
import apiclient.Records;
import apiclient.RecordsInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class editItemRecord extends AppCompatActivity {

    TextView txtEditName, txtEditPrice, txtEditQuantity;
    Records records;
    RecordsInterface recordsInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item_record);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_edit_item_record);

        txtEditName = findViewById(R.id.txt_edit_name);
        txtEditPrice = findViewById(R.id.txt_edit_price);
        txtEditQuantity = findViewById(R.id.txt_edit_quantity);

        Intent it = getIntent();
        records = (Records) it.getSerializableExtra("current_records");
        txtEditName.setText(records.getName());
        txtEditPrice.setText(Integer.toString(records.getPrice()));
        txtEditQuantity.setText(Integer.toString(records.getQuantity()));
    }

    public void back(View v){
        finish();
    }

    public void deleteRecords(View v){
        recordsInterface = APIClient.getClient().create(RecordsInterface.class);
        Call<Records> delRecords = recordsInterface.delRecords(records.getId());
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
        finish();
    }
}