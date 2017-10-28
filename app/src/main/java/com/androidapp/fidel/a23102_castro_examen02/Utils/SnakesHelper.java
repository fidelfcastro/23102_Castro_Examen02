package com.androidapp.fidel.a23102_castro_examen02.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.androidapp.fidel.a23102_castro_examen02.Snakes;

import java.util.ArrayList;

/**
 * Created by fidel on 10/27/2017.
 */

public class SnakesHelper {
    private DBUtils dbHelper;
    private SQLiteDatabase database;
    private String[] SNAKES_TABLE_COLUMNS=
            {
                    DBUtils.SNAKES_ID,
                    DBUtils.SNAKES_BEGIN,
                    DBUtils.SNAKES_DESTINATION,
            };


    public SnakesHelper(Context context){
        dbHelper = new DBUtils(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Snakes addSankes(String snakeId, String begin, String destination) {
        ContentValues values = new ContentValues();
        values.put(DBUtils.SNAKES_ID,snakeId);
        values.put(DBUtils.SNAKES_BEGIN,begin);
        values.put(DBUtils.SNAKES_DESTINATION,destination);

        long lId = database.insert(DBUtils.SNAKES_TABLE_NAME,null,values);

        Cursor cursor = database.query(DBUtils.SNAKES_TABLE_NAME,
                SNAKES_TABLE_COLUMNS,DBUtils.SNAKES_ID + " = " +  lId,
                null,null,null,null);

        cursor.moveToFirst();
        Snakes oSnakes = parseSnakes(cursor);
        cursor.close();
        return oSnakes;
    }

    public int deleteSnakes(){
        return database.delete(DBUtils.SNAKES_TABLE_NAME, DBUtils.SNAKES_ID + " > 0", null);
    }

    public ArrayList<Snakes> getAllSnakes() {
        ArrayList<Snakes> oSnakes = new ArrayList<>();
        Cursor cursor = database.query(DBUtils.SNAKES_TABLE_NAME, SNAKES_TABLE_COLUMNS, null,null,null,null,null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            oSnakes.add(parseSnakes(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        return oSnakes;
    }

    private Snakes parseSnakes(Cursor cursor) {
        Snakes oSankes = new Snakes();
        oSankes.setSnakesId(cursor.getInt(cursor.getColumnIndex(DBUtils.SNAKES_ID)));
        oSankes.setBegin(cursor.getString(cursor.getColumnIndex(DBUtils.SNAKES_BEGIN)));
        oSankes.setDestination(cursor.getString(cursor.getColumnIndex(DBUtils.SNAKES_BEGIN)));
        return oSankes;
    }
}
