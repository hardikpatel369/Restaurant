package com.nspl.restaurant.RetrofitApi.Interface;


import com.nspl.restaurant.RetrofitApi.ApiClasses.RetailRecentOrder.ClsRetailRecentOrderResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface InterfaceRetailRecentOrder {

    @GET("Service/getRetailRecentorder")
    Call<ClsRetailRecentOrderResponse> retailRecentOrder(@Query("CounterID") int counterId,
                                                     @Query("DeptID") int departmentId);

}
