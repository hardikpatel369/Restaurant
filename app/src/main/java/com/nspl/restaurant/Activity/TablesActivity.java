package com.nspl.restaurant.Activity;

import android.app.Dialog;
import androidx.lifecycle.ViewModelProviders;
import android.content.Intent;
import androidx.databinding.DataBindingUtil;
import android.graphics.drawable.ColorDrawable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import android.util.Log;
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
    String Mode = "";
    String counterId = "";
    int departmentId = 0;
    String branchId = "";
    private TableAdapter tableAdapter;
    private TextView tvTableNo,tvMenu,tvOrderDetail;

    List<ClsTable> tablesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tables);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_tables);

        setTitle("Tables");

        mBinding.rv.setLayoutManager(new GridLayoutManager(TablesActivity.this, 2));

        mTablesActivityViewModel =                  ViewModelProviders.of(this)
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

        tableAdapter.SetOnCounterClickListener((clsTable, position) -> {
            Intent intent = new Intent();
            intent.putExtra("TableNo", clsTable.getTABLENAMENUMBER());
            intent.putExtra("TableStatus", clsTable.getSTATUS());
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
            tvMenu.setOnClickListener(v -> startActivity(new Intent(TablesActivity.this,MenuActivity.class)));


        });

//        tableAdapter.SetOnCounterClickListener((clsTable, position) ->
//                startActivity(new Intent(TablesActivity.this, MenuActivity.class)
//                        .putExtra("TableNo", clsTable.getTABLENAMENUMBER())
//                        .putExtra("TableStatus", clsTable.getSTATUS())));
    }
}