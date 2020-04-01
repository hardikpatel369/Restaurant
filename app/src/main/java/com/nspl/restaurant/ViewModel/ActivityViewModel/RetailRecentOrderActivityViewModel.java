package com.nspl.restaurant.ViewModel.ActivityViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.nspl.restaurant.Global.Repository;
import com.nspl.restaurant.RetrofitApi.ApiClasses.CustomerInfo.ClsRetailCustomer;
import com.nspl.restaurant.RetrofitApi.ApiClasses.CustomerInfo.ClsRetailCustomerResponse;
import com.nspl.restaurant.RetrofitApi.ApiClasses.RetailRecentOrder.ClsRetailRecentOrderResponse;

public class RetailRecentOrderActivityViewModel extends AndroidViewModel {

    private Repository mRepository;

    public RetailRecentOrderActivityViewModel(@NonNull Application application) {
        super(application);

        this.mRepository = new Repository(application);
    }

    public LiveData<ClsRetailRecentOrderResponse> getRetailRecentOrder(int counterId,int departmentId){
        return mRepository.GetRetailRecentOrder(counterId,departmentId);
    }

    public LiveData<ClsRetailCustomerResponse> postRetailCustomer(ClsRetailCustomer clsRetailCustomer){
        return mRepository.PostRetailCustomer(clsRetailCustomer);
    }
}
