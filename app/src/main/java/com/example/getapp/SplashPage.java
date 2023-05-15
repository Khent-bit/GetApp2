package com.example.getapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
        new Handler().postDelayed(() -> {
                Intent intent = new Intent(SplashPage.this, SignPage.class);
                startActivity(intent);
                finish();
        }, 2000);
    }
}