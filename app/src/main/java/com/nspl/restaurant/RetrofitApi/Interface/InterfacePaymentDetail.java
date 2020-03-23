package com.nspl.restaurant.RetrofitApi.Interface;

import com.nspl.restaurant.RetrofitApi.ApiClasses.PaymentDetails.ClsPayDetail;

import retrofit2.Call;
import retrofit2.http.GET;

public interface InterfacePaymentDetail {

    @GET("Service/GetPayDetailsList")
    Call<ClsPayDetail> GetPaymentDetailList();
}
