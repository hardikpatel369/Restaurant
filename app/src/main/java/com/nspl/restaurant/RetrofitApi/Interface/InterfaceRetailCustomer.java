package com.nspl.restaurant.RetrofitApi.Interface;

import com.nspl.restaurant.RetrofitApi.ApiClasses.CustomerInfo.ClsRetailCustomer;
import com.nspl.restaurant.RetrofitApi.ApiClasses.CustomerInfo.ClsRetailCustomerResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface InterfaceRetailCustomer {

    @POST("Service/RetailCustomerInsert")
    Call<ClsRetailCustomerResponse> postCustomerInfo(@Body ClsRetailCustomer clsRetailCustomer);

/*
    @POST("Service/RetailCustomerInsert")
    Call<ClsRetailCustomer> postCustomerInfo(@Query("Name") String cName,
                                             @Query("Company") String cCompany,
                                             @Query("GSTIN") String cGSTIN,
                                             @Query("City") String cCity,
                                             @Query("MobileNo") String cMobileNo,
                                             @Query("EmployeeName") String cEmployeeName,
                                             @Query("EmployeeCode") String cEmployeeCode,
                                             @Query("BranchID") String cBranchId,
                                             @Query("OrderID") int cOrderId);
*/

}
