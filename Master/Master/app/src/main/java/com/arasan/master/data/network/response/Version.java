package com.arasan.master.data.network.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Version {

    @SerializedName("uid")
    @Expose
    public Integer uid;
    @SerializedName("version")
    @Expose
    public String version;
    @SerializedName("approved_status")
    @Expose
    public String approvedStatus;
}
