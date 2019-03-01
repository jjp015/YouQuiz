package com.example.jeremy.youquiz;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class QuestionActivity extends AppCompatActivity {
    private static final String TAG = "QuestionActivity";
    ArrayList<Quiz> quizList;
    ArrayList<Integer> result;
    private String question;
    private String answerChoice = "";
    private int questionType;
    private TextView mQuestionTextView;
    private RelativeLayout mMultipleCheckQuiz, mMultipleRadioQuiz, mTrueFalseRadioQuiz;
    private TextInputLayout mTextShortAnswerLayoutQuiz;
    private RadioGroup mRadioGroupMultipleQuiz, mRadioGroupTrueFalseQuiz;
    private RadioButton mRadioAquiz, mRadioBquiz, mRadioCquiz, mRadioDquiz, mRadioTrueQuiz,
            mRadioFalseQuiz;
    private CheckBox mCheckBoxAquiz, mCheckBoxBquiz, mCheckBoxCquiz, mCheckBoxDquiz;
    private ImageButton mPrevButton, mNextButton;
    private Button mBackButtonQuiz, mSubmitButtonQuiz;
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
        mTrueFalseRadioQuiz = findViewById(R.id.true_false_radio_quiz);
        mPrevButton = findViewById(R.id.prev_button);
        mNextButton = findViewById(R.id.next_button);

        mMultipleRadioQuiz = findViewById(R.id.multiple_radio_quiz);
        mRadioAquiz = findViewById(R.id.radio_a_quiz);
        mRadioBquiz = findViewById(R.id.radio_b_quiz);
        mRadioCquiz = findViewById(R.id.radio_c_quiz);
        mRadioDquiz = findViewById(R.id.radio_d_quiz);
        mCheckBoxAquiz = findViewById(R.id.check_a_quiz);
        mCheckBoxBquiz = findViewById(R.id.check_b_quiz);
        mCheckBoxCquiz = findViewById(R.id.check_c_quiz);
        mCheckBoxDquiz = findViewById(R.id.check_d_quiz);
        mRadioTrueQuiz = findViewById(R.id.radio_true);
        mRadioFalseQuiz = findViewById(R.id.radio_false);

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

        mBackButtonQuiz = findViewById(R.id.back_button2);
        mBackButtonQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mSubmitButtonQuiz = findViewById(R.id.submit_button_quiz);
        mSubmitButtonQuiz.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Context context = getApplicationContext();
                switch(questionType) {
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                }
            }
        });
    }

    private void updateQuestion() {
        question = (mCurrentIndex + 1) + ". " + quizList.get(mCurrentIndex).getQuestion();
        mQuestionTextView.setText(question);
    }

    private void updateType() {
        questionType = quizList.get(mCurrentIndex).getType();
        int counter = 0;
        switch(questionType) {
            case 0: //Multiple Choice
                counter = 0;
                answerChoice = "A. ";
                for(int i = 0; i < quizList.get(mCurrentIndex).getAnswerChoice().length(); i++) {
                    if(quizList.get(mCurrentIndex).getAnswerChoice().charAt(i) == '`') break;
                    else {
                        answerChoice = answerChoice +
                                quizList.get(mCurrentIndex).getAnswerChoice().charAt(counter);
                        counter++;
                    }
                }
                counter++;
                mRadioAquiz.setText(answerChoice);
                answerChoice = "B. ";

                for(int i = 0; i < quizList.get(mCurrentIndex).getAnswerChoice().length(); i++) {
                    if(quizList.get(mCurrentIndex).getAnswerChoice().charAt(counter) == '`') break;
                    else {
                        answerChoice = answerChoice +
                                quizList.get(mCurrentIndex).getAnswerChoice().charAt(counter);
                        counter++;
                    }
                }
                counter++;
                mRadioBquiz.setText(answerChoice);
                answerChoice = "C. ";

                for(int i = 0; i < quizList.get(mCurrentIndex).getAnswerChoice().length(); i++) {
                    if(quizList.get(mCurrentIndex).getAnswerChoice().charAt(counter) == '`') break;
                    else {
                        answerChoice = answerChoice +
                                quizList.get(mCurrentIndex).getAnswerChoice().charAt(counter);
                        counter++;
                    }
                }
                counter++;
                mRadioCquiz.setText(answerChoice);
                answerChoice = "D. ";

                for(int i = 0; i < quizList.get(mCurrentIndex).getAnswerChoice().length(); i++) {
                    if(quizList.get(mCurrentIndex).getAnswerChoice().charAt(counter) == '`') break;
                    else {
                        answerChoice = answerChoice +
                                quizList.get(mCurrentIndex).getAnswerChoice().charAt(counter);
                        counter++;
                    }
                }
                mRadioDquiz.setText(answerChoice);

                mTextShortAnswerLayoutQuiz.setVisibility(View.GONE);
                mMultipleRadioQuiz.setVisibility(View.VISIBLE);
                mTrueFalseRadioQuiz.setVisibility(View.GONE);
                mMultipleCheckQuiz.setVisibility(View.GONE);
                break;
            case 1: //Multiple Answer Choices
                counter = 0;
                answerChoice = "A. ";
                for(int i = 0; i < quizList.get(mCurrentIndex).getAnswerChoice().length(); i++) {
                    if(quizList.get(mCurrentIndex).getAnswerChoice().charAt(i) == '`') break;
                    else {
                        answerChoice = answerChoice +
                                quizList.get(mCurrentIndex).getAnswerChoice().charAt(counter);
                        counter++;
                    }
                }
                counter++;
                mCheckBoxAquiz.setText(answerChoice);
                answerChoice = "B. ";

                for(int i = 0; i < quizList.get(mCurrentIndex).getAnswerChoice().length(); i++) {
                    if(quizList.get(mCurrentIndex).getAnswerChoice().charAt(counter) == '`') break;
                    else {
                        answerChoice = answerChoice +
                                quizList.get(mCurrentIndex).getAnswerChoice().charAt(counter);
                        counter++;
                    }
                }
                counter++;
                mCheckBoxBquiz.setText(answerChoice);
                answerChoice = "C. ";

                for(int i = 0; i < quizList.get(mCurrentIndex).getAnswerChoice().length(); i++) {
                    if(quizList.get(mCurrentIndex).getAnswerChoice().charAt(counter) == '`') break;
                    else {
                        answerChoice = answerChoice +
                                quizList.get(mCurrentIndex).getAnswerChoice().charAt(counter);
                        counter++;
                    }
                }
                counter++;
                mCheckBoxCquiz.setText(answerChoice);
                answerChoice = "D. ";

                for(int i = 0; i < quizList.get(mCurrentIndex).getAnswerChoice().length(); i++) {
                    if(quizList.get(mCurrentIndex).getAnswerChoice().charAt(counter) == '`') break;
                    else {
                        answerChoice = answerChoice +
                                quizList.get(mCurrentIndex).getAnswerChoice().charAt(counter);
                        counter++;
                    }
                }
                mCheckBoxDquiz.setText(answerChoice);

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
