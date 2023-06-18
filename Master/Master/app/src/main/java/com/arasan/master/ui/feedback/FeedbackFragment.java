package com.arasan.master.ui.feedback;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.arasan.master.R;
import com.arasan.master.data.network.response.Feedback;
import com.arasan.master.data.viewmodel.ConnexionViewModel;
import com.arasan.master.di.listeners.ItemClick;
import com.arasan.master.di.utility.Utils;
import com.arasan.master.ui.feedback.adapter.FeedbackAdapter;
import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class FeedbackFragment extends Fragment implements ItemClick {

    ConnexionViewModel mConnectionViewModel;
    ImageButton mSend;
    RecyclerView mRecyclerView;
    List<Feedback> mFeedbackList;
    FeedbackAdapter adapter;
    AppCompatEditText mMessageBox;
    RelativeLayout mRelativeLayout;
    int SelectedPosition;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mConnectionViewModel = new ViewModelProvider(this).get(ConnexionViewModel.class);
        return inflater.inflate(R.layout.fragment_feedback, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRelativeLayout = view.findViewById(R.id.feedback_parent);
        mSend = view.findViewById(R.id.search_btn);
        mMessageBox = view.findViewById(R.id.search_field);
        mRecyclerView = view.findViewById(R.id.feedback_recyclerView);
        mConnectionViewModel.GetFeedback("126161");
        mConnectionViewModel.UpdateFeedbackStatus("126161");
        listenForData();
        listenForFeedbackData();
        listenForFeedbackUpdateStatus();
        listenForDeleteFeedback();
        mSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!TextUtils.isEmpty(mMessageBox.getText().toString()))
                {
                    HashMap<String, String> aData = new HashMap<>();
                    //aData.put("id", PreferenceManager.getPrefInstance(getContext()).getID());
                    aData.put("id", "126161");
                    aData.put("type", "ADMIN");
                    aData.put("message", mMessageBox.getText().toString());
                    aData.put("name", "Kavya");
                    aData.put("phonenumber", "1234567890");
                    aData.put("status", "Read");
                    mConnectionViewModel.AddFeedback(aData);
                }
                else {
                    Utils.ShowSnackBarRelativeLayout(mRelativeLayout, "Please Enter Message");
                }
            }
        });
    }

    private void listenForDeleteFeedback() {
        mConnectionViewModel.mDeleteFeedback.observe(getViewLifecycleOwner(),deleteStatus ->{
            if(deleteStatus.message.equalsIgnoreCase("Successful"))
            {
                Log.e("Update", new Gson().toJson(deleteStatus) );
                mFeedbackList.remove(SelectedPosition);
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void listenForFeedbackUpdateStatus() {
        mConnectionViewModel.mUpdateFeedbackStatus.observe(getViewLifecycleOwner(), updateStatus->{
            if(updateStatus.message.equalsIgnoreCase("Successful"))
            {
                Log.e("Update", new Gson().toJson(updateStatus) );

            }
        });
    }

    private void listenForFeedbackData() {
        mConnectionViewModel.mGetFeedback.observe(getViewLifecycleOwner(), getFeedback -> {
            Log.e("getFeedback", new Gson().toJson(getFeedback));
            mFeedbackList = getFeedback.feedback;
            adapter = new FeedbackAdapter(getContext(), mFeedbackList,this::onItemClick);
            mRecyclerView.setAdapter(adapter);
        });
    }

    private void listenForData() {
        mConnectionViewModel.mAddFeedback.observe(getViewLifecycleOwner(), getStatus -> {
            Log.e("Status", new Gson().toJson(getStatus));
            Feedback feedback = getStatus.userFeedBack;
            Date c = Calendar.getInstance().getTime();
            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm aa", Locale.getDefault());
            String formattedDate = df.format(c);
            Log.e("formate", formattedDate);
            try {
                Date date = df.parse(formattedDate);
                feedback.createdAt = date;
            } catch (ParseException e) {
                e.printStackTrace();
            }
            mFeedbackList.add(0,feedback);
            mMessageBox.setText("");
            adapter.notifyDataSetChanged();
        });
    }

    @Override
    public void onItemClick(int position) {
        SelectedPosition = position;
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Confirm");
        builder.setMessage("Are you sure Delete this Message?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                mConnectionViewModel.DeleteFeedback(mFeedbackList.get(SelectedPosition).uid);

                dialog.dismiss();
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }
}