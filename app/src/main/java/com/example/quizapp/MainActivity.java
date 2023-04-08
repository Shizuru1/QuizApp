package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }

        // Initialize elements
        EditText mEditText = findViewById(R.id.editTextTextPersonName);
        Button mButton= findViewById(R.id.button);
        sharedPref = getSharedPreferences("MY_PREF", Context.MODE_PRIVATE);
        String savedName = sharedPref.getString("userName", "");
        mEditText.setText(savedName);

        // Change Activity
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputValue = mEditText.getText().toString();
                if (!inputValue.equals("")) {
                    SharedPreferences.Editor prefEditor = sharedPref.edit();
                    prefEditor.putString("userName", inputValue);
                    prefEditor.apply();

                    Intent i = new Intent(MainActivity.this, SecondActivity.class);
                    // i.putExtra(inputValue, 1);
                    startActivity(i);
                }
            }
        });
    }
}