package com.example.jeremy.youquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class QuestionActivity extends AppCompatActivity {
    Intent intent = getIntent();
    ArrayList<Quiz> quiz = intent.getParcelableArrayListExtra("quiz");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
    }
}
