package com.androidapp.fidel.a23102_castro_examen02;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by fidel on 10/27/2017.
 */

public class Snakes implements Parcelable {
    private Integer snakesId;
    private String begin;
    private String destination;

    public Integer getSnakesId() {
        return snakesId;
    }

    public void setSnakesId(Integer snakesId) {
        this.snakesId = snakesId;
    }


    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }


    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Snakes()
    {

    }

    public Snakes(Integer snakesId, String begin, String destination) {
        this.snakesId = snakesId;
        this.begin = begin;
        this.destination = destination;

    }

    public Snakes(Parcel in) {
        snakesId=in.readInt();
        begin = in.readString();
        destination=in.readString();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(snakesId);
        dest.writeString(begin);
        dest.writeString(destination);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){
        @Override
        public Snakes createFromParcel(Parcel in){
            return new Snakes(in);
        }
        @Override
        public Snakes[] newArray(int size){
            return new Snakes[size];
        }
    };

    public JSONObject getJson() throws JSONException {
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("id",snakesId);
        jsonBody.put("begin",begin);
        jsonBody.put("destination",destination);

        return jsonBody;

    }
}
