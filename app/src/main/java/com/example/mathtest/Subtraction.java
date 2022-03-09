package com.example.mathtest;

import java.util.Random;

public class Subtraction {
    int fore, back, answer;
    String quiz;

    Subtraction(){
        do{
            Random rd1 = new Random();
            Random rd2 = new Random();
            fore = rd1.nextInt(91) + 10;
            back = rd2.nextInt(100) + 1;
        }while (fore <= back);
    }
    String makeQuiz() {
        quiz = fore+" - "+back;
        return quiz;
    }
    int makeAnswer() {
        answer = fore - back;
        return answer;
    }
}
