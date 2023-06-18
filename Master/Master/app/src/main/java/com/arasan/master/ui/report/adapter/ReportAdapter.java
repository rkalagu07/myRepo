package com.arasan.master.ui.report.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.arasan.master.R;
import com.arasan.master.data.network.response.Report;
import com.arasan.master.di.listeners.ItemClick;
import com.arasan.master.ui.home.HomeAdapter;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.ViewHolder> {

    private ArrayList<Report> mReportList;
    private ItemClick mItemClick;

    public ReportAdapter(ArrayList<Report> mReportList, ItemClick mItemClick) {
        this.mReportList = mReportList;
        this.mItemClick = mItemClick;
    }

    @NonNull
    @Override
    public ReportAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.report_item, parent, false);
        return new ReportAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReportAdapter.ViewHolder holder, int position) {
        Glide.with(holder.itemView.getContext()).load(mReportList.get(position).pic).into(holder.mLogo);
        holder.mName.setText(mReportList.get(position).name);
        holder.mService.setText(mReportList.get(position).service);
        holder.mMessage.setText(mReportList.get(position).message);
        if (mReportList.get(position).shopName.equalsIgnoreCase("NOT SPECIFIED")) {
            holder.mShop.setVisibility(View.GONE);
        } else {
            holder.mShop.setText(mReportList.get(position).shopName);
        }

    }

    @Override
    public int getItemCount() {
        return mReportList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CircleImageView mLogo;
        TextView mName, mService, mShop, mMessage, mSubmit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mLogo = itemView.findViewById(R.id.service_provider_pic);
            mName = itemView.findViewById(R.id.name);
            mService = itemView.findViewById(R.id.services);
            mMessage = itemView.findViewById(R.id.report_message);
            mSubmit = itemView.findViewById(R.id.checked_report);
            mShop = itemView.findViewById(R.id.shop_name);
            mSubmit.setOnClickListener(this::onClick);
        }

        @Override
        public void onClick(View view) {
            int aId = view.getId();
            if (aId == R.id.checked_report) {
                mItemClick.onItemClick(getAbsoluteAdapterPosition());
            }
        }
    }
}
