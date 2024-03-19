package com.example.learningapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

//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

public class SecondActivity extends AppCompatActivity {
    Chip[] chips = new Chip[50];
    String[] chipNames = new String[50];

    int number = 0;

    private String PREFS_NAME = "prefs";

    private String BUTTON_STATE_KEY_PREFIX = "button_state_";
    private String QUESTION_KEY_PREFIX = "question_";

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

        if(chipNames[0] == null)
        {
            chipNames[0] = "Analysis";
            chipNames[1] = "BWL";
            chipNames[2] = "Informatik";
            chipNames[3] = "Maschinenbau";
            chipNames[4] = "Wirtschaftsingenieurwesen";
            chipNames[5] = "Wirtschaftsinformatik";
        }

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
                System.out.println("Chip" + i + " " + chipNames[i]);
                addModule(chipNames[i]);
                //ChipListener(chips[i]);
                //ChipOnLongClickListener(chips[i]);
                if(chips[i] != null)
                {
                    ChipOnLongClickListener(chips[i]);
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
                    chipNames[number] = modul;
                    addModule(modul);
                    ChipListener(chips[number - 1]);
                    ChipOnLongClickListener(chips[number - 1]);
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
        number = 0;
        while (findViewById(number + 6262) != null) { // Überprüfen Sie, ob die ID bereits verwendet wird
            number++; // Inkrementieren Sie die ID, bis eine eindeutige gefunden wird
        }
        ChipGroup group = findViewById(R.id.chipGroup);
        chips[number] = new Chip(this);
        chips[number].setText(text);
        int id = number + 6262;
        while (findViewById(id) != null) { // Überprüfen Sie, ob die ID bereits verwendet wird
            id++; // Inkrementieren Sie die ID, bis eine eindeutige gefunden wird
        }
        chips[number].setId(id);
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

        for(int i = 0; i < chipNames.length; i++)
        {
            editor.remove("Module" + i);
            if(chipNames[i] != null)
            {
                System.out.println("Saved" + i + " " + chipNames[i]);
                editor.putString("Module" + i, chipNames[i]);
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

    public void ChipOnLongClickListener(Chip chip)
    {
        final String name = chip.getText().toString();
        chip.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(SecondActivity.this);
                alert.setTitle("Edit Question");
                alert.setMessage("Do you want to edit the question or delete it?");
                alert.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        editModule(chip);
                    }
                });
                alert.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ChipGroup layout = findViewById(R.id.chipGroup);
                        //buttons[questionnumber+1].setText("Q: " + (questionnumber));
                        int numberN = 0;
                        for(int i=0;i<chipNames.length-1;i++)
                        {
                            if(chipNames[i] != null)
                            {
                                if(chipNames[i] == name)
                                {
                                    System.out.println("Found" + i);
                                    numberN = i;
                                }
                            }
                            //quiz[i] = null;
                        }

                        chips[numberN] = null;
                        chipNames[numberN] = null;
                        layout.removeView(chip);
                        BUTTON_STATE_KEY_PREFIX = BUTTON_STATE_KEY_PREFIX + "_" + name;
                        QUESTION_KEY_PREFIX = QUESTION_KEY_PREFIX + "_" + name;
                        PREFS_NAME = PREFS_NAME + "_" + name;
                        number--;
                        saveModules();
                        deleteQuestions();

                        //saveModules();
                        //onCreate(null);
                    }
                });
                AlertDialog dialog = alert.create();
                dialog.show();
                return true;
            }});
    };

    public void editModule(Chip chip)
    {
        AlertDialog alertDialog = new AlertDialog.Builder(SecondActivity.this).create();
        alertDialog.setTitle("Edit Module");
        View alertView = getLayoutInflater().inflate(R.layout.addmodule_dialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(SecondActivity.this);
        builder.setView(alertView);
        String name = chip.getText().toString();

        builder.setPositiveButton("Edit", (dialog, which) -> {

            EditText edit = alertView.findViewById(R.id.edittext);
            String modul = edit.getText().toString();
            for(int i=0;i<chipNames.length;i++)
            {
                if(chipNames[i] != null)
                {
                    if(chipNames[i].equals(name))
                    {
                        chipNames[i] = modul;
                    }
                }
            }

            chip.setText(modul);
            saveModules();
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    public void deleteQuestions()
    {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        for(int i = 0; i < 500; i++)
        {
            editor.remove(BUTTON_STATE_KEY_PREFIX);
        }
        editor.remove(QUESTION_KEY_PREFIX);
        editor.apply();
    }


}


