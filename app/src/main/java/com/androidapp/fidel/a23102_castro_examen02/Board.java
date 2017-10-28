package com.androidapp.fidel.a23102_castro_examen02;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by fidel on 10/27/2017.
 */

public class Board implements Parcelable {

    private String name;
    private String author;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    public Board(){
    }

    public Board(String name, String author) {
        this.name = name;
        this.author = author;
    }

    public Board(Parcel in) {
        name = in.readString();
        author=in.readString();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(author);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){
        @Override
        public Board createFromParcel(Parcel in){
            return new Board(in);
        }
        @Override
        public Board[] newArray(int size){
            return new Board[size];
        }
    };

    public JSONObject getJson() throws JSONException {
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("name",name);
        jsonBody.put("author",author);

        return jsonBody;

    }

}
