package com.nspl.restaurant.ViewModel.ActivityViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.nspl.restaurant.Global.Repository;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Kitchen.ClsKitchenSectionResponse;

public class KitchenSectionActivityViewModel extends AndroidViewModel {

    private Repository mRepository;

    public KitchenSectionActivityViewModel(@NonNull Application application) {
        super(application);

        this.mRepository = new Repository(application);
    }

    public LiveData<ClsKitchenSectionResponse> getKitchenSection(String employeeID, int kitchenID, String section, String itemidlist){
        return mRepository.GetKitchenSection(employeeID,kitchenID,section,itemidlist);
    }

}
