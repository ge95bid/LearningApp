package com.example.learningapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;
import android.view.MenuItem;

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
                PopupMenu popupMenu = new PopupMenu(SecondActivity.this, chip1);
                popupMenu.getMenuInflater().inflate(R.menu.dropdown_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(SecondActivity.this, "You Clicked " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
    }
}
