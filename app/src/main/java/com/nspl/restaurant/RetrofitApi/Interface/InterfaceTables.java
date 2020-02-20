package com.nspl.restaurant.RetrofitApi.Interface;

import com.nspl.restaurant.RetrofitApi.ApiClasses.Tables.ClsTablesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface InterfaceTables {

  /*  @GET("Service/{Mode}")
    Call<ClsTablesResponse> GetTables(@Path("Mode") String Mode,
                                      @Query("counterID") String counterID,
                                      @Query("DepartmentID") String DepartmentIDs,
                                      @Query("BRANCH_ID") String BRANCH_ID);*/


    @GET("Service/getTableList")
    Call<ClsTablesResponse> GetTables(@Query("DepartmentID") int DepartmentID);
}
