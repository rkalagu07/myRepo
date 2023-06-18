package com.arasan.master.ui.report;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.arasan.master.R;
import com.arasan.master.data.network.response.Report;
import com.arasan.master.data.viewmodel.ConnexionViewModel;
import com.arasan.master.di.listeners.ItemClick;
import com.arasan.master.di.utility.Utils;
import com.arasan.master.ui.report.adapter.ReportAdapter;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ReportFragment extends Fragment implements ItemClick {

    RecyclerView mRecyclerView;
    ConnexionViewModel mConnectionViewModel;
    ReportAdapter mReportAdapter;
    RelativeLayout mParentLayout;
    private ArrayList<Report> mReportList;
    private int SelectedPosition;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mConnectionViewModel = new ViewModelProvider(this).get(ConnexionViewModel.class);
        return inflater.inflate(R.layout.fragment_report, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView = view.findViewById(R.id.report_RV);
        mParentLayout = view.findViewById(R.id.parent);
        mConnectionViewModel.GetReportData("N");
        ListenForGetReportData();
        ListenForUpdateReportStatus();

    }

    private void ListenForUpdateReportStatus() {
        mConnectionViewModel.mUpdateReportStatus.observe(getViewLifecycleOwner(), getReportData ->{
            if(getReportData.message.equalsIgnoreCase("Successful"))
            {
                mReportList.remove(SelectedPosition);
                mReportAdapter.notifyDataSetChanged();
                Utils.ShowSnackBarRelativeLayout(mParentLayout,"Update Successfully");
            }
        });
    }

    private void ListenForGetReportData() {
        mConnectionViewModel.mGetReportData.observe(getViewLifecycleOwner(), getReportData ->{
            if(getReportData.message.equalsIgnoreCase("Successful"))
            {
                mReportList = getReportData.report;
                mReportAdapter = new ReportAdapter(mReportList,this);
                mRecyclerView.setAdapter(mReportAdapter);
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        SelectedPosition = position;
        mConnectionViewModel.UpdateReportStatus(mReportList.get(position).uid);
    }
}