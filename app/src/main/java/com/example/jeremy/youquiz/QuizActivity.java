package com.example.jeremy.youquiz;

import android.annotation.SuppressLint;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

public class QuizActivity extends AppCompatActivity {
    private static final String KEY_INDEX = "index";

    private RelativeLayout mMultipleInput;
    private TextInputLayout mTextInputLayout, mTextShortAnswerLayout;
    private TextInputEditText mEditText;

    private Spinner mSpinner;
    private TextInputEditText mInputEditText;
    private Button mSubmitButton;
    private RadioGroup mMultipleChoice;
    private CheckBox mMultipleAnswer;
    private EditText mShortAnswer;
    private ImageButton mPrevButton, mNextButton;

    private int mCurrentIndex = 0;
    private boolean mAllAnswered = false;
    private double mScore = 0;

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
                R.array.answer_option, android.R.layout.simple_spinner_item);
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
                String text = parent.getSelectedItem().toString();

                if (text.equals("Select Option")) {
                    mMultipleInput.setVisibility(View.GONE);
                    mTextShortAnswerLayout.setVisibility(View.GONE);
                    mSubmitButton.setVisibility(View.GONE);
                }
                else if (text.equals("Multiple Choice")) {
                    mMultipleInput.setVisibility(View.VISIBLE);
                    mTextShortAnswerLayout.setVisibility(View.GONE);
                    mSubmitButton.setVisibility(View.VISIBLE);
                }
                else if(text.equals("True/False")) {
                    mMultipleInput.setVisibility(View.GONE);
                    mTextShortAnswerLayout.setVisibility(View.GONE);
                    mSubmitButton.setVisibility(View.VISIBLE);
                }
                else if(text.equals("Multiple Answer Choices")) {
                    mMultipleInput.setVisibility(View.VISIBLE);
                    mTextShortAnswerLayout.setVisibility(View.GONE);
                    mSubmitButton.setVisibility(View.VISIBLE);
                }
                else if(text.equals("Short Answer")) {
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
                    mTextInputLayout.setError("Max character length is " + mTextInputLayout.getCounterMaxLength());
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

        mInputEditText = findViewById(R.id.edit_text_a);
        mInputEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        mInputEditText = findViewById(R.id.edit_text_b);
        mInputEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        mInputEditText = findViewById(R.id.edit_text_c);
        mInputEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        mInputEditText = findViewById(R.id.edit_text_d);
        mInputEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(QuizActivity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
