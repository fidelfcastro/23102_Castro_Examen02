package com.androidapp.fidel.a23102_castro_examen02;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by fidel on 10/27/2017.
 */

public class Ladders implements Parcelable {
    private Integer laddersId;
    private String begin;
    private String destination;

    public Integer getLaddersId() {
        return laddersId;
    }

    public void setLaddersId(Integer laddersId) {
        this.laddersId = laddersId;
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

    public Ladders()
    {

    }

    public Ladders(Integer laddersId, String begin, String destination) {
        this.laddersId = laddersId;
        this.begin = begin;
        this.destination = destination;

    }

    public Ladders(Parcel in) {
        laddersId=in.readInt();
        begin = in.readString();
        destination=in.readString();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(laddersId);
        dest.writeString(begin);
        dest.writeString(destination);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){
        @Override
        public Ladders createFromParcel(Parcel in){
            return new Ladders(in);
        }
        @Override
        public Ladders[] newArray(int size){
            return new Ladders[size];
        }
    };

    public JSONObject getJson() throws JSONException {
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("id",laddersId);
        jsonBody.put("begin",begin);
        jsonBody.put("destination",destination);

        return jsonBody;

    }
}
