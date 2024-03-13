package com.example.learningapp;

//import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;
//import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.learningapp.Quiztemplate;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ThirdActivity extends AppCompatActivity {
    Intent i;

    String name;

    Button reset;

    private String PREFS_NAME = "prefs";
    private String BUTTON_STATE_KEY_PREFIX = "button_state_";

    private String QUESTION_KEY_PREFIX = "question_";

    private Quiztemplate[] quiz = new Quiztemplate[8];

    private Button[] buttons = new Button[8];

    static int length;

    int question_button_number;



    //@SuppressLint("MissingInflatedId")

    //Erstellt das View
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        int modulenumber = (int) getIntent().getIntExtra("Moduln",0);
        name = (String) getIntent().getStringExtra("Modul");
        PREFS_NAME = PREFS_NAME + "_" + name;
        BUTTON_STATE_KEY_PREFIX = BUTTON_STATE_KEY_PREFIX + "_" + name;
        QUESTION_KEY_PREFIX = QUESTION_KEY_PREFIX + "_" + name;

        TextView modul = findViewById(R.id.thirdbutton);
        modul.setText(name);


        loadQuestions();

        if(quiz[0] == null) {
            //Toast.makeText(this, "Quiz wurde erstellt", Toast.LENGTH_SHORT).show();
            //int value = name.compareTo("Analysis");
            //Toast.makeText(this, value, Toast.LENGTH_SHORT).show();

            
                quiz[0] = new Quiztemplate("Was ist die Definition einer stetigen Funktion?", new String[]{"Eine Funktion ohne Sprünge.", "Eine Funktion ohne Nullstellen.", "Eine Funktion ohne Extremstellen.", "Eine Funktion ohne Unstetigkeitsstellen."}, 3);
                quiz[1] = new Quiztemplate("Welche Bedingung muss erfüllt sein, damit eine Funktion an einer Stelle differenzierbar ist?", new String[]{"Stetigkeit", "Stetigkeit und Unstetigkeit", "Grenzwertexistenz", "Stetigkeit und Differenzierbarkeit"}, 0);
                quiz[2] = new Quiztemplate("Was ist der Hauptsatz der Differential- und Integralrechnung?", new String[]{"Der Fläche unter dem Graphen.", "Fläche über dem Graphen.", "Umkehrung der Integration.", "Dem Integral der Funktion."}, 2);
                quiz[3] = new Quiztemplate("Wie lautet die Definition des Grenzwerts einer Funktion?", new String[]{"Der Wert, den die Funktion an einer Stelle annimmt.", "Der größte Funktionswert.", "Die kleinste Funktionsstelle.", "Die Annäherung der Funktionswerte an einen bestimmten Punkt."}, 3);
                quiz[4] = new Quiztemplate("Welche Funktion ist auf dem Intervall [0, ∞) nicht beschränkt?", new String[]{"sin(x)", "cos(x)", "e^x", "ln(x)"}, 2);
            if (name == "BWL") {
                quiz[0] = new Quiztemplate("Was beschreibt der Begriff 'ROI' in der Betriebswirtschaftslehre?", new String[]{"Return on Investment", "Rate of Inflation", "Revenue of Interest", "Risk of Investment"}, 0);
                quiz[1] = new Quiztemplate("Welche der folgenden Funktionen ist eine zentrale Aufgabe des Controllings in Unternehmen?", new String[]{"Produktentwicklung", "Personalrekrutierung", "Kostenmanagement", "Verkaufsförderung"}, 2);
                quiz[2] = new Quiztemplate("Was versteht man unter dem Begriff 'Break-even-Point'?", new String[]{"Der Zeitpunkt, an dem ein Unternehmen gegründet wird.", "Der Punkt, an dem die Produktionskosten gedeckt sind.", "Der Moment, an dem ein Unternehmen Insolvenz anmeldet.", "Der Zeitpunkt, an dem der Gewinn am höchsten ist."}, 1);
                quiz[3] = new Quiztemplate("Was ist der Unterschied zwischen Absatz- und Beschaffungsmarketing?", new String[]{"Absatzmarketing richtet sich an Endverbraucher, Beschaffungsmarketing an Unternehmen.", "Absatzmarketing bezieht sich auf den Verkauf, Beschaffungsmarketing auf den Einkauf.", "Es gibt keinen Unterschied.", "Absatzmarketing ist Teil des Beschaffungsmarketings."}, 0);
                quiz[4] = new Quiztemplate("Was ist das Ziel des Working Capital Managements?", new String[]{"Maximierung der Lagerbestände", "Optimierung der Produktionsprozesse", "Minimierung der Kapitalbindung im Umlaufvermögen", "Erhöhung der langfristigen Verbindlichkeiten"}, 2);
            } else if (name == "Informatik") {
                quiz[0] = new Quiztemplate("Was ist ein Algorithmus?", new String[]{"Ein Computerprogramm", "Eine mathematische Gleichung", "Eine Schritt-für-Schritt-Anleitung zur Lösung eines Problems", "Ein elektronisches Bauteil"}, 2);
                quiz[1] = new Quiztemplate("Welche Aufgabe hat ein Compiler in der Softwareentwicklung?", new String[]{"Übersetzung von Quellcode in Maschinencode", "Optimierung von Algorithmen", "Testen von Software", "Verwaltung von Datenbanken"}, 0);
                quiz[2] = new Quiztemplate("Was ist eine Schnittstelle (Interface) in der objektorientierten Programmierung?", new String[]{"Ein physischer Anschluss am Computer", "Eine Verbindung zum Internet", "Ein Bereich im Speicher für temporäre Daten", "Eine Sammlung von Methoden, die von Klassen implementiert werden müssen"}, 3);
                quiz[3] = new Quiztemplate("Was versteht man unter 'Open Source' Software?", new String[]{"Software, die kostenlos heruntergeladen werden kann", "Software, deren Quellcode öffentlich zugänglich ist", "Software, die von großen Unternehmen entwickelt wird", "Software, die nur für ausgewählte Benutzer zugänglich ist"}, 1);
                quiz[4] = new Quiztemplate("Was ist ein SQL-Injection-Angriff?", new String[]{"Ein Angriff auf ein drahtloses Netzwerk", "Ein Angriff auf einen Webserver", "Ein Angriff auf ein E-Mail-Konto", "Ein Angriff auf ein Betriebssystem"}, 1);
            }
        }

        length = quiz.length;

        for(int i = 0;i<length;i++)
        {
            if(quiz[i] != null)
            {
                question_button_number++;
                String text = "Q: " + (i+1);
                createButton(i,text);
                buttons[i] = findViewById(i);
                createButtonListener(buttons[i],i);
                createLongButtonListener(buttons[i],i);
                loadButtonState(buttons[i],i);
            }

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
                    if(buttons[i] != null)
                    {
                        buttons[i].setBackgroundColor(getResources().getColor(R.color.blue));
                    }
                }
                /*button1.setBackgroundColor(getResources().getColor(R.color.blue));
                button2.setBackgroundColor(getResources().getColor(R.color.blue));
                button3.setBackgroundColor(getResources().getColor(R.color.blue));
                button4.setBackgroundColor(getResources().getColor(R.color.blue));
                button5.setBackgroundColor(getResources().getColor(R.color.blue));*/
            }
        });

        Button add = findViewById(R.id.plus);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createQuestionDialog();
                //quiz[5] = new Quiztemplate("What is the capital of Greece?", new String[]{"Athens", "Thessaloniki", "Patras", "Heraklion"}, 0);
                //createButton(5,"Q: 6");
                //createButtonListener(buttons[5],6);
            }
        });




    }

    public void createQuestionDialog()
    {
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.dialog, null);
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("New Question");
        alert.setView(alertLayout);
        alert.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(question_button_number==8)
                {
                    return;
                }
                EditText question = alertLayout.findViewById(R.id.questiontext);
                question.setHint("Enter your Question:");

                EditText option1 = alertLayout.findViewById(R.id.answer1);
                option1.setHint("Enter your first and correct answer: ");
                EditText option2 = alertLayout.findViewById(R.id.answer2);
                option2.setHint("Enter your second answer: ");
                EditText option3 = alertLayout.findViewById(R.id.answer3);
                option3.setHint("Enter your third answer: ");
                EditText option4 = alertLayout.findViewById(R.id.answer4);
                option4.setHint("Enter your fourth answer: ");

                String questiontext = question.getText().toString();
                String[] options = new String[4];
                options[0] = option1.getText().toString();
                options[1] = option2.getText().toString();
                options[2] = option3.getText().toString();
                options[3] = option4.getText().toString();

                quiz[question_button_number] = new Quiztemplate(questiontext, options, 0);
                String text = "Q: " + (question_button_number+1);
                createButton(question_button_number,text);
                buttons[question_button_number] = findViewById(question_button_number);
                createButtonListener(buttons[question_button_number],question_button_number);
                createLongButtonListener(buttons[question_button_number],question_button_number);
                question_button_number++;


            }
        });
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Toast.makeText(getBaseContext(), "Cancelled", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();

    }

    public int getRadioAnswer()
    {
        int answer = 0;
        return answer;
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

    public void createLongButtonListener(Button button, final int questionnumber)
    {
        button.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v)
            {
                AlertDialog.Builder alert = new AlertDialog.Builder(ThirdActivity.this);
                alert.setTitle("Edit Question");
                alert.setMessage("Do you want to edit the question or delete it?");
                alert.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        editButtonQuestion(questionnumber);
                    }
                });
                alert.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        quiz[questionnumber] = null;
                        buttons[questionnumber].setVisibility(View.GONE);
                    }
                });
                AlertDialog dialog = alert.create();
                dialog.show();
                return true;
            }});
    };

    public void editButtonQuestion(int number)
    {
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.dialog, null);
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Edit Question");
        alert.setView(alertLayout);
        alert.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(question_button_number==8)
                {
                    return;
                }
                EditText question = alertLayout.findViewById(R.id.questiontext);
                question.setHint("Enter your Question:");

                EditText option1 = alertLayout.findViewById(R.id.answer1);
                option1.setHint("Enter your first and correct answer: ");
                EditText option2 = alertLayout.findViewById(R.id.answer2);
                option2.setHint("Enter your second answer: ");
                EditText option3 = alertLayout.findViewById(R.id.answer3);
                option3.setHint("Enter your third answer: ");
                EditText option4 = alertLayout.findViewById(R.id.answer4);
                option4.setHint("Enter your fourth answer: ");

                String questiontext = question.getText().toString();
                String[] options = new String[4];
                options[0] = option1.getText().toString();
                options[1] = option2.getText().toString();
                options[2] = option3.getText().toString();
                options[3] = option4.getText().toString();

                quiz[number] = new Quiztemplate(questiontext, options, 0);
                String text = "Q: " + (number+1);
            }
        });
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Toast.makeText(getBaseContext(), "Cancelled", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();

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
                i.putExtra("Question 1", quiz[questionnumber]);
                startActivityForResult(i, questionnumber);
            }
        });
    }

    //Nach Abschluss des Quizes wird HandleResult aufgerufen
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        handleResult(resultCode, buttons[requestCode], requestCode);

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
        //Toast.makeText(this, "handleResult", Toast.LENGTH_SHORT).show();
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

        if(number==0) {
            editor.putInt(buttonStateKey, state);
        }
        else if(number==1) {
            editor.putInt(buttonStateKey, state);
            //Toast.makeText(this, "2 Wurde gespeichert", Toast.LENGTH_SHORT).show();
        }
        else if(number==2) {
            editor.putInt(buttonStateKey, state);
        }
        else if(number==3) {
            editor.putInt(buttonStateKey, state);
        }
        else if(number==4) {
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
                //Toast.makeText(this, "1", Toast.LENGTH_SHORT).show();
                button.setBackgroundColor(getResources().getColor(R.color.green));
            }
            else if (savedButtonState == 2)
            {
                //Toast.makeText(this, "2", Toast.LENGTH_SHORT).show();
                button.setBackgroundColor(getResources().getColor(R.color.red));
            }
            else if (savedButtonState == 3)
            {
                //Toast.makeText(this, "3", Toast.LENGTH_SHORT).show();
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
