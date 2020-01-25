package com.nspl.restaurant.RetrofitApi.Interface;

import com.nspl.restaurant.RetrofitApi.ApiClasses.ClsLoginResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface InterfaceLogin {

    @GET("Service/Login")
    Call<ClsLoginResponse> postLogin(@Query("username") String username,
                                     @Query("password") String password,
                                     @Query("imei") String imei,
                                     @Query("appVersion") String appVersion);


}
