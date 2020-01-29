package com.nspl.restaurant.ViewModel.FragmentViewModel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.annotation.NonNull;

import com.nspl.restaurant.Global.Repository;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Counters.ClsCounterResponse;

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
