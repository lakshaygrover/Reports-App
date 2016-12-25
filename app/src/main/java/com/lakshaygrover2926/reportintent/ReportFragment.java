package com.lakshaygrover2926.reportintent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.util.UUID;

/**
 * Created by LAKSHAY on 12/19/2016.
 */
public class ReportFragment extends Fragment {

    private static final String ARG_REPORT_ID = "report_id";
    private Report mReport;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mResolvedCheckBox;

    public static ReportFragment newInstance(UUID reportid){
        Bundle args = new Bundle();
        args.putSerializable(ARG_REPORT_ID,reportid);
        ReportFragment reportFragment = new ReportFragment();
        reportFragment.setArguments(args);
        return reportFragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        UUID reportid = (UUID) getArguments().getSerializable(ARG_REPORT_ID);
        mReport = ReportStore.get(getActivity()).getReport(reportid);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_report, container, false);
        mTitleField = (EditText) v.findViewById(R.id.report_title);
        mTitleField.setText(mReport.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mReport.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        mDateButton = (Button) v.findViewById(R.id.report_date);
        mDateButton.setText(mReport.getdate().toString());
        mDateButton.setEnabled(false);

        mResolvedCheckBox = (CheckBox) v.findViewById(R.id.report_resolved);
        mResolvedCheckBox.setChecked(mReport.isResolved());
        mResolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mReport.setResolved(isChecked);
            }
        });

        return v;

    }



}
