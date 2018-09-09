package com.example.root.showuremo.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by hp on 28-01-2018.
 */

public class SendData {
    @SerializedName("image")
    @Expose
    private String image;

    public void setImage(String image){this.image = image;}

}
