package com.example.jeremy.youquiz;

import android.os.Parcel;
import android.os.Parcelable;

public class Quiz implements Parcelable {
    private String mQuestion;
    private String mAnswer;
    private int mType;
    private String mAnswerChoice;
    private String mUserAnswer;

    public Quiz(String question, String answer, int type, String answerChoice) {
        mQuestion = question;
        mAnswer = answer;
        mType = type;
        mAnswerChoice = answerChoice;
    }

    public String getQuestion() {
        return mQuestion;
    }

    public String getAnswer() {
        return mAnswer;
    }

    public int getType() {
        return mType;
    }

    public String getAnswerChoice() { return mAnswerChoice; }

    public String getUserAnswer() { return mUserAnswer; }

    protected Quiz(Parcel in) {
        mQuestion = in.readString();
        mAnswer = in.readString();
        mType = in.readInt();
        mAnswerChoice = in.readString();
        mUserAnswer = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mQuestion);
        dest.writeString(mAnswer);
        dest.writeInt(mType);
        dest.writeString(mAnswerChoice);
        dest.writeString(mUserAnswer);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Quiz> CREATOR = new Creator<Quiz>() {
        @Override
        public Quiz createFromParcel(Parcel in) {
            return new Quiz(in);
        }

        @Override
        public Quiz[] newArray(int size) {
            return new Quiz[size];
        }
    };
}
