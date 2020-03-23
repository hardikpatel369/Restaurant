package com.nspl.restaurant.RetrofitApi.Interface;

import com.nspl.restaurant.RetrofitApi.ApiClasses.OrderActions.ClsReasonList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface InterfaceReasonList {

    @GET("Service/GetReasonlist")
    Call<ClsReasonList> GetRequestList();
}
