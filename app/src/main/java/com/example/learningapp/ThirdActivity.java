package com.example.learningapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.learningapp.Quiztemplate;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

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

    private static final String QUESTION_KEY_PREFIX = "question_";

    private Quiztemplate[] quiz = new Quiztemplate[5];

    private Button[] buttons = new Button[5];



    @SuppressLint("MissingInflatedId")

    //Erstellt das View
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);


        loadQuestions();

        if(quiz[0] == null)
        {
            Toast.makeText(this, "Quiz wurde erstellt", Toast.LENGTH_SHORT).show();
            quiz[0] = new Quiztemplate("Welche der folgenden Funktionen ist stetig, aber nicht differenzierbar?", new String[]{"|x|", "x^2", "e^x", "sin(x)"}, 0);
            quiz[1] = new Quiztemplate("Was beschreibt die Aussage des Hauptsatzes der Differential- und Integralrechnung in Analysis 1?", new String[]{"Umkehrung der Integration.", "Der Fläche unter dem Graphen.", "Fläche über dem Graphen.", "Dem Integral der Funktion."}, 0);
            quiz[2] = new Quiztemplate("What is the capital of France?", new String[]{"Paris", "Marseille", "Lyon", "Toulouse"}, 0);
            quiz[3] = new Quiztemplate("What is the capital of Italy?", new String[]{"Rome", "Milan", "Naples", "Turin"}, 0);
            quiz[4] = new Quiztemplate("What is the capital of Spain?", new String[]{"Madrid", "Barcelona", "Valencia", "Seville"}, 0);
        }

        final int length = quiz.length;

        for(int i = 0;i<length;i++)
        {
            String text = "Q: " + (i+1);
            createButton(i,text);
            buttons[i] = findViewById(i);
            createButtonListener(buttons[i],i+1);
            loadButtonState(buttons[i],i+1);
        }

        //button1 = findViewById(R.id.B1);
        //button2 = findViewById(R.id.B2);
        //button3 = findViewById(R.id.B3);
        //button4 = findViewById(R.id.B4);
        //button5 = findViewById(R.id.B5);

        //createButtonListener(button1,1);
        //createButtonListener(button2,2);
        //createButtonListener(button3,3);
        //createButtonListener(button4,4);
        //createButtonListener(button5,5);

        //createButton(90,"test");


        //loadButtonState(button1,1);
        //loadButtonState(button2,2);
        //loadButtonState(button3,3);
        //loadButtonState(button4,4);
        //loadButtonState(button5,5);

        reset = findViewById(R.id.reset);
        //Reseted alle Buttons
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences prefs = getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.clear();
                editor.apply();
                for(int i=0;i<length;i++)
                {
                    buttons[i].setBackgroundColor(getResources().getColor(R.color.blue));
                }
                /*button1.setBackgroundColor(getResources().getColor(R.color.blue));
                button2.setBackgroundColor(getResources().getColor(R.color.blue));
                button3.setBackgroundColor(getResources().getColor(R.color.blue));
                button4.setBackgroundColor(getResources().getColor(R.color.blue));
                button5.setBackgroundColor(getResources().getColor(R.color.blue));*/
            }
        });




    }

    public void createButton(int number, String text)
    {
        GridLayout layout = findViewById(R.id.gridLayout);

        Button button = new Button(this);
        button.setId(number);
        button.setText(text);
        //button.setBackgroundColor(getResources().getColor(R.color.blue));
        button.setLayoutParams(new android.view.ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT));
        button.setPadding(0, 0, 0, 0);

        button.setTextSize(20);
        button.setTextColor(getResources().getColor(R.color.white));
        button.setAllCaps(false);
        button.setHeight(175);
        //button.setBackground(getResources().getDrawable(R.drawable.rounded_corner));

        GradientDrawable shape =  new GradientDrawable();
        shape.setCornerRadius( 20 );
        shape.setColor(getResources().getColor(R.color.blue));
        button.setBackground(shape);

        GridLayout.LayoutParams params1 = new GridLayout.LayoutParams();
        params1.setMargins(10, 10, 10, 10);
        layout.addView(button,params1);


    }

    //Erstellt den ButtonListener
    public void createButtonListener(Button button, final int questionnumber)
    {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if(questionnumber==1) {
                    i = new Intent(ThirdActivity.this, QuizActivity.class);
                    i.putExtra("Question 1", quiz[0]);
                    startActivityForResult(i, 1);
                }
                if(questionnumber==2) {
                    i = new Intent(ThirdActivity.this, QuizActivity.class);
                    i.putExtra("Question 1", quiz[1]);
                    startActivityForResult(i, 2);
                }
                if(questionnumber==3) {
                    i = new Intent(ThirdActivity.this, QuizActivity.class);
                    i.putExtra("Question 1", quiz[2]);
                    startActivityForResult(i, 3);
                }
                if(questionnumber==4) {
                    i = new Intent(ThirdActivity.this, QuizActivity.class);
                    i.putExtra("Question 1", quiz[3]);
                    startActivityForResult(i, 4);
                }
                if(questionnumber==5) {
                    i = new Intent(ThirdActivity.this, QuizActivity.class);
                    i.putExtra("Question 1", quiz[4]);
                    startActivityForResult(i, 5);
                }*/

                i = new Intent(ThirdActivity.this, QuizActivity.class);
                i.putExtra("Question 1", quiz[questionnumber-1]);
                startActivityForResult(i, questionnumber);
            }
        });
    }

    //Nach Abschluss des Quizes wird HandleResult aufgerufen
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        handleResult(resultCode, buttons[requestCode-1], requestCode);

        /*if (requestCode == 1)
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
        }*/

        saveQuestions();
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

    private void saveQuestions()
    {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        Gson gson = new Gson();
        String questionJson = gson.toJson(quiz);

        editor.putString(QUESTION_KEY_PREFIX, questionJson);


        editor.apply();

    }

    private void loadQuestions()
    {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
        Gson gson = new Gson();
        String questionJson = prefs.getString(QUESTION_KEY_PREFIX, null);

        if(questionJson != null)
        {
            quiz = gson.fromJson(questionJson, Quiztemplate[].class);
        }
    }

    private String getQuestionKey(int i)
    {
        return QUESTION_KEY_PREFIX + "_" + i;
    }
}
