package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity
{
    Button   startButton;
    TextView resultTextView;
    TextView pointsTextView;
    Button   button0;
    Button   button1;
    Button   button2;
    Button   button3;
    TextView sumTextView;
    TextView timerTextView;
    Button   playAgainButton;
    ConstraintLayout constraintLayout;

    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestions = 0;
    boolean play=true;

    public void playAgain(View view)
    {
        play=true;
        generateQuestion();
        score = 0;
        numberOfQuestions = 0;
        timerTextView.setText("30s");
        pointsTextView.setText("0/0");
        resultTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);
        new CountDownTimer(30100, 1000)
        {
            @Override
            public void onTick(long l)
            {
                timerTextView.setText(l/1000 + "s");
            }
            @Override
            public void onFinish()
            {
                playAgainButton.setVisibility(View.VISIBLE);
                play=false;
                resultTextView.setText("Your score: " + score+"/" +numberOfQuestions);
            }
        }.start();
    }
    public void generateQuestion()
    {
        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);
        sumTextView.setText(a+" + "+b);
        locationOfCorrectAnswer=rand.nextInt(4);
        answers.clear();
        for (int i=0; i<4; i++)
        {
            if (i==locationOfCorrectAnswer)
                answers.add(a + b);
            else
            {
                int incorrectAnswer=rand.nextInt(41);

                while(incorrectAnswer==a+b)
                    incorrectAnswer = rand.nextInt(41);
                answers.add(incorrectAnswer);
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }
    public void chooseAnswer(View view)
    {
        if(play)
        {
            if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer)))
            {
                score++;
                resultTextView.setText("Correct!");
            }
            else
                resultTextView.setText("Wrong!");
            numberOfQuestions++;
            pointsTextView.setText(score + "/" + numberOfQuestions);
            generateQuestion();
        }
    }
    public void start(View view)
    {
        startButton.setVisibility(View.INVISIBLE);
        constraintLayout.setVisibility(ConstraintLayout.VISIBLE);
        playAgain(playAgainButton);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton     =findViewById(R.id.startButton);
        sumTextView     =findViewById(R.id.sumTextView);
        button0         =findViewById(R.id.button0);
        button1         =findViewById(R.id.button1);
        button2         =findViewById(R.id.button2);
        button3         =findViewById(R.id.button3);
        resultTextView  =findViewById(R.id.resultTextView);
        pointsTextView  =findViewById(R.id.pointsTextView);
        timerTextView   =findViewById(R.id.timerTextView);
        playAgainButton =findViewById(R.id.playAgainButton);
        constraintLayout=findViewById(R.id.constraintLayout);
    }
}
