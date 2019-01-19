package com.example.jeremy.youquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;

import java.util.ArrayList;

public class QuestionActivity extends AppCompatActivity {
    private ImageButton mPrevButton, mNextButton;
    private boolean mAllAnswered = false;
    private double mScore = 0;
    private static final String KEY_INDEX = "index";
    private int mCurrentIndex = 0;

    Intent intent = getIntent();
    ArrayList<Quiz> quiz = intent.getParcelableArrayListExtra("quiz");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        if (savedInstanceState != null)
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
    }
}
