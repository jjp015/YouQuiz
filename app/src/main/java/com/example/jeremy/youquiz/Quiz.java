package com.example.jeremy.youquiz;

import android.os.Parcel;
import android.os.Parcelable;

public class Quiz implements Parcelable {
    private String mQuestion;
    private String mAnswer;
    private int mType;

    public Quiz(String question, String answer, int type) {
        mQuestion = question;
        mAnswer = answer;
        mType = type;
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

    protected Quiz(Parcel in) {
        mQuestion = in.readString();
        mAnswer = in.readString();
        mType = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mQuestion);
        dest.writeString(mAnswer);
        dest.writeInt(mType);
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
