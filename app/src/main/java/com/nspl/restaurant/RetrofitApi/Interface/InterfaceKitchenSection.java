package com.nspl.restaurant.RetrofitApi.Interface;

import com.nspl.restaurant.RetrofitApi.ApiClasses.Kitchen.ClsKitchenSectionResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface InterfaceKitchenSection {

    @GET("kitchen/getDisplay")
    Call<ClsKitchenSectionResponse> getKitchenSection (@Query("employeeId") String employeeId,
                                                       @Query("kitchenID") int kitchenID,
                                                       @Query("section") String section,
                                                       @Query("itemidlist") String itemidlist);

}
