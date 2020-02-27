package com.nspl.restaurant.RetrofitApi.Interface;

import com.nspl.restaurant.RetrofitApi.ApiClasses.Order.ClsOrderSummaryResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface InterfaceOrderDetail {

    @GET("Service/orderDetail")
    Call<ClsOrderSummaryResponse> GetOrderSummary (@Query("orderId") int orderId);
}
