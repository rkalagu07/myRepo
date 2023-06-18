package com.arasan.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ShopClick {

    Button mSheetClick;
    private CustomerShops_Sheet mCustomerShopsSheet;
    ArrayList<Shop> mShopList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSheetClick = findViewById(R.id.sheet_click);
        mSheetClick.setOnClickListener(v -> {

            mShopList = new ArrayList<>();
            mShopList.add(new Shop("ABC Pvt Ltd", "London", "1234567890"));
            mShopList.add(new Shop("DEF Pvt Ltd", "London", "1234567890"));
            mShopList.add(new Shop("GHJ Pvt Ltd", "London", "1234567890"));
            mShopList.add(new Shop("IJK Pvt Ltd", "London", "1234567890"));
            mShopList.add(new Shop("LMN Pvt Ltd", "London", "1234567890"));
            mShopList.add(new Shop("OPQ Pvt Ltd", "London", "1234567890"));
            mShopList.add(new Shop("RST Pvt Ltd", "London", "1234567890"));
            mShopList.add(new Shop("UVW Pvt Ltd", "London", "1234567890"));
            mShopList.add(new Shop("XYZ Pvt Ltd", "London", "1234567890"));
            mShopList.add(new Shop("QWE Pvt Ltd", "London", "1234567890"));
            mShopList.add(new Shop("BHJ Pvt Ltd", "London", "1234567890"));

            mCustomerShopsSheet = new CustomerShops_Sheet(mShopList);
            mCustomerShopsSheet.show(getSupportFragmentManager(), "Customer List");
        });
    }

    @Override
    public void onShopCLick(int position) {
        Toast.makeText(this, mShopList.get(position).Name, Toast.LENGTH_SHORT).show();
    }


}