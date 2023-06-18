package com.arasan.master.data.network.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Feedback {

    @SerializedName("uid")
    @Expose
    public String uid;

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("phonenumber")
    @Expose
    public String phonenumber;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("created_at")
    @Expose
    public Date createdAt;
}
