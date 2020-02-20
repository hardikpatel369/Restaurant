package com.nspl.restaurant.ViewModel.ActivityViewModel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.annotation.NonNull;

import com.nspl.restaurant.Global.Repository;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Menu.ClsMenuResponse;

public class MenuActivityViewModel extends AndroidViewModel {

    private Repository mRepository;

    public MenuActivityViewModel(@NonNull Application application) {
        super(application);

        this.mRepository = new Repository(application);
    }

    public LiveData<ClsMenuResponse> getMenuResponse(int departmentId){
        return mRepository.GetMenuList(departmentId);
    }
}
