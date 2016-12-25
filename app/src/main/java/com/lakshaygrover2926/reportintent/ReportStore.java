package com.lakshaygrover2926.reportintent;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by LAKSHAY on 12/22/2016.
 */
public class ReportStore {
    private static ReportStore mReportstore;
    private List<Report> mReports;

    public static ReportStore get(Context context){
        if(mReportstore == null){
            mReportstore = new ReportStore(context);
        }
        return mReportstore;
    }

    private ReportStore(Context context){
        mReports = new ArrayList<>();
        for(int i = 0;i<100;i++){
            Report report = new Report();
            report.setTitle("Report #"+i);
            report.setResolved(i%2==0);
            mReports.add(report);
        }
    }

    public List<Report> getReports() {
        return mReports;
    }

    public Report getReport(UUID id){
        for(Report report:mReports){
            if(report.getId().equals(id)){
                return report;
            }
        }

        return null;
    }
}
