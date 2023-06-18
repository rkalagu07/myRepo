package com.arasan.master.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.arasan.master.R;
import com.arasan.master.data.network.response.SearchedUser;
import com.arasan.master.data.viewmodel.ConnexionViewModel;
import com.arasan.master.di.listeners.ItemClick;
import com.arasan.master.di.utility.Utils;
import com.arasan.master.ui.status.StatusProfilesAdapter;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class PendingServiceFragment extends Fragment implements ItemClick {

    ConnexionViewModel mViewModel;
    RecyclerView mPendingRecyclerView;
    List<SearchedUser> mSearchedUsers;
    int SelectedPosition;
    StatusProfilesAdapter mStatusProfilesAdapter;
    RelativeLayout mLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pending_service_provider, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mLayout = view.findViewById(R.id.parent);
        mPendingRecyclerView = view.findViewById(R.id.pending_fragment);
        mViewModel = new ViewModelProvider(this).get(ConnexionViewModel.class);
        mViewModel.CheckUser("NotVerified");
        listenForData();
        listenForStatus();
    }

    private void listenForStatus() {
        mViewModel.mUpdateProfileStatus.observe(getViewLifecycleOwner(), getStatus -> {
            Log.e("Get Status", new Gson().toJson(getStatus));
            if (getStatus.message.equalsIgnoreCase("Successful")) {
                mSearchedUsers.remove(SelectedPosition);
                mStatusProfilesAdapter.notifyDataSetChanged();
                Utils.ShowSnackBarRelativeLayout(mLayout,"Status Update Successfully");
            }
        });
    }

    private void listenForData() {
        mViewModel.mGetPendingServiceProvider.observe(getViewLifecycleOwner(), getUsers -> {
            mSearchedUsers = getUsers.searchedUsers;
            mStatusProfilesAdapter = new StatusProfilesAdapter(mSearchedUsers, this);
            mPendingRecyclerView.setAdapter(mStatusProfilesAdapter);
        });
    }

    @Override
    public void onItemClick(int position) {
        SelectedPosition = position;
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Select Status");
        String[] type = {"Verified", "Not Upload Internet Photos", "Profile Mismatched", "Blocked"};
        builder.setItems(type, (dialog, which) -> {
            if (type[which].equalsIgnoreCase("Verified")) {
                loadApi(mSearchedUsers.get(position).id, type[which],"https://services-spiro.s3.ap-south-1.amazonaws.com/t2.jpg","Your Account is Verified","N","Congratulations");
            } else if (type[which].equalsIgnoreCase("Not Upload Internet Photos")) {
                loadApi(mSearchedUsers.get(position).id, type[which],"https://services-spiro.s3.ap-south-1.amazonaws.com/t3.jpg","Do Not Upload Internet Photos","N","Your Account is Not Accepted");
            } else if (type[which].equalsIgnoreCase("Profile Mismatched")) {
                loadApi(mSearchedUsers.get(position).id, type[which],"https://services-spiro.s3.ap-south-1.amazonaws.com/t3.jpg","You Provided False Data","N","Your Account is Not Accepted");
            } else if (type[which].equalsIgnoreCase("Blocked")) {
                loadApi(mSearchedUsers.get(position).id, type[which],"https://services-spiro.s3.ap-south-1.amazonaws.com/t3.jpg","You Involved in False Activities","N","Your Account is Not Accepted");
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void loadApi(String id, String status, String emoji, String message, String notification_status, String title) {
        HashMap<String, String> aData = new HashMap<>();
        aData.put("id", id);
        aData.put("status", status);
        aData.put("emoji", emoji);
        aData.put("message", message);
        aData.put("notification_status", notification_status);
        aData.put("title", title);
        mViewModel.UpdateProfileStatus(aData);
    }
}