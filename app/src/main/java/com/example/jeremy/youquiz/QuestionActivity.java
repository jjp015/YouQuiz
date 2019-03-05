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
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class QuestionActivity extends AppCompatActivity {
    private static final String TAG = "QuestionActivity";
    private ArrayList<Quiz> quizList;
    private boolean checkAnswered[];
    private String question;
    private String answerChoice = "";
    private String answerSubmit = "";
    private int questionType;
    private TextView mQuestionTextView;
    private RelativeLayout mMultipleCheckQuiz, mMultipleRadioQuiz, mTrueFalseRadioQuiz;
    private TextInputLayout mTextShortAnswerLayoutQuiz;
    private RadioGroup mRadioGroupTrueFalseQuiz;
    private RadioButton mRadioAquiz, mRadioBquiz, mRadioCquiz, mRadioDquiz, mRadioTrueQuiz,
            mRadioFalseQuiz;
    private CheckBox mCheckBoxAquiz, mCheckBoxBquiz, mCheckBoxCquiz, mCheckBoxDquiz;
    private EditText mShortAnswerQuiz;
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
        checkAnswered = new boolean[quizList.size()];
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

        for(int i = 0; i < checkAnswered.length; i++) {
            Log.d(TAG, "Boolean value: " + checkAnswered[i]);
        }

        mRadioAquiz.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mRadioBquiz.setChecked(false);
                    mRadioCquiz.setChecked(false);
                    mRadioDquiz.setChecked(false);
                }
            }
        });

        mRadioBquiz.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mRadioAquiz.setChecked(false);
                    mRadioCquiz.setChecked(false);
                    mRadioDquiz.setChecked(false);
                }
            }
        });

        mRadioCquiz.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mRadioAquiz.setChecked(false);
                    mRadioBquiz.setChecked(false);
                    mRadioDquiz.setChecked(false);
                }
            }
        });

        mRadioDquiz.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mRadioAquiz.setChecked(false);
                    mRadioBquiz.setChecked(false);
                    mRadioCquiz.setChecked(false);
                }
            }
        });

        if (savedInstanceState != null)
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);

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
                Toast toast;
                Context context = getApplicationContext();
                switch(questionType) {
                    case 0: //Multiple Choice
                        if(mRadioAquiz.isChecked()) {
                            submitDisable();
                            multipleButtonDisable();
                            answerSubmit = mRadioAquiz.getText().toString();
                            if(answerSubmit.equals(quizList.get(mCurrentIndex).getAnswer())) {
                                mScore++;
                                toast = Toast.makeText(context, "Correct", Toast.LENGTH_LONG);
                            }
                            else toast = Toast.makeText(context, "Incorrect",
                                    Toast.LENGTH_LONG);
                        }
                        else if(mRadioBquiz.isChecked()) {
                            submitDisable();
                            multipleButtonDisable();
                            answerSubmit = mRadioBquiz.getText().toString();
                            if(answerSubmit.equals(quizList.get(mCurrentIndex).getAnswer())) {
                                mScore++;
                                toast = Toast.makeText(context, "Correct", Toast.LENGTH_LONG);
                            }
                            else toast = Toast.makeText(context, "Incorrect",
                                    Toast.LENGTH_LONG);
                        }
                        else if(mRadioCquiz.isChecked()) {
                            submitDisable();
                            multipleButtonDisable();
                            answerSubmit.equals(mRadioCquiz.getText().toString());
                            if(answerSubmit.equals(quizList.get(mCurrentIndex).getAnswer())) {
                                mScore++;
                                toast = Toast.makeText(context, "Correct", Toast.LENGTH_LONG);
                            }
                            else toast = Toast.makeText(context, "Incorrect",
                                    Toast.LENGTH_LONG);
                        }
                        else if(mRadioDquiz.isChecked()) {
                            submitDisable();
                            multipleButtonDisable();
                            answerSubmit.equals(mRadioDquiz.getText().toString());
                            if(answerSubmit.equals(quizList.get(mCurrentIndex).getAnswer())) {
                                mScore++;
                                toast = Toast.makeText(context, "Correct", Toast.LENGTH_LONG);
                            }
                            else toast = Toast.makeText(context, "Incorrect",
                                    Toast.LENGTH_LONG);
                        }
                        else {
                            toast = Toast.makeText(context, "Select Answer!",
                                    Toast.LENGTH_SHORT);
                        }
                        toast.show();
                        break;
                    case 1: //Multiple Answer Choices
                        answerSubmit = "";
                        if(mCheckBoxAquiz.isChecked()) {
                            answerSubmit += mCheckBoxAquiz.getText().toString() + "\n";
                        }
                        if(mCheckBoxBquiz.isChecked()) {
                            answerSubmit +=  mCheckBoxBquiz.getText().toString() + "\n";
                        }
                        if(mCheckBoxCquiz.isChecked()) {
                            answerSubmit +=  mCheckBoxCquiz.getText().toString() + "\n";
                        }
                        if(mCheckBoxDquiz.isChecked()) {
                            answerSubmit +=  mCheckBoxDquiz.getText().toString() + "\n";
                        }
                        if(answerSubmit.length() <= 0) {
                            toast = Toast.makeText(context, "Select Answer!",
                                    Toast.LENGTH_SHORT);
                            toast.show();
                        }
                        Log.d(TAG, answerSubmit);
                        Log.d(TAG, quiz.get(mCurrentIndex).getAnswer());
                        if(answerSubmit.equals(quiz.get(mCurrentIndex).getAnswer())) {
                            mScore++;
                            toast = Toast.makeText(context, "Correct", Toast.LENGTH_LONG);
                        }
                        else {
                            toast = Toast.makeText(context, "Incorrect", Toast.LENGTH_LONG);
                        }
                        if(answerSubmit.length() > 0) {
                            submitDisable();
                            multipleButtonDisable();
                        }
                        toast.show();
                        break;
                    case 2: //True/False
                        break;
                    case 3: //Short Answer
                        break;
                }
            }
        });
        updateQuestion();
        updateType();
    }

    private void checkBoxButtonEnable() {
        mCheckBoxAquiz.setEnabled(true);
        mCheckBoxBquiz.setEnabled(true);
        mCheckBoxCquiz.setEnabled(true);
        mCheckBoxDquiz.setEnabled(true);
    }

    private void checkBoxButtonDisable() {
        mCheckBoxAquiz.setEnabled(false);
        mCheckBoxBquiz.setEnabled(false);
        mCheckBoxCquiz.setEnabled(false);
        mCheckBoxDquiz.setEnabled(false);
    }

    private void trueFalseEnable() {
        mRadioGroupTrueFalseQuiz.setEnabled(true);
    }

    private void trueFalseDisable() {
        mRadioGroupTrueFalseQuiz.setEnabled(false);
    }

    private void multipleButtonEnable() {
        mRadioAquiz.setEnabled(true);
        mRadioBquiz.setEnabled(true);
        mRadioCquiz.setEnabled(true);
        mRadioDquiz.setEnabled(true);
    }

    private void multipleButtonDisable() {
        mRadioAquiz.setEnabled(false);
        mRadioBquiz.setEnabled(false);
        mRadioCquiz.setEnabled(false);
        mRadioDquiz.setEnabled(false);
    }

    private void submitDisable() {
        checkAnswered[mCurrentIndex] = true;
        mSubmitButtonQuiz.setEnabled(false);
    }

    private void updateQuestion() {
        question = (mCurrentIndex + 1) + ". " + quizList.get(mCurrentIndex).getQuestion();
        mQuestionTextView.setText(question);
        if(checkAnswered[mCurrentIndex]) mSubmitButtonQuiz.setEnabled(false);
        else mSubmitButtonQuiz.setEnabled(true);
    }

    private void updateType() {
        questionType = quizList.get(mCurrentIndex).getType();
        int counter = 0;
        switch(questionType) {
            case 0: //Multiple Choice
                if(!checkAnswered[mCurrentIndex]) multipleButtonEnable();
                else multipleButtonDisable();
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
