package com.nspl.restaurant.RetrofitApi.Interface;

import com.nspl.restaurant.RetrofitApi.ApiClasses.OrderActions.ClsReturnReplace;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface InterfaceReturnReplace {

    @GET("Service/ReturnReplaceItemUpdate")
    Call<ClsReturnReplace> postReturnReplace (@Query("REMARK") String remark ,
                                              @Query("Wastage") boolean wastage ,
                                              @Query("ORDER_DETAIL_ID") int OrderDetailID,
                                              @Query("ORDER_ID") int OrderID,
                                              @Query("STATUS") String status);
}
