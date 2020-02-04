package com.nspl.restaurant.RetrofitApi.Interface;

import com.nspl.restaurant.RetrofitApi.ApiClasses.ClsLogoutResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface InterfaceLogout {

    @GET("Service/Logout")
    Call<ClsLogoutResponse> postLogout(@Query("username") String username,
                                       @Query("imei") String imei,
                                       @Query("logoutdate") String logoutdate,
                                       @Query("logouttime") String logouttime);

}
