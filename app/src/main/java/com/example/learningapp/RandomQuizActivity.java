package com.example.learningapp;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class RandomQuizActivity extends AppCompatActivity {
    private RandomQuizTemplate randomQuizTemplate;
    private TextView questionTextView;
    private RadioGroup optionsRadioGroup;
    private Button nextButton;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.random_quiz);

        // Initialize views
        questionTextView = findViewById(R.id.question_text_view);
        optionsRadioGroup = findViewById(R.id.optionsRadioGroup);
        nextButton = findViewById(R.id.next_button);
        submitButton = findViewById(R.id.submit_button);

        // Create a list of all questions
        List<Question> allQuestions = new ArrayList<>();
        // Add questions here...
        allQuestions.add(new Question("Was ist die Definition einer stetigen Funktion?", "Eine Funktion ohne Sprünge.", "Eine Funktion ohne Nullstellen.", "Eine Funktion ohne Extremstellen.", "Eine Funktion ohne Unstetigkeitsstellen.", 0));
        allQuestions.add(new Question("Welche Bedingung muss erfüllt sein, damit eine Funktion an einer Stelle differenzierbar ist?", "Stetigkeit", "Stetigkeit und Unstetigkeit", "Grenzwertexistenz", "Stetigkeit und Differenzierbarkeit", 3));
        allQuestions.add(new Question("Was ist der Hauptsatz der Differential- und Integralrechnung?", "Der Fläche unter dem Graphen.", "Fläche über dem Graphen.", "Umkehrung der Integration.", "Dem Integral der Funktion.", 3));
        allQuestions.add(new Question("Wie lautet die Definition des Grenzwerts einer Funktion?", "Der Wert, den die Funktion an einer Stelle annimmt.", "Der größte Funktionswert.", "Die kleinste Funktionsstelle.", "Die Annäherung der Funktionswerte an einen bestimmten Punkt.", 0));
        allQuestions.add(new Question("Welche Funktion ist auf dem Intervall [0, ∞) nicht beschränkt?", "sin(x)", "cos(x)", "e^x", "ln(x)", 3));

        // Create a random quiz template
        randomQuizTemplate = new RandomQuizTemplate(allQuestions);

        // Display the first question
        displayQuestion(randomQuizTemplate.getCurrentQuestion());
    }

    private void displayQuestion(Question question) {
        if (question != null) {
            // Set the question text
            questionTextView.setText(question.getQuestion());

            // Clear existing radio buttons
            optionsRadioGroup.removeAllViews();

            // Add options as radio buttons
            String[] options = {question.getOptionA(), question.getOptionB(), question.getOptionC(), question.getOptionD()};
            for (int i = 0; i < options.length; i++) {
                RadioButton radioButton = new RadioButton(this);
                radioButton.setId(i);
                radioButton.setText(options[i]); // Set the text of each radio button to the respective option
                optionsRadioGroup.addView(radioButton);
            }

            // Enable/disable buttons based on question index
            nextButton.setEnabled(randomQuizTemplate.hasNextQuestion());
            submitButton.setEnabled(!randomQuizTemplate.hasNextQuestion());
        }
    }



    public void onNextButtonClick(View view) {
        int selectedOptionIndex = optionsRadioGroup.getCheckedRadioButtonId();
        if (selectedOptionIndex != -1) {
            randomQuizTemplate.answerCurrentQuestion(selectedOptionIndex);
            displayQuestion(randomQuizTemplate.getCurrentQuestion());
        } else {
            Toast.makeText(this, "Please select an option", Toast.LENGTH_SHORT).show();
        }
    }

    public void onSubmitButtonClick(View view) {
        int selectedOptionIndex = optionsRadioGroup.getCheckedRadioButtonId();
        if (selectedOptionIndex != -1) {
            randomQuizTemplate.answerCurrentQuestion(selectedOptionIndex);
            showQuizResult();
        } else {
            Toast.makeText(this, "Please select an option", Toast.LENGTH_SHORT).show();
        }
    }

    private void showQuizResult() {
        int correctAnswers = randomQuizTemplate.getCorrectAnswers();
        int totalQuestions = randomQuizTemplate.getTotalQuestions();
        Toast.makeText(this, "You got " + correctAnswers + " out of " + totalQuestions + " questions correct!", Toast.LENGTH_LONG).show();
    }




}
