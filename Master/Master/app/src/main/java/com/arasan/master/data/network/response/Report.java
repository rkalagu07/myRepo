package com.arasan.master.data.network.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Report {

    @SerializedName("uid")
    @Expose
    public String uid;

    @SerializedName("pic")
    @Expose
    public String pic;
    @SerializedName("service")
    @Expose
    public String service;
    @SerializedName("phonenumber")
    @Expose
    public String phonenumber;
    @SerializedName("shop_name")
    @Expose
    public String shopName;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("user_id")
    @Expose
    public String userId;
    @SerializedName("provider_id")
    @Expose
    public String providerId;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("check_read")
    @Expose
    public String checkRead;
    @SerializedName("date_time")
    @Expose
    public String dateTime;
}
