package com.example.mathtest;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    TextView textNum, quiz, textAnswer, wrong, correct, score, tvYear, tvMonth, tvDay, tvHour, tvMinute, min, sec;
    Button[] btn = new Button[10];
    ImageButton btnDel, btnCheck;
    int num, answer, checkAnswer;
    LinearLayout mainLayout, optionLayout, firstLayout, lastLayout;
    Button start, retry, close;
    Button timetable, addtion, subtraction;
    LinkedList<Integer> wrongNum;
    long startT;
    long finishT;
    int periodS, periodM;
    int option;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textNum = (TextView)findViewById(R.id.num);
        quiz = (TextView)findViewById(R.id.quiz);
        textAnswer = (TextView)findViewById(R.id.answer);

        btn[0] = (Button)findViewById(R.id.btn0);
        btn[1] = (Button)findViewById(R.id.btn1);
        btn[2] = (Button)findViewById(R.id.btn2);
        btn[3] = (Button)findViewById(R.id.btn3);
        btn[4] = (Button)findViewById(R.id.btn4);
        btn[5] = (Button)findViewById(R.id.btn5);
        btn[6] = (Button)findViewById(R.id.btn6);
        btn[7] = (Button)findViewById(R.id.btn7);
        btn[8] = (Button)findViewById(R.id.btn8);
        btn[9] = (Button)findViewById(R.id.btn9);
        btnDel = (ImageButton)findViewById(R.id.btnDel);
        btnCheck = (ImageButton)findViewById(R.id.btnCheck);

        mainLayout = (LinearLayout)findViewById(R.id.mainLayout);
        optionLayout = (LinearLayout)findViewById(R.id.optionLayout);
        firstLayout = (LinearLayout)findViewById(R.id.firstLayout);
        lastLayout = (LinearLayout)findViewById(R.id.lastLayout);
        start = (Button)findViewById(R.id.start);
        retry = (Button)findViewById(R.id.retry);
        close = (Button)findViewById(R.id.close);
        timetable = (Button)findViewById(R.id.timetable);
        addtion = (Button)findViewById(R.id.addition);
        subtraction = (Button)findViewById(R.id.subtraction);
        score = (TextView)findViewById(R.id.score);
        wrong = (TextView)findViewById(R.id.wrong);
        correct = (TextView)findViewById(R.id.correct);
        tvYear = (TextView)findViewById(R.id.tvYear);
        tvMonth = (TextView)findViewById(R.id.tvMonth);
        tvDay = (TextView)findViewById(R.id.tvDay);
        tvHour = (TextView)findViewById(R.id.tvHour);
        tvMinute = (TextView)findViewById(R.id.tvMinute);
        min = (TextView)findViewById(R.id.min);
        sec = (TextView)findViewById(R.id.sec);

        // 시작 버튼 클릭 --> 옵션 화면으로 전환
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainLayout.setVisibility(View.INVISIBLE);
                optionLayout.setVisibility(View.VISIBLE);
            }
        });

       // 구구단 버튼 클릭
        timetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num = 1;
                option = 0;
                textNum.setText(Integer.toString(num));
                wrongNum = new LinkedList<Integer>();
                TimeTable tt = new TimeTable();
                quiz.setText(tt.makeQuiz());
                answer = tt.makeAnswer();
                optionLayout.setVisibility(View.INVISIBLE);
                firstLayout.setVisibility(View.VISIBLE);
                // 시간 측정
                startT = System.currentTimeMillis();
            }
        });

        // 덧셈 버튼 클릭
        addtion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num = 1;
                option = 1;
                textNum.setText(Integer.toString(num));
                wrongNum = new LinkedList<Integer>();
                RandomQuestion RQ = new RandomQuestion();
                quiz.setText(RQ.makeQuiz());
                answer = RQ.makeAnswer();
                optionLayout.setVisibility(View.INVISIBLE);
                firstLayout.setVisibility(View.VISIBLE);
                // 시간 측정
                startT = System.currentTimeMillis();
            }
        });

        // 뺄셈 버튼 클릭
        subtraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num = 1;
                option = 2;
                textNum.setText(Integer.toString(num));
                wrongNum = new LinkedList<Integer>();
                Subtraction st = new Subtraction();
                quiz.setText(st.makeQuiz());
                answer = st.makeAnswer();
                optionLayout.setVisibility(View.INVISIBLE);
                firstLayout.setVisibility(View.VISIBLE);
                // 시간 측정
                startT = System.currentTimeMillis();
            }
        });


        //숫자 입력
        for (int i = 0; i < btn.length; i++) {

            int btnNum = i;
            btn[i].setOnClickListener(new View.OnClickListener() {

                @SuppressLint("SetTextI18n")
                @Override
                public void onClick(View view) {
                    if (textAnswer.getText().toString().length() > 7){
                        Toast.makeText(getApplicationContext(), "입력 값을 초과하였습니다..", Toast.LENGTH_SHORT).show();
                        textAnswer.setText("");
                    }else {
                        textAnswer.setText(textAnswer.getText().toString() + btnNum);
                    }
                }
            });
        }

        //숫자 지우기
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    textAnswer.setText(textAnswer.getText().toString().substring(0, textAnswer.getText().length()-1));
                }catch (Exception e){
                    textAnswer.setText("");
                }
            }
        });


        // 답 제출
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                try {
                    checkAnswer = Integer.parseInt(textAnswer.getText().toString());
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), "답을 입력해주세요.", Toast.LENGTH_SHORT).show();
                    textAnswer.setText("");
                }

                //답이 맞으면 토스트 정답입니다.
                if(answer == checkAnswer) {
                    Toast.makeText(getApplicationContext(), "정답입니다.", Toast.LENGTH_SHORT).show();
                    textAnswer.setText("");

                    //다음 번호
                    num = Integer.parseInt(textNum.getText().toString()) + 1;
                    
                    
                    //
                    //수학 퀴즈 종료 후 결과 출력
                    //
                    if(num == 11){

                        finishT = System.currentTimeMillis();
                        periodS = (int)(finishT - startT)/1000;
                        periodM = periodS/60;

                        min.setText(Integer.toString(periodM));
                        sec.setText(Integer.toString(periodS));


                        try {

                            //점수 계산
                            deleteDuplicationA(wrongNum);
                            int scoreInt = (10-wrongNum.size())*10;
                            score.setText(Integer.toString(scoreInt));
                            wrong.setText(Integer.toString(wrongNum.size()));
                            correct.setText(Integer.toString(10-wrongNum.size()));


                            //날짜 입력
                            LocalDate today = LocalDate.now();
                            LocalTime present = LocalTime.now();
                            tvYear.setText(Integer.toString(today.getYear()));
                            tvMonth.setText(Integer.toString(today.getMonthValue()));
                            tvDay.setText(Integer.toString(today.getDayOfMonth()));
                            tvHour.setText(Integer.toString(present.getHour()));
                            tvMinute.setText(Integer.toString(present.getMinute()));





                            //화면 전환
                            firstLayout.setVisibility(View.INVISIBLE);
                            lastLayout.setVisibility(View.VISIBLE);

                        }catch (Exception e){
                            e.printStackTrace();
                        }

                    }else {
                        //0 구구단, 1 덧셈, 2 뺄셈
                        if (option == 0){
                            textNum.setText(Integer.toString(num));
                            TimeTable tt = new TimeTable();
                            quiz.setText(tt.makeQuiz());
                            answer = tt.makeAnswer();
                        }
                        else if (option == 1){
                            textNum.setText(Integer.toString(num));
                            RandomQuestion RQ = new RandomQuestion();
                            quiz.setText(RQ.makeQuiz());
                            answer = RQ.makeAnswer();
                        }
                        else{
                            textNum.setText(Integer.toString(num));
                            Subtraction st = new Subtraction();
                            quiz.setText(st.makeQuiz());
                            answer = st.makeAnswer();
                        }

                        //다음 문제

                    }


                //틀리면 토스트 틀렸습니다.
                }else {
                    Toast.makeText(getApplicationContext(), "틀렸습니다.", Toast.LENGTH_SHORT).show();
                    wrongNum.add(num);
                    textAnswer.setText("");
                }



            }
        });

        //다시하기 클릭
        retry.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {

                //다 초기화하기
                optionLayout.setVisibility(View.VISIBLE);
                lastLayout.setVisibility(View.INVISIBLE);
//                num = 1;
//                textNum.setText(Integer.toString(num));
//                wrongNum = new LinkedList<Integer>();
//                startT = System.currentTimeMillis();
//
//                if (option == 0){
//                    TimeTable tt = new TimeTable();
//                    quiz.setText(tt.makeQuiz());
//                    answer = tt.makeAnswer();
//                }
//                else if (option == 1){
//                    RandomQuestion RQ = new RandomQuestion();
//                    quiz.setText(RQ.makeQuiz());
//                    answer = RQ.makeAnswer();
//                }
//                else{
//                    Subtraction st = new Subtraction();
//                    quiz.setText(st.makeQuiz());
//                    answer = st.makeAnswer();
//                }

            }
        });

        //종료하기 클릭
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public void onBackPressed(){
        super.onBackPressed();
    }


    static void deleteDuplicationA(List<Integer> list) {
        Set<Integer> set = new HashSet<>();
        Iterator<Integer> i;

        i = list.iterator();
        while(i.hasNext()) {
            set.add(i.next());
        }

        // == Set iterator (자동정렬) ==
        list.clear();
        i = set.iterator();
        while (i.hasNext()) {
            list.add(i.next());
        }
    }

}