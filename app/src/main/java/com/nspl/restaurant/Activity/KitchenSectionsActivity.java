package com.nspl.restaurant.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.nspl.restaurant.Adapter.KitchenSectionAdapter;
import com.nspl.restaurant.R;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Kitchen.ClsKitchenSection;
import com.nspl.restaurant.ViewModel.ActivityViewModel.KitchenSectionActivityViewModel;
import com.nspl.restaurant.databinding.ActivityKitchenSectionsBinding;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import java8.util.stream.Collectors;
import java8.util.stream.StreamSupport;

public class KitchenSectionsActivity extends AppCompatActivity {

    ActivityKitchenSectionsBinding binding;
    private KitchenSectionAdapter adapter;
    List<ClsKitchenSection> sectionList = new ArrayList<>();
    List<ClsKitchenSection> _listItemAddons = new ArrayList<>();
    List<ClsKitchenSection> listItems = new ArrayList<>();
    List<ClsKitchenSection> listTableToken = new ArrayList<>();
    Context context;
    String section;
    String kitchenName;
    String itemidlist = "";
    int kitchenID;
    String employeeId;
    List<String> tableTokenList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_kitchen_sections);

        Intent intent = getIntent();
        section = intent.getStringExtra("section");
        kitchenName = intent.getStringExtra("kitchenName");
        kitchenID = intent.getIntExtra("kitchenId", 0);

        SharedPreferences sha = getSharedPreferences("LoginDetails", MODE_PRIVATE);
        employeeId = sha.getString("EMPLOYEE_ID", "");

        binding.pb.setVisibility(View.VISIBLE);

        initToolbar();

        adapter = new KitchenSectionAdapter(context);
        binding.rvKitchen.setLayoutManager(new LinearLayoutManager(context));

        loadAdapter();

        binding.swipeToRefresh.setOnRefreshListener(() -> {
            binding.swipeToRefresh.setRefreshing(true);
            loadAdapter();
        });

        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
//                adapter.updateList(sectionList);
                loadAdapter();
                handler.postDelayed(this, 60000);
            }
        };
        handler.post(runnable);
    }

    private void loadAdapter() {
        KitchenSectionActivityViewModel kitchenSectionActivityViewModel = ViewModelProviders.of(this).get(KitchenSectionActivityViewModel.class);

        kitchenSectionActivityViewModel.getKitchenSection(employeeId, kitchenID, section, itemidlist).observe(this, clsKitchenSectionResponse -> {

            if (clsKitchenSectionResponse != null) {
                sectionList = clsKitchenSectionResponse.getDATA();

                if (sectionList.size() != 0) {
                    listItems = StreamSupport.stream(sectionList)
                            .filter(s -> s.getISADDON().equals(false))
                            .collect(Collectors.toList());

                    Gson gson = new Gson();
                    String jsonInString = gson.toJson(listItems);
                    Log.e("Kitchen", "Item---" + jsonInString);

                    for (ClsKitchenSection _ObjItem : listItems) {

                        _listItemAddons = StreamSupport.stream(sectionList)
                                .filter(s -> s.getISADDON().equals(true) && s.getREFRANCEITEMID().equals(_ObjItem.getREFRANCEITEMID()))
                                .collect(Collectors.toList());

                        _ObjItem.setListAddons(_listItemAddons);
                        listItems.set(listItems.indexOf(_ObjItem), _ObjItem);

                        String jsonInString2 = gson.toJson(_listItemAddons);
                        Log.e("Kitchen", "Addon---" + jsonInString2);
                    }

                    List<String> tableTokenList = new ArrayList<>();
                    for (int i = 0; i < sectionList.size(); i++) {
                        tableTokenList.add(sectionList.get(i).getTABLENAMENUMBER());
                        Log.e("Kitchen", "tableTokenList---" + tableTokenList);
                    }

                    Set<String> set = new HashSet<>(tableTokenList);
                    tableTokenList.clear();
                    tableTokenList.addAll(set);
                    Log.e("Kitchen", "RemoveSame : TableTokenList---" + tableTokenList);

//                    for (int i = 0; i < tableTokenList.size(); i++) {
//                        String tableToken = tableTokenList.get(i);
//
//                        for (int b = 0; b < sectionList.size(); b++) {
//                            if (sectionList.get(b).getTABLENAMENUMBER().equals(tableToken)) {
//                                listTableToken.add(sectionList.get(b));
//                                Log.e("Kitchen", "listTableToken---" + tableTokenList);
//                            }
//                        }
//                    }

                    adapter.addOrderDetail(listItems);
                    binding.rvKitchen.setAdapter(adapter);
                    binding.pb.setVisibility(View.GONE);
                    binding.swipeToRefresh.setRefreshing(false);
                } else {
                    binding.tvNoData.setVisibility(View.VISIBLE);
                    binding.tvNoData.setText(clsKitchenSectionResponse.getMESSAGE());
                    binding.pb.setVisibility(View.GONE);
                    binding.swipeToRefresh.setRefreshing(false);
                }
            }
        });
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(kitchenName);
        getSupportActionBar().setSubtitle(section);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
