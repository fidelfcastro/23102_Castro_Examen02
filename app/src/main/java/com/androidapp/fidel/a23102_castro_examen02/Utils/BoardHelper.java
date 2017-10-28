package com.androidapp.fidel.a23102_castro_examen02.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.androidapp.fidel.a23102_castro_examen02.Board;

import java.util.ArrayList;

/**
 * Created by fidel on 10/27/2017.
 */

public class BoardHelper {
    private DBUtils dbHelper;
    private SQLiteDatabase database;
    private String[] BOARD_TABLE_COLUMNS=
            {
                    DBUtils.BOARD_NAME,
                    DBUtils.BOARD_AUTHOR,

            };


    public BoardHelper(Context context){
        dbHelper = new DBUtils(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Board addBoard(String name, String author) {
        ContentValues values = new ContentValues();
        values.put(DBUtils.BOARD_NAME,name);
        values.put(DBUtils.BOARD_AUTHOR,author);


        long lId = database.insert(DBUtils.BOARD_TABLE_NAME,null,values);

        Cursor cursor = database.query(DBUtils.BOARD_TABLE_NAME,
                BOARD_TABLE_COLUMNS,DBUtils.BOARD_ID + " = " +  lId,
                null,null,null,null);

        cursor.moveToFirst();
        Board oBoards = parseBoard(cursor);
        cursor.close();
        return oBoards;
    }


    public ArrayList<Board> getAllBoards() {
        ArrayList<Board> oBoards = new ArrayList<>();
        Cursor cursor = database.query(DBUtils.BOARD_TABLE_NAME, BOARD_TABLE_COLUMNS, null,null,null,null,null);

        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            oBoards.add(parseBoard(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        return oBoards;
    }

    private Board parseBoard(Cursor cursor) {
        Board oBoard = new Board();
        oBoard.setName(cursor.getString(cursor.getColumnIndex(DBUtils.BOARD_NAME)));
        oBoard.setAuthor(cursor.getString(cursor.getColumnIndex(DBUtils.BOARD_AUTHOR)));

        return oBoard;
    }
}
