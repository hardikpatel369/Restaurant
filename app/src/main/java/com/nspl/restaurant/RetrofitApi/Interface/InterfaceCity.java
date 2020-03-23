package com.nspl.restaurant.RetrofitApi.Interface;

import com.nspl.restaurant.RetrofitApi.ApiClasses.City.ClsCityResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface InterfaceCity {

    @GET("Service/GetCity")
    Call<ClsCityResponse> GetCity();
}
