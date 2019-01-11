package com.example.jeremy.youquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
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

        Log.d(TAG, "onCreate(Bundle) called");

        ArrayList<String> questionList = getIntent().getStringArrayListExtra("question");
        ArrayList<String>answerList = getIntent().getStringArrayListExtra("answer");
        Log.d(TAG, "Received arrays");

        Log.d(TAG, "Size of array is: " + questionList.size());
    }
}
