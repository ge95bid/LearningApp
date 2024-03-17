package com.example.learningapp;

public class RandomQuizActivity extends androidx.appcompat.app.AppCompatActivity {
        @Override
        public void onCreate(android.os.Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.random_intro);

                // Retrieve the subject name from the intent
                String subjectName = getIntent().getStringExtra("Modul");

                android.widget.TextView textViewSubject = findViewById(R.id.textViewSubject);

                // Set the subject name dynamically
                if (subjectName != null) {
                        textViewSubject.setText(subjectName);
                } else {
                        textViewSubject.setText("Unknown Subject");
                }
        }
}
