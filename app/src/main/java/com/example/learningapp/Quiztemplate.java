package com.example.learningapp;

import static java.lang.reflect.Array.*;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Quiztemplate implements Serializable{
    private String questiontext;
    private String[] option;
    private int answer;

    private static String correctAnswer;

    Quiztemplate(String questiontext, String[] option, int answerN) {
        this.questiontext = questiontext;
        this.option = option;
        this.answer = answerN;
        correctAnswer = option[answerN];


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
        correctAnswer = option[answer];
        Random rand = new Random();
        int dr = 4;
        int random = rand.nextInt(dr);
        ArrayList<String> temp = new ArrayList<>(Arrays.asList(option));

        for(int i=0;i<option.length;i++)
        {
            option[i] = temp.get(random);
            System.out.println(option[i]);
            temp.remove(random);
            dr--;
            if(option[i].equals(correctAnswer))
            {
                answer = i;
            }
            if(dr!=0)
            {
                random = rand.nextInt(dr);
            }
            else
            {
                //option[i+1] = temp.get(0);
                break;
            }




        }
    }

}
