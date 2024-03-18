package com.example.learningapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;



public class SecondActivity extends AppCompatActivity {
    Chip[] chips = new Chip[50];
    String[] chipNames = new String[50];

    static int number = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        /*chipNames[0] = "Analysis";
        chipNames[1] = "BWL";
        chipNames[2] = "Informatik";
        chipNames[3] = "Maschinenbau";
        chipNames[4] = "Wirtschaftsingenieurwesen";
        chipNames[5] = "Wirtschaftsinformatik";*/

        loadModules();

        /*Chip chip1 = findViewById(R.id.analysis1);
        Chip chip2 = findViewById(R.id.bwl);
        Chip chip3 = findViewById(R.id.info);
        Chip chip4 = findViewById(R.id.maschinenbau);
        Chip chip5 = findViewById(R.id.wing);
        Chip chip6 = findViewById(R.id.winf);*/

        for(int i = 0; i < chips.length; i++)
        {
            if(chipNames[i] != null)
            {
                addModule(chipNames[i]);
                ChipListener(chips[i]);
                if(chips[i] != null)
                {
                    ChipListener(chips[i]);
                }

            }
        }



        /*chip1.setOnClickListener(new View.OnClickListener() {
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
        });*/

        Chip chip = findViewById(R.id.addmodule);
        chip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(SecondActivity.this).create();
                alertDialog.setTitle("Add Module");
                View alertView = getLayoutInflater().inflate(R.layout.addmodule_dialog, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(SecondActivity.this);
                builder.setView(alertView);

                builder.setPositiveButton("Add", (dialog, which) -> {

                    EditText edit = alertView.findViewById(R.id.edittext);
                    String modul = edit.getText().toString();
                    addModule(modul);
                    ChipListener(chips[number - 1]);
                    saveModules();


                    // Do something when the button is clicked
                });

                builder.setNegativeButton("Cancel", (dialog, which) -> {
                    // Do something when the button is clicked
                });
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });

    }

    public void addModule(String text)
    {
        ChipGroup group = findViewById(R.id.chipGroup);
        chips[number] = new Chip(this);
        chips[number].setText(text);
        chips[number].setId(View.generateViewId());
        chips[number].setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        chips[number].setPadding(0, 0, 0, 0);
        chips[number].setTextSize(20);
        chips[number].setTextColor(getResources().getColor(R.color.white));
        chips[number].setAllCaps(false);
        chips[number].setHeight(175);

        chips[number].setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        int chipColor = getResources().getColor(R.color.blue); // Die gewünschte Hintergrundfarbe
        chips[number].setChipBackgroundColor(ColorStateList.valueOf(chipColor));


        group.addView(chips[number]);
        number++;

    }

    public void ChipListener(Chip chip)
    {
        chip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveModules();
                Intent i = new Intent(SecondActivity.this, ThirdActivity.class);
                i.putExtra("Modul", chip.getText());
                startActivity(i);
            }
        });
    }

    public void saveModules()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("Modules", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        for(int i = 0; i < chips.length; i++)
        {
            if(chips[i] != null)
            {
                editor.putString("Module" + i, chips[i].getText().toString());
            }
        }
        editor.apply();
    }

    public void loadModules()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("Modules", MODE_PRIVATE);
        for(int i = 0; i < chips.length; i++)
        {
            if(sharedPreferences.contains("Module" + i))
            {
                chipNames[i] = sharedPreferences.getString("Module" + i, "");
            }
        }
    }


}
