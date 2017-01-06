package com.lakshaygrover2926.reportintent;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.lakshaygrover2926.reportintent.ReportDBSchema.ReportTable;

/**
 * Created by LAKSHAY on 1/6/2017.
 */
public class ReportBaseHelper extends SQLiteOpenHelper {


    public static final int VERSION = 1;
    public static final String DATABASE_NAME = "reportbase.db";

    public ReportBaseHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ ReportTable.NAME + "("+
        " _id integer primary key autoincrement, " +
        ReportTable.Cols.UUID + ", " +
        ReportTable.Cols.TITLE + ", " +
        ReportTable.Cols.DATE + ", " +
        ReportTable.Cols.RESOLVED + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
