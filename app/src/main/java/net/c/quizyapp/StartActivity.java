package net.c.quizyapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class StartActivity extends AppCompatActivity {

    int locationOfCurrentAnswer;
    int score = 0;
    int numberOfQuestions = 0;
    TextView resultTextView;
    TextView sumTextView;
    TextView button0;
    TextView button1;
    TextView button2;
    TextView button3;
    TextView scoreTextView;
    TextView mainScoreTV;
    TextView timerTextView;
    Button playAgainButton;
    ConstraintLayout resultScreen;
    ArrayList<Integer> answers = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        sumTextView = findViewById(R.id.sumTextView);
        button0 = findViewById(R.id.textView8);
        button1 = findViewById(R.id.textView10);
        button2 = findViewById(R.id.textView12);
        button3 = findViewById(R.id.textView14);
        resultTextView = findViewById(R.id.resultTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        mainScoreTV = findViewById(R.id.mainScoreTV);
        timerTextView = findViewById(R.id.timerTextView);
        playAgainButton = findViewById(R.id.playAgainButton);
        resultScreen = findViewById(R.id.resultScreen);

        playAgain(playAgainButton);

    }

    public void chooseAnswer(View view){
        if (Integer.toString(locationOfCurrentAnswer).equals(view.getTag().toString())){
            resultTextView.setText("Correct \uD83D\uDE0A");
            score++;
        }else{
            resultTextView.setText("Wrong \uD83D\uDE14");
        }
        numberOfQuestions++;
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
        newQuestion();

    }

    public void newQuestion(){

        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        sumTextView.setText("Ques: "+Integer.toString(a) + " x " + Integer.toString(b));

        locationOfCurrentAnswer = rand.nextInt(4);
        answers.clear();
        for (int i=0; i<4; i++){
            if (i == locationOfCurrentAnswer) {
                answers.add(a*b);
            }
            else {
                int wrongAnswer = rand.nextInt(401);

                while (wrongAnswer == a+b){
                    wrongAnswer = rand.nextInt(401);
                }
                answers.add(wrongAnswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }

    public void playAgain(View view){
        score = 0;
        numberOfQuestions = 0;
        timerTextView.setText("30s");
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
        newQuestion();
        resultTextView.setText("");
        resultScreen.setVisibility(View.GONE);

        new CountDownTimer(30100, 1000){

            @Override
            public void onTick(long l) {
                timerTextView.setText(String.valueOf(l / 1000) + "s");
            }

            @Override
            public void onFinish() {
                mainScoreTV.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
                resultScreen.setVisibility(View.VISIBLE);
            }
        }.start();
    }
}