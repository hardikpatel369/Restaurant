package com.nspl.restaurant.ViewModel.ActivityViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.nspl.restaurant.Global.Repository;

import com.nspl.restaurant.RetrofitApi.ApiClasses.Tables.ClsTablesResponse;

public class TablesActivityViewModel extends AndroidViewModel {


    private Repository mRepository;


    public TablesActivityViewModel(@NonNull Application application) {
        super(application);

        this.mRepository = new Repository(application);

    }

    public LiveData<ClsTablesResponse> getTablesResponse(int _departmentID) {
        return mRepository.GetTables(_departmentID);
    }
}
