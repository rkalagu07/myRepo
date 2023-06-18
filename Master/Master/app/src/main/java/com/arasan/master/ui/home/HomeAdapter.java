package com.arasan.master.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.arasan.master.R;
import com.arasan.master.di.listeners.ItemClick;
import com.arasan.master.ui.model.Home;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHoler> {

    ArrayList<Home> mHomeList;
    ItemClick mItemClick;
    public HomeAdapter(ArrayList<Home> mHomeList, ItemClick aItemClick) {
        this.mHomeList = mHomeList;
        this.mItemClick = aItemClick;
    }



    @NonNull
    @Override
    public HomeAdapter.ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_home, parent, false);
        return new ViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.ViewHoler holder, int position) {

        Glide.with(holder.itemView.getContext()).load(mHomeList.get(position).img).into(holder.mImage);
        holder.mName.setText(mHomeList.get(position).names);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mItemClick.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mHomeList.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder {
        ImageView mImage;
        TextView mName;
        public ViewHoler(@NonNull View itemView) {
            super(itemView);

            mImage = itemView.findViewById(R.id.service_url);
            mName = itemView.findViewById(R.id.service_name);
        }
    }
}
