package com.arasan.master.data.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.arasan.master.data.network.response.ConnectionRes;
import com.arasan.master.data.network.response.ResFeedback;
import com.arasan.master.data.network.response.ServerMsg;
import com.arasan.master.data.repository.ConnexionRepository;
import java.util.HashMap;
import javax.inject.Inject;
import dagger.hilt.android.lifecycle.HiltViewModel;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

@HiltViewModel
public class ConnexionViewModel extends ViewModel {
    private final ConnexionRepository mRetrofitRepository;
    public MutableLiveData<ConnectionRes> mGetPendingServiceProvider = new MutableLiveData<>();
    public MutableLiveData<ConnectionRes> mUpdateStatus = new MutableLiveData<>();
    public MutableLiveData<ResFeedback> mAddFeedback = new MutableLiveData<>();
    public MutableLiveData<ResFeedback> mGetFeedback = new MutableLiveData<>();
    public MutableLiveData<ConnectionRes> mUpdateFeedbackStatus = new MutableLiveData<>();
    public MutableLiveData<ConnectionRes> mDeleteFeedback = new MutableLiveData<>();
    public MutableLiveData<ConnectionRes> mGetAppVersion = new MutableLiveData<>();
    public MutableLiveData<ConnectionRes> mUpdateAppVersion = new MutableLiveData<>();
    public MutableLiveData<ConnectionRes> mUpdateApprovedStatus = new MutableLiveData<>();
    public MutableLiveData<ConnectionRes> mGetReportData = new MutableLiveData<>();
    public MutableLiveData<ConnectionRes> mUpdateReportStatus = new MutableLiveData<>();
    public MutableLiveData<ConnectionRes> mUpdateProfileStatus = new MutableLiveData<>();
    @Inject
    public ConnexionViewModel(ConnexionRepository retroRepository) {
        this.mRetrofitRepository = retroRepository;
    }
    public void CheckUser(String status) {
        mRetrofitRepository.GetPendingService(mGetPendingServiceProvider,status);
    }
//    public void UpdateStatus(String status,String id) {
//        mRetrofitRepository.UpdateStatus(mUpdateStatus,status,id);
//    }
    public void AddFeedback(HashMap<String,String> partData)
    {
        mRetrofitRepository.AddFeedback(partData,mAddFeedback);
    }
    public void GetFeedback(String id)
    {
        mRetrofitRepository.GetFeedback(id,mGetFeedback);
    }
    public void UpdateFeedbackStatus(String id)
    {
        mRetrofitRepository.UpdateFeedbackStatus(id,mUpdateFeedbackStatus);
    }
    public void DeleteFeedback(String id)
    {
        mRetrofitRepository.DeleteFeedback(id,mDeleteFeedback);
    }
    public void GetAppVersion() {
        mRetrofitRepository.GetAppVersion(mGetAppVersion);
    }

    public void UpdateAppVersion(String version)
    {
        mRetrofitRepository.UpdateAppVersion(version,mUpdateAppVersion);
    }
    public void UpdateApprovedStatus(String approved_status)
    {
        mRetrofitRepository.UpdateAppApprovedStatus(approved_status,mUpdateApprovedStatus);
    }
    public void GetReportData(String status) {
        mRetrofitRepository.GetReportData(mGetReportData,status);
    }
    public void UpdateReportStatus(String user_id)
    {
        mRetrofitRepository.UpdateReportStatus(user_id,mUpdateReportStatus);
    }
    public void UpdateProfileStatus(HashMap<String,String> partData)
    {
        mRetrofitRepository.UpdateStatusProfile(partData,mUpdateProfileStatus);
    }
}
