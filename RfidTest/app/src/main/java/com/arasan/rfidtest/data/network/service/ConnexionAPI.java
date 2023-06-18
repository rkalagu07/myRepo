package com.arasan.rfidtest.data.network.service;

import com.arasan.rfidtest.data.network.model.TvShowItem;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ConnexionAPI {

    @GET("shows")
    Call<ArrayList<TvShowItem>> GetTvShows();
}
