package com.example.jeremy.youquiz;

import android.annotation.SuppressLint;
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
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {
    private static final String KEY_INDEX = "index";

    private RelativeLayout mMultipleInput;
    private TextInputLayout mTextInputLayout, mTextShortAnswerLayout;
    private TextInputEditText mEditText;

    private Spinner mSpinner;
    private TextInputEditText mInputEditText, mEditTextA, mEditTextB, mEditTextC, mEditTextD;
    private Button mSubmitButton;
    private RadioGroup mMultipleChoice;
    private CheckBox mMultipleAnswer;
    private EditText mShortAnswer;
    private ImageButton mPrevButton, mNextButton;

    private int mCurrentIndex = 0;
    private boolean mAllAnswered = false;
    private double mScore = 0;
    private String spinnerChoice;

    public QuizActivity() {

    }

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null)
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mSpinner = findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.question_option, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        mSpinner.setAdapter(adapter);

        mSubmitButton = findViewById(R.id.submit_button);
        mMultipleInput = findViewById(R.id.multiple_input);
        mTextShortAnswerLayout = findViewById(R.id.short_answer_layout);

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerChoice = parent.getSelectedItem().toString();

                if (spinnerChoice.equals("Select Option")) {
                    mMultipleInput.setVisibility(View.GONE);
                    mTextShortAnswerLayout.setVisibility(View.GONE);
                    mSubmitButton.setVisibility(View.GONE);
                }
                else if (spinnerChoice.equals("Multiple Choice")) {
                    mMultipleInput.setVisibility(View.VISIBLE);
                    mTextShortAnswerLayout.setVisibility(View.GONE);
                    mSubmitButton.setVisibility(View.VISIBLE);
                }
                else if(spinnerChoice.equals("True/False")) {
                    mMultipleInput.setVisibility(View.GONE);
                    mTextShortAnswerLayout.setVisibility(View.GONE);
                    mSubmitButton.setVisibility(View.VISIBLE);
                }
                else if(spinnerChoice.equals("Multiple Answer Choices")) {
                    mMultipleInput.setVisibility(View.VISIBLE);
                    mTextShortAnswerLayout.setVisibility(View.GONE);
                    mSubmitButton.setVisibility(View.VISIBLE);
                }
                else if(spinnerChoice.equals("Short Answer")) {
                    mMultipleInput.setVisibility(View.GONE);
                    mTextShortAnswerLayout.setVisibility(View.VISIBLE);
                    mSubmitButton.setVisibility(View.VISIBLE);
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
        mInputEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        mEditTextA = findViewById(R.id.edit_text_a);
        mEditTextA.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        mEditTextB = findViewById(R.id.edit_text_b);
        mEditTextB.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        mEditTextC = findViewById(R.id.edit_text_c);
        mEditTextC.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        mEditTextD = findViewById(R.id.edit_text_d);
        mEditTextD.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        mShortAnswer = findViewById(R.id.short_answer_text);
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((spinnerChoice.equals("Multiple Choice") ||
                        spinnerChoice.equals("Multiple Answer Choices")) &&
                        ((mEditText.getText().toString().length() <= 0 ||
                                mEditTextA.getText().toString().length() <=0 ||
                                mEditTextB.getText().toString().length() <=0 ||
                                mEditTextC.getText().toString().length() <=0 ||
                                mEditTextD.getText().toString().length() <=0))) {
                    Log.d("QuizActivity", "I am here");
                }

                else if((spinnerChoice.equals("True/False")) &&
                        (mEditText.getText().toString().length() <= 0)) {
                    Log.d("QuizActivity", spinnerChoice);
                }
                else if((spinnerChoice.equals("Short Answer")) &&
                        ((mEditText.getText().toString().length() <= 0 ||
                                mShortAnswer.getText().toString().length() <= 0))) {
                    Log.d("QuizActivity", spinnerChoice);
                }
                else {
                    Log.d("QuizActivity", "Submitted");
                }
            }
        });
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(QuizActivity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
