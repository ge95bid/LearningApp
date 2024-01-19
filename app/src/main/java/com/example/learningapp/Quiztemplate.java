package com.example.learningapp;

import java.io.Serializable;

public class Quiztemplate implements Serializable{
    private String questiontext;
    private String[] option;
    private int answer;

    Quiztemplate(String questiontext, String[] option, int answer) {
        this.questiontext = questiontext;
        this.option = option;
        this.answer = answer;
    }

    public String getQuestiontext() {
        return questiontext;
    }

    public void setQuestiontext(String questiontext) {
        this.questiontext = questiontext;
    }

    public String[] getOption() {
        return option;
    }

    public void setOption(String[] option) {
        this.option = option;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

}
