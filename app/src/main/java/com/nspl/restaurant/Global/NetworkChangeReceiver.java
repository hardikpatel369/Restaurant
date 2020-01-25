package com.nspl.restaurant.Global;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.nspl.restaurant.R;


public class NetworkChangeReceiver extends BroadcastReceiver {


    NetworkChangers networkChangersListener;

    @Override
    public void onReceive(Context context, Intent intent) {

        try {
            if (!ClsGlobal.CheckInternetConnection(context)) {
                networkChangersListener.OnNetworkChange(context.getString(R.string.NoInternet));
                Log.e("keshav", "Conectivity Failure !!! ");
            }

        } catch (NullPointerException e) {
            e.printStackTrace();
        }

    }


    public void SetOnNetworkChange(NetworkChangers networkChangersListener) {
        this.networkChangersListener = networkChangersListener;
    }


    public interface NetworkChangers {
        void OnNetworkChange(String status);
    }


}
