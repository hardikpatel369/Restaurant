package com.nspl.restaurant.Activity;

import androidx.appcompat.app.AlertDialog;
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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.nspl.restaurant.Adapter.ItemOrderDetailAdapter;
import com.nspl.restaurant.Global.ClsGlobal;
import com.nspl.restaurant.R;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Order.ClsOrderSummary;
import com.nspl.restaurant.ViewModel.ActivityViewModel.OrderDetailActivityViewModel;
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
    OrderDetailActivityViewModel mOrderDetailActivityViewModel;
    private ItemOrderDetailAdapter adapter;
    Context context;
    int orderId, quantity,OrderDetailID;
    String table_Number;
    String grand_Total;
    String itemName;
    String Mode = "";
    List<ClsOrderSummary> summaryList = new ArrayList<>();
    List<ClsOrderSummary> listItems = new ArrayList<>();
    ClsOrderSummary clsOrderSummary;
    Dialog mDialog, rDialog;

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
//        grand_Total = sp.getString("grand_Total", "Not found");
        initToolbar();

        adapter = new ItemOrderDetailAdapter(context);

        binding.rvOrderDetail.setLayoutManager(new LinearLayoutManager(context));

        binding.btnConfirmOrder.setOnClickListener(v -> {
            confirmOrder();
            loadAdapter();
        });

        Log.d("OrderDetailActivity", "order_Id: " + orderId);
        Log.d("OrderDetailActivity", "Table_No: " + table_Number);

        loadAdapter();

        binding.swipeToRefresh.setOnRefreshListener(() -> {
            binding.swipeToRefresh.setRefreshing(true);
            loadAdapter();
        });

        adapter.SetOnOrderDetailClickListener((clsOrderSummary, position) -> {

            mDialog = new Dialog(this);
            if (mDialog.isShowing()) return;
            mDialog = new Dialog(OrderDetailActivity.this);
            mDialog.setContentView(R.layout.dialog_item_action);
            mDialog.setCanceledOnTouchOutside(true);
            //noinspection ConstantConditions
            mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            mDialog.setCancelable(true);
            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            lp.copyFrom(Objects.requireNonNull(mDialog.getWindow()).getAttributes());
            lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
            mDialog.getWindow().setAttributes(lp);

            TextView tvItemTitle, tvPrintKot, tvComplete, tvReturn, tvReplace,tvDelete;
            LinearLayout llPrintKOT, llComplete, llReturn, llReplace, llDelete, llNoAction;

            tvItemTitle = mDialog.findViewById(R.id.tvItemTitle);
            tvPrintKot = mDialog.findViewById(R.id.tvPrintKot);
            tvComplete = mDialog.findViewById(R.id.tvComplete);
            tvReturn = mDialog.findViewById(R.id.tvReturn);
            tvReplace = mDialog.findViewById(R.id.tvReplace);
            tvDelete = mDialog.findViewById(R.id.tvDelete);

            llPrintKOT = mDialog.findViewById(R.id.llPrintKOT);
            llComplete = mDialog.findViewById(R.id.llComplete);
            llReturn = mDialog.findViewById(R.id.llReturn);
            llReplace = mDialog.findViewById(R.id.llReplace);
            llDelete = mDialog.findViewById(R.id.llDelete);
            llNoAction = mDialog.findViewById(R.id.llNoAction);

            itemName = clsOrderSummary.getITEMNAME();
            OrderDetailID = clsOrderSummary.getORDERDETAILID();
            tvItemTitle.setText(itemName);

            if (clsOrderSummary.getPRINTKOT() != null) {
                llPrintKOT.setVisibility(View.VISIBLE);
            } else {
                llPrintKOT.setVisibility(View.GONE);
            }

            if (clsOrderSummary.getSTATUS().equalsIgnoreCase("PENDING")) {
                llDelete.setVisibility(View.VISIBLE);
            } else if (clsOrderSummary.getSTATUS().equalsIgnoreCase("COMPLETE")) {
                llPrintKOT.setVisibility(View.VISIBLE);
                llReplace.setVisibility(View.VISIBLE);
                llReturn.setVisibility(View.VISIBLE);
            } else if (clsOrderSummary.getSTATUS().equalsIgnoreCase("REPLACE")) {

            } else if (clsOrderSummary.getSTATUS().equalsIgnoreCase("RETURN")) {

            } else if (clsOrderSummary.getSTATUS().equalsIgnoreCase("CONFIRM PENDING")) {

            } else {
                Toast.makeText(this, "Something went wrong!!!!!", Toast.LENGTH_SHORT).show();
            }
            mDialog.show();

            tvReturn.setOnClickListener(v -> {
                mDialog.dismiss();
                alertDialogBox();
            });

            tvReplace.setOnClickListener(v -> alertDialogBox());

            tvDelete.setOnClickListener(v -> {
                Mode = "DELETE";
                orderPrintDelete();
            });

            tvPrintKot.setOnClickListener(v -> {
                Mode = "PRINT";
                orderPrintDelete();
            });

            tvComplete.setOnClickListener(v -> {
                Toast.makeText(OrderDetailActivity.this, "Complete!!!!!!!!!", Toast.LENGTH_SHORT).show();
            });
        });
    }

    private void alertDialogBox() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Replace");
        builder.setMessage("Are you sure you want to replace?");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", (dialogInterface, i) -> {
            Toast.makeText(this, "Yes", Toast.LENGTH_SHORT).show();
            ReturnAlertDialogBox();
        });
        builder.setNegativeButton("No", (dialogInterface, i) -> {
            Toast.makeText(this, "No", Toast.LENGTH_SHORT).show();
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void ReturnAlertDialogBox() {
        rDialog = new Dialog(this);
        if (rDialog.isShowing()) return;
        rDialog = new Dialog(OrderDetailActivity.this);
        rDialog.setContentView(R.layout.dialog_return);
        rDialog.setCanceledOnTouchOutside(true);
        rDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        rDialog.setCancelable(true);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(Objects.requireNonNull(rDialog.getWindow()).getAttributes());
        lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
        rDialog.getWindow().setAttributes(lp);
        rDialog.show();

        TextView tvItemName, tvReason;
        Button btnClose, btnReturn;
        RadioGroup radioGroup;

        tvItemName = rDialog.findViewById(R.id.tvItemName);
        tvReason = rDialog.findViewById(R.id.tvReason);
        btnClose = rDialog.findViewById(R.id.btnClose);
        btnReturn = rDialog.findViewById(R.id.btnReturn);
        radioGroup = rDialog.findViewById(R.id.rgWastage);

        tvItemName.setText(itemName);
        String reason = tvReason.getText().toString();

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {

                case R.id.rbWastageYes:
                    Toast.makeText(context, "Wastage", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.rbWastageNo:
                    Toast.makeText(context, "No wastage", Toast.LENGTH_SHORT).show();
                    break;
            }
        });

        btnClose.setOnClickListener(v -> {
            rDialog.dismiss();
        });
        btnReturn.setOnClickListener(v -> {
            rDialog.dismiss();
        });
    }

    private void confirmOrder() {
        mOrderDetailActivityViewModel = ViewModelProviders.of(this).get(OrderDetailActivityViewModel.class);

        mOrderDetailActivityViewModel.getConfirmOrderResponse(orderId).observe(this, clsConfirmOrderResponse -> {

            if (orderId == 0) {
                Toast.makeText(this, "Something went wrong in order Id!!!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Confirm order Successfully.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void orderPrintDelete(){
        mOrderDetailActivityViewModel = ViewModelProviders.of(this).get(OrderDetailActivityViewModel.class);

        mOrderDetailActivityViewModel.getOrderPrintDeleteResponse(OrderDetailID,orderId,Mode).observe(this,clsOrderPrintDeleteResponse -> {
            mDialog.dismiss();
            loadAdapter();
        });
    }

    @SuppressLint("SetTextI18n")
    private void loadAdapter() {
        mTablesActivityViewModel = ViewModelProviders.of(this).get(TablesActivityViewModel.class);

        mTablesActivityViewModel.getOrderSummaryResponse(orderId).observe(this, clsOrderSummaryResponse -> {

            if (clsOrderSummaryResponse != null) {
                summaryList = clsOrderSummaryResponse.getDATA();

                if (summaryList.size() != 0) {

                    listItems = StreamSupport.stream(summaryList)
                            .filter(s -> s.getISADDON().equals(false))
                            .collect(Collectors.toList());

                    Objects.requireNonNull(getSupportActionBar()).setSubtitle("Table : " + table_Number + ", "
                            + "Items : " + listItems.size());

                    for (ClsOrderSummary _ObjItem : listItems) {
                        List<ClsOrderSummary> _listItemAddons = new ArrayList<>();
                        _listItemAddons = StreamSupport.stream(summaryList)
                                .filter(s -> s.getISADDON().equals(true) && s.getITEMID().equals(_ObjItem.getITEMID()))
                                .collect(Collectors.toList());

                        _ObjItem.setListAddons(_listItemAddons);
                        listItems.set(listItems.indexOf(_ObjItem), _ObjItem);
                    }

                    clsOrderSummary = new ClsOrderSummary();
                    double total = 0.0;
                    double parcleCharge = 0.0;
                    double grandTotal;
                    for (int i = 0; i < summaryList.size(); i++) {
                        total += summaryList.get(i).getTOTALAMOUNT();

                        if (summaryList.get(i).getPARCELCHARGES() != null) {
                            parcleCharge += summaryList.get(i).getPARCELCHARGES();
                            Log.d("Total", "loadAdapter: " + summaryList.get(i).getPARCELCHARGES());
                        }
                        Log.d("Total", "loadAdapter: " + summaryList.get(i).getTOTALAMOUNT());
                    }
                    grandTotal = total + parcleCharge;
                    binding.btnBill.setText("Bill : " + ClsGlobal.round(grandTotal,2) );

                    adapter.addOrderDetail(listItems);
                    binding.rvOrderDetail.setAdapter(adapter);
                    binding.swipeToRefresh.setRefreshing(false);
                }
                binding.pb.setVisibility(View.GONE);
            }
            binding.pb.setVisibility(View.GONE);
        });
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Order details");
        getSupportActionBar().setSubtitle("Table : " + table_Number + ", " + "Items : " + summaryList.size());
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
