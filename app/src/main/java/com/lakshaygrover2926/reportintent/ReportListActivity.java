package com.lakshaygrover2926.reportintent;

import android.support.v4.app.Fragment;

/**
 * Created by LAKSHAY on 12/22/2016.
 */
public class ReportListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment(){
        return new ReportListFragment();
    }
}
