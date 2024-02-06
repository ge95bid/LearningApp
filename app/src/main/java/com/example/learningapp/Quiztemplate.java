package com.example.learningapp;

import java.io.Serializable;
import java.util.Random;

public class Quiztemplate implements Serializable{
    private String questiontext;
    private String[] option;
    private int answer;

    Quiztemplate(String questiontext, String[] option, int answerN) {
        this.questiontext = questiontext;
        this.option = option;
        this.answer = answerN;

        Random rand = new Random();
        int random = rand.nextInt(4);
        String temp[] = option.clone();
        int d = 0;

        for(int i=0;i<option.length;i++)
        {
            d = i+random;
            if(d>3)
            {
                d = d-4;
                option[d] = temp[i];
            }
            else
            {
                option[d] = temp[i];
            }
        }

        switch(random)
        {
            case 0:
                answer = 0;
                break;
            case 1:
                answer = 1;
                break;
            case 2:
                answer = 2;
                break;
            case 3:
                answer = 3;
                break;
        }

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
        //System.out.println(answer);
        return answer;
    }

    public void randomnize()
    {
        Random rand = new Random();
        int random = rand.nextInt(4);
        String temp[] = option.clone();
        int d = 0;

        for(int i=0;i<option.length;i++)
        {
            d = i+random;
            if(d>3)
            {
                d = d-4;
                option[d] = temp[i];
            }
            else
            {
                option[d] = temp[i];
            }
        }

        switch(random)
        {
            case 0:
                answer = 0;
                break;
            case 1:
                answer = 1;
                break;
            case 2:
                answer = 2;
                break;
            case 3:
                answer = 3;
                break;
        }
    }

}
