package com.example.learningapp;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.learningapp.Quiztemplate;
import com.google.android.material.chip.Chip;


public class QuizActivity extends AppCompatActivity {

    private Quiztemplate quiz;
    private Chip button1;
    private Chip button2;
    private Chip button3;
    private Chip button4;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        quiz = (Quiztemplate) getIntent().getSerializableExtra("Question 1");
        quiz.randomnize();

        TextView questiontext = findViewById(R.id.quiztext);

        questiontext.setText(quiz.getQuestiontext());

        button1 = findViewById(R.id.answer1);
        button1.setText(quiz.getOption()[0]);
        button1.setChipBackgroundColorResource(R.color.blue);
        setChipListener(button1,0);

        button2 = findViewById(R.id.answer2);
        button2.setText(quiz.getOption()[1]);
        button2.setChipBackgroundColorResource(R.color.blue);
        setChipListener(button2,1);

        button3 = findViewById(R.id.answer3);
        button3.setText(quiz.getOption()[2]);
        button3.setChipBackgroundColorResource(R.color.blue);
        setChipListener(button3,2);

        button4 = findViewById(R.id.answer4);
        button4.setText(quiz.getOption()[3]);
        button4.setChipBackgroundColorResource(R.color.blue);
        setChipListener(button4,3);

    }

    public void setChipListener(Chip chip, final int option)
    {
        //chip.setChipBackgroundColorResource(R.color.green);
        chip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                Toast.makeText(QuizActivity.this, "Funktioniert", Toast.LENGTH_SHORT).show();
                if(checkAnswer(option))
                {
                    chip.setChipBackgroundColorResource(R.color.green);
                    setResult(RESULT_OK);
                    finish();
                }
                else
                {
                    chip.setChipBackgroundColorResource(R.color.red);
                    setResult(RESULT_FIRST_USER);

                    showCorrectAnswer();

                    finish();
                }
            }
        });
    }

    public boolean checkAnswer(int option)
    {
        int correctanswer = getCorrectAnswer();
        if(option == correctanswer)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public int getCorrectAnswer()
    {
        return quiz.getAnswer();
    }

    public void showCorrectAnswer()
    {
        int correctanswer = getCorrectAnswer();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                switch(correctanswer)
                {
                    case 0:
                        button1.setChipBackgroundColorResource(R.color.green);
                        break;
                    case 1:
                        button2.setChipBackgroundColorResource(R.color.green);
                        break;
                    case 2:
                        button3.setChipBackgroundColorResource(R.color.green);
                        break;
                    case 3:
                        button4.setChipBackgroundColorResource(R.color.green);
                        break;
                }
            }
        }, 5000);

    }

    public void setCorrectAnswerTint(Chip chip)
    {
        chip.setChipBackgroundColorResource(R.color.green);
    }


}
