package com.example.learningapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;

public class SecondActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Chip chip1 = findViewById(R.id.elektro);
        Chip chip2 = findViewById(R.id.bwl);
        Chip chip3 = findViewById(R.id.info);
        Chip chip4 = findViewById(R.id.maschinenbau);
        Chip chip5 = findViewById(R.id.wing);

        chip1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SecondActivity.this, Elektrotechnik.class);
                startActivity(i);
            }
        });
    }
}
