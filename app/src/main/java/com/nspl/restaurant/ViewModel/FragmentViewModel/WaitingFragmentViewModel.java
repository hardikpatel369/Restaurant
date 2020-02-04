package com.nspl.restaurant.ViewModel.FragmentViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.nspl.restaurant.Global.Repository;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Waiting.ClsWaitingResponse;

public class WaitingFragmentViewModel extends AndroidViewModel {
    private Repository mRepository;

    public WaitingFragmentViewModel(@NonNull Application application) {
        super(application);

        this.mRepository=new Repository(application);
    }
    public LiveData<ClsWaitingResponse> getWaitingResponse(){
        return mRepository.getWaitingList();
    }
}
