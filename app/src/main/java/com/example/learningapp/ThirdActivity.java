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

    private static final String PREFS_NAME = "prefs";
    private static final String BUTTON_STATE_KEY_1 = "buttonState";
    private static final String BUTTON_STATE_KEY_2 = "buttonState";
    private static final String BUTTON_STATE_KEY_3 = "buttonState";
    private static final String BUTTON_STATE_KEY_4 = "buttonState";
    private static final String BUTTON_STATE_KEY_5 = "buttonState";
    @SuppressLint("MissingInflatedId")
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
        //loadButtonState(button2,2);
        //loadButtonState(button3,3);
        //loadButtonState(button4,4);
        //loadButtonState(button5,5);


    }

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

    public void handleResult(int resultCode, Button button, int number)
    {
        if (resultCode == RESULT_OK)
        {
            button.setBackgroundColor(getResources().getColor(R.color.green));
            saveButtonState(number,1);
        }
        else if (resultCode == RESULT_CANCELED)
        {
            button.setBackgroundColor(getResources().getColor(R.color.red));
            saveButtonState(number,2);
        }
        else
        {
            saveButtonState(number,3);
        }


    }

    public void saveButtonState(final int number, int state)
    {
        Toast.makeText(this, "Wurde gespeichert", Toast.LENGTH_SHORT).show();
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        if(number==1) {
            editor.putInt(BUTTON_STATE_KEY_1, state);
        }
        if(number==2) {
            editor.putInt(BUTTON_STATE_KEY_2, state);
        }
        if(number==3) {
            editor.putInt(BUTTON_STATE_KEY_3, state);
        }
        if(number==4) {
            editor.putInt(BUTTON_STATE_KEY_4, state);
        }
        if(number==5) {
            editor.putInt(BUTTON_STATE_KEY_5, state);
        }
        editor.apply();
    }

    public void loadButtonState(Button button, int number)
    {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME,MODE_PRIVATE);

        int savedButtonState = -1;
        if(number==1) {
            savedButtonState = prefs.getInt(BUTTON_STATE_KEY_1, -1);
        }
        else if(number==2) {
            savedButtonState = prefs.getInt(BUTTON_STATE_KEY_2, -1);
        }
        else if(number==3) {
            savedButtonState = prefs.getInt(BUTTON_STATE_KEY_3, -1);
        }
        else if(number==4) {
            savedButtonState = prefs.getInt(BUTTON_STATE_KEY_4, -1);
        }
        else if(number==5) {
            savedButtonState = prefs.getInt(BUTTON_STATE_KEY_5, -1);
        }

        if(savedButtonState != -1)
        {
            if (savedButtonState == 1)
            {
                button.setBackgroundColor(getResources().getColor(R.color.green));
            }
            else if (savedButtonState == 2)
            {
                button.setBackgroundColor(getResources().getColor(R.color.red));
            }
            else
            {
            }
        }
    }

}
