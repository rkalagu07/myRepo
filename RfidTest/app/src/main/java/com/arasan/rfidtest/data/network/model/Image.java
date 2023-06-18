package com.arasan.rfidtest.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Image {

    @SerializedName("medium")
    @Expose
    public String medium;

    @SerializedName("original")
    @Expose
    public String original;
}
