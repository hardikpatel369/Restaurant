package com.nspl.restaurant.Activity;

import androidx.lifecycle.ViewModelProviders;
import androidx.databinding.DataBindingUtil;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nspl.restaurant.Adapter.FilterCategoryAdapter;
import com.nspl.restaurant.Adapter.MenuAdapter;
import com.nspl.restaurant.R;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Menu.ClsCategorys;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Menu.ClsDataMenu;
import com.nspl.restaurant.ViewModel.ActivityViewModel.MenuActivityViewModel;
import com.nspl.restaurant.databinding.ActivityMenuBinding;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity implements FilterCategoryAdapter.OnItemClickListenerCategory {

    ActivityMenuBinding mBinding;
    String _menuName;
    List<ClsDataMenu> dataMenus = new ArrayList<>();
    List<ClsCategorys> lstCategorys = new ArrayList<>();

    MenuActivityViewModel mMenuActivityViewModel;
    String TableNo = "", TableStatus = "";
    private MenuAdapter mMenuAdapter;
    private FilterCategoryAdapter categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_menu);
        mBinding.rvMainMenu.setLayoutManager(new LinearLayoutManager(MenuActivity.this));

        mBinding.pb.setVisibility(View.VISIBLE);
        mBinding.rvItemFilter.setLayoutManager(new
                LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        mBinding.rvCategoryFilter.setLayoutManager(new
                LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        mMenuActivityViewModel = ViewModelProviders.of(this).get(MenuActivityViewModel.class);

        mMenuAdapter = new MenuAdapter(this);
        categoryAdapter = new FilterCategoryAdapter(this,this);

        mBinding.rvMainMenu.setAdapter(mMenuAdapter);
        mBinding.rvCategoryFilter.setAdapter(categoryAdapter);
        TableNo = getIntent().getStringExtra("TableNo");
        TableStatus = getIntent().getStringExtra("TableStatus");

        mMenuActivityViewModel.getMenuResponse().observe(this, clsMenuResponse -> {


            if (clsMenuResponse != null) {
                dataMenus = clsMenuResponse.getmDataMenu();

                for (ClsDataMenu currentDataMenu : dataMenus) {
                    _menuName = currentDataMenu.getmMenu().getNAME();
                    setTitle(_menuName);

                    lstCategorys = currentDataMenu.getmCATEGORYS();

                    Gson gson = new Gson();
                    String jsonInString = gson.toJson(lstCategorys);
                    Log.e("lstCategorys", "lstCategorys--------------" + jsonInString);

                    mMenuAdapter.addItems(lstCategorys);
                    mBinding.rvMainMenu.setAdapter(mMenuAdapter);
                }
                mBinding.pb.setVisibility(View.GONE);
            }
            categoryAdapter.addCategory(lstCategorys);
            mBinding.rvCategoryFilter.setAdapter(categoryAdapter);
            mBinding.pb.setVisibility(View.GONE);
        });

        mMenuAdapter.SetOnMenuClickListener((clsTable, position) -> {
            Toast.makeText(this, "Category Name", Toast.LENGTH_LONG).show();

        });
    }

    @Override
    public void OnclickOK(String cName) {
        Toast.makeText(this, cName, Toast.LENGTH_SHORT).show();
        Log.d("Test-----", "MenuActivity: "+cName);
    }
}