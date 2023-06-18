package com.arasan.master.ui.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arasan.master.R;
import com.arasan.master.di.listeners.ItemClick;
import com.arasan.master.ui.model.Home;

import java.util.ArrayList;


public class HomeFragment extends Fragment implements ItemClick {

    RecyclerView mRecyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((AppCompatActivity) requireActivity()).getSupportActionBar().setTitle("Dashboard");
        mRecyclerView = view.findViewById(R.id.dashboard);
        ArrayList<Home> aHomeList = new ArrayList<>();
        aHomeList.add(new Home(R.drawable.a1,"Status"));
        aHomeList.add(new Home(R.drawable.a1,"User"));
        aHomeList.add(new Home(R.drawable.a1,"Feedback"));
        aHomeList.add(new Home(R.drawable.a1,"Settings"));
        aHomeList.add(new Home(R.drawable.a1,"Report"));
        GridLayoutManager layoutManager=new GridLayoutManager(getContext(),2);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(new HomeAdapter(aHomeList,this::onItemClick));
    }

    @Override
    public void onItemClick(int position) {
        if(position == 0)
        {
            Navigation.findNavController(getView()).navigate(R.id.action_homeFragment_to_pendingServiceProvider);
        }
        else if(position == 2)
        {
            Navigation.findNavController(getView()).navigate(R.id.action_homeFragment_to_feedbackFragment);
        }
        else if(position == 3)
        {
            Navigation.findNavController(getView()).navigate(R.id.action_homeFragment_to_settingsFragment);
        }
        else if(position == 4)
        {
            Navigation.findNavController(getView()).navigate(R.id.action_homeFragment_to_reportFragment);
        }
    }
}