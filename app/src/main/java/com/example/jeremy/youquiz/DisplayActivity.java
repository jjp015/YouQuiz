package com.example.jeremy.youquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class DisplayActivity extends AppCompatActivity {
    private Button mBackButton, mFinishButton;
    private static final String TAG = "DisplayActivity";
    private static final String KEY_INDEX = "index";
    private int mCurrentIndex = 0;

    Intent intent = getIntent();
    ArrayList<Quiz> quiz = intent.getParcelableArrayListExtra("quiz");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        if (savedInstanceState != null)
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);

        TableLayout tableLayout = findViewById(R.id.display_activity);
        TableRow.LayoutParams layoutParams;
        TableRow[] tableRow = new TableRow[quiz.size()];
        mBackButton = findViewById(R.id.back_button);
        mFinishButton = findViewById(R.id.finish_button);

        for(int i = 0; i < quiz.size(); i++) {
            tableRow[i] = new TableRow(getApplicationContext());

            TextView numberText = new TextView(getApplicationContext());
            layoutParams = new TableRow.LayoutParams(0,
                    TableRow.LayoutParams.WRAP_CONTENT, 0.5f);
            numberText.setLayoutParams(layoutParams);
            numberText.setGravity(Gravity.LEFT);
            numberText.setTextSize(getResources().getDimension(R.dimen.displaySize));
            numberText.setPaddingRelative(0,0,0,20);
            numberText.setText(i + 1 +".");

            TextView questionText = new TextView(getApplicationContext());
            layoutParams = new TableRow.LayoutParams(0,
                    TableRow.LayoutParams.WRAP_CONTENT, 5f);
            questionText.setLayoutParams(layoutParams);
            questionText.setGravity(Gravity.LEFT);
            questionText.setTextSize(getResources().getDimension(R.dimen.displaySize));
            questionText.setPaddingRelative(0,0,15,20);
            questionText.setText(quiz.get(i).getQuestion());

            TextView answerText = new TextView(getApplicationContext());
            layoutParams = new TableRow.LayoutParams(0,
                    TableRow.LayoutParams.WRAP_CONTENT, 3.5f);
            answerText.setLayoutParams(layoutParams);
            answerText.setGravity(Gravity.CENTER);
            answerText.setTextSize(getResources().getDimension(R.dimen.displaySize));
            answerText.setPaddingRelative(0,0,0,20);
            answerText.setText(quiz.get(i).getAnswer());

            tableRow[i].addView(numberText);
            tableRow[i].addView(questionText);
            tableRow[i].addView(answerText);

            tableLayout.addView(tableRow[i]);
        }

        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mFinishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplayActivity.this, QuestionActivity.class);
                intent.putParcelableArrayListExtra("quiz", quiz);
                startActivity(intent);
            }
        });
    }
}
