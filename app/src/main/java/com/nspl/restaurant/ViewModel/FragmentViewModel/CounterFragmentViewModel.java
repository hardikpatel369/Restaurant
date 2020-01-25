package com.nspl.restaurant.ViewModel.FragmentViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.nspl.restaurant.Global.Repository;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Counters.ClsCounterResponse;

import java.util.List;

public class CounterFragmentViewModel extends AndroidViewModel {


//    private LiveData<ClsCounterResponse> CounterResponse;
    private Repository mRepository;

    public CounterFragmentViewModel(@NonNull Application application) {
        super(application);

        this.mRepository = new Repository(application);
//        CounterResponse = mRepository.GetCounters();
    }


    public LiveData<ClsCounterResponse> getCountersResponse(){
        return  mRepository.GetCounters();
    }

}
