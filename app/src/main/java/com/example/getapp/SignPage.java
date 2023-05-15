package com.example.getapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignPage extends AppCompatActivity {

    Button b;
    Button n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_page);
        n = findViewById(R.id.button2);
        b = findViewById(R.id.button);

        n.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(SignPage.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
        );
    }
}