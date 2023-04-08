package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

public class SecondActivity extends AppCompatActivity {

    int qNumber, randInt1, randInt2, randInt3, randInt4, answer;
    String operator;
    Boolean answerCheck1 = false, answerCheck2 = false, answerCheck3 = false, answerCheck4 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Initialize elements
        TextView sTextView = findViewById(R.id.textView);
        TextView qTextView = findViewById(R.id.textView2);
        Button ans1 = findViewById(R.id.button2);
        Button ans2 = findViewById(R.id.button3);
        Button ans3 = findViewById(R.id.button4);
        Button ans4 = findViewById(R.id.button6);
        Button nextButton = findViewById(R.id.button5);
        ProgressBar progressBar = findViewById(R.id.progressBar);
        Random rand = new Random();
        randInt1 = rand.nextInt(10);
        randInt2 = rand.nextInt(10) + 1;
        randInt3 = rand.nextInt(4);
        randInt4 = rand.nextInt(4);
        qNumber = 1;
        String qText = "Question " + qNumber;
        qTextView.setText(qText);
        switch (randInt3) {
            case 0:
                operator = "sum of ";
                answer = randInt1 + randInt2;
                break;
            case 1:
                operator = "difference between ";
                if (randInt1 > randInt2) {
                    answer = randInt1 - randInt2;
                } else {
                    answer = randInt2 - randInt1;
                }
                break;
            case 2:
                operator = "product of ";
                answer = randInt1 * randInt2;
                break;
            case 3:
                operator = "quotient of ";
                answer = randInt1 / randInt2;
                break;
        }
        switch (randInt4) {
            case 0:
                ans1.setText(Integer.toString(answer));
                answerCheck1 = true;
                break;
            case 1:
                ans2.setText(Integer.toString(answer));
                answerCheck2 = true;
                break;
            case 2:
                ans3.setText(Integer.toString(answer));
                answerCheck3 = true;
                break;
            case 3:
                ans4.setText(Integer.toString(answer));
                answerCheck4 = true;
                break;
        }
        String sText = "What is the " + operator + randInt1 + " and " + randInt2;
        sTextView.setText(sText);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Next Question
                if (qNumber >= 20) {
                    Intent i = new Intent(SecondActivity.this, EndActivity.class);
                    startActivity(i);
                } else {
                    ColorStateList resetColor = ColorStateList.valueOf(Color.parseColor("#FFEEEEEE"));
                    ans1.setBackgroundTintList(resetColor);
                    ans2.setBackgroundTintList(resetColor);
                    ans3.setBackgroundTintList(resetColor);
                    ans4.setBackgroundTintList(resetColor);
                    ans1.setClickable(true);
                    ans2.setClickable(true);
                    ans3.setClickable(true);
                    ans4.setClickable(true);
                    nextButton.setClickable(false);
                    qNumber += 1;
                    String qText = "Question " + qNumber;
                    qTextView.setText(qText);
                    progressBar.setProgress(progressBar.getProgress() + 1);
                    randInt1 = rand.nextInt(10);
                    randInt2 = rand.nextInt(10) + 1;
                    randInt3 = rand.nextInt(4);
                    randInt4 = rand.nextInt(4);
                    switch (randInt3) {
                        case 0:
                            operator = "sum of ";
                            answer = randInt1 + randInt2;
                            break;
                        case 1:
                            operator = "difference between ";
                            if (randInt1 > randInt2) {
                                answer = randInt1 - randInt2;
                            } else {
                                answer = randInt2 - randInt1;
                            }
                            break;
                        case 2:
                            operator = "product of ";
                            answer = randInt1 * randInt2;
                            break;
                        case 3:
                            operator = "quotient of ";
                            answer = randInt1 / randInt2;
                            break;
                    }
                    switch (randInt4) {
                        case 0:
                            ans1.setText(Integer.toString(answer));
                            answerCheck1 = true;
                            break;
                        case 1:
                            ans2.setText(Integer.toString(answer));
                            answerCheck2 = true;
                            break;
                        case 2:
                            ans3.setText(Integer.toString(answer));
                            answerCheck3 = true;
                            break;
                        case 3:
                            ans4.setText(Integer.toString(answer));
                            answerCheck4 = true;
                            break;
                    }
                    String sText = "What is the " + operator + randInt1 + " and " + randInt2;
                    sTextView.setText(sText);
                }
            }
        });

        nextButton.setClickable(false);

        ans1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // First Answer
                ColorStateList green = ColorStateList.valueOf(Color.GREEN);
                ColorStateList red = ColorStateList.valueOf(Color.RED);
                if (answerCheck1) {
                    // correct
                    ans1.setBackgroundTintList(green);
                    answerCheck1 = false;
                } else {
                    // incorrect
                    ans1.setBackgroundTintList(red);
                    if (answerCheck2) {
                        ans2.setBackgroundTintList(green);
                        answerCheck2 = false;
                    } else if (answerCheck3) {
                        ans3.setBackgroundTintList(green);
                        answerCheck3 = false;
                    } else if (answerCheck4) {
                        ans4.setBackgroundTintList(green);
                        answerCheck4 = false;
                    }
                }
                ans1.setClickable(false);
                ans2.setClickable(false);
                ans3.setClickable(false);
                ans4.setClickable(false);
                nextButton.setClickable(true);
            }
        });

        ans2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Second Answer
                ColorStateList green = ColorStateList.valueOf(Color.GREEN);
                ColorStateList red = ColorStateList.valueOf(Color.RED);
                if (answerCheck2) {
                    // correct
                    ans2.setBackgroundTintList(green);
                    answerCheck2 = false;
                } else {
                    // incorrect
                    ans2.setBackgroundTintList(red);
                    if (answerCheck1) {
                        ans1.setBackgroundTintList(green);
                        answerCheck1 = false;
                    } else if (answerCheck3) {
                        ans3.setBackgroundTintList(green);
                        answerCheck3 = false;
                    } else if (answerCheck4) {
                        ans4.setBackgroundTintList(green);
                        answerCheck4 = false;
                    }
                }
                ans1.setClickable(false);
                ans2.setClickable(false);
                ans3.setClickable(false);
                ans4.setClickable(false);
                nextButton.setClickable(true);
            }
        });

        ans3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Third Answer
                ColorStateList green = ColorStateList.valueOf(Color.GREEN);
                ColorStateList red = ColorStateList.valueOf(Color.RED);
                if (answerCheck3) {
                    // correct
                    ans3.setBackgroundTintList(green);
                    answerCheck3 = false;
                } else {
                    // incorrect
                    ans3.setBackgroundTintList(red);
                    if (answerCheck1) {
                        ans1.setBackgroundTintList(green);
                        answerCheck1 = false;
                    } else if (answerCheck2) {
                        ans2.setBackgroundTintList(green);
                        answerCheck2 = false;
                    } else if (answerCheck4) {
                        ans4.setBackgroundTintList(green);
                        answerCheck4 = false;
                    }
                }
                ans1.setClickable(false);
                ans2.setClickable(false);
                ans3.setClickable(false);
                ans4.setClickable(false);
                nextButton.setClickable(true);
            }
        });

        ans4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Fourth Answer
                ColorStateList green = ColorStateList.valueOf(Color.GREEN);
                ColorStateList red = ColorStateList.valueOf(Color.RED);
                if (answerCheck4) {
                    // correct
                    ans4.setBackgroundTintList(green);
                    answerCheck4 = false;
                } else {
                    // incorrect
                    ans4.setBackgroundTintList(red);
                    if (answerCheck1) {
                        ans1.setBackgroundTintList(green);
                        answerCheck1 = false;
                    } else if (answerCheck2) {
                        ans2.setBackgroundTintList(green);
                        answerCheck2 = false;
                    } else if (answerCheck3) {
                        ans3.setBackgroundTintList(green);
                        answerCheck3 = false;
                    }
                }
                ans1.setClickable(false);
                ans2.setClickable(false);
                ans3.setClickable(false);
                ans4.setClickable(false);
                nextButton.setClickable(true);
            }
        });
    }
}