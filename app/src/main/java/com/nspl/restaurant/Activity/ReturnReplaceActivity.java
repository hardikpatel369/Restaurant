package com.nspl.restaurant.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nspl.restaurant.Adapter.ItemOrderDetailAdapter;
import com.nspl.restaurant.R;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Order.ClsOrderSummary;
import com.nspl.restaurant.ViewModel.ActivityViewModel.ReturnReplaceActivityViewModel;
import com.nspl.restaurant.ViewModel.ActivityViewModel.TablesActivityViewModel;

import java.util.ArrayList;
import java.util.List;

import java8.util.stream.Collectors;
import java8.util.stream.StreamSupport;

public class ReturnReplaceActivity extends AppCompatActivity
        implements ItemOrderDetailAdapter.CbOrderDetailClickListener {

    private ItemOrderDetailAdapter adapter;
    private int orderId;
    private int order_detail_id;
    private RecyclerView rv_return_replace_order;
    private boolean status = false;
    private List<ClsOrderSummary> summaryList = new ArrayList<>();
    private List<ClsOrderSummary> _listItemAddons = new ArrayList<>();
    private List<ClsOrderSummary> listItems = new ArrayList<>();
    ProgressBar pb;
    SwipeRefreshLayout swipeToRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return_replace);

        SharedPreferences sp = getSharedPreferences("CounterData", MODE_PRIVATE);
        orderId = sp.getInt("OrderId", 0);

        rv_return_replace_order = findViewById(R.id.rv_return_replace_order);
        TextView btnBill = findViewById(R.id.btn_bill);
        pb = findViewById(R.id.pb);
        pb.setVisibility(View.VISIBLE);

        swipeToRefresh = findViewById(R.id.swipeToRefresh);

        initToolbar();

        adapter = new ItemOrderDetailAdapter(this, this);
        rv_return_replace_order.setLayoutManager(new LinearLayoutManager(this));
        loadAdapter();

        adapter.SetOnOrderDetailClickListener((clsOrderSummary, position) -> {
//            Toast.makeText(this, clsOrderSummary.getITEMNAME(), Toast.LENGTH_SHORT).show();
        });

        btnBill.setOnClickListener(v -> {
            ReturnReplace();
        });

        swipeToRefresh.setOnRefreshListener(() -> {
            swipeToRefresh.setRefreshing(true);
            loadAdapter();
        });

    }

    private void loadAdapter() {
        TablesActivityViewModel mTablesActivityViewModel = ViewModelProviders.of(this).get(TablesActivityViewModel.class);

        mTablesActivityViewModel.getOrderSummaryResponse(orderId).observe(this, clsOrderSummaryResponse -> {

            if (clsOrderSummaryResponse != null) {
                summaryList = clsOrderSummaryResponse.getDATA();

                if (summaryList.size() != 0) {
                    listItems = StreamSupport.stream(summaryList)
                            .filter(s -> s.getISADDON().equals(false))
                            .collect(Collectors.toList());

                    Gson gson = new Gson();
                    String jsonInString = gson.toJson(listItems);
                    Log.e("Addon", "Item---" + jsonInString);

                    for (ClsOrderSummary _ObjItem : listItems) {

                        _listItemAddons = StreamSupport.stream(summaryList)
                                .filter(s -> s.getISADDON().equals(true) && s.getREFRANCEITEMID().equals(_ObjItem.getREFRANCEITEMID()))
                                .collect(Collectors.toList());

                        _ObjItem.setListAddons(_listItemAddons);
                        listItems.set(listItems.indexOf(_ObjItem), _ObjItem);

                        Gson gson1 = new Gson();
                        String jsonInString1 = gson1.toJson(_listItemAddons);
                        Log.e("Addon", "Addon---" + jsonInString1);
                    }

                    //show only return/replace items
                    List<ClsOrderSummary> listItemsReturn = new ArrayList<>();
                    for (ClsOrderSummary _ObjItem : listItems) {
                        if (_ObjItem.getSTATUS().equalsIgnoreCase("return")
                                || _ObjItem.getSTATUS().equalsIgnoreCase("replace")
                        ) {
                            listItemsReturn.add(_ObjItem);
                        }
                    }
                    adapter.addOrderDetail(listItemsReturn, "ReturnReplaceActivity");
                    rv_return_replace_order.setAdapter(adapter);
                    pb.setVisibility(View.GONE);
                    swipeToRefresh.setRefreshing(false);
                }
            }
        });
    }

    private void ReturnReplace() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Replace Or Return Items");
        builder.setMessage("Do you want to add this items in bill?");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", (dialogInterface, i) -> {
            if (order_detail_id != 0) {
                status = true;
            }
            ReturnReplaceActivityViewModel activityViewModel = ViewModelProviders.of(this).get(ReturnReplaceActivityViewModel.class);
            activityViewModel.getReturnReplace(order_detail_id, status).observe(this, clsReturnReplace -> {

                String message = clsReturnReplace.getMESSAGE();
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            });
            Intent intent = new Intent(this, BillActivity.class);
            startActivity(intent);
        });
        builder.setNegativeButton("No", (dialogInterface, i) -> {
            Intent intent = new Intent(this, BillActivity.class);
            startActivity(intent);
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Return or Replace ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
        Intent intent = new Intent(this,OrderDetailActivity.class);
        startActivity(intent);
    }

    @Override
    public void OnClick(int order_detail_id) {
        this.order_detail_id = order_detail_id;
    }
}
