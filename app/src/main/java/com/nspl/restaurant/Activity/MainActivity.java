package com.nspl.restaurant.Activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.net.ConnectivityManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nspl.restaurant.BuildConfig;
import com.nspl.restaurant.DataModel.ClsUserInfo;


import com.nspl.restaurant.Global.ClsGlobal;
import com.nspl.restaurant.Global.NetworkChangeReceiver;
import com.nspl.restaurant.Global.Repository;
import com.nspl.restaurant.R;

import com.nspl.restaurant.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity
        implements NetworkChangeReceiver.NetworkChangers {

    ActivityMainBinding mBinding;

    Repository mRepository;

    NetworkChangeReceiver mNetworkChangeReceiver;
    ClsUserInfo getUserInfo;
    private static final int PERMISSIONS_REQUEST_READ_PHONE_STATE = 999;

    private TelephonyManager mTelephonyManager;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBinding = DataBindingUtil.setContentView(this,
                R.layout.activity_main);

        mNetworkChangeReceiver = new NetworkChangeReceiver();
        mNetworkChangeReceiver.SetOnNetworkChange(this);
//        if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE)
//                != PackageManager.PERMISSION_GRANTED) {
//            requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE},
//                    PERMISSIONS_REQUEST_READ_PHONE_STATE);
//        } else {
//            getDeviceImei();
//        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(mNetworkChangeReceiver, intentFilter);
        String versionName = BuildConfig.VERSION_NAME;
        mBinding.tvVersionName.setText("V " + versionName);
        mRepository = new Repository(MainActivity.this);


        getUserInfo = ClsGlobal.getUserInfo(MainActivity.this);

        if (getUserInfo != null && getUserInfo.getLOCAL_LOGIN_STATUS()
                .equalsIgnoreCase("ACTIVE")) {
            Intent i = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(i);
        }


        mBinding.btnLogin.setOnClickListener(v -> {

            if (LOGINVALIDATION()) {
                LoginApiCall();
            }

        });


        mBinding.txtHelp.setOnClickListener(v -> {

//            mRepository.GetMenuList().observe(this, clsMenuResponse -> {
//                if (clsMenuResponse != null) {
//
//                    StringBuilder stringBuilder = new StringBuilder();
//                    stringBuilder.append(clsMenuResponse.toString());
//                    int maxLogSize = 1000;
//                    for (int i = 0; i <= stringBuilder.length() / maxLogSize; i++) {
//                        int start = i * maxLogSize;
//                        int end = (i + 1) * maxLogSize;
//                        end = end > stringBuilder.length() ? stringBuilder.length() : end;
//
//                        Log.v("ExpenseMisBody- ", stringBuilder.substring(start, end));
//                    }
//
//
////                        Gson gson = new Gson();
////                        String jsonInString = gson.toJson(clsMenuResponse);
////                        Log.d("amtsdf", "Insert- " + jsonInString);
//                    Log.d("amtsdf", "Insert- " + clsMenuResponse.getmESSAGE());
//                    Log.d("amtsdf", "Insert- " + clsMenuResponse.getsUCCESS());
//
//                    Gson gson1 = new Gson();
//                    String jsonInString1 = gson1.toJson(clsMenuResponse.getmDataMenu());
//                    Log.d("amtsdf", "Insert- " + jsonInString1);
//
//                }
//
//            });

//            mRepository.LogoutApi("SMP001","1",ClsGlobal.getCurrentDate(),
//                    ClsGlobal.getCurrentTime_AM_OR_PM())
//                    .observe(this, clsLogoutResponse -> {
//
//                        if (clsLogoutResponse != null){
//
//                            Gson gson = new Gson();
//                            String jsonInString = gson.toJson(clsLogoutResponse);
//                            Log.d("amtsdf", "Insert- " + jsonInString);
//
//                        }
//
//                    });


//            mRepository.GetCounters().observe(this, clsCounterResponse -> {
//                if (clsCounterResponse != null){
////
//                        Gson gson = new Gson();
//                        String jsonInString = gson.toJson(clsCounterResponse);
//                        Log.d("amtsdf", "Insert- " + jsonInString);
////
//                    }
//            });


        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_READ_PHONE_STATE
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            getDeviceImei();
        }
    }

    private void getDeviceImei() {
        mTelephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        String deviceid = mTelephonyManager.getDeviceId();
        Log.d("msg", "DeviceImei " + deviceid);
    }


    private void LoginApiCall() {

        mRepository.LoginApi(mBinding.edtMobile.getText().toString().trim()
                , mBinding.edtPassword.getText().toString().trim(), "1", "1")
                .observe(MainActivity.this, clsLoginResponse -> {

                    if (clsLoginResponse != null) {

                        Gson gson = new Gson();
                        String jsonInString = gson.toJson(clsLoginResponse);
                        Log.d("amtsdf", "Insert- " + jsonInString);


                        ClsUserInfo clsUserInfo = new ClsUserInfo();
                        if (clsLoginResponse.getDATA().getLOGINSTATUS()
                                .equalsIgnoreCase("ACTIVE")) {

                            if (clsLoginResponse.getSUCCESS().equalsIgnoreCase("1")
                                    || clsLoginResponse.getSUCCESS().equalsIgnoreCase("2")) {

                                clsUserInfo.setEMPLOYEE_ID(String.valueOf(
                                        clsLoginResponse.getDATA().getEMPLOYEEID()));
                                clsUserInfo.setFIRST_NAME(clsLoginResponse.getDATA().getFIRSTNAME());
                                clsUserInfo.setROLE_ID(String.valueOf(clsLoginResponse.getDATA().getROLEID()));
                                clsUserInfo.setEMPLOYEE_CODE(clsLoginResponse.getDATA().getEMPLOYEECODE());
                                clsUserInfo.setFULL_NAME(clsLoginResponse.getDATA().getFULLNAME());
                                clsUserInfo.setDESIGNATION_ID(String.valueOf(clsLoginResponse.getDATA().getDESIGNATIONID()));
                                clsUserInfo.setDESIGNATION_NAME(clsLoginResponse.getDATA().getDESIGNATIONNAME());
                                clsUserInfo.setLOGIN_STATUS(clsLoginResponse.getDATA().getLOGINSTATUS());
                                clsUserInfo.setCOUNTER_IDS(clsLoginResponse.getDATA().getCOUNTERIDS());
                                clsUserInfo.setIMEI(clsLoginResponse.getDATA().getIMEI());
                                clsUserInfo.setBRANCH_ID(String.valueOf(clsLoginResponse.getDATA().getBRANCHID()));
                                clsUserInfo.setDEPARTMENT_IDS(String.valueOf(clsLoginResponse.getDATA().getDEPARTMENTIDS()));
                                clsUserInfo.setLOCAL_LOGIN_STATUS("ACTIVE");

                                ClsGlobal.setUserInfo(clsUserInfo, MainActivity.this);

                                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                                startActivity(intent);

                                ClsGlobal.MakeToast(getString(R.string.LoginSuccessful), MainActivity.this);

                            } else if (clsLoginResponse.getSUCCESS().equalsIgnoreCase("3")) {

                            } else if (clsLoginResponse.getSUCCESS().equalsIgnoreCase("4")) {

                            } else if (clsLoginResponse.getSUCCESS().equalsIgnoreCase("5")) {

                            }
                        } else {
                            ClsGlobal.MakeToast(getString(R.string.LoginNotValid), MainActivity.this);
                        }
                    } else {
                        ClsGlobal.MakeToast("Networking Error!", MainActivity.this);
                    }

                    Log.e("clsLoginResponse", String.valueOf(clsLoginResponse));


                });


    }


    private boolean LOGINVALIDATION() {

        boolean result = true;

        if (!ClsGlobal.CheckInternetConnection(MainActivity.this)) {
            Toast.makeText(MainActivity.this, getString(R.string.NoInternet),
                    Toast.LENGTH_SHORT).show();
            return false;
        }

        if (mBinding.edtMobile.getText().toString() == null ||
                mBinding.edtMobile.getText().toString().isEmpty()) {
            Toast.makeText(MainActivity.this, getString(R.string.NoUserId), Toast.LENGTH_SHORT).show();
            return false;
        }

        if (mBinding.edtPassword.getText().toString() == null ||
                mBinding.edtPassword.getText().toString().isEmpty()) {
            Toast.makeText(MainActivity.this, getString(R.string.NoPassword), Toast.LENGTH_SHORT).show();
            return false;
        }

        return result;
    }

    @Override
    public void onPause() {
        super.onPause();
        try {
            Log.e("OnNetworkChange", "onPause call");
            unregisterReceiver(mNetworkChangeReceiver);
//            mNetworkChangeReceiver = null;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void OnNetworkChange(String status) {
        Log.e("OnNetworkChange", "OnNetworkChange call");

        if (status.equalsIgnoreCase(getString(R.string.NoInternet))) {
            ClsGlobal.CreateSnakeBar(mBinding.mainRelativeLayout, getString(R.string.NoInternet));
        } else if (status.equalsIgnoreCase(getString(R.string.InternetConnected))) {
            ClsGlobal.CreateSnakeBar(mBinding.mainRelativeLayout, getString(R.string.InternetConnected));
        }


    }
}
