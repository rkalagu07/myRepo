package com.arasan.master.data.network.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResFeedback {

    @SerializedName("Message")
    @Expose
    public String message;
    @SerializedName("Feedback")
    @Expose
    public List<Feedback> feedback;

    @SerializedName("UserFeedBack")
    @Expose
    public Feedback userFeedBack;
}
