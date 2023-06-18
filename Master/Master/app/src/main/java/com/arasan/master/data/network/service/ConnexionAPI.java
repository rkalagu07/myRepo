package com.arasan.master.data.network.service;



import com.arasan.master.data.network.response.ConnectionRes;
import com.arasan.master.data.network.response.ResFeedback;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ConnexionAPI {

//    @PUT("setStatus")
//    Call<ConnectionRes> UpdateStatus(@Query("status") String status,@Query("id") String id);

    @GET("pending_status")
    Call<ConnectionRes> PendingStatus(@Query("status") String status);

    @FormUrlEncoded
    @POST("feedback")
    Call<ResFeedback> AddFeedBack(@FieldMap HashMap<String, String> data);

    @GET("feedback")
    Call<ResFeedback> GetFeedback(@Query("id") String id);

    @PUT("feedback")
    Call<ConnectionRes> UpdateFeedBackStatus(@Query("id") String id);

    @DELETE("feedback")
    Call<ConnectionRes> DeleteFeedback(@Query("id") String id);

    @GET("app_update")
    Call<ConnectionRes> GetAppUpdate();

    @PUT("app_update")
    Call<ConnectionRes> UpdateAppVersion(@Query("version") String version);

    @PUT("change_approved_status")
    Call<ConnectionRes> UpdateApprovedStatus(@Query("approved_status") String approved_status);

    @GET("get_report")
    Call<ConnectionRes> GetReportData(@Query("status") String status);

    @PUT("update_report")
    Call<ConnectionRes> UpdateReport(@Query("user_id") String user_id);

    @FormUrlEncoded
    @POST("setStatus")
    Call<ConnectionRes> UpdateStatus(@FieldMap HashMap<String, String> data);
}
