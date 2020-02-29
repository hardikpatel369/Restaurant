package com.nspl.restaurant.ViewModel.FragmentViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.nspl.restaurant.Global.Repository;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Waiting.ClsRequestWaitingResponse;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Waiting.ClsWaitingResponse;

public class WaitingFragmentViewModel extends AndroidViewModel {
    private Repository mRepository;

    public WaitingFragmentViewModel(@NonNull Application application) {
        super(application);

        this.mRepository=new Repository(application);
    }
    public LiveData<ClsWaitingResponse> insertWaiting(ClsWaitingResponse clsWaitingResponse){
        return mRepository.postWaitingList(clsWaitingResponse);
    }
    public LiveData<ClsWaitingResponse> updateWaiting(ClsWaitingResponse clsWaitingResponse){
        return mRepository.updateWaitingList(clsWaitingResponse);
    }
    public LiveData<ClsWaitingResponse> deleteWaiting(ClsWaitingResponse clsWaitingResponse){
        return mRepository.deleteWaitingObj(clsWaitingResponse);
    }

    public LiveData<ClsWaitingResponse> completeWaiting(ClsWaitingResponse clsWaitingResponse){
        return mRepository.completeWaitingObj(clsWaitingResponse);
    }

    public LiveData<ClsWaitingResponse> getWaitingResponse(int _branchId){
        return mRepository.GetWatingPersonList(_branchId);
    }

    public LiveData<ClsRequestWaitingResponse> getRequestResponse(){
        return mRepository.GetSpecialRequestlist();
    }
}
