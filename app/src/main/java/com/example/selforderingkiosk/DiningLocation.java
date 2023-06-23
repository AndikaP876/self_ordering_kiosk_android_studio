package com.example.selforderingkiosk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class DiningLocation extends AppCompatActivity {

    Button btnEatIn;
    Button btnTakeAway;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dining_location);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dining_location);

        btnEatIn = findViewById(R.id.btn_eat_in);
        btnTakeAway = findViewById(R.id.btn_take_away);
        btnEatIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DiningLocation.this,Menu.class);
                startActivity(intent);
            }
        });
        btnTakeAway.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DiningLocation.this,Menu.class);
                startActivity(intent);
            }
        });
    }
}