package com.lakshaygrover2926.reportintent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by LAKSHAY on 12/22/2016.
 */
public class ReportStore {
    private static ReportStore mReportstore;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static ReportStore get(Context context){
        if(mReportstore == null){
            mReportstore = new ReportStore(context);
        }
        return mReportstore;
    }

    private ReportStore(Context context){

        mContext= context.getApplicationContext();
        mDatabase = new ReportBaseHelper(mContext).getWritableDatabase();


    }

    public void addReport(Report r){
        ContentValues values = getContentValues(r);
        mDatabase.insert(ReportDBSchema.ReportTable.NAME, null, values);

    }

    public  void updateReport(Report report){
        String uuidString = report.getId().toString();
        ContentValues values = getContentValues(report);
        mDatabase.update(ReportDBSchema.ReportTable.NAME, values,
                ReportDBSchema.ReportTable.Cols.UUID + " = ?",
                new String[]{uuidString});
    }

    public List<Report> getReports() {
        List<Report> reports = new ArrayList<>();
        ReportCursorWrapper cursorWrapper = queryReports(null, null);
        try{
            cursorWrapper.moveToFirst();
            while (!cursorWrapper.isAfterLast()){
                reports.add(cursorWrapper.getReport());
                cursorWrapper.moveToNext();
            }
        }finally{
            cursorWrapper.close();
        }

        return reports;
    }

    public Report getReport(UUID id){

        ReportCursorWrapper cursorWrapper = queryReports(
                ReportDBSchema.ReportTable.Cols.UUID + " = ?",
                new String[]{id.toString()});


        try{
            if(cursorWrapper.getCount() == 0){
                return null;
            }
            cursorWrapper.moveToFirst();
            return cursorWrapper.getReport();
        }finally {
            cursorWrapper.close();
        }
    }

    private static ContentValues getContentValues(Report report){
        ContentValues values = new ContentValues();
        values.put(ReportDBSchema.ReportTable.Cols.UUID, report.getId().toString());
        values.put(ReportDBSchema.ReportTable.Cols.TITLE, report.getTitle());
        values.put(ReportDBSchema.ReportTable.Cols.DATE, report.getdate().toString());
        values.put(ReportDBSchema.ReportTable.Cols.RESOLVED, report.isResolved()? 1: 0);

        return values;
    }

    private ReportCursorWrapper queryReports(String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(
                ReportDBSchema.ReportTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );

        return new ReportCursorWrapper(cursor);
    }
}
