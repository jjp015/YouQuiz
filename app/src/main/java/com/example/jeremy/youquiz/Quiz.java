package com.example.jeremy.youquiz;

import android.os.Parcel;
import android.os.Parcelable;

public class Quiz implements Parcelable {
    String mQuestion;
    String mAnswer;
    int mType;
    public Quiz(String question, String answer, int type) {
        mQuestion = question;
        mAnswer = answer;
        mType = type;
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
