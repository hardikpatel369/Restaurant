package com.nspl.restaurant.ViewModel.ActivityViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.nspl.restaurant.Global.Repository;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Menu.ClsMenuResponse;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Tables.ClsTablesResponse;

public class MenuActivityViewModel extends AndroidViewModel {

    private Repository mRepository;

    public MenuActivityViewModel(@NonNull Application application) {
        super(application);

        this.mRepository = new Repository(application);
    }

    public LiveData<ClsMenuResponse> getMenuResponse(){
        return mRepository.GetMenuList();
    }
}
