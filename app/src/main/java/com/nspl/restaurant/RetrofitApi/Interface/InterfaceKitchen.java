package com.nspl.restaurant.RetrofitApi.Interface;

import com.nspl.restaurant.RetrofitApi.ApiClasses.Kitchen.ClsKitchenResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface InterfaceKitchen {

    @GET("kitchen/getSections")
    Call<ClsKitchenResponse> getKitchen (@Query("employeeId") String employeeId);
}
