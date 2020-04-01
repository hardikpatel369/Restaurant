package com.nspl.restaurant.Activity;

import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.ViewModelProviders;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;

import androidx.databinding.DataBindingUtil;

import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

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
    int departmentId;
    private TableAdapter tableAdapter;

    List<ClsTable> tablesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_tables);
        mBinding.pb.setVisibility(View.VISIBLE);

        initToolbar();

        SharedPreferences sp = getSharedPreferences("CounterData", MODE_PRIVATE);
        departmentId = sp.getInt("departmentId", 0);


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

//            ClsTable current = tablesList.get(position);
//            final Dialog mDialog = new Dialog(this);
//            mDialog.setContentView(R.layout.dialog_table);
//            mDialog.setCanceledOnTouchOutside(true);
//            Objects.requireNonNull(mDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
//            mDialog.setCancelable(true);
//            mDialog.show();
//            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
//            lp.copyFrom(Objects.requireNonNull(mDialog.getWindow()).getAttributes());
//            lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
//            mDialog.getWindow().setAttributes(lp);
//
//            tvMenu = mDialog.findViewById(R.id.tvMenu);
//            tvTableNo = mDialog.findViewById(R.id.tvTableNo);
//            tvOrderDetail = mDialog.findViewById(R.id.tvOrderDetail);
//
//            tvTableNo.setText(current.getTABLENAMENUMBER());
//            String tableNumber = clsTable.getTABLENAMENUMBER();

            SharedPreferences sharedPreferences = getSharedPreferences("CounterData", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("TABLE_ID", clsTable.getTABLEID());
            editor.putString("TABLE_NO", clsTable.getTABLENAMENUMBER());
            editor.putInt("OrderId", clsTable.getRUNNINGORDERID());
            editor.putString("OrderNo", clsTable.getRunningOrderNo());
            editor.putString("Table_Number", clsTable.getTABLENAMENUMBER());
            editor.putInt("quantity_Total", clsTable.getTOTALQUANTITY());
            editor.putString("grand_Total", String.valueOf(clsTable.getTOTALAMOUNT()));
            editor.apply();

            Intent intent = new Intent(TablesActivity.this, OrderDetailActivity.class);
            startActivity(intent);

//            tvMenu.setOnClickListener(v -> {
//                Intent intent = new Intent(TablesActivity.this, MenuActivity.class);
//                startActivity(intent);
//            });

//            tvOrderDetail.setOnClickListener(v -> {
//                Intent intent1 = new Intent(TablesActivity.this, OrderDetailActivity.class);
//                startActivity(intent1);
//            });
        });
    }

    private void addNotification() {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_alarm_black_24dp) //set icon for notification
                        .setContentTitle("Order Ready") //set title of notification
                        .setContentText("Table : 01, Dept : AC-Hall, pulao is ready.")//this is notification message
                        .setVibrate(new long[]{500, 1500})
                        .setAutoCancel(true) // makes auto cancel of notification
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT); //set priority of notification


//        Intent notificationIntent = new Intent(this, NotificationView.class);
//        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        //notification message will get at NotificationView
//        notificationIntent.putExtra("message", "This is a notification message");

//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent,
//                PendingIntent.FLAG_UPDATE_CURRENT);
//        builder.setContentIntent(pendingIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Tables");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save_print, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            case R.id.bill_save_print:
                addNotification();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadAdapter();
    }
}