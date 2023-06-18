package com.arasan.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ViewHolder> {
    ArrayList<Shop> mShopList;
    ItemClickListener mItemClickListener;

    public ShopAdapter(ArrayList<Shop> aShopList, ItemClickListener aItemClickListener) {
        mItemClickListener = aItemClickListener;
        mShopList = aShopList;
    }

    @NonNull
    @Override
    public ShopAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopAdapter.ViewHolder holder, int position) {
        holder.mShopName.setText(mShopList.get(position).Name);
        holder.mShopAddress.setText(mShopList.get(position).Address);
        holder.mShopMobile.setText(mShopList.get(position).Mobile);
    }

    @Override
    public int getItemCount() {
        return mShopList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView mShopName, mShopAddress, mShopMobile;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mShopName = itemView.findViewById(R.id.shopname);
            mShopAddress = itemView.findViewById(R.id.shopaddress);
            mShopMobile = itemView.findViewById(R.id.mobileno);
            itemView.setOnClickListener(this::onClick);
        }

        @Override
        public void onClick(View v) {
            mItemClickListener.onItemClick(getAdapterPosition());
        }
    }
}
