package com.nspl.restaurant.Global;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.google.android.material.snackbar.Snackbar;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.google.gson.Gson;
import com.nspl.restaurant.DataModel.ClsUserInfo;

import java.text.SimpleDateFormat;
import java.util.Date;

import static android.content.Context.MODE_PRIVATE;

public class ClsGlobal {

    private static final String SPLoginDetails = "LoginDetails";

    public static boolean CheckInternetConnection(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }

    public static void MakeToast(String message,Context context){
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }

    public static void CreateSnakeBar(RelativeLayout relativeLayout,String message){
        Snackbar snackbar = Snackbar
                .make(relativeLayout, message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    public static String getCurrentDate() {
        // set the format to sql date time
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static String getCurrentTime_AM_OR_PM() {
        // set the format to sql date time
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm aa");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static void setUserInfo(ClsUserInfo objClsUserInfo, Context c) {

        SharedPreferences mPreferences = c.getSharedPreferences(SPLoginDetails, MODE_PRIVATE);
        SharedPreferences.Editor editor = mPreferences.edit();

        String EMPLOYEE_ID = "EMPLOYEE_ID";
        String FIRST_NAME = "FIRST_NAME";
        String ROLE_ID = "ROLE_ID";
        String EMPLOYEE_CODE = "EMPLOYEE_CODE";
        String FULL_NAME = "FULL_NAME";
        String DESIGNATION_ID = "DESIGNATION_ID";
        String DESIGNATION_NAME = "DESIGNATION_NAME";
        String LOGIN_STATUS = "LOGIN_STATUS";
        String COUNTER_IDS = "COUNTER_IDS";
        String IMEI = "IMEI";
        String BRANCH_ID = "BRANCH_ID";
        String LOCAL_LOGIN_STATUS = "LOCAL_LOGIN_STATUS";
        String DEPARTMENT_IDS = "DEPARTMENT_IDS";

        editor.putString(EMPLOYEE_ID, objClsUserInfo.getEMPLOYEE_ID());
        editor.putString(FIRST_NAME, objClsUserInfo.getFIRST_NAME());
        editor.putString(ROLE_ID, objClsUserInfo.getROLE_ID());
        editor.putString(EMPLOYEE_CODE, objClsUserInfo.getEMPLOYEE_CODE());
        editor.putString(FULL_NAME, objClsUserInfo.getFULL_NAME());
        editor.putString(DESIGNATION_ID, objClsUserInfo.getDESIGNATION_ID());
        editor.putString(DESIGNATION_NAME, objClsUserInfo.getDESIGNATION_NAME());
        editor.putString(LOGIN_STATUS, objClsUserInfo.getLOGIN_STATUS());
        editor.putString(COUNTER_IDS, objClsUserInfo.getCOUNTER_IDS());
        editor.putString(IMEI, objClsUserInfo.getIMEI());
        editor.putString(BRANCH_ID, objClsUserInfo.getBRANCH_ID());
        editor.putString(LOCAL_LOGIN_STATUS, objClsUserInfo.getLOCAL_LOGIN_STATUS());
        editor.putString(DEPARTMENT_IDS,objClsUserInfo.getDEPARTMENT_IDS());

        editor.apply();
    }



    public static ClsUserInfo getUserInfo(Context c) {
        ClsUserInfo objClsUserInfo = new ClsUserInfo();

        SharedPreferences mPreferences = c.getSharedPreferences(SPLoginDetails, MODE_PRIVATE);

        String EMPLOYEE_ID = "EMPLOYEE_ID";
        String FIRST_NAME = "FIRST_NAME";
        String ROLE_ID = "ROLE_ID";
        String EMPLOYEE_CODE = "EMPLOYEE_CODE";
        String FULL_NAME = "FULL_NAME";
        String DESIGNATION_ID = "DESIGNATION_ID";
        String DESIGNATION_NAME = "DESIGNATION_NAME";
        String LOGIN_STATUS = "LOGIN_STATUS";
        String COUNTER_IDS = "COUNTER_IDS";
        String IMEI = "IMEI";
        String BRANCH_ID = "BRANCH_ID";
        String LOCAL_LOGIN_STATUS = "LOCAL_LOGIN_STATUS";
        String DEPARTMENT_IDS = "DEPARTMENT_IDS";


        objClsUserInfo.setEMPLOYEE_ID(mPreferences.getString(EMPLOYEE_ID, objClsUserInfo.getEMPLOYEE_ID()));
        objClsUserInfo.setFIRST_NAME(mPreferences.getString(FIRST_NAME, objClsUserInfo.getFIRST_NAME()));
        objClsUserInfo.setROLE_ID(mPreferences.getString(ROLE_ID, objClsUserInfo.getROLE_ID()));
        objClsUserInfo.setEMPLOYEE_CODE(mPreferences.getString(EMPLOYEE_CODE, objClsUserInfo.getEMPLOYEE_CODE()));
        objClsUserInfo.setFULL_NAME(mPreferences.getString(FULL_NAME, objClsUserInfo.getFULL_NAME()));
        objClsUserInfo.setDESIGNATION_ID(mPreferences.getString(DESIGNATION_ID, objClsUserInfo.getDESIGNATION_ID()));
        objClsUserInfo.setDESIGNATION_NAME(mPreferences.getString(DESIGNATION_NAME, objClsUserInfo.getDESIGNATION_NAME()));
        objClsUserInfo.setLOGIN_STATUS(mPreferences.getString(LOGIN_STATUS, objClsUserInfo.getLOGIN_STATUS()));
        objClsUserInfo.setCOUNTER_IDS(mPreferences.getString(COUNTER_IDS, objClsUserInfo.getCOUNTER_IDS()));
        objClsUserInfo.setIMEI(mPreferences.getString(IMEI, objClsUserInfo.getIMEI()));
        objClsUserInfo.setBRANCH_ID(mPreferences.getString(BRANCH_ID, objClsUserInfo.getBRANCH_ID()));
        objClsUserInfo.setLOCAL_LOGIN_STATUS(mPreferences.getString(LOCAL_LOGIN_STATUS, objClsUserInfo.getLOCAL_LOGIN_STATUS()));
        objClsUserInfo.setDEPARTMENT_IDS(mPreferences.getString(DEPARTMENT_IDS,objClsUserInfo.getDEPARTMENT_IDS()));

        Gson gson = new Gson();
        String jsonInString = gson.toJson(objClsUserInfo);
        Log.e("Result", "getobjClsUserInfo---" + jsonInString);

        return objClsUserInfo;
    }

}
