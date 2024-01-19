package com.example.learningapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.learningapp.Quiztemplate;
public class ThirdActivity extends AppCompatActivity {
    Intent i;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;

    Button reset;

    private static final String PREFS_NAME = "prefs";
    private static final String BUTTON_STATE_KEY_PREFIX = "button_state_";
    @SuppressLint("MissingInflatedId")

    //Erstellt das View
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        button1 = findViewById(R.id.B1);
        button2 = findViewById(R.id.B2);
        button3 = findViewById(R.id.B3);
        button4 = findViewById(R.id.B4);
        button5 = findViewById(R.id.B5);

        createButtonListener(button1,1);
        createButtonListener(button2,2);
        createButtonListener(button3,3);
        createButtonListener(button4,4);
        createButtonListener(button5,5);


        loadButtonState(button1,1);
        loadButtonState(button2,2);
        loadButtonState(button3,3);
        loadButtonState(button4,4);
        loadButtonState(button5,5);

        reset = findViewById(R.id.reset);
        //Reseted alle Buttons
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences prefs = getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.clear();
                editor.apply();
                button1.setBackgroundColor(getResources().getColor(R.color.blue));
                button2.setBackgroundColor(getResources().getColor(R.color.blue));
                button3.setBackgroundColor(getResources().getColor(R.color.blue));
                button4.setBackgroundColor(getResources().getColor(R.color.blue));
                button5.setBackgroundColor(getResources().getColor(R.color.blue));
            }
        });


    }

    //Erstellt den ButtonListener
    public void createButtonListener(Button button, final int questionnumber)
    {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(questionnumber==1) {
                    Quiztemplate quiz1 = new Quiztemplate("What is the capital of India?", new String[]{"Delhi", "Mumbai", "Kolkata", "Chennai"}, 0);
                    i = new Intent(ThirdActivity.this, QuizActivity.class);
                    i.putExtra("Question 1", quiz1);
                    startActivityForResult(i, 1);
                }
                if(questionnumber==2) {
                    Quiztemplate quiz2 = new Quiztemplate("What is the capital of Germany?", new String[]{"Berlin", "Munich", "Hamburg", "Frankfurt"}, 0);
                    i = new Intent(ThirdActivity.this, QuizActivity.class);
                    i.putExtra("Question 1", quiz2);
                    startActivityForResult(i, 2);
                }
                if(questionnumber==3) {
                    Quiztemplate quiz3 = new Quiztemplate("What is the capital of France?", new String[]{"Paris", "Marseille", "Lyon", "Toulouse"}, 0);
                    i = new Intent(ThirdActivity.this, QuizActivity.class);
                    i.putExtra("Question 1", quiz3);
                    startActivityForResult(i, 3);
                }
                if(questionnumber==4) {
                    Quiztemplate quiz4 = new Quiztemplate("What is the capital of Italy?", new String[]{"Rome", "Milan", "Naples", "Turin"}, 0);
                    i = new Intent(ThirdActivity.this, QuizActivity.class);
                    i.putExtra("Question 1", quiz4);
                    startActivityForResult(i, 4);
                }
                if(questionnumber==5) {
                    Quiztemplate quiz5 = new Quiztemplate("What is the capital of Spain?", new String[]{"Madrid", "Barcelona", "Valencia", "Seville"}, 0);
                    i = new Intent(ThirdActivity.this, QuizActivity.class);
                    i.putExtra("Question 1", quiz5);
                    startActivityForResult(i, 5);
                }
            }
        });
    }

    //Nach Abschluss des Quizes wird HandleResult aufgerufen
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1)
        {
            handleResult(resultCode, button1, 1);
        }
        else if (requestCode == 2)
        {
            handleResult(resultCode, button2, 2);
        }
        else if (requestCode == 3)
        {
            handleResult(resultCode, button3, 3);
        }
        else if (requestCode == 4)
        {
            handleResult(resultCode, button4, 4);
        }
        else if (requestCode == 5)
        {
            handleResult(resultCode, button5, 5);
        }
        else
        {
            Toast.makeText(this, "Wurde nicht gespeichert", Toast.LENGTH_SHORT).show();
        }
    }

    //Legt fest, ob der Button grün oder rot wird
    public void handleResult(int resultCode, Button button, int number)
    {
        Toast.makeText(this, "handleResult", Toast.LENGTH_SHORT).show();
        if (resultCode == RESULT_OK)
        {
            button.setBackgroundColor(getResources().getColor(R.color.green));
            saveButtonState(number,1);
        }
        else if (resultCode == RESULT_CANCELED)
        {
            button.setBackgroundColor(getResources().getColor(R.color.blue));
            saveButtonState(number,3);
        }
        else
        {
            button.setBackgroundColor(getResources().getColor(R.color.red));
            saveButtonState(number,2);
        }


    }

    //Die Farbe des Buttons wird gespeichert
    public void saveButtonState(final int number, int state)
    {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        String buttonStateKey = getButtonStateKey(number);

        if(number==1) {
            editor.putInt(buttonStateKey, state);
        }
        else if(number==2) {
            editor.putInt(buttonStateKey, state);
            Toast.makeText(this, "2 Wurde gespeichert", Toast.LENGTH_SHORT).show();
        }
        else if(number==3) {
            editor.putInt(buttonStateKey, state);
        }
        else if(number==4) {
            editor.putInt(buttonStateKey, state);
        }
        else if(number==5) {
            editor.putInt(buttonStateKey, state);
        }
        editor.apply();
    }

    //Die Farbe des Buttons wird geladen
    public void loadButtonState(Button button, int number)
    {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME,MODE_PRIVATE);

        int savedButtonState = prefs.getInt(getButtonStateKey(number), -1);


        if(savedButtonState != -1)
        {
            if (savedButtonState == 1)
            {
                Toast.makeText(this, "1", Toast.LENGTH_SHORT).show();
                button.setBackgroundColor(getResources().getColor(R.color.green));
            }
            else if (savedButtonState == 2)
            {
                Toast.makeText(this, "2", Toast.LENGTH_SHORT).show();
                button.setBackgroundColor(getResources().getColor(R.color.red));
            }
            else if (savedButtonState == 3)
            {
                Toast.makeText(this, "3", Toast.LENGTH_SHORT).show();
                button.setBackgroundColor(getResources().getColor(R.color.blue));
            }
        }
    }

    //Der Key für die Farbe des Buttons wird erstellt
    private String getButtonStateKey(int number) {
        return BUTTON_STATE_KEY_PREFIX + "_" + number;
    }

}
