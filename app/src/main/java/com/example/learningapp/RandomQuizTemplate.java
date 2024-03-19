package com.example.learningapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomQuizTemplate {
    private List<Question> allQuestions;
    private List<Question> selectedQuestions;
    private int currentQuestionIndex = 0;
    private int correctAnswers = 0;

    public RandomQuizTemplate(List<Question> allQuestions) {
        this.allQuestions = allQuestions;
        selectedQuestions = new ArrayList<>();
        selectRandomQuestions();
    }

    private void selectRandomQuestions() {
        // Shuffle all questions to ensure randomness
        Collections.shuffle(allQuestions);
        // Select the first three questions from the shuffled list
        for (int i = 0; i < 3 && i < allQuestions.size(); i++) {
            selectedQuestions.add(allQuestions.get(i));
        }
    }

    public Question getCurrentQuestion() {
        if (currentQuestionIndex < selectedQuestions.size()) {
            return selectedQuestions.get(currentQuestionIndex);
        }
        return null;
    }

    public void answerCurrentQuestion(int selectedOptionIndex) {
        Question currentQuestion = getCurrentQuestion();
        if (currentQuestion != null && currentQuestion.getCorrectAnswerIndex() == selectedOptionIndex) {
            // Correct answer
            correctAnswers++;
        }
        currentQuestionIndex++;
    }

    public boolean hasNextQuestion() {
        return currentQuestionIndex < selectedQuestions.size();
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public int getTotalQuestions() {
        return selectedQuestions.size();
    }
}
