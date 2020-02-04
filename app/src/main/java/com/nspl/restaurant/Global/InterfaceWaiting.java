package com.nspl.restaurant.Global;

import com.nspl.restaurant.RetrofitApi.ApiClasses.Waiting.ClsWaitingResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface InterfaceWaiting {
    @GET("Service/crudWaitingAPI")
    Call<ClsWaitingResponse> getWaitingList();//todo



}
