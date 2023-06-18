package com.arasan.rfidtest.data.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.arasan.rfidtest.data.network.model.TvShowItem;
import com.arasan.rfidtest.data.repository.TvShowRepo;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class TvShowViewModel extends ViewModel {

    private final TvShowRepo mRetrofitRepository;
    private MutableLiveData<ArrayList<TvShowItem>> postsLiveData;

    @Inject
    public TvShowViewModel(TvShowRepo retroRepository) {
        this.mRetrofitRepository = retroRepository;
    }

    public LiveData<ArrayList<TvShowItem>> getPostsLiveData() {
        if (postsLiveData == null) {
            postsLiveData = new MutableLiveData<>();
        }
        mRetrofitRepository.GetTvShows(postsLiveData);
        return postsLiveData;
    }

}
