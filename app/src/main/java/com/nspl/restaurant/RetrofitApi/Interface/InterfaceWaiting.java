package com.nspl.restaurant.RetrofitApi.Interface;

import com.nspl.restaurant.RetrofitApi.ApiClasses.Waiting.ClsWaitingResponse;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Waiting.ClsWaitingResponseCls;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface InterfaceWaiting {
    /* @POST("Service/crudWaitingAPI")
     Call<ClsWaitingResponseCls> postWaitingList();//todo
 */
    @POST("Service/crudWaitingAPI")
    Call<ClsWaitingResponse> postWaitingList(@Body ClsWaitingResponse clsWaitingResponse);

    @GET("Service/getWaitingList")
    Call<ClsWaitingResponse> GetWaitingList(@Query("BRANCH_ID") int BranchId);
}
