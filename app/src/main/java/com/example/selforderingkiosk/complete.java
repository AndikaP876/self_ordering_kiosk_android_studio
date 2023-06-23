package com.example.selforderingkiosk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import apiclient.APIClient;
import apiclient.Records;
import apiclient.RecordsInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class complete extends AppCompatActivity {
    RecordsInterface recordsInterface;
    Button btnComplete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_complete);

        btnComplete = findViewById(R.id.btn_complete);

        // Buat instance RecordsInterface

        // Atur pendengar klik untuk tombol
        btnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAllDataFromServer();
                Intent intent = new Intent(complete.this, SplashScreen.class);
                startActivity(intent);
            }
        });
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