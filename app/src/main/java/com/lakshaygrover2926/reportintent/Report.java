package com.lakshaygrover2926.reportintent;

import java.util.Date;
import java.util.UUID;

/**
 * Created by LAKSHAY on 12/19/2016.
 */
public class Report {


    private UUID mId;
    private String mTitle;
    private Date mdate;
    private boolean mResolved;



    public void setdate(Date mdate)
    {
        this.mdate = mdate;
    }

    public Date getdate()
    {
        return mdate;
    }

    public boolean isResolved() {
        return mResolved;
    }

    public void setResolved(boolean mResolved) {
        this.mResolved = mResolved;
    }


    public Report(){
        this(UUID.randomUUID());

    }

    public Report(UUID id){
        mId = id;
        mdate = new Date();
    }

    public UUID getId(){
        return mId;
    }

    public String getTitle(){
        return mTitle;
    }

    public void setTitle(String title){
        mTitle = title;
    }

}
