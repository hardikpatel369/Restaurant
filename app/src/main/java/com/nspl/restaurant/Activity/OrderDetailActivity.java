package com.nspl.restaurant.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nspl.restaurant.Adapter.ItemOrderDetailAdapter;
import com.nspl.restaurant.R;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Order.ClsOrderSummary;
import com.nspl.restaurant.ViewModel.ActivityViewModel.TablesActivityViewModel;
import com.nspl.restaurant.databinding.ActivityOrderDetailBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import java8.util.stream.Collectors;
import java8.util.stream.StreamSupport;

public class OrderDetailActivity extends AppCompatActivity {

    ActivityOrderDetailBinding binding;
    TablesActivityViewModel mTablesActivityViewModel;
    private ItemOrderDetailAdapter adapter;
    Context context;
    int orderId, quantity;
    String table_Number;
    String grand_Total;
    List<ClsOrderSummary> summaryList = new ArrayList<>();
    List<ClsOrderSummary> listItems = new ArrayList<>();

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_order_detail);

        binding.pb.setVisibility(View.VISIBLE);
        SharedPreferences sp = getSharedPreferences("CounterData", MODE_PRIVATE);
        orderId = sp.getInt("OrderId", 0);
        quantity = sp.getInt("quantity_Total", 0);
        table_Number = sp.getString("Table_Number", "Not found");
        grand_Total = sp.getString("grand_Total", "Not found");
        initToolbar();

        adapter = new ItemOrderDetailAdapter(context);

        binding.rvOrderDetail.setLayoutManager(new LinearLayoutManager(context));
        binding.btnBill.setText("Bill : " + grand_Total + "(" + quantity + ")");

        Log.d("OrderDetailActivity", "order_Id: " + orderId);
        Log.d("OrderDetailActivity", "Table_No: " + table_Number);

        mTablesActivityViewModel = ViewModelProviders.of(this).get(TablesActivityViewModel.class);

        mTablesActivityViewModel.getOrderSummaryResponse(orderId).observe(this, clsOrderSummaryResponse -> {

            if (clsOrderSummaryResponse != null) {
                summaryList = clsOrderSummaryResponse.getDATA();

                if (summaryList.size() != 0) {

                    listItems = StreamSupport.stream(summaryList)
                            .filter(s -> s.getISADDON().equals(false))
                            .collect(Collectors.toList());

                    getSupportActionBar().setSubtitle("Table : " + table_Number + ", "
                            + "Items : " +listItems.size());


                    for (ClsOrderSummary _ObjItem : listItems) {
                        List<ClsOrderSummary> _listItemAddons = new ArrayList<>();
                        _listItemAddons = StreamSupport.stream(summaryList)
                                .filter(s -> s.getISADDON().equals(true) && s.getITEMID().equals(_ObjItem.getITEMID()))
                                .collect(Collectors.toList());

                        _ObjItem.setListAddons(_listItemAddons);
                        listItems.set(listItems.indexOf(_ObjItem), _ObjItem);
                    }

                    adapter.addOrderDetail(listItems);
                    binding.rvOrderDetail.setAdapter(adapter);
                }
                binding.pb.setVisibility(View.GONE);
            }
            binding.pb.setVisibility(View.GONE);
        });

        adapter.SetOnOrderDetailClickListener((clsOrderSummary, position) -> {

            Dialog mDialog = new Dialog(this);
            if (mDialog.isShowing()) return;
            mDialog = new Dialog(OrderDetailActivity.this);
            mDialog.setContentView(R.layout.dialog_item_action);
            mDialog.setCanceledOnTouchOutside(true);
            Objects.requireNonNull(mDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            mDialog.setCancelable(true);
            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            lp.copyFrom(Objects.requireNonNull(mDialog.getWindow()).getAttributes());
            lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
            mDialog.getWindow().setAttributes(lp);

            TextView tvItemTitle,tvPrintKot,tvComplete,tvReturn,tvReplace;
            LinearLayout ll2,ll3,ll4,ll5,ll6;

            tvItemTitle = mDialog.findViewById(R.id.tvItemTitle);
            tvPrintKot = mDialog.findViewById(R.id.tvPrintKot);
            tvComplete = mDialog.findViewById(R.id.tvComplete);
            tvReturn = mDialog.findViewById(R.id.tvReturn);
            tvReplace = mDialog.findViewById(R.id.tvReplace);
            
            ll2 = mDialog.findViewById(R.id.ll2);
            ll3 = mDialog.findViewById(R.id.ll3);
            ll4 = mDialog.findViewById(R.id.ll4);
            ll5 = mDialog.findViewById(R.id.ll5);
            ll6 = mDialog.findViewById(R.id.ll6);

            tvItemTitle.setText(clsOrderSummary.getITEMNAME());
            
            if (clsOrderSummary.getSTATUS().equalsIgnoreCase("PENDING")){
                ll2.setVisibility(View.GONE);
                ll3.setVisibility(View.GONE);
                ll4.setVisibility(View.GONE);
                ll5.setVisibility(View.GONE);
            } else if (clsOrderSummary.getSTATUS().equalsIgnoreCase("COMPLETE")){
                ll2.setVisibility(View.GONE);
                ll3.setVisibility(View.GONE);
                ll6.setVisibility(View.GONE);
            }else if (clsOrderSummary.getSTATUS().equalsIgnoreCase("RETURN")) {
                ll2.setVisibility(View.GONE);
                ll3.setVisibility(View.GONE);
                ll4.setVisibility(View.GONE);
                ll5.setVisibility(View.GONE);
                ll6.setVisibility(View.GONE);
            }else if (clsOrderSummary.getSTATUS().equalsIgnoreCase("REPLACE")) {
                ll2.setVisibility(View.GONE);
                ll3.setVisibility(View.GONE);
                ll4.setVisibility(View.GONE);
                ll5.setVisibility(View.GONE);
                ll6.setVisibility(View.GONE);
            }else{
                Toast.makeText(this, "OOPS, Something went wrong!!!!!", Toast.LENGTH_SHORT).show();
            }
            mDialog.show();

            tvPrintKot.setOnClickListener(v -> {
                Toast.makeText(OrderDetailActivity.this, "PrintKOT!!!!!!!!!", Toast.LENGTH_SHORT).show();
            });

            tvComplete.setOnClickListener(v -> {
                Toast.makeText(OrderDetailActivity.this, "Complete!!!!!!!!!", Toast.LENGTH_SHORT).show();
            });

            tvReturn.setOnClickListener(v -> {
                Toast.makeText(OrderDetailActivity.this, "Return!!!!!!!!!", Toast.LENGTH_SHORT).show();
            });

            tvReplace.setOnClickListener(v -> {
                Toast.makeText(OrderDetailActivity.this, "Replace!!!!!!!!!", Toast.LENGTH_SHORT).show();
            });

        });
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Order details");
        getSupportActionBar().setSubtitle("Table : " + table_Number + ", " + "Items : " +summaryList.size());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(OrderDetailActivity.this, TablesActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(OrderDetailActivity.this, TablesActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}
