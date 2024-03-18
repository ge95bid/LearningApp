package com.example.learningapp;

import android.os.Bundle;
import java.util.List;


import androidx.appcompat.app.AppCompatActivity;

public class RandomQuizActivity extends AppCompatActivity {
    // Views


    // List to store random questions
    public List<Quiztemplate> randomQuestions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.random_quiz);
        // Initialize views
        questionTextView = findViewById(R.id.question_text_view);
        option1Button = findViewById(R.id.option1_button);
        option2Button = findViewById(R.id.option2_button);
        option3Button = findViewById(R.id.option3_button);
        option4Button = findViewById(R.id.option4_button);
        nextButton = findViewById(R.id.next_button);
        submitButton = findViewById(R.id.submit_button);
        optionsRadioGroup = findViewById(R.id.optionsRadioGroup);

        // Retrieve the ArrayList<Quiztemplate> from the intent extras
        ArrayList<android.os.Parcelable> parcelableArrayList = getIntent().getParcelableArrayListExtra("RandomQuestions");

        // Initialize the ArrayList to store Quiztemplate objects
        randomQuestions = new ArrayList<>();

        // Check if the ArrayList is not null and contains at least one question
        if (parcelableArrayList != null && !parcelableArrayList.isEmpty()) {
            // Iterate over each Parcelable object and cast them to Quiztemplate
            for (android.os.Parcelable parcelable : parcelableArrayList) {
                if (parcelable instanceof Quiztemplate) {
                    randomQuestions.add((Quiztemplate) parcelable);
                }
            }
        }

        // Display the first question
        displayQuestion(randomQuestions.get(0));
    }

    // Method to display a question
    private void displayQuestion(Quiztemplate question) {
        // Set the question text
        questionTextView.setText(question.getQuestiontext());

        // Set the options text for each radio button
        option1Button.setText(question.getOption()[0]);
        option2Button.setText(question.getOption()[1]);
        option3Button.setText(question.getOption()[2]);
        option4Button.setText(question.getOption()[3]);
    }
}