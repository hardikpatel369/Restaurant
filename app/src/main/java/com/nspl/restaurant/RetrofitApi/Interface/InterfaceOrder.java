package com.nspl.restaurant.RetrofitApi.Interface;

import com.nspl.restaurant.RetrofitApi.ApiClasses.Order.ClsOrder;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Order.ClsOrderResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface InterfaceOrder {


    @POST("Service/addtoOrder")
    Call<ClsOrderResponse> addOrder(@Body ClsOrder clsOrder);
}
