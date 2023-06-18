package com.arasan.rfidtest.data.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.arasan.rfidtest.data.network.model.TvShowItem;
import com.arasan.rfidtest.data.network.service.ConnexionAPI;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Module
@InstallIn(SingletonComponent.class)
public class TvShowRepo {

    @Inject
    ConnexionAPI retroServiceInterface;

    @Inject
    public TvShowRepo(ConnexionAPI retroService) {
        this.retroServiceInterface = retroService;
    }

    public void GetTvShows(MutableLiveData<ArrayList<TvShowItem>> liveData)
    {
        Call<ArrayList<TvShowItem>> call = retroServiceInterface.GetTvShows();
        call.enqueue(new Callback<ArrayList<TvShowItem>>() {
            @Override
            public void onResponse(Call<ArrayList<TvShowItem>> call, Response<ArrayList<TvShowItem>> response) {
                liveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<TvShowItem>> call, Throwable t) {
                liveData.postValue(null);
                Log.e( "onFailure: ",t.getMessage() );
            }
        });
    }


}
