package com.android.busroutes.sample.rest;

import com.android.busroutes.sample.rest.response.ImagesResponse;
import retrofit2.Call;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("5882b4b6280000421ccbd489")
    Call<ImagesResponse> getImages();

}