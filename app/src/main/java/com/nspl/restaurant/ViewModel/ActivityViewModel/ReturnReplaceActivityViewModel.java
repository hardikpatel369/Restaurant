package com.nspl.restaurant.ViewModel.ActivityViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.nspl.restaurant.Global.Repository;
import com.nspl.restaurant.RetrofitApi.ApiClasses.OrderActions.ClsReturnReplace;
import com.nspl.restaurant.RetrofitApi.ApiClasses.ReturnReplace.CLsReturnReplaceResponse;

public class ReturnReplaceActivityViewModel extends AndroidViewModel {

    private Repository mRepository;

    public ReturnReplaceActivityViewModel(@NonNull Application application) {
        super(application);

        this.mRepository = new Repository(application);
    }

    public LiveData<ClsReturnReplace> getReturnReplace(int OrderDetailId, boolean status){
        return mRepository.GetReturnReplace(OrderDetailId,status);
    }

}
