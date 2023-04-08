package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EndActivity extends AppCompatActivity {

    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        // Initialize elements
        TextView congratsText = findViewById(R.id.textView3);
        TextView scoreText = findViewById(R.id.textView5);
        Button changeActivity = findViewById(R.id.button7);
        Button finishButton = findViewById(R.id.button8);
        sharedPref = getSharedPreferences("MY_PREF", Context.MODE_PRIVATE);
        String savedName = sharedPref.getString("userName", "");
        String congrats = "Congratulations " + savedName + "!";
        int score = getIntent().getIntExtra("score", 0);
        String scores = score + " / 20";
        congratsText.setText(congrats);
        scoreText.setText(scores);

        // Change Activity
        changeActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(EndActivity.this, MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // End Activity and application
                Intent i = new Intent(EndActivity.this, MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.putExtra("EXIT", true);
                startActivity(i);
            }
        });
    }
}