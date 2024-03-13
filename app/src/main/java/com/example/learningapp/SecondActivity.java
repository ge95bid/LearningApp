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

        Chip chip1 = findViewById(R.id.analysis1);
        Chip chip2 = findViewById(R.id.bwl);
        Chip chip3 = findViewById(R.id.info);
        Chip chip4 = findViewById(R.id.maschinenbau);
        Chip chip5 = findViewById(R.id.wing);
        Chip chip6 = findViewById(R.id.winf);

        chip1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SecondActivity.this, ThirdActivity.class);
                i.putExtra("Modul", "Analysis");
                startActivity(i);
            }
        });

        chip2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SecondActivity.this, ThirdActivity.class);
                i.putExtra("Modul", "BWL");
                startActivity(i);
            }
        });

        chip3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SecondActivity.this, ThirdActivity.class);
                i.putExtra("Modul", "Informatik");
                startActivity(i);
            }
        });
        chip4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SecondActivity.this, ThirdActivity.class);
                i.putExtra("Modul", "Maschinenbau");
                startActivity(i);
            }
        });
        chip5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SecondActivity.this, ThirdActivity.class);
                i.putExtra("Modul", "Wirtschaftsingenieurwesen");
                startActivity(i);
            }
        });
        chip6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SecondActivity.this, ThirdActivity.class);
                i.putExtra("Modul", "Wirtschaftsinformatik");
                startActivity(i);
            }
        });

    }
}
