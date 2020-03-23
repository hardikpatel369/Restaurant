package com.nspl.restaurant.ViewModel.ActivityViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.nspl.restaurant.Global.Repository;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Order.ClsOrder;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Order.ClsOrderResponse;

public class AddItemOrderActivityViewModel extends AndroidViewModel {

    private Repository mRepository;

    public AddItemOrderActivityViewModel(@NonNull Application application) {
        super(application);
        this.mRepository = new Repository(application);
    }

    public LiveData<ClsOrderResponse> PostItemOrder(ClsOrder clsOrder){
        return mRepository.PostItemOrder(clsOrder);
    }

}
