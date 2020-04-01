package com.nspl.restaurant.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.google.gson.Gson;
import com.nspl.restaurant.Adapter.CategoryItemsAdapter;
import com.nspl.restaurant.R;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Menu.ClsCategorys;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Menu.ClsItem;
import com.nspl.restaurant.ViewModel.ActivityViewModel.MenuActivityViewModel;
import com.nspl.restaurant.databinding.ActivityCategoryItemsBinding;

import java.util.ArrayList;
import java.util.List;

public class CategoryItemsActivity extends AppCompatActivity {

    ActivityCategoryItemsBinding binding;
    MenuActivityViewModel mMenuActivityViewModel;
    ClsCategorys clsCategorys = new ClsCategorys();
    List<ClsItem> listItems = new ArrayList<>();
    CategoryItemsAdapter adapter;
    int departmentId;
    String categoryName;
    String table_Number;
    String CounterType;
    String orderNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_category_items);
        binding.pb.setVisibility(View.VISIBLE);

        SharedPreferences sp = getSharedPreferences("CounterData", MODE_PRIVATE);
        departmentId = sp.getInt("departmentId", 0);
        table_Number = sp.getString("Table_Number", "Not found");
        CounterType = sp.getString("CounterType", "");
        orderNo = sp.getString("OrderNo", "");


        Intent intent = getIntent();
        clsCategorys = (ClsCategorys) intent.getSerializableExtra("clsCategorys");
        categoryName = intent.getStringExtra("categoryName");
        Gson gson = new Gson();
        String jsonInString = gson.toJson(clsCategorys);
        Log.e("clsCategorys", "onCreate---" + jsonInString);

        initToolbar();

//        binding.rvCategoryItems.setLayoutManager(new GridLayoutManager(CategoryItemsActivity.this, 2));
        binding.rvCategoryItems.setLayoutManager(new LinearLayoutManager(CategoryItemsActivity.this));
        adapter = new CategoryItemsAdapter(this);
        binding.rvCategoryItems.setAdapter(adapter);

        mMenuActivityViewModel = ViewModelProviders.of(this).get(MenuActivityViewModel.class);
        LoadAdapter();

        adapter.SetOnItemListClickListener((clsItem, position) -> {
            Intent intent1 = new Intent(CategoryItemsActivity.this, AddItemOrderActivity.class);
            intent1.putExtra("clsItem", clsItem);
            startActivity(intent1);
        });

        binding.swipeToRefresh.setOnRefreshListener(() -> {
            binding.swipeToRefresh.setRefreshing(true);
            LoadAdapter();
        });

    }

    private void LoadAdapter() {
        mMenuActivityViewModel.getMenuResponse(departmentId).observe(this, clsMenuResponse -> {

            if (clsMenuResponse != null) {
                listItems = clsCategorys.getiTEMS();

                if (listItems != null) {
                    adapter.addCategoryItemsAdapter(listItems);
                    binding.pb.setVisibility(View.GONE);
                    binding.swipeToRefresh.setRefreshing(false);
                }
            }
        });
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(categoryName);
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
        Intent intent = new Intent(this, CategorysActivity.class);
        startActivity(intent);
    }
}
