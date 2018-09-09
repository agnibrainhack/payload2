package com.example.root.showuremo.rest;

import com.example.root.showuremo.pojo.ImageUpload;
import com.example.root.showuremo.pojo.SendData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by hp on 28-01-2018.
 */

public interface ApiInterface {
    @Headers({"Content-Type: application/json"})
    @POST("api/note/")
    Call<ImageUpload> savePost(@Body SendData data);

}
