package com.arasan.master.data.network.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ServerMsg {

    @SerializedName("Message")
    @Expose
    public String message;
}
