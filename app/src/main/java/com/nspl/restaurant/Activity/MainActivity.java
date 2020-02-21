package com.nspl.restaurant.Activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;

import androidx.databinding.DataBindingUtil;

import android.net.ConnectivityManager;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AppCompatActivity;

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

import com.nspl.restaurant.RetrofitApi.ApiClasses.ClsLoginResponseData;
import com.nspl.restaurant.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NetworkChangeReceiver.NetworkChangers {

    ActivityMainBinding mBinding;
    final String MY_PREF_FILE = "user_details";
    Repository mRepository;
    SharedPreferences sharedPreferences;

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

        sharedPreferences = getSharedPreferences(MY_PREF_FILE, MODE_PRIVATE);
        String id = sharedPreferences.getString("id", "not found");
        String pass = sharedPreferences.getString("pass", "not found");
        String chk = sharedPreferences.getString("Check", "not found");
        if (chk.equals("checked")) {
            mBinding.edtMobile.setText(id);
            mBinding.edtPassword.setText(pass);
            mBinding.chkRemember.setChecked(true);

        } else {
            mBinding.edtMobile.setText(id);
            mBinding.edtPassword.setText(pass);
            mBinding.chkRemember.setChecked(false);
        }

        mNetworkChangeReceiver = new NetworkChangeReceiver();
        mNetworkChangeReceiver.SetOnNetworkChange(this);
        if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE},
                    PERMISSIONS_REQUEST_READ_PHONE_STATE);
        } else {
            getDeviceImei();
        }
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
                isRemember();
            }
        });

        mBinding.txtHelp.setOnClickListener(v -> {

        });
    }

    private void isRemember() {
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if (mBinding.chkRemember.isChecked()) {
            editor.putString("Check", "checked");
            String userId = mBinding.edtMobile.getText().toString();
            editor.putString("id", userId);
            String pass = mBinding.edtPassword.getText().toString();
            editor.putString("pass", pass);
            editor.apply();

        } else {
            editor.putString("Check", "notChecked");
            editor.putString("id", "");
            editor.putString("pass", "");
            editor.apply();
        }

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
                .observe(MainActivity.this, objLoginResponse -> {

                    if (objLoginResponse != null) {

                        Gson gson = new Gson();
                        String jsonInString = gson.toJson(objLoginResponse);
                        Log.d("jsonInString", "jsonInString- " + jsonInString);

                        ClsUserInfo clsUserInfo = new ClsUserInfo();
                        List<ClsLoginResponseData> _list = objLoginResponse.getDATA();
                        ClsLoginResponseData obj = new ClsLoginResponseData();
                        obj = _list.get(0);

                        if (obj.getLOGINSTATUS().equalsIgnoreCase("ACTIVE")) {

                            if (objLoginResponse.getSUCCESS().equalsIgnoreCase("1")
                                    || objLoginResponse.getSUCCESS().equalsIgnoreCase("2")) {

                                clsUserInfo.setEMPLOYEE_ID(String.valueOf(obj.getEMPLOYEEID()));
                                clsUserInfo.setFIRST_NAME(obj.getFIRSTNAME());
                                clsUserInfo.setROLE_ID(String.valueOf(obj.getROLEID()));
                                clsUserInfo.setEMPLOYEE_CODE(obj.getEMPLOYEECODE());
                                clsUserInfo.setFULL_NAME(obj.getFULLNAME());
                                clsUserInfo.setDESIGNATION_ID(String.valueOf(obj.getDESIGNATIONID()));
                                clsUserInfo.setDESIGNATION_NAME(obj.getDESIGNATIONNAME());
                                clsUserInfo.setLOGIN_STATUS(obj.getLOGINSTATUS());
                                clsUserInfo.setCOUNTER_IDS(obj.getCOUNTERIDS());
                                clsUserInfo.setIMEI(obj.getIMEI());
                                clsUserInfo.setBRANCH_ID(String.valueOf(obj.getBRANCHID()));
                                clsUserInfo.setDEPARTMENT_IDS(String.valueOf(obj.getDEPARTMENTIDS()));
                                clsUserInfo.setLOCAL_LOGIN_STATUS("ACTIVE");

                                ClsGlobal.setUserInfo(clsUserInfo, MainActivity.this);

                                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                                startActivity(intent);

                                ClsGlobal.MakeToast(getString(R.string.LoginSuccessful), MainActivity.this);

                            } else if (objLoginResponse.getSUCCESS().equalsIgnoreCase("3")) {

                            } else if (objLoginResponse.getSUCCESS().equalsIgnoreCase("4")) {

                            } else if (objLoginResponse.getSUCCESS().equalsIgnoreCase("5")) {

                            }
                        } else {
                            ClsGlobal.MakeToast(getString(R.string.LoginNotValid), MainActivity.this);
                        }
                    } else {
                        ClsGlobal.MakeToast("Networking Error!", MainActivity.this);
                    }
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
