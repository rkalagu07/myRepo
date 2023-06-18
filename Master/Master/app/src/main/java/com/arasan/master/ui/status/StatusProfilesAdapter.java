package com.arasan.master.ui.status;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.arasan.master.R;
import com.arasan.master.data.network.response.SearchedUser;
import com.arasan.master.di.listeners.ItemClick;
import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class StatusProfilesAdapter extends RecyclerView.Adapter<StatusProfilesAdapter.ViewHolder> {

    List<SearchedUser> mUserList;
    ItemClick mItemClick;
    public StatusProfilesAdapter(List<SearchedUser> mUserList,ItemClick aItemClick) {
        this.mUserList = mUserList;
        mItemClick = aItemClick;
    }

    @NonNull
    @Override
    public StatusProfilesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_item, parent, false);
        return new StatusProfilesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StatusProfilesAdapter.ViewHolder holder, int position) {
        Glide.with(holder.itemView.getContext()).load(mUserList.get(position).galleryPic).into(holder.mGalleryImage);
        Glide.with(holder.itemView.getContext()).load(mUserList.get(position).pic).into(holder.mProfileImage);
        holder.mProfileName.setText(mUserList.get(position).name);
        holder.mShopName.setText(mUserList.get(position).shopName);
        holder.mService.setText(mUserList.get(position).service);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mItemClick.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mUserList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        AppCompatImageView  mGalleryImage;
        CircleImageView mProfileImage;
        AppCompatTextView mProfileName,mShopName,mService;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mProfileName = itemView.findViewById(R.id.new_profile_name);
            mShopName = itemView.findViewById(R.id.new_profile_shopname);
            mService = itemView.findViewById(R.id.new_profile_service);
            mGalleryImage = itemView.findViewById(R.id.gallery_image);
            mProfileImage = itemView.findViewById(R.id.new_profile_profile);
        }
    }
}
