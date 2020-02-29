package com.nspl.restaurant.RetrofitApi.Interface;

import com.nspl.restaurant.RetrofitApi.ApiClasses.OrderActions.ClsConfirmOrderResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface InterfaceConfirmOrder {

    @GET("Service/ConfirmorderPrintKOT")
    Call<ClsConfirmOrderResponse> GetConfirmOrder (@Query("orderId") int orderId);
}
