package com.arasan.master.ui.settings;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.arasan.master.R;
import com.arasan.master.data.viewmodel.ConnexionViewModel;
import com.google.gson.Gson;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SettingsFragment extends Fragment {

    Switch mSwitch;
    TextView mVersion;
    ConnexionViewModel mConnectionViewModel;
    RelativeLayout mLayout;
    String AppVersion;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mConnectionViewModel = new ViewModelProvider(this).get(ConnexionViewModel.class);
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mLayout = view.findViewById(R.id.appversiolayout);
        mSwitch = view.findViewById(R.id.approve);
        mVersion =  view.findViewById(R.id.version);
        ((AppCompatActivity) requireActivity()).getSupportActionBar().setTitle("Settings");

        mConnectionViewModel.GetAppVersion();
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                mConnectionViewModel.UpdateApprovedStatus(b ? "Y" : "N");
            }
        });
        mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Enter App Version");

                // set the custom layout
                final View customLayout = getLayoutInflater().inflate(R.layout.custom_layout, null);
                builder.setView(customLayout);

                // add a button
                builder.setPositiveButton("OK", (dialog, which) -> {
                    // send data from the AlertDialog to the Activity
                    EditText editText = customLayout.findViewById(R.id.editText);
                    sendDialogDataToActivity(editText.getText().toString());
                });
                // create and show the alert dialog
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        ListenForAppUpdates();
        ListenForUpdateAppVersion();
        ListenForUpdateApprovedStatus();
    }

    private void sendDialogDataToActivity(String toString) {
        Log.e("sendDialogDataToActivity", toString );
        mConnectionViewModel.UpdateAppVersion(toString);
    }

    private void ListenForUpdateAppVersion() {
        mConnectionViewModel.mUpdateAppVersion.observe(getViewLifecycleOwner(),updateAppVersion ->{
            if(updateAppVersion.message.equalsIgnoreCase("Successful"))
            {
                mVersion.setText(updateAppVersion.data);
                AppVersion = updateAppVersion.data;
            }
        });
    }

    private void ListenForUpdateApprovedStatus() {
        mConnectionViewModel.mUpdateApprovedStatus.observe(getViewLifecycleOwner(),updateApprovedStatus ->{
            if(updateApprovedStatus.message.equalsIgnoreCase("Successful"))
            {
                Toast.makeText(getContext(), "Successfully Updated", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void ListenForAppUpdates() {
        mConnectionViewModel.mGetAppVersion.observe(getViewLifecycleOwner(),getAppVersion ->{
            Log.e("Test", new Gson().toJson(getAppVersion));
            if(getAppVersion.message.equalsIgnoreCase("Successful"))
            {
                mSwitch.setChecked(getAppVersion.version.approvedStatus.equalsIgnoreCase("Y"));
            }
            mVersion.setText(getAppVersion.version.version);
        });
    }
}