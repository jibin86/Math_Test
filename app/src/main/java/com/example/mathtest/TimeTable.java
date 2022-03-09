package com.example.mathtest;

import java.util.Random;

public class TimeTable {
    int fore, back, answer;
    String quiz;

    TimeTable() {
        Random rd1 = new Random();
        Random rd2 = new Random();
        fore = rd1.nextInt(8) + 2;
        back = rd2.nextInt(8) + 2;
    }

    String makeQuiz(){
        quiz = fore + " X " + back;
        return quiz;
    }

    int makeAnswer(){
        answer = fore * back;
        return answer;
    }
}
