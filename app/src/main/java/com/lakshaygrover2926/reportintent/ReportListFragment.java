package com.lakshaygrover2926.reportintent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

/**
 * Created by LAKSHAY on 12/22/2016.
 */
public class ReportListFragment extends Fragment {

    private  static final String SAVED_SUBTITLE_VISIBLE = "subtitle";
    private RecyclerView mReportRecyclerView;
    private ReportAdapter mAdapter;
    private boolean mSubtitleVisible;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menu_item_new_report:
                Report report = new Report();
                ReportStore.get(getActivity()).addReport(report);
                Intent intent = ReportPagerActivity.newIntent(getActivity(), report.getId());
                startActivity(intent);
                return true;
            case R.id.menu_show_subtitle:
                mSubtitleVisible = !mSubtitleVisible;
                getActivity().invalidateOptionsMenu();
                updateSubtitle();
                return true;
            default:
                return  super.onOptionsItemSelected(item);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflator, ViewGroup container, Bundle savedIntanceState){
        View view = inflator.inflate(R.layout.fragment_report_list, container, false);
        mReportRecyclerView = (RecyclerView) view.findViewById(R.id.report_recycler_view);
        mReportRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        if(savedIntanceState!=null){
            mSubtitleVisible = savedIntanceState.getBoolean(SAVED_SUBTITLE_VISIBLE);
        }
        updateUI();
        return view;

    }

    @Override
    public void onResume(){
        super.onResume();
        updateUI();
    }

    @Override
    public void onSaveInstanceState(Bundle bundle){
        super.onSaveInstanceState(bundle);
        bundle.putBoolean(SAVED_SUBTITLE_VISIBLE, mSubtitleVisible);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fargment_report_list, menu);

        MenuItem subtitleItem = menu.findItem(R.id.menu_show_subtitle);
        if(mSubtitleVisible){
            subtitleItem.setTitle(R.string.hide_subtitle);
        }
        else{
            subtitleItem.setTitle(R.string.show_subtitle);
        }
    }

    private void updateSubtitle(){
        ReportStore reportStore = ReportStore.get(getActivity());
        int reportCount = reportStore.getReports().size();
        String sub = Integer.toString(reportCount);
        String subtitle = getString(R.string.subtitle_format, reportCount);

        if(!mSubtitleVisible){
            subtitle = null;
        }

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.getSupportActionBar().setSubtitle(subtitle);
    }

    public void updateUI() {
        ReportStore reportStore = ReportStore.get(getActivity());
        List<Report> reports = reportStore.getReports();
        if(mAdapter == null) {
            mAdapter = new ReportAdapter(reports);
            mReportRecyclerView.setAdapter(mAdapter);
        }
        else{
            mAdapter.setReports(reports);
            mAdapter.notifyDataSetChanged();
        }
        updateSubtitle();
    }


    private class ReportHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private Report mReport;
        private TextView mTitleTextView;
        private TextView mDateTextView;
        private CheckBox mResolvedCheckBox;
        public ReportHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);
            mTitleTextView = (TextView) itemView.findViewById(R.id.list_item_report_title_text_view);
            mDateTextView = (TextView) itemView.findViewById(R.id.list_item_report_date_text_view);
            mResolvedCheckBox = (CheckBox) itemView.findViewById(R.id.list_report_resolved_check_box);
        }

        private void bindReport(Report report){
            mReport = report;
            mTitleTextView.setText(mReport.getTitle());
           // mDateTextView.setText(mReport.getdate().toString());
            mResolvedCheckBox.setChecked(mReport.isResolved());
        }

        @Override
        public void onClick(View v){
            Intent intent = ReportPagerActivity.newIntent(getActivity(), mReport.getId());
            startActivity(intent);
        }
    }


    private class ReportAdapter extends RecyclerView.Adapter<ReportHolder>{
        public List<Report> mReports;
        public ReportAdapter(List<Report> reports){
            mReports = reports;
        }


        @Override
        public ReportHolder onCreateViewHolder(ViewGroup parent, int viewType){
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_report, parent, false);

            return new ReportHolder(view);
        }

        @Override
        public void onBindViewHolder(ReportHolder holder, int position){
            Report report = mReports.get(position);
            holder.bindReport(report);
        }

        @Override
        public int getItemCount(){
            return mReports.size();
        }

        public void setReports(List<Report> reports){
            mReports = reports;
        }
    }
}