package com.example.jeremy.youquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class DisplayActivity extends AppCompatActivity {
    private static final String TAG = "DisplayActivity";
    private static final String KEY_INDEX = "index";
    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        if (savedInstanceState != null)
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);

        Intent intent = getIntent();

        ArrayList<Quiz> quiz = intent.getParcelableArrayListExtra("quiz");

        Log.d(TAG, "Size of array is: " + quiz.size());

        TableLayout tableLayout = findViewById(R.id.display_activity);
        TableRow.LayoutParams layoutParams;

        for(int i = 0; i < quiz.size(); i++) {
            TableRow[] tableRow = new TableRow[quiz.size()];
            tableRow[i] = new TableRow(getApplicationContext());
            //tableRow[i].setGravity(Gravity.CENTER);

            TextView numberText = new TextView(getApplicationContext());
            layoutParams = new TableRow.LayoutParams(0,
                    TableRow.LayoutParams.WRAP_CONTENT, 1f);
            numberText.setLayoutParams(layoutParams);
            numberText.setGravity(Gravity.LEFT);
            numberText.setText(i + 1 +".");

            TextView questionText = new TextView(getApplicationContext());
            layoutParams = new TableRow.LayoutParams(0,
                    TableRow.LayoutParams.WRAP_CONTENT, 5f);
            questionText.setLayoutParams(layoutParams);
            questionText.setGravity(Gravity.LEFT);
            questionText.setText(quiz.get(i).getQuestion());

            TextView answerText = new TextView(getApplicationContext());
            layoutParams = new TableRow.LayoutParams(0,
                    TableRow.LayoutParams.WRAP_CONTENT, 3f);
            answerText.setLayoutParams(layoutParams);
            answerText.setGravity(Gravity.LEFT);
            answerText.setText(quiz.get(i).getAnswer());

            tableRow[i].addView(numberText);
            tableRow[i].addView(questionText);
            tableRow[i].addView(answerText);

            tableLayout.addView(tableRow[i]);
        }

        /*
        TableRow tableRow = new TableRow(this.getApplicationContext());
        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT);
        tableRow = new TableRow(this);
        TextView numberText = new TextView(this);
        TextView questionText = new TextView(this);
        TextView answerText = new TextView(this);
        */

        /*
        for(int i = 0; i < quiz.size(); i++) {
            tableRow = new TableRow(this.getApplicationContext());
            //numberText.setLayoutParams(layoutParams);
            numberText.setText(i + 1 +".");
            tableLayout.addView(numberText);
            //questionText.setLayoutParams(layoutParams);
            questionText.setText(quiz.get(i).getQuestion());
            tableLayout.addView(questionText);
            answerText.setText(quiz.get(i).getAnswer());
            tableLayout.addView(answerText);
        }
        */
    }
}
