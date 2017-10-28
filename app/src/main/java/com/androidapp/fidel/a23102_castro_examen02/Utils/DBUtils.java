package com.androidapp.fidel.a23102_castro_examen02.Utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by fidel on 10/27/2017.
 */

public class DBUtils extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "parcial2.db";
    public static final int DATABASE_VERSION = 1;

    public static final String BOARD_TABLE_NAME = "BOARD";
    public static final String BOARD_ID = "_boardId";
    public static final String BOARD_NAME = "_boardName";
    public static final String BOARD_AUTHOR = "_boardAuthor";
    public static final String LADDER_FRGKEY = "_ladderFrgKey";
    public static final String SNAKES_FRGKEY = "_snakesFrgKey";

    public static final String LADDERS_TABLE_NAME = "LADDERS";
    public static final String LADDER_ID = "_ladderId";
    public static final String LADDER_BEGIN = "_ladderBegin";
    public static final String LADDER_DESTINATION = "_ladderDestination";

    public static final String SNAKES_TABLE_NAME = "SNAKES";
    public static final String SNAKES_ID = "_snakesId";
    public static final String SNAKES_BEGIN = "_snakesBegin";
    public static final String SNAKES_DESTINATION = "_snakesDestination";

    public static final String DB_BOARD_CREATE = "CREATE TABLE "+
            BOARD_TABLE_NAME +
            "(" +
            BOARD_ID +
            " integer primary key autoincrement," +
            BOARD_NAME  +
            " text NOT NULL," +
            BOARD_AUTHOR +
            " text NOT NULL," +
            LADDER_FRGKEY +
            " integer NOT NULL," +
            SNAKES_FRGKEY  +
            " integer NOT NULL" +
            ")";

    public static final String DB_LATTER_CREATE = "CREATE TABLE "+
            LADDERS_TABLE_NAME +
            "(" +
            LADDER_ID +
            " integer primary key autoincrement," +
            LADDER_BEGIN  +
            " integer NOT NULL," +
            LADDER_DESTINATION +
            " integer NOT NULL," +
            ")";

    public static final String DB_SNAKES_CREATE = "CREATE TABLE "+
            SNAKES_TABLE_NAME +
            " (" +
            SNAKES_ID +
            " integer primary key autoincrement," +
            SNAKES_BEGIN  +
            " integer NOT NULL," +
            SNAKES_DESTINATION+
            " integer NOT NULL," +
            ")";


    public DBUtils(Context context){
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DB_BOARD_CREATE);
        sqLiteDatabase.execSQL(DB_LATTER_CREATE);
        sqLiteDatabase.execSQL(DB_SNAKES_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS BOARD");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS LADDERS");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS SNAKES");
        onCreate(sqLiteDatabase);


    }
}
