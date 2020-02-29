package com.nspl.restaurant.RetrofitApi.Interface;

import com.nspl.restaurant.RetrofitApi.ApiClasses.OrderActions.ClsOrderPrintDeleteResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface InterfaceOrderPrintDelete {

    @GET("Service/OrderItemStatus")
    Call<ClsOrderPrintDeleteResponse> GetPrintDelete (@Query("OrderDetailID") int OrderDetailID,
                                                      @Query("OrderID") int OrderID,
                                                      @Query("Mode") String Mode);
}
