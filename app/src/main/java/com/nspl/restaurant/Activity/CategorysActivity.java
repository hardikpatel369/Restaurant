package com.nspl.restaurant.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.nspl.restaurant.Adapter.CategoryAdapter;
import com.nspl.restaurant.R;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Menu.ClsCategorys;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Menu.ClsDataMenu;
import com.nspl.restaurant.ViewModel.ActivityViewModel.MenuActivityViewModel;
import com.nspl.restaurant.databinding.ActivityCategorysBinding;

import java.util.ArrayList;
import java.util.List;

public class CategorysActivity extends AppCompatActivity {

    ActivityCategorysBinding binding;
    MenuActivityViewModel mMenuActivityViewModel;
    CategoryAdapter adapter;
    int departmentId;
    String table_Number;
    String CounterType;
    String orderNo;
    List<ClsCategorys> listCategories = new ArrayList<>();
    List<ClsDataMenu> dataMenus = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_categorys);
        binding.pb.setVisibility(View.VISIBLE);

        SharedPreferences sp = getSharedPreferences("CounterData", MODE_PRIVATE);
        departmentId = sp.getInt("departmentId", 0);
        table_Number = sp.getString("Table_Number", "");
        CounterType = sp.getString("CounterType", "");
        orderNo = sp.getString("OrderNo", "");

        initToolbar();

//        binding.rvCategory.setLayoutManager(new GridLayoutManager(CategorysActivity.this,2));
        binding.rvCategory.setLayoutManager(new LinearLayoutManager(CategorysActivity.this));

        adapter = new CategoryAdapter(this);
        binding.rvCategory.setAdapter(adapter);

        mMenuActivityViewModel = ViewModelProviders.of(this).get(MenuActivityViewModel.class);

        adapter.SetOnItemListClickListener((clsCategorys, position) -> {
            Intent intent = new Intent(CategorysActivity.this, CategoryItemsActivity.class);
            intent.putExtra("clsCategorys", clsCategorys);
            intent.putExtra("categoryName", clsCategorys.getcATEGORYNAME());
            startActivity(intent);
        });
        loadAdapter();

        binding.swipeToRefresh.setOnRefreshListener(() -> {
            binding.swipeToRefresh.setRefreshing(true);
            loadAdapter();
        });
    }

    private void loadAdapter() {
        mMenuActivityViewModel.getMenuResponse(departmentId).observe(this, clsMenuResponse -> {

            if (clsMenuResponse != null) {
                dataMenus = clsMenuResponse.getmDataMenu();

                for (ClsDataMenu currentDataMenu : dataMenus) {
                    listCategories = currentDataMenu.getmCATEGORYS();

                    if (listCategories != null) {
                        adapter.addCategory(listCategories);
                        binding.pb.setVisibility(View.GONE);
                        binding.swipeToRefresh.setRefreshing(false);
                    }
                }
            }
        });
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Category");
        if (CounterType.equalsIgnoreCase("RETAIL")) {
            getSupportActionBar().setSubtitle("Order No : " + orderNo);
        } else {
            getSupportActionBar().setSubtitle("Table : " + table_Number);
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, OrderDetailActivity.class);
        startActivity(intent);
    }
}
