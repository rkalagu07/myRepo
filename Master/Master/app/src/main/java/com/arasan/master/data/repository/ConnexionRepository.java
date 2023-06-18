package com.arasan.master.data.repository;


import androidx.lifecycle.MutableLiveData;

import com.arasan.master.data.network.response.ConnectionRes;
import com.arasan.master.data.network.response.ResFeedback;
import com.arasan.master.data.network.service.ConnexionAPI;

import java.util.HashMap;

import javax.inject.Inject;

import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Module
@InstallIn(SingletonComponent.class)
public class ConnexionRepository {

    @Inject
    ConnexionAPI retroServiceInterface;

    @Inject
    public ConnexionRepository(ConnexionAPI retroService) {
        this.retroServiceInterface = retroService;
    }
    public void GetPendingService(MutableLiveData<ConnectionRes> liveData,String status)
    {
        Call<ConnectionRes> call = retroServiceInterface.PendingStatus(status);
        call.enqueue(new Callback<ConnectionRes>() {
            @Override
            public void onResponse(Call<ConnectionRes> call, Response<ConnectionRes> response) {
                if(response.isSuccessful()) {
                    liveData.postValue(response.body());
                } else {
                    liveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<ConnectionRes> call, Throwable t) {
                liveData.postValue(null);
            }
        });
    }

//    public void UpdateStatus(MutableLiveData<ConnectionRes> liveData,String status,String id)
//    {
//        Call<ConnectionRes> call = retroServiceInterface.UpdateStatus(status,id);
//        call.enqueue(new Callback<ConnectionRes>() {
//            @Override
//            public void onResponse(Call<ConnectionRes> call, Response<ConnectionRes> response) {
//                if(response.isSuccessful()) {
//                    liveData.postValue(response.body());
//                } else {
//                    liveData.postValue(null);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ConnectionRes> call, Throwable t) {
//                liveData.postValue(null);
//            }
//        });
//    }

    public void AddFeedback(HashMap<String,String> updateData, MutableLiveData<ResFeedback> liveData)
    {
        Call<ResFeedback> call = retroServiceInterface.AddFeedBack(updateData);
        call.enqueue(new Callback<ResFeedback>() {
            @Override
            public void onResponse(Call<ResFeedback> call, Response<ResFeedback> response) {
                if(response.isSuccessful()) {
                    liveData.postValue(response.body());
                } else {
                    liveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<ResFeedback> call, Throwable t) {
                liveData.postValue(null);
            }
        });
    }

    public void GetFeedback(String id,MutableLiveData<ResFeedback> liveData) {
        Call<ResFeedback> call = retroServiceInterface.GetFeedback(id);
        call.enqueue(new Callback<ResFeedback>() {
            @Override
            public void onResponse(Call<ResFeedback> call, Response<ResFeedback> response) {
                if(response.isSuccessful()) {
                    liveData.postValue(response.body());
                } else {
                    liveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<ResFeedback> call, Throwable t) {
                liveData.postValue(null);
            }
        });
    }

    public void UpdateFeedbackStatus(String id,MutableLiveData<ConnectionRes> liveData) {
        Call<ConnectionRes> call = retroServiceInterface.UpdateFeedBackStatus(id);
        call.enqueue(new Callback<ConnectionRes>() {
            @Override
            public void onResponse(Call<ConnectionRes> call, Response<ConnectionRes> response) {
                if(response.isSuccessful()) {
                    liveData.postValue(response.body());
                } else {
                    liveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<ConnectionRes> call, Throwable t) {
                liveData.postValue(null);
            }
        });

    }
    public void DeleteFeedback(String id,MutableLiveData<ConnectionRes> liveData) {
        Call<ConnectionRes> call = retroServiceInterface.DeleteFeedback(id);
        call.enqueue(new Callback<ConnectionRes>() {
            @Override
            public void onResponse(Call<ConnectionRes> call, Response<ConnectionRes> response) {
                if(response.isSuccessful()) {
                    liveData.postValue(response.body());
                } else {
                    liveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<ConnectionRes> call, Throwable t) {
                liveData.postValue(null);
            }
        });

    }
    public void GetAppVersion( MutableLiveData<ConnectionRes> liveData) {
        Call<ConnectionRes> call = retroServiceInterface.GetAppUpdate();
        call.enqueue(new Callback<ConnectionRes>() {
            @Override
            public void onResponse(Call<ConnectionRes> call, Response<ConnectionRes> response) {
                if(response.isSuccessful()) {
                    liveData.postValue(response.body());
                } else {
                    liveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<ConnectionRes> call, Throwable t) {
                liveData.postValue(null);
            }
        });
    }

    public void UpdateAppVersion(String version,MutableLiveData<ConnectionRes> liveData) {
        Call<ConnectionRes> call = retroServiceInterface.UpdateAppVersion(version);
        call.enqueue(new Callback<ConnectionRes>() {
            @Override
            public void onResponse(Call<ConnectionRes> call, Response<ConnectionRes> response) {
                if(response.isSuccessful()) {
                    liveData.postValue(response.body());
                } else {
                    liveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<ConnectionRes> call, Throwable t) {
                liveData.postValue(null);
            }
        });

    }
    public void UpdateAppApprovedStatus(String approved_status,MutableLiveData<ConnectionRes> liveData) {
        Call<ConnectionRes> call = retroServiceInterface.UpdateApprovedStatus(approved_status);
        call.enqueue(new Callback<ConnectionRes>() {
            @Override
            public void onResponse(Call<ConnectionRes> call, Response<ConnectionRes> response) {
                if(response.isSuccessful()) {
                    liveData.postValue(response.body());
                } else {
                    liveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<ConnectionRes> call, Throwable t) {
                liveData.postValue(null);
            }
        });

    }

    public void GetReportData( MutableLiveData<ConnectionRes> liveData,String status) {
        Call<ConnectionRes> call = retroServiceInterface.GetReportData(status);
        call.enqueue(new Callback<ConnectionRes>() {
            @Override
            public void onResponse(Call<ConnectionRes> call, Response<ConnectionRes> response) {
                if(response.isSuccessful()) {
                    liveData.postValue(response.body());
                } else {
                    liveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<ConnectionRes> call, Throwable t) {
                liveData.postValue(null);
            }
        });
    }
    public void UpdateReportStatus(String reportstatus,MutableLiveData<ConnectionRes> liveData) {
        Call<ConnectionRes> call = retroServiceInterface.UpdateReport(reportstatus);
        call.enqueue(new Callback<ConnectionRes>() {
            @Override
            public void onResponse(Call<ConnectionRes> call, Response<ConnectionRes> response) {
                if(response.isSuccessful()) {
                    liveData.postValue(response.body());
                } else {
                    liveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<ConnectionRes> call, Throwable t) {
                liveData.postValue(null);
            }
        });

    }
    public void UpdateStatusProfile(HashMap<String,String> updateData, MutableLiveData<ConnectionRes> liveData)
    {
        Call<ConnectionRes> call = retroServiceInterface.UpdateStatus(updateData);
        call.enqueue(new Callback<ConnectionRes>() {
            @Override
            public void onResponse(Call<ConnectionRes> call, Response<ConnectionRes> response) {
                if(response.isSuccessful()) {
                    liveData.postValue(response.body());
                } else {
                    liveData.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<ConnectionRes> call, Throwable t) {
                liveData.postValue(null);
            }
        });
    }
}
