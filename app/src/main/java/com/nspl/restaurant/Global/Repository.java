package com.nspl.restaurant.Global;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nspl.restaurant.Activity.AddItemOrderActivity;
import com.nspl.restaurant.DataModel.ClsUserInfo;
import com.nspl.restaurant.RetrofitApi.ApiClasses.City.ClsCity;
import com.nspl.restaurant.RetrofitApi.ApiClasses.City.ClsCityResponse;
import com.nspl.restaurant.RetrofitApi.ApiClasses.CustomerInfo.ClsRetailCustomer;
import com.nspl.restaurant.RetrofitApi.ApiClasses.CustomerInfo.ClsRetailCustomerResponse;
import com.nspl.restaurant.RetrofitApi.ApiClasses.GenerateBill.ClsBillDetail;
import com.nspl.restaurant.RetrofitApi.ApiClasses.GenerateBill.ClsBillDetailResponse;
import com.nspl.restaurant.RetrofitApi.ApiClasses.GenerateBill.ClsPaymentDetail;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Login.ClsLoginResponse;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Login.ClsLogoutResponse;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Counters.ClsCounterResponse;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Menu.ClsMenuResponse;
import com.nspl.restaurant.RetrofitApi.ApiClasses.MobileNo.ClsMobileNoResponse;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Order.ClsOrder;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Order.ClsOrderResponse;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Order.ClsOrderSummaryResponse;
import com.nspl.restaurant.RetrofitApi.ApiClasses.OrderActions.ClsConfirmOrderResponse;
import com.nspl.restaurant.RetrofitApi.ApiClasses.OrderActions.ClsOrderPrintDeleteResponse;
import com.nspl.restaurant.RetrofitApi.ApiClasses.OrderActions.ClsReasonList;
import com.nspl.restaurant.RetrofitApi.ApiClasses.OrderActions.ClsReturnReplace;
import com.nspl.restaurant.RetrofitApi.ApiClasses.PaymentDetails.ClsPayDetail;
import com.nspl.restaurant.RetrofitApi.ApiClasses.RetailRecentOrder.ClsRetailRecentOrderResponse;
import com.nspl.restaurant.RetrofitApi.ApiClasses.ReturnReplace.CLsReturnReplaceResponse;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Tables.ClsTablesResponse;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Waiting.ClsRequestWaitingResponse;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Waiting.ClsWaitingResponse;
import com.nspl.restaurant.RetrofitApi.ApiClasses.tax.ClsTaxSlabResponse;
import com.nspl.restaurant.RetrofitApi.Interface.InterfaceCity;
import com.nspl.restaurant.RetrofitApi.Interface.InterfaceConfirmOrder;
import com.nspl.restaurant.RetrofitApi.Interface.InterfaceCounters;
import com.nspl.restaurant.RetrofitApi.Interface.InterfaceGenerateBill;
import com.nspl.restaurant.RetrofitApi.Interface.InterfaceLogin;
import com.nspl.restaurant.RetrofitApi.Interface.InterfaceLogout;
import com.nspl.restaurant.RetrofitApi.Interface.InterfaceMenu;
import com.nspl.restaurant.RetrofitApi.Interface.InterfaceMobileNo;
import com.nspl.restaurant.RetrofitApi.Interface.InterfaceOrder;
import com.nspl.restaurant.RetrofitApi.Interface.InterfaceOrderDetail;
import com.nspl.restaurant.RetrofitApi.Interface.InterfaceOrderPrintDelete;
import com.nspl.restaurant.RetrofitApi.Interface.InterfacePaymentDetail;
import com.nspl.restaurant.RetrofitApi.Interface.InterfaceReasonList;
import com.nspl.restaurant.RetrofitApi.Interface.InterfaceRetailCustomer;
import com.nspl.restaurant.RetrofitApi.Interface.InterfaceRetailRecentOrder;
import com.nspl.restaurant.RetrofitApi.Interface.InterfaceReturnOrReplace;
import com.nspl.restaurant.RetrofitApi.Interface.InterfaceReturnReplace;
import com.nspl.restaurant.RetrofitApi.Interface.InterfaceTables;
import com.nspl.restaurant.RetrofitApi.Interface.InterfaceTaxSlab;
import com.nspl.restaurant.RetrofitApi.Interface.InterfaceWaiting;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {

    private Context context;
    private ClsUserInfo obj = new ClsUserInfo();
    private SharedPreferences sharedPreferences;

    public Repository(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
    }

    /**
     * Login Api.
     *
     * @return Login Response.
     */
    public LiveData<ClsLoginResponse> LoginApi(String userName, String Password
            , String imei, String appVersion) {

        final MutableLiveData<ClsLoginResponse> loginData = new MutableLiveData<>();
        InterfaceLogin interfaceLogin = ApiClient.getRetrofitInstance().create(InterfaceLogin.class);
        Log.e("--URL--", "interfaceLogin: " + interfaceLogin.toString());
        Call<ClsLoginResponse> call = interfaceLogin.postLogin(userName, Password, "124", appVersion);
        Log.e("--URL--", "************************  before call : " + call.request().url());

        call.enqueue(new Callback<ClsLoginResponse>() {

            @Override
            public void onResponse(Call<ClsLoginResponse> call, Response<ClsLoginResponse> response) {
                Log.e("--URL--", "response: " + response);

                if (response.body() != null) {
                    String _response = response.body().toString();
                    Log.e("--response--", "response: " + _response);
//                    clsLoginResponseData = response.body();
                    if (response.code() == 200) {
                        loginData.setValue(response.body());

                        Gson gson = new Gson();
                        String jsonInString = gson.toJson(response.body());
                        Log.d("--URL--", "onResponse----LoginApi---: " + jsonInString);
                    }
                }
            }

            @Override
            public void onFailure(Call<ClsLoginResponse> call, Throwable t) {

                try {
                    loginData.setValue(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.d("--URL--", "onFailure:LoginApi " + t.toString());
            }
        });
        return loginData;
    }


    /**
     * Logout Api.
     *
     * @return Login Response.
     */
    public LiveData<ClsLogoutResponse> LogoutApi(String userName
            , String imei, String logoutdate, String logouttime) {

        final MutableLiveData<ClsLogoutResponse> LogoutData = new MutableLiveData<>();

        InterfaceLogout interfaceLogout = ApiClient.getRetrofitInstance().create(InterfaceLogout.class);
        Log.e("--URL--", "interfaceLogin: " + interfaceLogout.toString());

        Call<ClsLogoutResponse> call = interfaceLogout.postLogout(userName,
                imei, logoutdate,
                logouttime);

        Log.e("--URL--", "************  before call : "
                + call.request().url());
        call.enqueue(new Callback<ClsLogoutResponse>() {

            @Override
            public void onResponse(Call<ClsLogoutResponse> call, Response<ClsLogoutResponse> response) {

                if (response.body() != null && response.code() == 200) {
                    LogoutData.setValue(response.body());

                    Gson gson = new Gson();
                    String jsonInString = gson.toJson(response.body());
                    Log.d("--URL--", "onResponse----LogoutApi---: " + jsonInString);
                }
            }

            @Override
            public void onFailure(Call<ClsLogoutResponse> call, Throwable t) {
                try {
                    LogoutData.setValue(null);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.d("--URL--", "onFailure:LogoutApi " + t.toString());
            }
        });

        return LogoutData;

    }

    /**
     * Getting the Menu List.
     *
     * @return LiveData<ClsMenuResponse>
     */
    public LiveData<ClsMenuResponse> GetMenuList(int departmentId) {

        final MutableLiveData<ClsMenuResponse> MenuResponse = new MutableLiveData<>();
        InterfaceMenu interfaceLogout = ApiClient.getRetrofitInstance().create(InterfaceMenu.class);
        Log.e("--URL--", "interfaceLogin: " + interfaceLogout.toString());
        obj = ClsGlobal.getUserInfo(context);

        String employeeId = sharedPreferences.getString("EMPLOYEE_ID", "NotFound");
//        Call<ClsMenuResponse> call = interfaceLogout.GetMenuList("7", "6");
        Call<ClsMenuResponse> call = interfaceLogout.GetMenuList(employeeId, departmentId);

        Log.e("--URL--", "************  before call : " + call.request().url());
        call.enqueue(new Callback<ClsMenuResponse>() {
            @Override
            public void onResponse(Call<ClsMenuResponse> call, Response<ClsMenuResponse> response) {
                if (response.body() != null && response.code() == 200) {
                    MenuResponse.setValue(response.body());

                    Gson gson = new Gson();
                    String jsonInString = gson.toJson(response.body());
                    Log.d("--URL--", "onResponse----GetMenuList---: " + jsonInString);
                }
            }

            @Override
            public void onFailure(Call<ClsMenuResponse> call, Throwable t) {
                try {
                    MenuResponse.setValue(null);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.d("--URL--", "onFailure:GetMenuList " + t.toString());
            }
        });

        return MenuResponse;

    }

    /**
     * Getting the Counters.
     *
     * @return Counters.
     */
    public LiveData<ClsCounterResponse> GetCounters() {

        final MutableLiveData<ClsCounterResponse> CountersResponse = new MutableLiveData<>();
        InterfaceCounters interfaceLogout = ApiClient.getRetrofitInstance().create(InterfaceCounters.class);
        Log.e("--URL--", "interfaceLogin: " + interfaceLogout.toString());
        obj = ClsGlobal.getUserInfo(context);

        String branchId = sharedPreferences.getString("BRANCH_ID", "NotFound");
        String counterId = sharedPreferences.getString("COUNTER_IDS", "NotFound");


//        Call<ClsCounterResponse> call = interfaceLogout.GetMenuList("1,2,5", obj.getBRANCH_ID());
//        Call<ClsCounterResponse> call = interfaceLogout.GetMenuList(obj.getCOUNTER_IDS(),obj.getBRANCH_ID());
        Call<ClsCounterResponse> call = interfaceLogout.GetMenuList(counterId, branchId);


        Log.e("--URL--", "************  before call : "
                + call.request().url());
        call.enqueue(new Callback<ClsCounterResponse>() {
            @Override
            public void onResponse(Call<ClsCounterResponse> call, Response<ClsCounterResponse> response) {

                if (response.body() != null && response.code() == 200) {
                    CountersResponse.setValue(response.body());

                    Gson gson = new Gson();
                    String jsonInString = gson.toJson(response.body());
                    Log.d("--URL--", "onResponse----GetCounters---: " + jsonInString);
                }
            }

            @Override
            public void onFailure(Call<ClsCounterResponse> call, Throwable t) {

                try {
                    CountersResponse.setValue(null);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.d("--URL--", "onFailure: GetCounters" + t.toString());
            }
        });

        return CountersResponse;
    }

    /**
     * Getting the List of Tables.
     *
     * @return LiveData<ClsTablesResponse>.
     */
    public LiveData<ClsTablesResponse> GetTables(int departmentId) {

        final MutableLiveData<ClsTablesResponse> TablesResponse = new MutableLiveData<>();
        InterfaceTables interfaceLogout = ApiClient.getRetrofitInstance().create(InterfaceTables.class);
        Log.e("--URL--", "interfaceLogin: " + interfaceLogout.toString());
        Log.e("--URL--", "Id: " + obj.getDEPARTMENT_IDS());
        Log.e("--URL--", "BranchId: " + obj.getBRANCH_ID());


        Log.e("--URL--", "departmentId: " + departmentId);

        Call<ClsTablesResponse> call = interfaceLogout.GetTables(departmentId);

        Log.e("--URL--", "************  before call : " + call.request().url());

        call.enqueue(new Callback<ClsTablesResponse>() {
            @Override
            public void onResponse(Call<ClsTablesResponse> call, Response<ClsTablesResponse> response) {

                if (response.body() != null && response.code() == 200) {
                    TablesResponse.setValue(response.body());

                    Gson gson = new Gson();
                    String jsonInString = gson.toJson(response.body());
                    Log.d("--URL--", "onResponse----GetTables---: " + jsonInString);
                }
            }

            @Override
            public void onFailure(Call<ClsTablesResponse> call, Throwable t) {
                try {
                    TablesResponse.setValue(null);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.d("--URL--", "onFailure:GetTables " + t.toString());
            }
        });
        return TablesResponse;
    }

    /**
     * Getting the List of Order.
     *
     * @return LiveData<ClsOrderSummaryResponse>.
     */
    public LiveData<ClsOrderSummaryResponse> GetOrderSummary(int orderId) {
        final MutableLiveData<ClsOrderSummaryResponse> OrderSummaryResponse = new MutableLiveData<>();
        InterfaceOrderDetail interfaceOrderDetail = ApiClient.getRetrofitInstance().create(InterfaceOrderDetail.class);

        Call<ClsOrderSummaryResponse> call = interfaceOrderDetail.GetOrderSummary(orderId);
        Log.e("--URL--", "************  before call : " + call.request().url());
        call.enqueue(new Callback<ClsOrderSummaryResponse>() {
            @Override
            public void onResponse(Call<ClsOrderSummaryResponse> call, Response<ClsOrderSummaryResponse> response) {
                if (response.body() != null && response.code() == 200) {
                    OrderSummaryResponse.setValue(response.body());

                    Gson gson = new Gson();
                    String jsonInString = gson.toJson(response.body());
                    Log.d("--URL--", "onResponse----GetOrderSummary---: " + jsonInString);
                }
            }

            @Override
            public void onFailure(Call<ClsOrderSummaryResponse> call, Throwable t) {
                try {
                    OrderSummaryResponse.setValue(null);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.d("--URL--", "onFailure:GetOrderSummary " + t.toString());
            }
        });
        return OrderSummaryResponse;
    }

    public LiveData<ClsWaitingResponse> postWaitingList(ClsWaitingResponse obj) {
        final MutableLiveData<ClsWaitingResponse> WaitingResponse = new MutableLiveData<>();
        InterfaceWaiting interfaceWaiting = ApiClient.getRetrofitInstance().create(InterfaceWaiting.class);

        Log.e("--Waiting--", "interfaceLogin: " + interfaceWaiting.toString());

        Gson gson = new Gson();
        String jsonInString = gson.toJson(interfaceWaiting);
        Log.e("--Waiting--", "GsonObj: " + jsonInString);

        //  InterfaceWaiting interfaceWaiting = ApiClient.getRetrofitInstance().create(InterfaceWaiting.class);

        Call<ClsWaitingResponse> call = interfaceWaiting.postWaitingList(obj);

        Log.e("--Waiting--", "interfaceLogin: " + interfaceWaiting.toString());
        Log.e("--Waiting--", "Url: " + call.request().url());


        call.enqueue(new Callback<ClsWaitingResponse>() {
            @Override
            public void onResponse(Call<ClsWaitingResponse> call, Response<ClsWaitingResponse>
                    response) {

                if (response.body() != null) {
                    Log.e("--Waiting--", "Message: " + response.body().getMESSAGE());
                    //  Toast.makeText(getContext(), response.body().getMESSAGE(), Toast.LENGTH_SHORT).show();
                    Log.e("--Waiting--", "success: " + response.body().getSUCCESS());
                    WaitingResponse.postValue(response.body());

                    Gson gson = new Gson();
                    String jsonInString = gson.toJson(response.body());
                    Log.d("--URL--", "onResponse----postWaitingList---: " + jsonInString);

                } else {
                    Toast.makeText(context, "Someting Went Wrong.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ClsWaitingResponse> call, Throwable t) {
                try {
                    WaitingResponse.postValue(null);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.d("--URL--", "onFailure: postWaitingList" + t.toString());
            }
        });

        return WaitingResponse;
    }

    public LiveData<ClsWaitingResponse> GetWatingPersonList(int _branchId) {

        final MutableLiveData<ClsWaitingResponse> waitingResponseList = new MutableLiveData<>();
        InterfaceWaiting interfaceWaiting = ApiClient.getRetrofitInstance().create(InterfaceWaiting.class);
        Call<ClsWaitingResponse> call = interfaceWaiting.GetWaitingList(1);

        Log.e("--URL--", "************  before call : " + call.request().url());

        call.enqueue(new Callback<ClsWaitingResponse>() {
            @Override
            public void onResponse(Call<ClsWaitingResponse> call, Response<ClsWaitingResponse> response) {

                Log.e("--URL--", "onResponse: " + response.code());
                if (response.body() != null && response.code() == 200) {

                    waitingResponseList.setValue(response.body());

                    Gson gson = new Gson();
                    String jsonInString = gson.toJson(response.body());
                    Log.d("--URL--", "onResponse----GetWatingPersonList---: " + jsonInString);
                }
            }

            @Override
            public void onFailure(Call<ClsWaitingResponse> call, Throwable t) {

                try {
                    waitingResponseList.setValue(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.d("--URL--", "onFailure:GetWatingPersonList " + t.toString());
            }
        });

        return waitingResponseList;
    }

    public LiveData<ClsWaitingResponse> deleteWaitingObj(ClsWaitingResponse clsWaitingResponse) {
        final MutableLiveData<ClsWaitingResponse> WaitingResponse = new MutableLiveData<>();
        InterfaceWaiting interfaceWaiting = ApiClient.getRetrofitInstance().create(InterfaceWaiting.class);

        Log.e("--Waiting--", "interfaceLogin: " + interfaceWaiting.toString());

        Gson gson = new Gson();
        String jsonInString = gson.toJson(interfaceWaiting);
        Log.e("--Waiting--", "GsonObj: " + jsonInString);

        //  InterfaceWaiting interfaceWaiting = ApiClient.getRetrofitInstance().create(InterfaceWaiting.class);

        Call<ClsWaitingResponse> call = interfaceWaiting.deleteWaitingList(clsWaitingResponse);

        Log.e("--Waiting--", "interfaceLogin: " + interfaceWaiting.toString());
        Log.e("--Waiting--", "Url: " + call.request().url());


        call.enqueue(new Callback<ClsWaitingResponse>() {
            @Override
            public void onResponse(Call<ClsWaitingResponse> call, Response<ClsWaitingResponse>
                    response) {

                Log.e("--Waiting--", "Message: " + response.body().getMESSAGE());
                //  Toast.makeText(getContext(), response.body().getMESSAGE(), Toast.LENGTH_SHORT).show();
                Log.e("--Waiting--", "success: " + response.body().getSUCCESS());
                WaitingResponse.postValue(response.body());

                Gson gson = new Gson();
                String jsonInString = gson.toJson(response.body());
                Log.d("--URL--", "onResponse----deleteWaitingObj---: " + jsonInString);

            }

            @Override
            public void onFailure(Call<ClsWaitingResponse> call, Throwable t) {
                try {
                    WaitingResponse.postValue(null);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.d("--URL--", "onFailure:deleteWaitingObj " + t.toString());
            }
        });

        return WaitingResponse;
    }

    public LiveData<ClsWaitingResponse> completeWaitingObj(ClsWaitingResponse clsWaitingResponse) {

        final MutableLiveData<ClsWaitingResponse> WaitingResponse = new MutableLiveData<>();
        InterfaceWaiting interfaceWaiting = ApiClient.getRetrofitInstance().create(InterfaceWaiting.class);

        Log.e("--Waiting--", "interfaceLogin: " + interfaceWaiting.toString());

        Gson gson = new Gson();
        String jsonInString = gson.toJson(interfaceWaiting);
        Log.e("--Waiting--", "GsonObj: " + jsonInString);

        //  InterfaceWaiting interfaceWaiting = ApiClient.getRetrofitInstance().create(InterfaceWaiting.class);

        Call<ClsWaitingResponse> call = interfaceWaiting.completeWaitingList(clsWaitingResponse);

        Log.e("--Waiting--", "interfaceLogin: " + interfaceWaiting.toString());
        Log.e("--Waiting--", "Url: " + call.request().url());


        call.enqueue(new Callback<ClsWaitingResponse>() {
            @Override
            public void onResponse(Call<ClsWaitingResponse> call, Response<ClsWaitingResponse>
                    response) {

                WaitingResponse.postValue(response.body());

                Gson gson = new Gson();
                String jsonInString = gson.toJson(response.body());
                Log.d("--URL--", "onResponse----completeWaitingObj---: " + jsonInString);
            }

            @Override
            public void onFailure(Call<ClsWaitingResponse> call, Throwable t) {
                try {
                    WaitingResponse.postValue(null);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.d("--URL--", "onFailure: completeWaitingObj" + t.toString());
            }
        });

        return WaitingResponse;
    }

    public LiveData<ClsRequestWaitingResponse> GetSpecialRequestlist() {

        final MutableLiveData<ClsRequestWaitingResponse> requestWaitingResponseList = new MutableLiveData<>();
        InterfaceWaiting interfaceWaiting = ApiClient.getRetrofitInstance().create(InterfaceWaiting.class);
        Call<ClsRequestWaitingResponse> call = interfaceWaiting.GetRequestList();

        Log.e("--URL--", "************  before call : " + call.request().url());

        call.enqueue(new Callback<ClsRequestWaitingResponse>() {
            @Override
            public void onResponse(Call<ClsRequestWaitingResponse> call, Response<ClsRequestWaitingResponse> response) {

                Log.e("--URL--", "onRequestResponse: " + response.code());
                if (response.body() != null && response.code() == 200) {

                    requestWaitingResponseList.setValue(response.body());

                    Gson gson = new Gson();
                    String jsonInString = gson.toJson(response.body());
                    Log.d("--URL--", "onResponse----GetSpecialRequestlist---: " + jsonInString);
                }
            }

            @Override
            public void onFailure(Call<ClsRequestWaitingResponse> call, Throwable t) {
                Log.e("--URL--", "onFailure: GetSpecialRequestlist" + t.getMessage());
                try {
                    requestWaitingResponseList.setValue(null);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.d("--URL--", "onFailure: " + t.toString());
            }
        });

        return requestWaitingResponseList;
    }

    public LiveData<ClsReasonList> GetReasonList() {
        final MutableLiveData<ClsReasonList> reasonList = new MutableLiveData<>();
        InterfaceReasonList interfaceReasonList = ApiClient.getRetrofitInstance().create(InterfaceReasonList.class);

        Call<ClsReasonList> call = interfaceReasonList.GetRequestList();
        Log.e("--URL--", "************  before call : " + call.request().url());

        call.enqueue(new Callback<ClsReasonList>() {
            @Override
            public void onResponse(Call<ClsReasonList> call, Response<ClsReasonList> response) {
                if (response.body() != null && response.code() == 200) {
                    reasonList.setValue(response.body());

                    Gson gson = new Gson();
                    String jsonInString = gson.toJson(response.body());
                    Log.d("--URL--", "onResponse----GetReasonList---: " + jsonInString);
                }
            }

            @Override
            public void onFailure(Call<ClsReasonList> call, Throwable t) {
                try {
                    reasonList.setValue(null);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.d("--URL--", "onFailure: GetReasonList" + t.toString());
            }
        });
        return reasonList;
    }

    public LiveData<ClsPayDetail> GetPaymentDetailList() {
        final MutableLiveData<ClsPayDetail> paymentList = new MutableLiveData<>();
        InterfacePaymentDetail interfacePaymentDetail = ApiClient.getRetrofitInstance().create(InterfacePaymentDetail.class);

        Call<ClsPayDetail> call = interfacePaymentDetail.GetPaymentDetailList();
        Log.e("--URL--", "************  before call : " + call.request().url());

        call.enqueue(new Callback<ClsPayDetail>() {
            @Override
            public void onResponse(Call<ClsPayDetail> call, Response<ClsPayDetail> response) {
                if (response.body() != null && response.code() == 200) {
                    paymentList.setValue(response.body());

                    Gson gson = new Gson();
                    String jsonInString = gson.toJson(response.body());
                    Log.d("--URL--", "onResponse----GetPaymentDetailList---: " + jsonInString);
                }
            }

            @Override
            public void onFailure(Call<ClsPayDetail> call, Throwable t) {
                try {
                    paymentList.setValue(null);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.d("--URL--", "onFailure: GetPaymentDetailList" + t.toString());
            }
        });
        return paymentList;
    }

    public LiveData<ClsReturnReplace> GetReturnReplace(int OrderDetailId, boolean status) {
        final MutableLiveData<ClsReturnReplace> returnReplace = new MutableLiveData<>();
        InterfaceReturnOrReplace interfaceReturnOrReplace = ApiClient.getRetrofitInstance().create(InterfaceReturnOrReplace.class);

        Call<ClsReturnReplace> call = interfaceReturnOrReplace.returnReplace(OrderDetailId, status);
        Log.e("--URL--", "************  before call : " + call.request().url());

        call.enqueue(new Callback<ClsReturnReplace>() {
            @Override
            public void onResponse(Call<ClsReturnReplace> call, Response<ClsReturnReplace> response) {
                if (response.body() != null && response.code() == 200) {
                    returnReplace.setValue(response.body());

                    Gson gson = new Gson();
                    String jsonInString = gson.toJson(response.body());
                    Log.d("--URL--", "onResponse----GetReturnReplace---: " + jsonInString);
                }
            }

            @Override
            public void onFailure(Call<ClsReturnReplace> call, Throwable t) {
                try {
                    returnReplace.setValue(null);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.d("--URL--", "onFailure:GetReturnReplace " + t.toString());
            }
        });
        return returnReplace;
    }


    public LiveData<ClsRetailRecentOrderResponse> GetRetailRecentOrder(int counterId, int departmentId) {
        final MutableLiveData<ClsRetailRecentOrderResponse> liveData = new MutableLiveData<>();
        InterfaceRetailRecentOrder interfaceRetailRecentOrder =
                ApiClient.getRetrofitInstance().create(InterfaceRetailRecentOrder.class);

        Call<ClsRetailRecentOrderResponse> call = interfaceRetailRecentOrder.retailRecentOrder(counterId, departmentId);
        Log.e("--URL--", "************  before call : " + call.request().url());

        call.enqueue(new Callback<ClsRetailRecentOrderResponse>() {
            @Override
            public void onResponse(Call<ClsRetailRecentOrderResponse> call, Response<ClsRetailRecentOrderResponse> response) {
                if (response.body() != null && response.code() == 200) {
                    liveData.setValue(response.body());

                    Gson gson = new Gson();
                    String jsonInString = gson.toJson(response.body());
                    Log.d("--URL--", "onResponse----GetRetailRecentOrder---: " + jsonInString);
                }
            }

            @Override
            public void onFailure(Call<ClsRetailRecentOrderResponse> call, Throwable t) {
                try {
                    liveData.setValue(null);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.d("--URL--", "onFailure: GetRetailRecentOrder" + t.toString());
            }
        });
        return liveData;
    }

    public LiveData<ClsCityResponse> GetCity() {
        final MutableLiveData<ClsCityResponse> cityList = new MutableLiveData<>();
        InterfaceCity interfaceCity = ApiClient.getRetrofitInstance().create(InterfaceCity.class);

        Call<ClsCityResponse> call = interfaceCity.GetCity();
        Log.e("--URL--", "************  before call : " + call.request().url());

        call.enqueue(new Callback<ClsCityResponse>() {
            @Override
            public void onResponse(Call<ClsCityResponse> call, Response<ClsCityResponse> response) {
                if (response.body() != null && response.code() == 200) {
                    cityList.setValue(response.body());

                    Gson gson = new Gson();
                    String jsonInString = gson.toJson(response.body());
                    Log.d("--URL--", "onResponse----GetCity---: " + jsonInString);
                }
            }

            @Override
            public void onFailure(Call<ClsCityResponse> call, Throwable t) {
                try {
                    cityList.setValue(null);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.d("--URL--", "onFailure: GetCity" + t.toString());
            }
        });

        return cityList;
    }


    public LiveData<ClsWaitingResponse> updateWaitingList(ClsWaitingResponse obj) {
        final MutableLiveData<ClsWaitingResponse> WaitingResponse = new MutableLiveData<>();
        InterfaceWaiting interfaceWaiting = ApiClient.getRetrofitInstance().create(InterfaceWaiting.class);

        Log.e("--Waiting--", "interfaceLogin: " + interfaceWaiting.toString());

        Gson gson = new Gson();
        String jsonInString = gson.toJson(interfaceWaiting);
        Log.e("--Waiting--", "GsonObj: " + jsonInString);

        //  InterfaceWaiting interfaceWaiting = ApiClient.getRetrofitInstance().create(InterfaceWaiting.class);

        Call<ClsWaitingResponse> call = interfaceWaiting.updateWaitingList(obj);

        Log.e("--Waiting--", "interfaceLogin: " + interfaceWaiting.toString());
        Log.e("--Waiting--", "Url: " + call.request().url());


        call.enqueue(new Callback<ClsWaitingResponse>() {
            @Override
            public void onResponse(Call<ClsWaitingResponse> call, Response<ClsWaitingResponse>
                    response) {
                WaitingResponse.postValue(response.body());

                Gson gson = new Gson();
                String jsonInString = gson.toJson(response.body());
                Log.d("--URL--", "onResponse----updateWaitingList---: " + jsonInString);
            }

            @Override
            public void onFailure(Call<ClsWaitingResponse> call, Throwable t) {
                try {
                    WaitingResponse.postValue(null);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.d("--URL--", "onFailure: updateWaitingList" + t.toString());
            }
        });

        return WaitingResponse;
    }


    /**
     * Getting the Confirm Order.
     *
     * @return LiveData<ClsConfirmOrderResponse>.
     */
    public LiveData<ClsConfirmOrderResponse> GetOrderConfirm(int orderId) {
        final MutableLiveData<ClsConfirmOrderResponse> ConfirmOrderResponse = new MutableLiveData<>();
        InterfaceConfirmOrder interfaceConfirmOrder = ApiClient.getRetrofitInstance().create(InterfaceConfirmOrder.class);

        Call<ClsConfirmOrderResponse> call = interfaceConfirmOrder.GetConfirmOrder(orderId);
        Log.e("--URL--", "************  before call : " + call.request().url());
        call.enqueue(new Callback<ClsConfirmOrderResponse>() {
            @Override
            public void onResponse(Call<ClsConfirmOrderResponse> call, Response<ClsConfirmOrderResponse> response) {
                if (response.body() != null && response.code() == 200) {
                    ConfirmOrderResponse.setValue(response.body());

                    Gson gson = new Gson();
                    String jsonInString = gson.toJson(response.body());
                    Log.d("--URL--", "onResponse----GetOrderConfirm---: " + jsonInString);
                }
            }

            @Override
            public void onFailure(Call<ClsConfirmOrderResponse> call, Throwable t) {
                ConfirmOrderResponse.setValue(null);
                Log.d("--URL--", "onFailure: GetOrderConfirm" + t.toString());
            }
        });
        return ConfirmOrderResponse;
    }

    /**
     * Post Item Order.
     *
     * @return LiveData<ClsMenuResponse>.
     */
    public LiveData<ClsOrderResponse> PostItemOrder(ClsOrder clsOrder) {
        final MutableLiveData<ClsOrderResponse> clsOrderResponse = new MutableLiveData<>();
        InterfaceOrder interfaceOrder = ApiClient.getRetrofitInstance().create(InterfaceOrder.class);
        Call<ClsOrderResponse> call = interfaceOrder.addOrder(clsOrder);

        call.enqueue(new Callback<ClsOrderResponse>() {
            @Override
            public void onResponse(Call<ClsOrderResponse> call, Response<ClsOrderResponse> response) {
                if (response.body() != null && response.code() == 200) {
                    clsOrderResponse.setValue(response.body());

                    Gson gson = new Gson();
                    String jsonInString = gson.toJson(response.body());
                    Log.d("--URL--", "onResponse----PostItemOrder---: " + jsonInString);
                }
            }

            @Override
            public void onFailure(Call<ClsOrderResponse> call, Throwable t) {
                clsOrderResponse.setValue(null);
                Log.d("--URL--", "onFailure: PostItemOrder" + t.toString());
            }
        });
        return clsOrderResponse;
    }

    /**
     * Post Item Order.
     *
     * @return LiveData<ClsBillDetail>.
     */
    public LiveData<ClsBillDetailResponse> PostBillDetail(ClsBillDetail clsBillDetail) {
        final MutableLiveData<ClsBillDetailResponse> clsBillDetailMutable = new MutableLiveData<>();
        InterfaceGenerateBill interfaceGenerateBill = ApiClient.getRetrofitInstance().create(InterfaceGenerateBill.class);
        Call<ClsBillDetailResponse> call = interfaceGenerateBill.generateBill(clsBillDetail);

        call.enqueue(new Callback<ClsBillDetailResponse>() {
            @Override
            public void onResponse(Call<ClsBillDetailResponse> call, Response<ClsBillDetailResponse> response) {
                if (response.body() != null && response.code() == 200) {
                    clsBillDetailMutable.setValue(response.body());

                    Gson gson = new Gson();
                    String jsonInString = gson.toJson(response.body());
                    Log.d("--URL--", "onResponse----PostBillDetail---: " + jsonInString);
                }
            }

            @Override
            public void onFailure(Call<ClsBillDetailResponse> call, Throwable t) {
                clsBillDetailMutable.setValue(null);
                Log.d("--URL--", "onFailure: PostBillDetail" + t.toString());
            }
        });
        return clsBillDetailMutable;
    }

    /**
     * Getting the Print or Delete Order.
     *
     * @return LiveData<ClsOrderPrintDeleteResponse>.
     */
    public LiveData<ClsOrderPrintDeleteResponse> GetOrderPrintDelete(int OrderDetailID,
                                                                     int OrderID, String Mode) {
        final MutableLiveData<ClsOrderPrintDeleteResponse> printDeleteResponse = new MutableLiveData<>();
        InterfaceOrderPrintDelete interfaceOrderPrintDelete = ApiClient.getRetrofitInstance()
                .create(InterfaceOrderPrintDelete.class);

        Call<ClsOrderPrintDeleteResponse> call = interfaceOrderPrintDelete.GetPrintDelete(OrderDetailID, OrderID, Mode);
        Log.e("--URL--", "************  before call : " + call.request().url());
        call.enqueue(new Callback<ClsOrderPrintDeleteResponse>() {
            @Override
            public void onResponse(Call<ClsOrderPrintDeleteResponse> call, Response<ClsOrderPrintDeleteResponse> response) {
                if (response.body() != null && response.code() == 200) {
                    printDeleteResponse.setValue(response.body());

                    Gson gson = new Gson();
                    String jsonInString = gson.toJson(response.body());
                    Log.d("--URL--", "onResponse----GetOrderPrintDelete---: " + jsonInString);
                }
            }

            @Override
            public void onFailure(Call<ClsOrderPrintDeleteResponse> call, Throwable t) {
                printDeleteResponse.setValue(null);
                Log.d("--URL--", "onFailure: GetOrderPrintDelete" + t.toString());
            }
        });
        return printDeleteResponse;
    }

    /**
     * Posting the Return Replace Order.
     *
     * @return LiveData<ClsReturnReplace>.
     */
    public LiveData<ClsReturnReplace> PostReturnReplaceOrder(String remark, boolean wastage, int OrderDetailID,
                                                             int OrderID, String status) {
        final MutableLiveData<ClsReturnReplace> returnReplaceMutableLiveData = new MutableLiveData<>();
        InterfaceReturnReplace interfaceReturnReplace = ApiClient.getRetrofitInstance()
                .create(InterfaceReturnReplace.class);

        Call<ClsReturnReplace> call = interfaceReturnReplace.postReturnReplace(remark, wastage, OrderDetailID, OrderID, status);
        Log.e("--URL--", "************  before call : " + call.request().url());
        call.enqueue(new Callback<ClsReturnReplace>() {
            @Override
            public void onResponse(Call<ClsReturnReplace> call, Response<ClsReturnReplace> response) {
                returnReplaceMutableLiveData.postValue(response.body());

                Gson gson = new Gson();
                String jsonInString = gson.toJson(response.body());
                Log.d("--URL--", "onResponse----PostReturnReplaceOrder---: " + jsonInString);

            }

            @Override
            public void onFailure(Call<ClsReturnReplace> call, Throwable t) {
                try {
                    returnReplaceMutableLiveData.postValue(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.d("--URL--", "onFailure: PostReturnReplaceOrder" + t.toString());
            }
        });
        return returnReplaceMutableLiveData;
    }

    public LiveData<ClsTaxSlabResponse> GetTaxSlab(String branch_id) {
        final MutableLiveData<ClsTaxSlabResponse> clsTaxSlabResponse = new MutableLiveData<>();

        InterfaceTaxSlab interfaceTaxSlab = ApiClient.getRetrofitInstance().create(InterfaceTaxSlab.class);

        Call<ClsTaxSlabResponse> call = interfaceTaxSlab.GetTaxSlab(branch_id);
        call.enqueue(new Callback<ClsTaxSlabResponse>() {
            @Override
            public void onResponse(Call<ClsTaxSlabResponse> call, Response<ClsTaxSlabResponse> response) {
                clsTaxSlabResponse.setValue(response.body());

                Gson gson = new Gson();
                String jsonInString = gson.toJson(response.body());
                Log.d("--URL--", "onResponse----GetTaxSlab---: " + jsonInString);
            }

            @Override
            public void onFailure(Call<ClsTaxSlabResponse> call, Throwable t) {
                try {
                    clsTaxSlabResponse.postValue(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.d("--URL--", "onFailure: GetTaxSlab" + t.toString());
            }
        });

        return clsTaxSlabResponse;
    }

    public LiveData<ClsRetailCustomerResponse> PostRetailCustomer(ClsRetailCustomer clsRetailCustomer) {
        final MutableLiveData<ClsRetailCustomerResponse> mutableLiveData = new MutableLiveData<>();
        InterfaceRetailCustomer interfaceRetailCustomer = ApiClient.getRetrofitInstance().create(InterfaceRetailCustomer.class);

        Call<ClsRetailCustomerResponse> call = interfaceRetailCustomer.postCustomerInfo(clsRetailCustomer);
        call.enqueue(new Callback<ClsRetailCustomerResponse>() {
            @Override
            public void onResponse(Call<ClsRetailCustomerResponse> call, Response<ClsRetailCustomerResponse> response) {
                if (response.body() != null && response.code() == 200) {
                    mutableLiveData.postValue(response.body());

                    Gson gson = new Gson();
                    String jsonInString = gson.toJson(response.body());
                    Log.d("--URL--", "onResponse----PostRetailCustomer---: " + jsonInString);
                }
            }

            @Override
            public void onFailure(Call<ClsRetailCustomerResponse> call, Throwable t) {
                mutableLiveData.postValue(null);
                Log.d("--URL--", "onFailure: PostRetailCustomer" + t.toString());
            }
        });
        return mutableLiveData;
    }


    public LiveData<ClsMobileNoResponse> GetCustomerName(String mobileNo) {
        final MutableLiveData<ClsMobileNoResponse> clsMobileNoResponse = new MutableLiveData<>();

        InterfaceMobileNo interfaceMobileNo = ApiClient.getRetrofitInstance().create(InterfaceMobileNo.class);
        Call<ClsMobileNoResponse> call = interfaceMobileNo.GetTaxSlab(mobileNo);

        call.enqueue(new Callback<ClsMobileNoResponse>() {
            @Override
            public void onResponse(Call<ClsMobileNoResponse> call, Response<ClsMobileNoResponse> response) {
                clsMobileNoResponse.setValue(response.body());

                Gson gson = new Gson();
                String jsonInString = gson.toJson(response.body());
                Log.d("--URL--", "onResponse----GetCustomerName---: " + jsonInString);
            }

            @Override
            public void onFailure(Call<ClsMobileNoResponse> call, Throwable t) {
                try {
                    clsMobileNoResponse.postValue(null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.d("--URL--", "onFailure: GetCustomerName" + t.toString());
            }
        });

        return clsMobileNoResponse;
    }
}
