package com.nspl.restaurant.RetrofitApi.Interface;

import com.nspl.restaurant.RetrofitApi.ApiClasses.GenerateBill.ClsBillDetail;
import com.nspl.restaurant.RetrofitApi.ApiClasses.GenerateBill.ClsBillDetailResponse;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Order.ClsOrderResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface InterfaceGenerateBill {

    @POST("Service/GenerateBill")
    Call<ClsBillDetailResponse> generateBill(@Body ClsBillDetail clsBillDetail);

}
