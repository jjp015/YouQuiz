package com.example.jeremy.youquiz;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;

public class QuestionActivity extends AppCompatActivity {
    private static final String TAG = "QuestionActivity";
    ArrayList<Quiz> quizList;
    private String question;
    private int questionType;
    private TextView mQuestionTextView;
    private RelativeLayout mMultipleCheckQuiz, mMultipleRadioQuiz, mTrueFalseRadioQuiz;
    private TextInputLayout mTextShortAnswerLayoutQuiz;
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
        mTextShortAnswerLayoutQuiz = findViewById(R.id.short_answer_layout_quiz);
        mMultipleCheckQuiz = findViewById(R.id.multiple_check_quiz);
        mMultipleRadioQuiz = findViewById(R.id.multiple_radio_quiz);
        mTrueFalseRadioQuiz = findViewById(R.id.true_false_radio_quiz);
        mPrevButton = findViewById(R.id.prev_button);
        mNextButton = findViewById(R.id.next_button);
        Log.d(TAG, "Starting QuestionActivity");

        if (savedInstanceState != null)
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);

        updateQuestion();
        updateType();
        Log.d(TAG, "The number type is: " + questionType);

        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % quizList.size();
                updateQuestion();
                updateType();
            }
        });

        mPrevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex - 1);
                if(mCurrentIndex < 0) mCurrentIndex = quiz.size() - 1;
                updateQuestion();
                updateType();
            }
        });

        mNextButton = findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % quiz.size();
                updateQuestion();
                updateType();
            }
        });
    }

    private void updateQuestion() {
        question = (mCurrentIndex + 1) + ". " + quizList.get(mCurrentIndex).getQuestion();
        mQuestionTextView.setText(question);
    }

    private void updateType() {
        questionType = quizList.get(mCurrentIndex).getType();
        switch(questionType) {
            case 0: //Multiple Choice
                mTextShortAnswerLayoutQuiz.setVisibility(View.GONE);
                mMultipleRadioQuiz.setVisibility(View.VISIBLE);
                mTrueFalseRadioQuiz.setVisibility(View.GONE);
                mMultipleCheckQuiz.setVisibility(View.GONE);
                break;
            case 1: //Multiple Answer Choices
                mTextShortAnswerLayoutQuiz.setVisibility(View.GONE);
                mMultipleRadioQuiz.setVisibility(View.GONE);
                mTrueFalseRadioQuiz.setVisibility(View.GONE);
                mMultipleCheckQuiz.setVisibility(View.VISIBLE);
                break;
            case 2: //True/False
                mTextShortAnswerLayoutQuiz.setVisibility(View.GONE);
                mMultipleRadioQuiz.setVisibility(View.GONE);
                mTrueFalseRadioQuiz.setVisibility(View.VISIBLE);
                mMultipleCheckQuiz.setVisibility(View.GONE);
                break;
            case 3: //Short Answer
                mTextShortAnswerLayoutQuiz.setVisibility(View.VISIBLE);
                mMultipleRadioQuiz.setVisibility(View.GONE);
                mTrueFalseRadioQuiz.setVisibility(View.GONE);
                mMultipleCheckQuiz.setVisibility(View.GONE);
                break;
        }
    }
}
