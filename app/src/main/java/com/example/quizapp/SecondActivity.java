package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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

    @SuppressLint("DefaultLocale")
    private String roundDecimal(double input) {
        String result;
        if ((int) input == input) {
            result = String.format("%.0f", input);
        } else if ((int) (10 * input) == 10 * input) {
            result = String.format("%.1f", input);
        } else if ((int) (100 * input) == 100 * input) {
            result = String.format("%.2f", input);
        } else {
            result = String.format("%.3f", input);
        }
        return result;
    }

    private void randomizeButtons(Button button1, Button button2, Button button3, String string1, String string2, String string3, int perm) {
        switch (perm) {
            case 0:
                button1.setText(string1);
                button2.setText(string2);
                button3.setText(string3);
                break;
            case 1:
                button1.setText(string1);
                button2.setText(string3);
                button3.setText(string2);
                break;
            case 2:
                button1.setText(string2);
                button2.setText(string1);
                button3.setText(string3);
                break;
            case 3:
                button1.setText(string2);
                button2.setText(string3);
                button3.setText(string1);
                break;
            case 4:
                button1.setText(string3);
                button2.setText(string1);
                button3.setText(string2);
                break;
            case 5:
                button1.setText(string3);
                button2.setText(string2);
                button1.setText(string1);
                break;
        }
    }

    int qNumber, randInt1, randInt2, randInt3, randInt4, randInt5, answer, fakeAnswer1, fakeAnswer2, fakeAnswer3, score;
    String operator, answerStr, fakeAnsStr, fakeAnsStr2, fakeAnsStr3;
    Boolean answerCheck1 = false, answerCheck2 = false, answerCheck3 = false, answerCheck4 = false;

    @SuppressLint("SetTextI18n")
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
        randInt2 = rand.nextInt(9) + 1;
        randInt3 = rand.nextInt(4);
        randInt4 = rand.nextInt(4);
        randInt5 = rand.nextInt(6);
        score = 0;
        qNumber = 1;
        String qText = "Question " + qNumber;
        qTextView.setText(qText);

        // generate question and answers
        switch (randInt3) {
            case 0:
                operator = "sum of ";
                answer = randInt1 + randInt2;
                if (randInt1 == 2 && randInt2 == 2) {
                    fakeAnswer1 = 5;
                    fakeAnswer3 = 8;
                } else {
                    fakeAnswer1 = randInt1 * randInt2;
                    if (randInt1 != randInt2 && randInt1 != 2) {
                        fakeAnswer3 = randInt2 * 2;
                    } else {
                        fakeAnswer3 = randInt1 + 2;
                    }
                }
                if (randInt1 != 0) {
                    fakeAnswer2 = 10 * randInt1 + randInt2;
                } else {
                    fakeAnswer2 = 10 * randInt2 + randInt1;
                }
                break;
            case 1:
                operator = "difference between ";
                if (randInt1 > randInt2) {
                    answer = randInt1 - randInt2;
                    fakeAnswer1 = randInt2 - randInt1;
                } else {
                    answer = randInt2 - randInt1;
                    if (randInt1 != randInt2) {
                        fakeAnswer1 = randInt1 - randInt2;
                    } else {
                        fakeAnswer1 = -randInt1;
                    }
                }
                if (randInt1 != 0) {
                    fakeAnswer2 = randInt1 + randInt2;
                } else {
                    if (randInt2 != 1) {
                        fakeAnswer2 = 1;
                    } else {
                        fakeAnswer2 = 2;
                    }
                }
                if (randInt1 != 2 * randInt2) {
                    // divide by 10 later
                    fakeAnswer3 = randInt1 * 5;
                } else {
                    fakeAnswer3 = randInt1;
                }
                break;
            case 2:
                operator = "product of ";
                answer = randInt1 * randInt2;
                if (randInt1 == 2 && randInt2 == 2) {
                    fakeAnswer1 = 5;
                } else {
                    fakeAnswer1 = randInt1 + randInt2;
                }
                if (randInt1 != randInt2) {
                    if (randInt1 != 0) {
                        fakeAnswer2 = randInt1 * randInt1;
                    } else {
                        fakeAnswer2 = -randInt2;
                    }
                    if (randInt2 != 1) {
                        fakeAnswer3 = randInt2 * randInt2;
                    } else {
                        fakeAnswer3 = randInt2 * 2;
                    }
                } else {
                    if (randInt1 != 3) {
                        fakeAnswer2 = answer - randInt1;
                    } else {
                        fakeAnswer2 = randInt1;
                    }
                    if (randInt1 != 1) {
                        fakeAnswer3 = answer + randInt1;
                    } else {
                        fakeAnswer3 = 10 * randInt1 + randInt2;
                    }
                }
                break;
            case 3:
                operator = "quotient of ";
                // divide by 1000 later
                answer = (1000 * randInt1) / randInt2;
                if (randInt1 == 4 && randInt2 == 2) {
                    fakeAnswer1 = randInt1;
                } else {
                    fakeAnswer1 = randInt1 - randInt2;
                }
                if (randInt1 != randInt2) {
                    if (randInt1 != 0) {
                        fakeAnswer2 = (1000 * randInt2) / randInt1;
                    } else {
                        fakeAnswer2 = (1000 * randInt2) / 13;
                    }
                } else {
                    if (randInt1 != 1) {
                        fakeAnswer2 = randInt1;
                    } else {
                        fakeAnswer2 = -1;
                    }
                }
                if (randInt1 == 5 && (randInt2 == 2 || randInt2 == 3)) {
                    fakeAnswer3 = (1000 * (randInt1 - 1)) / randInt2;
                } else {
                    fakeAnswer3 = (1000 * (randInt1 + 1)) / randInt2;
                }
                break;
        }
        if (randInt3 != 3) {
            answerStr = String.valueOf(answer);
        } else {
            answerStr = roundDecimal((double) answer / 1000.0);
        }
        fakeAnsStr = String.valueOf(fakeAnswer1);
        if (randInt3 == 3 && randInt1 != randInt2) {
            fakeAnsStr2 = roundDecimal((double) fakeAnswer2 / 1000.0);
        } else {
            fakeAnsStr2 = String.valueOf(fakeAnswer2);
        }
        if (randInt3 == 1 && randInt1 != 2 * randInt2) {
            fakeAnsStr3 = roundDecimal((double) fakeAnswer3 / 10.0);
        } else if (randInt3 == 3) {
            fakeAnsStr3 = roundDecimal((double) fakeAnswer3 / 1000.0);
        } else {
            fakeAnsStr3 = String.valueOf(fakeAnswer3);
        }
        switch (randInt4) {
            case 0:
                ans1.setText(answerStr);
                randomizeButtons(ans2, ans3, ans4, fakeAnsStr, fakeAnsStr2, fakeAnsStr3, randInt5);
                answerCheck1 = true;
                break;
            case 1:
                ans2.setText(answerStr);
                randomizeButtons(ans1, ans3, ans4, fakeAnsStr, fakeAnsStr2, fakeAnsStr3, randInt5);
                answerCheck2 = true;
                break;
            case 2:
                ans3.setText(answerStr);
                randomizeButtons(ans1, ans2, ans4, fakeAnsStr, fakeAnsStr2, fakeAnsStr3, randInt5);
                answerCheck3 = true;
                break;
            case 3:
                ans4.setText(answerStr);
                randomizeButtons(ans1, ans2, ans3, fakeAnsStr, fakeAnsStr2, fakeAnsStr3, randInt5);
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
                    i.putExtra("score", score);
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
                    randInt2 = rand.nextInt(9) + 1;
                    randInt3 = rand.nextInt(4);
                    randInt4 = rand.nextInt(4);
                    randInt5 = rand.nextInt(6);
                    switch (randInt3) {
                        case 0:
                            operator = "sum of ";
                            answer = randInt1 + randInt2;
                            if (randInt1 == 2 && randInt2 == 2) {
                                fakeAnswer1 = 5;
                                fakeAnswer3 = 8;
                            } else {
                                fakeAnswer1 = randInt1 * randInt2;
                                if (randInt1 != randInt2 && randInt1 != 2) {
                                    fakeAnswer3 = randInt2 * 2;
                                } else {
                                    fakeAnswer3 = randInt1 + 2;
                                }
                            }
                            if (randInt1 != 0) {
                                fakeAnswer2 = 10 * randInt1 + randInt2;
                            } else {
                                fakeAnswer2 = 10 * randInt2 + randInt1;
                            }
                            break;
                        case 1:
                            operator = "difference between ";
                            if (randInt1 > randInt2) {
                                answer = randInt1 - randInt2;
                                fakeAnswer1 = randInt2 - randInt1;
                            } else {
                                answer = randInt2 - randInt1;
                                if (randInt1 != randInt2) {
                                    fakeAnswer1 = randInt1 - randInt2;
                                } else {
                                    fakeAnswer1 = -randInt1;
                                }
                            }
                            if (randInt1 != 0) {
                                fakeAnswer2 = randInt1 + randInt2;
                            } else {
                                if (randInt2 != 1) {
                                    fakeAnswer2 = 1;
                                } else {
                                    fakeAnswer2 = 2;
                                }
                            }
                            if (randInt1 != 2 * randInt2) {
                                // divide by 10 later
                                fakeAnswer3 = randInt1 * 5;
                            } else {
                                fakeAnswer3 = randInt1;
                            }
                            break;
                        case 2:
                            operator = "product of ";
                            answer = randInt1 * randInt2;
                            if (randInt1 == 2 && randInt2 == 2) {
                                fakeAnswer1 = 5;
                            } else {
                                fakeAnswer1 = randInt1 + randInt2;
                            }
                            if (randInt1 != randInt2) {
                                if (randInt1 != 0) {
                                    fakeAnswer2 = randInt1 * randInt1;
                                } else {
                                    fakeAnswer2 = -randInt2;
                                }
                                if (randInt2 != 1) {
                                    fakeAnswer3 = randInt2 * randInt2;
                                } else {
                                    fakeAnswer3 = randInt2 * 2;
                                }
                            } else {
                                if (randInt1 != 3) {
                                    fakeAnswer2 = answer - randInt1;
                                } else {
                                    fakeAnswer2 = randInt1;
                                }
                                if (randInt1 != 1) {
                                    fakeAnswer3 = answer + randInt1;
                                } else {
                                    fakeAnswer3 = 10 * randInt1 + randInt2;
                                }
                            }
                            break;
                        case 3:
                            operator = "quotient of ";
                            // divide by 1000 later
                            answer = (1000 * randInt1) / randInt2;
                            if (randInt1 == 4 && randInt2 == 2) {
                                fakeAnswer1 = randInt1;
                            } else {
                                fakeAnswer1 = randInt1 - randInt2;
                            }
                            if (randInt1 != randInt2) {
                                if (randInt1 != 0) {
                                    fakeAnswer2 = (1000 * randInt2) / randInt1;
                                } else {
                                    fakeAnswer2 = (1000 * randInt2) / 13;
                                }
                            } else {
                                if (randInt1 != 1) {
                                    fakeAnswer2 = randInt1;
                                } else {
                                    fakeAnswer2 = -1;
                                }
                            }
                            if (randInt1 == 5 && (randInt2 == 2 || randInt2 == 3)) {
                                fakeAnswer3 = (1000 * (randInt1 - 1)) / randInt2;
                            } else {
                                fakeAnswer3 = (1000 * (randInt1 + 1)) / randInt2;
                            }
                            break;
                    }
                    if (randInt3 != 3) {
                        answerStr = String.valueOf(answer);
                    } else {
                        answerStr = roundDecimal((double) answer / 1000.0);
                    }
                    fakeAnsStr = String.valueOf(fakeAnswer1);
                    if (randInt3 == 3 && randInt1 != randInt2) {
                        fakeAnsStr2 = roundDecimal((double) fakeAnswer2 / 1000.0);
                    } else {
                        fakeAnsStr2 = String.valueOf(fakeAnswer2);
                    }
                    if (randInt3 == 1 && randInt1 != 2 * randInt2) {
                        fakeAnsStr3 = roundDecimal((double) fakeAnswer3 / 10.0);
                    } else if (randInt3 == 3) {
                        fakeAnsStr3 = roundDecimal((double) fakeAnswer3 / 1000.0);
                    } else {
                        fakeAnsStr3 = String.valueOf(fakeAnswer3);
                    }
                    switch (randInt4) {
                        case 0:
                            ans1.setText(answerStr);
                            randomizeButtons(ans2, ans3, ans4, fakeAnsStr, fakeAnsStr2, fakeAnsStr3, randInt5);
                            answerCheck1 = true;
                            break;
                        case 1:
                            ans2.setText(answerStr);
                            randomizeButtons(ans1, ans3, ans4, fakeAnsStr, fakeAnsStr2, fakeAnsStr3, randInt5);
                            answerCheck2 = true;
                            break;
                        case 2:
                            ans3.setText(answerStr);
                            randomizeButtons(ans1, ans2, ans4, fakeAnsStr, fakeAnsStr2, fakeAnsStr3, randInt5);
                            answerCheck3 = true;
                            break;
                        case 3:
                            ans4.setText(answerStr);
                            randomizeButtons(ans1, ans2, ans3, fakeAnsStr, fakeAnsStr2, fakeAnsStr3, randInt5);
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
                    score += 1;
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
                    score += 1;
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
                    score += 1;
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
                    score += 1;
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