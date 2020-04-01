package com.nspl.restaurant.Activity;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.databinding.DataBindingUtil;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nspl.restaurant.Adapter.FilterCategoryAdapter;
import com.nspl.restaurant.Adapter.MenuItemAdapter;
import com.nspl.restaurant.R;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Menu.ClsCategorys;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Menu.ClsCustomCategory;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Menu.ClsDataMenu;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Menu.ClsItem;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Menu.ClsMenuResponse;
import com.nspl.restaurant.ViewModel.ActivityViewModel.MenuActivityViewModel;
import com.nspl.restaurant.databinding.ActivityMenuBinding;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity implements FilterCategoryAdapter.OnItemClickListenerCategory {

    ActivityMenuBinding mBinding;
    String _menuName;
    int departmentId,counterId,orderId,table_id;
    String counterType,orderNo,branchId,table_Number;
    List<ClsDataMenu> dataMenus = new ArrayList<>();
    List<ClsCategorys> listCategories = new ArrayList<>();

    MenuActivityViewModel mMenuActivityViewModel;
//    private MenuAdapter mMenuAdapter;
    private MenuItemAdapter menuItemAdapter;
    private FilterCategoryAdapter categoryAdapter;
    List<ClsCustomCategory> item_CustomCategory = new ArrayList<>();
    List<String> headerlist = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_menu);
        mBinding.rvMainMenu.setLayoutManager(new LinearLayoutManager(MenuActivity.this));

        mBinding.rvItemFilter.setLayoutManager(new
                LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        mBinding.rvCategoryFilter.setLayoutManager(new
                LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        mMenuActivityViewModel = ViewModelProviders.of(this).get(MenuActivityViewModel.class);

        SharedPreferences sp = getSharedPreferences("CounterData",MODE_PRIVATE);
        table_id = sp.getInt("TABLE_ID",0);
        counterId = sp.getInt("CounterId",0);
        departmentId = sp.getInt("departmentId",0);
        branchId = sp.getString("BranchId","");
        counterType = sp.getString("CounterType","");
        orderId = sp.getInt("OrderId",0);
        orderNo = sp.getString("OrderNo","");
        table_Number = sp.getString("Table_Number","");

        Log.e("--mode--", "_departmentID(MenuActivity): " + departmentId);

        menuItemAdapter = new MenuItemAdapter(this);
        categoryAdapter = new FilterCategoryAdapter(this,this);

        mBinding.rvMainMenu.setAdapter(menuItemAdapter);
        mBinding.rvCategoryFilter.setAdapter(categoryAdapter);

        mMenuActivityViewModel.getMenuResponse(departmentId).observe(this, clsMenuResponse -> {

            if (clsMenuResponse != null) {
                SetupList(clsMenuResponse);
                initToolbar();
            }

            categoryAdapter.addCategory(listCategories);
            mBinding.rvCategoryFilter.setAdapter(categoryAdapter);
        });

        menuItemAdapter.SetOnItemClickListener((clsCustomCategory, position) -> {
            Intent intent = new Intent(this, AddItemOrderActivity.class);
            intent.putExtra("ClsCustomCategory",clsCustomCategory);
            startActivity(intent);
        });
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(_menuName);
        getSupportActionBar().setSubtitle("Table : "+table_Number);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void OnclickOK(String cName) {
        Toast.makeText(this, cName, Toast.LENGTH_SHORT).show();
        Log.d("Test-----", "MenuActivity: "+cName);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void SetupList(ClsMenuResponse clsMenuResponse){
        @SuppressLint("StaticFieldLeak") AsyncTask<Void,Void,List<ClsCustomCategory>>
                asyncTask = new AsyncTask<Void, Void, List<ClsCustomCategory>>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                mBinding.pb.setVisibility(View.VISIBLE);
            }

            @Override
            protected List<ClsCustomCategory> doInBackground(Void... voids) {
                dataMenus = clsMenuResponse.getmDataMenu();

                for (ClsDataMenu currentDataMenu : dataMenus) {
                    _menuName = currentDataMenu.getmMenu().getNAME();

                    listCategories = currentDataMenu.getmCATEGORYS();

                    for (ClsCategorys item : listCategories){
                        List<ClsItem> items = item.getiTEMS();
                        for (ClsItem current : items){

                            item_CustomCategory.add(new ClsCustomCategory(item.getcATEGORYNAME()
                                    ,current.getnAME(),current.getfOODTYPE()
                                    ,current.getiTEMID(),current.getItemIMAGE(),current.getsIZES()
                                    ,current.getaDDONS(),current.getcOMMENTS(),current.getnUTRITIONS()
                                    ,current.getpARCELPERQUANTITY(),current.getpARCELCHARGE()
                                    ,current.getnUTRITIONTITLE()
                            ));
                        }
                    }

                    item_CustomCategory = sortAndAddSections(item_CustomCategory);

                    Gson gson = new Gson();
                    String jsonInString = gson.toJson(item_CustomCategory);
                    Log.e("lstCategorys", "item_CustomCategory--------------" + jsonInString);
                }
                return item_CustomCategory;
            }

            @Override
            protected void onPostExecute(List<ClsCustomCategory> list) {
                super.onPostExecute(list);
                mBinding.pb.setVisibility(View.GONE);

                if (list != null && list.size() > 0){
                    menuItemAdapter.AddCategory(list);
                }
            }
        };
        asyncTask.execute();
    }

    private List<ClsCustomCategory> sortAndAddSections(List<ClsCustomCategory> itemList) {

        List<ClsCustomCategory> tempList = new ArrayList<>();

        String header = "";
        for (int i = 0; i < itemList.size(); i++) {

            if (itemList.get(i).getcATEGORYNAME() != null) {
                if (!(header.equals(String.valueOf(itemList.get(i)
                        .getcATEGORYNAME())))) {
                    ClsCustomCategory sectionCell = new
                            ClsCustomCategory(String.valueOf(itemList.get(i)
                            .getcATEGORYNAME())
                            ,true);

                    if (!headerlist.contains(String.valueOf(itemList.get(i)
                            .getcATEGORYNAME()))) {

                        tempList.add(sectionCell);

                        headerlist.add(String.valueOf(itemList.get(i).getcATEGORYNAME()));
                    }
                }
            }

            Log.e("check", "outside if");
            tempList.add(itemList.get(i));
        }
        return tempList;
    }
}