package org.jeonfeel.perpack4;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static String NAME = "DataBase.db";
    public static int VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, NAME, null, VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        //println("onCreate 호출됨");

        String sql = "create table if not exists Note("
                + " _id integer PRIMARY KEY autoincrement, "
                + " TITLE text, "
                + " YEAR text, "
                + " MONTH text, "
                + " DAY text, "
                + " HOUR text, "
                + " MIN text, "
                + " LIST text, "
                + " CHECKBOX text, "
                + " PURPOSE text)";

        String sql2 = "create table if not exists mylist_note("
                + " mylist_id integer PRIMARY KEY autoincrement, "
                + " mylistTITLE text, "
                + " mylistCHECKL text)";

        db.execSQL(sql);
        db.execSQL(sql2);
    }

    public void onOpen(SQLiteDatabase db) {
        println("onOpen 호출됨");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        println("onUpgrade 호출됨 : " + oldVersion + " -> " + newVersion);


    }

    public void println(String data) {
        Log.d("DatabaseHelper", data);
    }
    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.disableWriteAheadLogging();
    }
}
