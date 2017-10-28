package com.androidapp.fidel.a23102_castro_examen02.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import com.androidapp.fidel.a23102_castro_examen02.Ladders;

import java.util.ArrayList;

/**
 * Created by fidel on 10/27/2017.
 */

public class LaddersHelper {
    private DBUtils dbHelper;
    private SQLiteDatabase database;
    private String[] LADDERS_TABLE_COLUMNS=
            {
                    DBUtils.LADDER_ID,
                    DBUtils.LADDER_BEGIN,
                    DBUtils.LADDER_DESTINATION,
            };


    public LaddersHelper(Context context){
        dbHelper = new DBUtils(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Ladders addLaddets(String ladderId, String begin, String destination) {
        ContentValues values = new ContentValues();
        values.put(DBUtils.LADDER_ID,ladderId);
        values.put(DBUtils.LADDER_BEGIN,begin);
        values.put(DBUtils.LADDER_DESTINATION,destination);

        long lId = database.insert(DBUtils.LADDERS_TABLE_NAME,null,values);

        Cursor cursor = database.query(DBUtils.LADDERS_TABLE_NAME,
                LADDERS_TABLE_COLUMNS,DBUtils.LADDER_ID + " = " +  lId,
                null,null,null,null);

        cursor.moveToFirst();
        Ladders oLadders = parseLadders(cursor);
        cursor.close();
        return oLadders;
    }

    public int deleteLadders(){
        return database.delete(DBUtils.LADDERS_TABLE_NAME, DBUtils.LADDER_ID + " > 0", null);
    }

    public ArrayList<Ladders> getAllLadders() {
        ArrayList<Ladders> oLadders = new ArrayList<>();
        Cursor cursor = database.query(DBUtils.LADDERS_TABLE_NAME, LADDERS_TABLE_COLUMNS, null,null,null,null,null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            oLadders.add(parseLadders(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        return oLadders;
    }

    private Ladders parseLadders(Cursor cursor) {
        Ladders oSankes = new Ladders();
        oSankes.setLaddersId(cursor.getInt(cursor.getColumnIndex(DBUtils.LADDER_ID)));
        oSankes.setBegin(cursor.getString(cursor.getColumnIndex(DBUtils.LADDER_BEGIN)));
        oSankes.setDestination(cursor.getString(cursor.getColumnIndex(DBUtils.LADDER_DESTINATION)));
        return oSankes;
    }
}
