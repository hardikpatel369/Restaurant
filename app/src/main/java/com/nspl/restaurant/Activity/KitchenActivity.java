package com.nspl.restaurant.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.nspl.restaurant.Adapter.KitchenAdapter;
import com.nspl.restaurant.R;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Kitchen.ClsKitchen;
import com.nspl.restaurant.ViewModel.ActivityViewModel.KitchenActivityViewModel;
import com.nspl.restaurant.databinding.ActivityKitchenBinding;

import java.util.ArrayList;
import java.util.List;

public class KitchenActivity extends AppCompatActivity {

    ActivityKitchenBinding mBinding;
    String employeeId;
    KitchenActivityViewModel kitchenActivityViewModel;
    private KitchenAdapter adapter;
    List<ClsKitchen> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_kitchen);
        mBinding.pb.setVisibility(View.VISIBLE);

        initToolbar();

        SharedPreferences sha = getSharedPreferences("LoginDetails", MODE_PRIVATE);
        employeeId = sha.getString("EMPLOYEE_ID", "");

        mBinding.rvKitchen.setLayoutManager(new GridLayoutManager(KitchenActivity.this, 2));

        adapter = new KitchenAdapter(this);
        mBinding.rvKitchen.setAdapter(adapter);

        kitchenActivityViewModel = ViewModelProviders.of(this).get(KitchenActivityViewModel.class);

        loadAdapter();

        adapter.SetOnClickListener((clsKitchen, position) -> {
            Intent intent = new Intent(KitchenActivity.this, KitchenSectionsActivity.class);
            intent.putExtra("kitchenId", clsKitchen.getKITCHENID());
            intent.putExtra("section", clsKitchen.getSECTION());
            intent.putExtra("kitchenName", clsKitchen.getKITCHENNAME());
            startActivity(intent);
        });

        mBinding.swipeToRefresh.setOnRefreshListener(() -> {
            mBinding.swipeToRefresh.setRefreshing(true);
            loadAdapter();
        });
    }

    private void loadAdapter() {
        adapter = new KitchenAdapter(this);

        mBinding.rvKitchen.setAdapter(adapter);

        kitchenActivityViewModel.getKitchen(employeeId).observe(this,clsKitchenSectionResponse -> {

            if (clsKitchenSectionResponse != null){
                list = clsKitchenSectionResponse.getDATA();

                if (list.size() != 0){
                    adapter.AddItems(list);
                    mBinding.pb.setVisibility(View.GONE);
                    mBinding.swipeToRefresh.setRefreshing(false);
                }
            }
        });
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Kitchen");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
