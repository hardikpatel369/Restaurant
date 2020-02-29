package com.nspl.restaurant.Activity;

import android.app.Dialog;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import android.content.Intent;
import androidx.databinding.DataBindingUtil;

import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.nspl.restaurant.Adapter.TableAdapter;
import com.nspl.restaurant.R;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Tables.ClsTable;
import com.nspl.restaurant.ViewModel.ActivityViewModel.TablesActivityViewModel;
import com.nspl.restaurant.databinding.ActivityTablesBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TablesActivity extends AppCompatActivity {

    ActivityTablesBinding mBinding;
    TablesActivityViewModel mTablesActivityViewModel;
    int departmentId;
    private TableAdapter tableAdapter;
    private TextView tvTableNo, tvMenu, tvOrderDetail;

    List<ClsTable> tablesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_tables);
        mBinding.pb.setVisibility(View.VISIBLE);

        initToolbar();

        SharedPreferences sp = getSharedPreferences("CounterData",MODE_PRIVATE);
        departmentId = sp.getInt("departmentId",0);


        mBinding.rv.setLayoutManager(new GridLayoutManager(TablesActivity.this, 2));

        mTablesActivityViewModel = ViewModelProviders.of(this).get(TablesActivityViewModel.class);

        mBinding.swipeToRefresh.setOnRefreshListener(() -> {
            mBinding.swipeToRefresh.setRefreshing(true);
            loadAdapter();
        });

        Log.e("--mode--", "_departmentID(TableActivity): " + departmentId);
    }

    void loadAdapter() {
        tableAdapter = new TableAdapter(TablesActivity.this);

        mBinding.rv.setAdapter(tableAdapter);

        mTablesActivityViewModel.getTablesResponse(departmentId).observe(this, clsTablesResponse -> {

            if (clsTablesResponse != null) {
                tablesList = clsTablesResponse.getdATA();

                if (tablesList.size() != 0) {
                    tableAdapter.AddItems(tablesList);
                    mBinding.pb.setVisibility(View.GONE);
                    mBinding.swipeToRefresh.setRefreshing(false);
                }
            }
        });

        tableAdapter.SetOnCounterClickListener((clsTable, position) -> {

            ClsTable current = tablesList.get(position);
            final Dialog mDialog = new Dialog(this);
            mDialog.setContentView(R.layout.dialog_table);
            mDialog.setCanceledOnTouchOutside(true);
            Objects.requireNonNull(mDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            mDialog.setCancelable(true);
            mDialog.show();
            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            lp.copyFrom(Objects.requireNonNull(mDialog.getWindow()).getAttributes());
            lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
            mDialog.getWindow().setAttributes(lp);

            tvMenu = mDialog.findViewById(R.id.tvMenu);
            tvTableNo = mDialog.findViewById(R.id.tvTableNo);
            tvOrderDetail = mDialog.findViewById(R.id.tvOrderDetail);

            tvTableNo.setText(current.getTABLENAMENUMBER());
            String tableNumber = clsTable.getTABLENAMENUMBER();

            SharedPreferences sharedPreferences=getSharedPreferences("CounterData",MODE_PRIVATE);
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putInt("TABLE_ID",clsTable.getTABLEID());
            editor.putInt("OrderId",clsTable.getRUNNINGORDERID());
            editor.putString("OrderNo",clsTable.getRunningOrderNo());
            editor.putString("Table_Number",tableNumber);
            editor.putInt("quantity_Total",clsTable.getTOTALQUANTITY());
            editor.putString("grand_Total", String.valueOf(clsTable.getTOTALAMOUNT()));
            editor.apply();

            tvMenu.setOnClickListener(v -> {
                Intent intent = new Intent(TablesActivity.this, MenuActivity.class);
                startActivity(intent);
            });

            tvOrderDetail.setOnClickListener(v -> {
                Intent intent1 = new Intent(TablesActivity.this, OrderDetailActivity.class);
                startActivity(intent1);
            });
        });
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Tables");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                Intent intent = new Intent(TablesActivity.this, HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(TablesActivity.this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadAdapter();
    }
}