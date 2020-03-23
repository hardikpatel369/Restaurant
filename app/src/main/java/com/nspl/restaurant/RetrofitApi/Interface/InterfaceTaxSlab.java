package com.nspl.restaurant.RetrofitApi.Interface;

import com.nspl.restaurant.RetrofitApi.ApiClasses.tax.ClsTaxSlabResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface InterfaceTaxSlab {

    @GET("Service/GetTaxSlab")
    Call<ClsTaxSlabResponse> GetTaxSlab(@Query("BRANCHID") String branchId);

}
