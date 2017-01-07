package com.lakshaygrover2926.reportintent;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.Date;
import java.util.UUID;

/**
 * Created by LAKSHAY on 1/7/2017.
 */
public class ReportCursorWrapper extends CursorWrapper {

    public ReportCursorWrapper(Cursor cursor){
        super(cursor);
    }

    public Report getReport(){
        String uuidString = getString((getColumnIndex(ReportDBSchema.ReportTable.Cols.UUID)));
        String title = getString(getColumnIndex(ReportDBSchema.ReportTable.Cols.TITLE));
        long date = getLong(getColumnIndex(ReportDBSchema.ReportTable.Cols.DATE));
        int isResolved = getInt(getColumnIndex(ReportDBSchema.ReportTable.Cols.RESOLVED));

        Report report = new Report(UUID.fromString(uuidString));
        report.setTitle(title);
        report.setResolved(isResolved != 0);
        report.setdate(new Date(date));

        return report;
    }

}
