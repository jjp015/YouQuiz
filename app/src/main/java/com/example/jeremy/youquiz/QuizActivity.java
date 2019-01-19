package com.example.jeremy.youquiz;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {
    private RelativeLayout mMultipleInput, mMultipleCheck, mMultipleRadio, mTrueFalseRadio,
            mButtonGroup;
    private TextInputLayout mTextInputLayout, mTextShortAnswerLayout;
    private TextInputEditText mEditText;
    private TextInputEditText mEditTextA, mEditTextB, mEditTextC, mEditTextD;
    private Button mDoneButton;
    private EditText mShortAnswer;
    private CheckBox mCheckBoxA, mCheckBoxB, mCheckBoxC, mCheckBoxD;
    private RadioGroup mRadioGroupMultiple, mRadioGroupTrueFalse;
    private RadioButton mRadioA, mRadioB, mRadioC, mRadioD, mRadioTrue, mRadioFalse;
    private String spinnerChoice, questionText, shortAnswerText, aText, bText, cText, dText;
    private String answerList = "";
    private static final String TAG = "QuizActivity";

    ArrayList<Quiz>quiz = new ArrayList<>();

    public QuizActivity() {

    }

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Spinner mSpinner;
        TextInputEditText mInputEditText;
        Button mSubmitButton, mClearButton;

        mSpinner = findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.question_option, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        mSpinner.setAdapter(adapter);

        mMultipleRadio = findViewById(R.id.multiple_radio);
        mTrueFalseRadio = findViewById(R.id.true_false_radio);
        mButtonGroup = findViewById(R.id.button_group);
        mRadioA = findViewById(R.id.radio_a);
        mRadioB = findViewById(R.id.radio_b);
        mRadioC = findViewById(R.id.radio_c);
        mRadioD = findViewById(R.id.radio_d);
        mRadioTrue = findViewById(R.id.radio_true);
        mRadioFalse = findViewById(R.id.radio_false);
        mDoneButton= findViewById(R.id.done_button);
        mMultipleInput = findViewById(R.id.multiple_input);
        mTextShortAnswerLayout = findViewById(R.id.short_answer_layout);
        mMultipleCheck = findViewById(R.id.multiple_check);

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerChoice = parent.getSelectedItem().toString();

                switch (spinnerChoice) {
                    case "Select Option":
                        mMultipleInput.setVisibility(View.GONE);
                        mTextShortAnswerLayout.setVisibility(View.GONE);
                        mMultipleRadio.setVisibility(View.GONE);
                        mTrueFalseRadio.setVisibility(View.GONE);
                        mMultipleCheck.setVisibility(View.GONE);
                        mButtonGroup.setVisibility(View.GONE);
                        mDoneButton.setVisibility(View.GONE);
                        break;
                    case "Multiple Choice":
                        mMultipleInput.setVisibility(View.VISIBLE);
                        mTextShortAnswerLayout.setVisibility(View.GONE);
                        mMultipleRadio.setVisibility(View.VISIBLE);
                        mTrueFalseRadio.setVisibility(View.GONE);
                        mMultipleCheck.setVisibility(View.GONE);
                        mButtonGroup.setVisibility(View.VISIBLE);
                        mDoneButton.setVisibility(View.VISIBLE);
                        break;
                    case "True/False":
                        mMultipleInput.setVisibility(View.GONE);
                        mTextShortAnswerLayout.setVisibility(View.GONE);
                        mMultipleRadio.setVisibility(View.GONE);
                        mTrueFalseRadio.setVisibility(View.VISIBLE);
                        mMultipleCheck.setVisibility(View.GONE);
                        mButtonGroup.setVisibility(View.VISIBLE);
                        mDoneButton.setVisibility(View.VISIBLE);
                        break;
                    case "Multiple Answer Choices":
                        mMultipleInput.setVisibility(View.VISIBLE);
                        mTextShortAnswerLayout.setVisibility(View.GONE);
                        mMultipleRadio.setVisibility(View.GONE);
                        mTrueFalseRadio.setVisibility(View.GONE);
                        mMultipleCheck.setVisibility(View.VISIBLE);
                        mButtonGroup.setVisibility(View.VISIBLE);
                        mDoneButton.setVisibility(View.VISIBLE);
                        break;
                    case "Short Answer":
                        mMultipleInput.setVisibility(View.GONE);
                        mTextShortAnswerLayout.setVisibility(View.VISIBLE);
                        mMultipleRadio.setVisibility(View.GONE);
                        mTrueFalseRadio.setVisibility(View.GONE);
                        mMultipleCheck.setVisibility(View.GONE);
                        mButtonGroup.setVisibility(View.VISIBLE);
                        mDoneButton.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mTextInputLayout = findViewById(R.id.text_input_layout);
        mEditText = findViewById(R.id.edit_text);
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > mTextInputLayout.getCounterMaxLength())
                    mTextInputLayout.setError("Max character length is " +
                            mTextInputLayout.getCounterMaxLength());
                else
                    mTextInputLayout.setError(null);
            }
        });

        mInputEditText = findViewById(R.id.edit_text);
        mEditTextA = findViewById(R.id.edit_text_a);
        mEditTextB = findViewById(R.id.edit_text_b);
        mEditTextC = findViewById(R.id.edit_text_c);
        mEditTextD = findViewById(R.id.edit_text_d);
        mShortAnswer = findViewById(R.id.short_answer_text);
        mInputEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        mEditTextA.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        mEditTextB.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        mEditTextC.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        mEditTextD.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });


        mShortAnswer.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        mMultipleRadio.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        mTrueFalseRadio.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });


        mCheckBoxA = findViewById(R.id.check_a);
        mCheckBoxB = findViewById(R.id.check_b);
        mCheckBoxC = findViewById(R.id.check_c);
        mCheckBoxD = findViewById(R.id.check_d);
        mRadioGroupMultiple = findViewById(R.id.multiple_radio_group);
        mRadioGroupTrueFalse = findViewById(R.id.true_false_radio_group);
        mSubmitButton = findViewById(R.id.submit_button);
        mClearButton = findViewById(R.id.clear_button);

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                questionText = mEditText.getText().toString();
                Toast toast = Toast.makeText(context, "Enter in a question!",
                        Toast.LENGTH_SHORT);
                if (questionText.length() > 500) {
                    toast = Toast.makeText(context, "Error: Question is too long (Max 500)",
                            Toast.LENGTH_SHORT);
                }
                else if(questionText.length() > 0) {
                    switch (spinnerChoice) {
                        case "Multiple Choice":
                            aText = mEditTextA.getText().toString();
                            bText = mEditTextB.getText().toString();
                            cText = mEditTextC.getText().toString();
                            dText = mEditTextD.getText().toString();
                            if (aText.length() > 30 && bText.length() > 30 && cText.length() > 30 &&
                                    dText.length() > 30) {
                                toast = Toast.makeText(context, "Error: Answer is too long " +
                                        "(Max 30)", Toast.LENGTH_SHORT);
                            }
                            else if (aText.length() > 0 && bText.length() > 0 && cText.length() > 0
                                    && dText.toString().length() > 0) {
                                if(mRadioA.isChecked()) {
                                    quiz.add(new Quiz(questionText, "A. " + aText, 0));
                                    toast = Toast.makeText(context, "A" + aText, Toast.LENGTH_SHORT);
                                    clearForm();
                                } else if(mRadioB.isChecked()) {
                                    quiz.add(new Quiz(questionText, "B. " + bText, 0));
                                    toast = Toast.makeText(context, "B" + bText, Toast.LENGTH_SHORT);
                                    clearForm();
                                } else if(mRadioC.isChecked()) {
                                    quiz.add(new Quiz(questionText, "C. " + cText, 0));
                                    toast = Toast.makeText(context, "C" + cText, Toast.LENGTH_SHORT);
                                    clearForm();
                                } else if(mRadioD.isChecked()) {
                                    quiz.add(new Quiz(questionText, "D. " + dText, 0));
                                    toast = Toast.makeText(context, "D" + dText, Toast.LENGTH_SHORT);
                                    clearForm();
                                } else {
                                    toast = Toast.makeText(context, "Select answer choice!",
                                            Toast.LENGTH_SHORT);
                                }
                            } else {
                                toast = Toast.makeText(context, "Fill in answer choices!",
                                        Toast.LENGTH_SHORT);
                            }
                            toast.show();
                            break;
                        case "Multiple Answer Choices":
                            aText = mEditTextA.getText().toString();
                            bText = mEditTextB.getText().toString();
                            cText = mEditTextC.getText().toString();
                            dText = mEditTextD.getText().toString();
                            if (aText.length() > 30 && bText.length() > 30 && cText.length() > 30 &&
                                    dText.length() > 30) {
                                toast = Toast.makeText(context, "Error: Answer is too long " +
                                        "(Max 30)", Toast.LENGTH_SHORT);
                            }
                            else if (mEditTextA.getText().toString().length() > 0 &&
                                    mEditTextB.getText().toString().length() > 0 &&
                                    mEditTextC.getText().toString().length() > 0 &&
                                    mEditTextD.getText().toString().length() > 0) {
                                if (mCheckBoxA.isChecked() || mCheckBoxB.isChecked() ||
                                        mCheckBoxC.isChecked() || mCheckBoxD.isChecked()) {
                                    if(mCheckBoxA.isChecked()) {
                                        answerList = answerList + "A. " + aText + "\n";
                                    }
                                    if(mCheckBoxB.isChecked()) {
                                        answerList = answerList + "B. " + bText + "\n";
                                    }
                                    if(mCheckBoxC.isChecked()) {
                                        answerList = answerList + "C. " + cText + "\n";
                                    }
                                    if(mCheckBoxD.isChecked()) {
                                        answerList = answerList + "D. " + dText;
                                    }
                                    quiz.add(new Quiz(questionText, answerList, 1));
                                    toast = Toast.makeText(context, answerList, Toast.LENGTH_SHORT);
                                    clearForm();
                                    answerList = "";
                                } else {
                                    toast = Toast.makeText(context, "Select answer choice(s)!",
                                            Toast.LENGTH_SHORT);
                                }
                            } else {
                                toast = Toast.makeText(context, "Fill in answer choices!",
                                        Toast.LENGTH_SHORT);
                            }
                            toast.show();
                            break;
                        case "True/False":
                            if (mRadioTrue.isChecked()) {
                                quiz.add(new Quiz(questionText, "True", 2));
                                toast = Toast.makeText(context, "True", Toast.LENGTH_SHORT);
                                clearForm();
                            } else if (mRadioFalse.isChecked()) {
                                quiz.add(new Quiz(questionText, "False", 2));
                                toast = Toast.makeText(context, "False", Toast.LENGTH_SHORT);
                                clearForm();
                            } else {
                                toast = Toast.makeText(context, "Select answer choice!",
                                        Toast.LENGTH_SHORT);
                            }
                            toast.show();
                            break;
                        case "Short Answer":
                            shortAnswerText = mShortAnswer.getText().toString();
                            if (shortAnswerText.length() > 30) {
                                toast = Toast.makeText(context, "Error: Answer is too long " +
                                        "(Max 30)", Toast.LENGTH_SHORT);
                            }
                            else if (shortAnswerText.length() > 0) {
                                quiz.add(new Quiz(questionText, shortAnswerText, 3));
                                toast = Toast.makeText(context, "Short answer",
                                        Toast.LENGTH_SHORT);
                                clearForm();
                            } else {
                                toast = Toast.makeText(context, "Fill in short answer!",
                                        Toast.LENGTH_SHORT);
                            }
                            toast.show();
                            break;
                    }
                } else {
                    toast.show();
                }
            }
        });

        mClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearForm();
            }
        });

        mDoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quiz.isEmpty()) {
                    Context context = getApplicationContext();
                    Toast.makeText(context, "Quiz is empty!", Toast.LENGTH_SHORT).show();
                } else {
                    Log.d(TAG, "Started DisplayActivity");
                    Intent intent = new Intent(QuizActivity.this,
                            DisplayActivity.class);
                    intent.putParcelableArrayListExtra("quiz", quiz);
                    startActivity(intent);
                }
            }
        });
    }

    public void hideKeyboard(View view) {
        InputMethodManager imm =
                (InputMethodManager)getSystemService(QuizActivity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        view.clearFocus();
    }

    public void clearForm() {
        mEditText.setText("");
        mEditText.setText("");
        mEditTextA.setText("");
        mEditTextB.setText("");
        mEditTextC.setText("");
        mEditTextD.setText("");
        mShortAnswer.setText("");
        mCheckBoxA.setChecked(false);
        mCheckBoxB.setChecked(false);
        mCheckBoxC.setChecked(false);
        mCheckBoxD.setChecked(false);
        mRadioGroupMultiple.clearCheck();
        mRadioGroupTrueFalse.clearCheck();
    }
}
