package com.arasan.master.data.network.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchedUser {

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("shop_name")
    @Expose
    public String shopName;
    @SerializedName("pic")
    @Expose
    public String pic;
    @SerializedName("gallery_pic")
    @Expose
    public String galleryPic;
    @SerializedName("service")
    @Expose
    public String service;
}
