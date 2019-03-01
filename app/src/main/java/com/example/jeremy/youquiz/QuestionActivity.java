package com.example.jeremy.youquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.ArrayList;

public class QuestionActivity extends AppCompatActivity {
    private static final String TAG = "QuestionActivity";
    ArrayList<Quiz> quizList;
    private TextView mQuestionTextView;
    private ImageButton mPrevButton, mNextButton;
    private boolean mAllAnswered = false;
    private double mScore = 0;
    private int mCurrentIndex = 0;
    private static final String KEY_INDEX = "index";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        Intent intent = getIntent();
        final ArrayList<Quiz> quiz = intent.getParcelableArrayListExtra("quiz");
        quizList = quiz;
        mQuestionTextView = findViewById(R.id.text_view);
        mPrevButton = findViewById(R.id.prev_button);
        mNextButton = findViewById(R.id.next_button);
        Log.d(TAG, "Starting QuestionActivity");

        if (savedInstanceState != null)
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);


        String question = (mCurrentIndex + 1) + ". " + quizList.get(mCurrentIndex).getQuestion();
        mQuestionTextView.setText(question);
        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % quizList.size();
                updateQuestion();
            }
        });

        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex - 1);
                if(mCurrentIndex < 0) mCurrentIndex = quiz.size() - 1;
                updateQuestion();
            }
        });

        mNextButton = findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % quiz.size();
                updateQuestion();
            }
        });
    }

    private void updateQuestion() {
        String question = (mCurrentIndex + 1) + ". " + quizList.get(mCurrentIndex).getQuestion();
        mQuestionTextView.setText(question);
    }
}
