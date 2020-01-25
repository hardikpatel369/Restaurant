package com.nspl.restaurant.Global;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.nspl.restaurant.DataModel.ClsUserInfo;
import com.nspl.restaurant.RetrofitApi.ApiClasses.ClsLoginResponse;
import com.nspl.restaurant.RetrofitApi.ApiClasses.ClsLogoutResponse;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Counters.ClsCounterResponse;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Menu.ClsMenuResponse;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Tables.ClsTablesResponse;
import com.nspl.restaurant.RetrofitApi.Interface.InterfaceCounters;
import com.nspl.restaurant.RetrofitApi.Interface.InterfaceLogin;
import com.nspl.restaurant.RetrofitApi.Interface.InterfaceLogout;
import com.nspl.restaurant.RetrofitApi.Interface.InterfaceMenu;
import com.nspl.restaurant.RetrofitApi.Interface.InterfaceTables;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {

    private Context context;
    ClsUserInfo obj = new ClsUserInfo();

    public Repository(Context context) {
        this.context = context;

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
        Call<ClsLoginResponse> call = interfaceLogin.postLogin(userName, Password, imei, appVersion);
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
                loginData.setValue(null);

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
                LogoutData.setValue(null);

            }
        });

        return LogoutData;

    }

    /**
     * Getting the Menu List.
     *
     * @return LiveData<ClsMenuResponse>
     */
    public LiveData<ClsMenuResponse> GetMenuList() {

        final MutableLiveData<ClsMenuResponse> MenuResponse = new MutableLiveData<>();
        InterfaceMenu interfaceLogout = ApiClient.getRetrofitInstance().create(InterfaceMenu.class);
        Log.e("--URL--", "interfaceLogin: " + interfaceLogout.toString());

        Call<ClsMenuResponse> call = interfaceLogout.GetMenuList("7",
                "6");

        Log.e("--URL--", "************  before call : "
                + call.request().url());
        call.enqueue(new Callback<ClsMenuResponse>() {
            @Override
            public void onResponse(Call<ClsMenuResponse> call, Response<ClsMenuResponse> response) {
                if (response.body() != null && response.code() == 200) {
                    MenuResponse.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ClsMenuResponse> call, Throwable t) {
                MenuResponse.setValue(null);
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

//        Call<ClsCounterResponse> call = interfaceLogout.GetMenuList("1,2,5", obj.getBRANCH_ID());
        Call<ClsCounterResponse> call = interfaceLogout.GetMenuList(obj.getCOUNTER_IDS(),obj.getBRANCH_ID());


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

                CountersResponse.setValue(null);
            }
        });

        return CountersResponse;
    }

    /**
     * Getting the List of Tables.
     *
     * @return LiveData<ClsTablesResponse>.
     */
    public LiveData<ClsTablesResponse> GetTables(int _departmentID) {

        final MutableLiveData<ClsTablesResponse> TablesResponse = new MutableLiveData<>();
        InterfaceTables interfaceLogout = ApiClient.getRetrofitInstance().create(InterfaceTables.class);
        Log.e("--URL--", "interfaceLogin: " + interfaceLogout.toString());
        Log.e("--URL--", "Id: " + obj.getDEPARTMENT_IDS());
        Log.e("--URL--", "BranchId: " + obj.getBRANCH_ID());

//        Call<ClsTablesResponse> call = interfaceLogout.GetTables("getTable", "4", "1");
        Call<ClsTablesResponse> call = interfaceLogout.GetTables( _departmentID);


        Log.e("--URL--", "************  before call : "+ call.request().url());

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
                TablesResponse.setValue(null);
            }
        });

        return TablesResponse;
    }

}
