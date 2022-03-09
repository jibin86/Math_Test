package com.example.mathtest;

import java.util.*;

public class RandomQuestion {
    int fore, back, answer;
    String quiz;

    RandomQuestion() {

        do {
            Random rd1 = new Random();
            Random rd2 = new Random();
            fore = rd1.nextInt(100) + 1;
            back = rd2.nextInt(100) + 1;
        }while (fore + back > 100);



    }

    String makeQuiz() {
        quiz = fore+" + "+back;
        return quiz;
    }
    int makeAnswer() {
        answer = fore+back;
        return answer;
    }
}
