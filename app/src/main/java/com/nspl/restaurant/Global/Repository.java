package com.nspl.restaurant.Global;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nspl.restaurant.DataModel.ClsUserInfo;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Login.ClsLoginResponse;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Login.ClsLogoutResponse;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Counters.ClsCounterResponse;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Menu.ClsMenuResponse;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Order.ClsOrderSummaryResponse;
import com.nspl.restaurant.RetrofitApi.ApiClasses.OrderActions.ClsConfirmOrderResponse;
import com.nspl.restaurant.RetrofitApi.ApiClasses.OrderActions.ClsOrderPrintDeleteResponse;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Tables.ClsTablesResponse;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Waiting.ClsRequestWaitingResponse;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Waiting.ClsWaitingResponse;
import com.nspl.restaurant.RetrofitApi.Interface.InterfaceConfirmOrder;
import com.nspl.restaurant.RetrofitApi.Interface.InterfaceCounters;
import com.nspl.restaurant.RetrofitApi.Interface.InterfaceLogin;
import com.nspl.restaurant.RetrofitApi.Interface.InterfaceLogout;
import com.nspl.restaurant.RetrofitApi.Interface.InterfaceMenu;
import com.nspl.restaurant.RetrofitApi.Interface.InterfaceOrderDetail;
import com.nspl.restaurant.RetrofitApi.Interface.InterfaceOrderPrintDelete;
import com.nspl.restaurant.RetrofitApi.Interface.InterfaceTables;
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
                }
            }

            @Override
            public void onFailure(Call<ClsLogoutResponse> call, Throwable t) {
                try{
                    LogoutData.setValue(null);

                }catch(Exception e){
                    e.printStackTrace();
                }
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
                }
            }

            @Override
            public void onFailure(Call<ClsMenuResponse> call, Throwable t) {
                try{
                    MenuResponse.setValue(null);

                }catch(Exception e){
                    e.printStackTrace();
                }
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
                }
            }

            @Override
            public void onFailure(Call<ClsCounterResponse> call, Throwable t) {

                try{
                    CountersResponse.setValue(null);

                }catch(Exception e){
                    e.printStackTrace();
                }
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

//        String departmentId = sharedPreferences.getString("DEPARTMENT_IDS","NotFound");

//        Call<ClsTablesResponse> call = interfaceLogout.GetTables("getTable", "4", "1");
        Call<ClsTablesResponse> call = interfaceLogout.GetTables(departmentId);

        Log.e("--URL--", "************  before call : " + call.request().url());

        call.enqueue(new Callback<ClsTablesResponse>() {
            @Override
            public void onResponse(Call<ClsTablesResponse> call, Response<ClsTablesResponse> response) {
                Gson gson = new Gson();
                String jsonInString = gson.toJson(response.body());
                Log.e("Result", "objClsUserInfo---" + jsonInString);
                if (response.body() != null && response.code() == 200) {
                    TablesResponse.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ClsTablesResponse> call, Throwable t) {
                try{
                    TablesResponse.setValue(null);

                }catch(Exception e){
                    e.printStackTrace();
                }
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
                }
            }

            @Override
            public void onFailure(Call<ClsOrderSummaryResponse> call, Throwable t) {
                try{
                    OrderSummaryResponse.setValue(null);

                }catch(Exception e){
                    e.printStackTrace();
                }
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

                if(response.body()!=null) {
                    Log.e("--Waiting--", "Message: " + response.body().getMESSAGE());
                    //  Toast.makeText(getContext(), response.body().getMESSAGE(), Toast.LENGTH_SHORT).show();
                    Log.e("--Waiting--", "success: " + response.body().getSUCCESS());
                    WaitingResponse.postValue(response.body());

                }else {
                    Toast.makeText(context, "Someting Went Wrong.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ClsWaitingResponse> call, Throwable t) {
                try{
                    WaitingResponse.postValue(null);

                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        // Log.e("--URL--", "GsonObj: " + clsPersonWaitingInfo.toString());
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
                    Gson gson = new Gson();
                    String jsonInString = gson.toJson(response.body());
                    Log.e("--URL--", "GsonObj: " + jsonInString);
                    waitingResponseList.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ClsWaitingResponse> call, Throwable t) {
                Log.e("--URL--", "onFailure: " + t.getMessage());

                try{
                    waitingResponseList.setValue(null);
                }catch(Exception e){
                    e.printStackTrace();
                }
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


            }

            @Override
            public void onFailure(Call<ClsWaitingResponse> call, Throwable t) {
                try {
                    WaitingResponse.postValue(null);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        // Log.e("--URL--", "GsonObj: " + clsPersonWaitingInfo.toString());
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

                Log.e("--Waiting--", "Message: " + response.body().getMESSAGE());
                //  Toast.makeText(getContext(), response.body().getMESSAGE(), Toast.LENGTH_SHORT).show();
                Log.e("--Waiting--", "success: " + response.body().getSUCCESS());
                WaitingResponse.postValue(response.body());


            }

            @Override
            public void onFailure(Call<ClsWaitingResponse> call, Throwable t) {
                try {
                    WaitingResponse.postValue(null);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        // Log.e("--URL--", "GsonObj: " + clsPersonWaitingInfo.toString());
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

                Log.e("--URL--", "onRequestResponse: "+response.code() );
                if (response.body() != null && response.code() == 200){
                    Gson gson = new Gson();
                    String jsonInString = gson.toJson(response.body());
                    Log.e("--URL--", "GsonObj request: " + jsonInString);
                    requestWaitingResponseList.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ClsRequestWaitingResponse> call, Throwable t) {
                Log.e("--URL--", "onFailure: "+t.getMessage() );
                try {
                    requestWaitingResponseList.setValue(null);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        return requestWaitingResponseList;
    }

    public LiveData<ClsWaitingResponse> updateWaitingList(ClsWaitingResponse obj){
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

                Log.e("--Waiting--", "Message: " + response.body().getMESSAGE());
                //  Toast.makeText(getContext(), response.body().getMESSAGE(), Toast.LENGTH_SHORT).show();
                Log.e("--Waiting--", "success: " + response.body().getSUCCESS());
                WaitingResponse.postValue(response.body());


            }

            @Override
            public void onFailure(Call<ClsWaitingResponse> call, Throwable t) {
                try {
                    WaitingResponse.postValue(null);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        // Log.e("--URL--", "GsonObj: " + clsPersonWaitingInfo.toString());
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
                    Log.d("ConfirmOrderResponse", "onResponse: " + response.body());
                }
            }

            @Override
            public void onFailure(Call<ClsConfirmOrderResponse> call, Throwable t) {
                ConfirmOrderResponse.setValue(null);
                Log.d("ConfirmOrderResponse", "onFailure: " + t.toString());
            }
        });
        return ConfirmOrderResponse;
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
                    Log.d("printDeleteResponse", "onResponse: " + response.body());
                }
            }

            @Override
            public void onFailure(Call<ClsOrderPrintDeleteResponse> call, Throwable t) {
                printDeleteResponse.setValue(null);
                Log.d("printDeleteResponse", "onFailure: " + t.toString());
            }
        });
        return printDeleteResponse;
    }

}
