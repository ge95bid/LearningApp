package com.example.learningapp;

public class RandomIntro extends androidx.appcompat.app.AppCompatActivity {
        private android.widget.Button randomQuizButton;

        @Override
        public void onCreate(android.os.Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.random_intro);

                // Retrieve the questions from the intent
                Quiztemplate[] questions = (Quiztemplate[]) getIntent().getParcelableArrayExtra("RandomQuestions");

                // Retrieve the subject name from the intent
                String subjectName = getIntent().getStringExtra("Modul");

                android.widget.TextView textViewSubject = findViewById(R.id.textViewSubject);

                // Set the subject name dynamically
                if (subjectName != null) {
                        textViewSubject.setText(subjectName);
                } else {
                        textViewSubject.setText("Unknown Subject");
                }

                randomQuizButton = findViewById(com.example.learningapp.R.id.btnStartQuiz);

                randomQuizButton.setOnClickListener(new android.view.View.OnClickListener() {
                        @Override
                        public void onClick(android.view.View v) {
                                // intent to start RandomQuizActivity
                                android.content.Intent intent = new android.content.Intent(RandomIntro.this, RandomQuizActivity.class);
                                // Pass the list of questions to RandomQuizActivity
                                //if (questions != null) {
                                intent.putExtra("RandomQuestions", questions);
                                startActivity(intent);
                                // } else {
                                // Handle the case where questions are not available
                                // You can display a message or handle it according to your requirement
                                // }
                        }
                });

        }
}
