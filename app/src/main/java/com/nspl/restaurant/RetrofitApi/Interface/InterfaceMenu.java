package com.nspl.restaurant.RetrofitApi.Interface;

import com.nspl.restaurant.RetrofitApi.ApiClasses.Menu.ClsMenuResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface InterfaceMenu {

    @GET("Service/getMenu")
    Call<ClsMenuResponse> GetMenuList(@Query("employeeID") String employeeID,
                                     @Query("departmentIDs") String departmentIDs);
}
