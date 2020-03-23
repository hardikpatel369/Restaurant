package com.nspl.restaurant.RetrofitApi.Interface;

import com.nspl.restaurant.RetrofitApi.ApiClasses.OrderActions.ClsReturnReplace;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface InterfaceReturnOrReplace {

    @POST("Service/AddItemBill")
    Call<ClsReturnReplace> returnReplace(@Query("ORDER_DETAIL_ID") int orderDetailId,
                                         @Query("Status") boolean status);
}
