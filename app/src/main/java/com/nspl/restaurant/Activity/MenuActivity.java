package com.nspl.restaurant.Activity;

import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.databinding.DataBindingUtil;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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
    int departmentId,counterId,orderId,table_id;
    String counterType,orderNo,branchId,table_Number;
    List<ClsDataMenu> dataMenus = new ArrayList<>();
    List<ClsCategorys> listCategorys = new ArrayList<>();

    MenuActivityViewModel mMenuActivityViewModel;
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

        SharedPreferences sp = getSharedPreferences("CounterData",MODE_PRIVATE);
        table_id = sp.getInt("TABLE_ID",0);
        counterId = sp.getInt("CounterId",0);
        departmentId = sp.getInt("departmentId",0);
        branchId = sp.getString("BranchId","Not found");
        counterType = sp.getString("CounterType","Not found");
        orderId = sp.getInt("OrderId",0);
        orderNo = sp.getString("OrderNo","Not found");
        table_Number = sp.getString("Table_Number","Not found");

        Log.e("--mode--", "_departmentID(MenuActivity): " + departmentId);

        mMenuAdapter = new MenuAdapter(this, table_id, counterId, departmentId,
                branchId, counterType,orderId,orderNo);
        categoryAdapter = new FilterCategoryAdapter(this,this);

        mBinding.rvMainMenu.setAdapter(mMenuAdapter);
        mBinding.rvCategoryFilter.setAdapter(categoryAdapter);

        mMenuActivityViewModel.getMenuResponse(departmentId).observe(this, clsMenuResponse -> {


            if (clsMenuResponse != null) {
                dataMenus = clsMenuResponse.getmDataMenu();

                for (ClsDataMenu currentDataMenu : dataMenus) {
                    _menuName = currentDataMenu.getmMenu().getNAME();
                    initToolbar();

                    listCategorys = currentDataMenu.getmCATEGORYS();

                    Gson gson = new Gson();
                    String jsonInString = gson.toJson(listCategorys);
                    Log.e("lstCategorys", "lstCategorys--------------" + jsonInString);

                    mMenuAdapter.addItems(listCategorys);
                    mBinding.rvMainMenu.setAdapter(mMenuAdapter);
                }
                mBinding.pb.setVisibility(View.GONE);
            }
            categoryAdapter.addCategory(listCategorys);
            mBinding.rvCategoryFilter.setAdapter(categoryAdapter);
            mBinding.pb.setVisibility(View.GONE);
        });

        mMenuAdapter.SetOnMenuClickListener((clsTable, position) -> {
            Toast.makeText(this, "Category Name", Toast.LENGTH_LONG).show();

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        MenuItem mSearchItem=menu.findItem(R.id.search);
        SearchView mSearchView= (SearchView) mSearchItem.getActionView();
        search(mSearchView);
        return super.onCreateOptionsMenu(menu);
    }

    private void search(SearchView searchView) {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });
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
                Intent intent = new Intent(MenuActivity.this, TablesActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                break;

            case R.id.search:
                Toast.makeText(this, "Search !!!!!!!!", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(MenuActivity.this, TablesActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}