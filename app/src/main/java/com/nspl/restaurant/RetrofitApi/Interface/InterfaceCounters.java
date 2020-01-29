package com.nspl.restaurant.RetrofitApi.Interface;

import com.nspl.restaurant.RetrofitApi.ApiClasses.Counters.ClsCounterResponse;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Menu.ClsMenuResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface InterfaceCounters {

    @GET("Service/getCounter")
    Call<ClsCounterResponse> GetMenuList(@Query("counterIDs") String counterIDs,
                                         @Query("BRANCH_ID") String BRANCH_ID);

}
