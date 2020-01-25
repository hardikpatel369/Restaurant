package com.nspl.restaurant.RetrofitApi.ApiClasses.Menu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ClsDataMenu {


    @SerializedName("MENU")
    @Expose
    private ClsMenu mMenu;


    @SerializedName("CATEGORYS")
    @Expose
    private List<ClsCategorys> mCATEGORYS;


    public ClsMenu getmMenu() {
        return mMenu;
    }

    public void setmMenu(ClsMenu mMenu) {
        this.mMenu = mMenu;
    }

    public List<ClsCategorys> getmCATEGORYS() {
        return mCATEGORYS;
    }

    public void setmCATEGORYS(List<ClsCategorys> mCATEGORYS) {
        this.mCATEGORYS = mCATEGORYS;
    }
}
