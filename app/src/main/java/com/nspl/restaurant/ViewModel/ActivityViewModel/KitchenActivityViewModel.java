package com.nspl.restaurant.ViewModel.ActivityViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.nspl.restaurant.Global.Repository;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Kitchen.ClsKitchenResponse;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Kitchen.ClsKitchenSectionResponse;

public class KitchenActivityViewModel extends AndroidViewModel {

    private Repository mRepository;

    public KitchenActivityViewModel(@NonNull Application application) {
        super(application);

        this.mRepository = new Repository(application);
    }

    public LiveData<ClsKitchenResponse> getKitchen(String employeeid){
        return mRepository.GetKitchen(employeeid);
    }

}
