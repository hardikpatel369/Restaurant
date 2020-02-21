package com.nspl.restaurant.ViewModel.FragmentViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.nspl.restaurant.Global.Repository;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Waiting.ClsWaitingList;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Waiting.ClsWaitingResponse;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Waiting.ClsWaitingResponseCls;

public class WaitingFragmentViewModel extends AndroidViewModel {
    private Repository mRepository;

    public WaitingFragmentViewModel(@NonNull Application application) {
        super(application);

        this.mRepository=new Repository(application);
    }
    public LiveData<ClsWaitingResponse> insertWaiting(ClsWaitingResponse clsWaitingResponse){
        return mRepository.postWaitingList(clsWaitingResponse);
    }

//    public LiveData<ClsWaitingResponse> getWaitingResponse(int _departmentID){
//        return mRepository.GetWatingPersonList(_departmentID);
//    }

    public LiveData<ClsWaitingResponse> getWaitingResponse(int _branchId){
        return mRepository.GetWatingPersonList(_branchId);
    }

//    public void setDate(ClsWaitingList clsWaitingList) {
//        return mRepository.postWaitingList(clsWaitingList);
//    }
}
