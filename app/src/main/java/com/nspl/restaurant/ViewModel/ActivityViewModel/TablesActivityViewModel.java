package com.nspl.restaurant.ViewModel.ActivityViewModel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.annotation.NonNull;

import com.nspl.restaurant.Global.Repository;

import com.nspl.restaurant.RetrofitApi.ApiClasses.Tables.ClsTablesResponse;

public class TablesActivityViewModel extends AndroidViewModel {


    private Repository mRepository;


    public TablesActivityViewModel(@NonNull Application application) {
        super(application);

        this.mRepository = new Repository(application);

    }

    public LiveData<ClsTablesResponse> getTablesResponse(int departmentId) {
        return mRepository.GetTables( departmentId);
    }
}
