package com.lakshaygrover2926.reportintent;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;

import java.util.UUID;

public class ReportActivity extends SingleFragmentActivity {

    public static final String EXTRA_REPORT_id = "com.lakshaygrover2926.reportintent.report_id";

    public static Intent newIntent(Context packageContext, UUID reportId){
        Intent intent = new Intent(packageContext, ReportActivity.class);
        intent.putExtra(EXTRA_REPORT_id, reportId);
        return intent;
    }
    @Override
    protected Fragment createFragment(){
        UUID reportId = (UUID) getIntent().getSerializableExtra(EXTRA_REPORT_id);


        return ReportFragment.newInstance(reportId);
    }

}
