package com.arasan.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

public class CustomerShops_Sheet extends BottomSheetDialogFragment implements ItemClickListener {

    private ArrayList<Shop> mCustomerList;
    private ShopClick mSelectShopListener;

    public CustomerShops_Sheet(ArrayList<Shop> aCustomerList) {
        this.mCustomerList = aCustomerList;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable
            ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.bottom_sheet,
                container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSelectShopListener = (ShopClick) getActivity();

        RecyclerView aRecyclerview = getView().findViewById(R.id.bottom_RV);
        aRecyclerview.setAdapter(new ShopAdapter(mCustomerList, this));

    }


    @Override
    public void onItemClick(int position) {
        mSelectShopListener.onShopCLick(position);
        dismiss();
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mSelectShopListener = (ShopClick) getActivity();
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
        }
    }
}
