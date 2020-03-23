package com.nspl.restaurant.RetrofitApi.Interface;

import com.nspl.restaurant.RetrofitApi.ApiClasses.MobileNo.ClsMobileNoResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface InterfaceMobileNo {

    @GET("Service/getCustomerAPI")
    Call<ClsMobileNoResponse> GetTaxSlab(@Query("MobileNo") String mobileNo);
}
