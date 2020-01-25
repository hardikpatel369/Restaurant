package com.nspl.restaurant.Activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;

import com.nspl.restaurant.Adapter.TableAdapter;
import com.nspl.restaurant.R;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Tables.ClsTable;
import com.nspl.restaurant.ViewModel.ActivityViewModel.TablesActivityViewModel;
import com.nspl.restaurant.databinding.ActivityTablesBinding;

import java.util.ArrayList;
import java.util.List;

public class TablesActivity extends AppCompatActivity {

    ActivityTablesBinding mBinding;
    TablesActivityViewModel mTablesActivityViewModel;
    String Mode = "";
    String counterId = "";
    int departmentId = 0;
    String branchId = "";
    private TableAdapter tableAdapter;

    List<ClsTable> tablesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tables);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_tables);

        setTitle("Tables");

        mBinding.rv.setLayoutManager(new GridLayoutManager(TablesActivity.this, 2));

        mTablesActivityViewModel = ViewModelProviders.of(this)
                .get(TablesActivityViewModel.class);

        Mode = getIntent().getStringExtra("Mode");
        counterId = getIntent().getStringExtra("CounterId");
        departmentId = getIntent().getIntExtra("departmentId",0);
        branchId = getIntent().getStringExtra("BranchId");
        Log.e("--mode--", "Mode: " + Mode);

        Log.e("--mode--", "_departmentID: " + departmentId);

        tableAdapter = new TableAdapter(TablesActivity.this);

        mBinding.rv.setAdapter(tableAdapter);

        mTablesActivityViewModel.getTablesResponse(departmentId).observe(this, clsTablesResponse -> {

            if (clsTablesResponse != null) {
                tablesList = clsTablesResponse.getdATA();

                if (tablesList.size() != 0) {
                    tableAdapter.AddItems(tablesList);
                }
            }
        });

        tableAdapter.SetOnCounterClickListener((clsTable, position) ->
                startActivity(new Intent(TablesActivity.this, MenuActivity.class)
                        .putExtra("TableNo", clsTable.getTABLENAMENUMBER())
                        .putExtra("TableStatus", clsTable.getSTATUS())));
    }
}
