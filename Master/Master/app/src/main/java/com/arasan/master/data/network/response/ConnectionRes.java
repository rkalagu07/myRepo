package com.arasan.master.data.network.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ConnectionRes {
    @SerializedName("Message")
    @Expose
    public String message;

    @SerializedName("SearchedUsers")
    @Expose
    public List<SearchedUser> searchedUsers;

    @SerializedName("version")
    @Expose
    public Version version;

    @SerializedName("Data")
    @Expose
    public String data;

    @SerializedName("Report")
    @Expose
    public ArrayList<Report> report;
}
