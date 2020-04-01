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
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nspl.restaurant.Adapter.ItemOrderDetailAdapter;
import com.nspl.restaurant.R;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Order.ClsOrderSummary;
import com.nspl.restaurant.ViewModel.ActivityViewModel.OrderDetailActivityViewModel;
import com.nspl.restaurant.ViewModel.ActivityViewModel.TablesActivityViewModel;
import com.nspl.restaurant.databinding.ActivityOrderDetailBinding;

import java.util.ArrayList;
import java.util.HashSet;
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
    int orderId, quantity, OrderDetailID;
    String table_Number;
    String CounterType;
    String orderNo;
    String itemName;
    String Mode = "";
    String Status = "";
    String reason;
    boolean wastage;
    List<ClsOrderSummary> summaryList = new ArrayList<>();
    List<ClsOrderSummary> _listItemAddons = new ArrayList<>();
    List<ClsOrderSummary> listItems = new ArrayList<>();
    ClsOrderSummary clsOrderSummary;
    Dialog mDialog, rDialog;
    private String[] reasonArray;
    private List<String> reasonArrayList = new ArrayList<>();
    TextView tvItemName;
    Button btnClose, btnReturn;
    RadioGroup radioGroup;
    AutoCompleteTextView tvReason;
    HashSet<String> statusSet = new HashSet<>();

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
        CounterType = sp.getString("CounterType","");
        orderNo = sp.getString("OrderNo","");

        initToolbar();

        adapter = new ItemOrderDetailAdapter(context);

        binding.rvOrderDetail.setLayoutManager(new LinearLayoutManager(context));

        binding.btnConfirmOrder.setOnClickListener(v -> {
            confirmOrder();
        });

        Log.d("OrderDetailActivity", "order_Id: " + orderId);
        Log.d("OrderDetailActivity", "Table_No: " + table_Number);

        loadAdapter();

        binding.swipeToRefresh.setOnRefreshListener(() -> {
            binding.swipeToRefresh.setRefreshing(true);
            loadAdapter();
        });

        binding.fabAdd.setOnClickListener(v -> {
//            Old Menu
//            Intent intent = new Intent(OrderDetailActivity.this, MenuActivity.class);
//            startActivity(intent);

//            new menu
            Intent intent = new Intent(OrderDetailActivity.this, CategorysActivity.class);
            startActivity(intent);
        });

        binding.fabAdd.setColorFilter(Color.WHITE);

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

            TextView tvItemTitle, tvPrintKot, tvComplete, tvReturn, tvReplace, tvDelete;
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

            if (clsOrderSummary.getSTATUS().equalsIgnoreCase("PENDING")){
                llDelete.setVisibility(View.VISIBLE);
            } else if (clsOrderSummary.getSTATUS().equalsIgnoreCase("COMPLETE")) {
                llPrintKOT.setVisibility(View.GONE);
                llReplace.setVisibility(View.VISIBLE);
                llReturn.setVisibility(View.VISIBLE);
            } else if (clsOrderSummary.getSTATUS().equalsIgnoreCase("REPLACE")) {
                llPrintKOT.setVisibility(View.GONE);
                llNoAction.setVisibility(View.VISIBLE);
            } else if (clsOrderSummary.getSTATUS().equalsIgnoreCase("RETURN")) {
                llPrintKOT.setVisibility(View.GONE);
                llNoAction.setVisibility(View.VISIBLE);
            } else if (clsOrderSummary.getSTATUS().equalsIgnoreCase("CONFIRM PENDING")) {
                llDelete.setVisibility(View.VISIBLE);
            } else {
                Toast.makeText(this, "Something went wrong!!!!!", Toast.LENGTH_SHORT).show();
            }

            if (clsOrderSummary.getoRDERCANCELOPTION() == null){
                llDelete.setVisibility(View.GONE);
            }else if(clsOrderSummary.getoRDERCANCELOPTION()){
                llDelete.setVisibility(View.VISIBLE);
            }else{
                llDelete.setVisibility(View.GONE);
            }
            mDialog.show();

            tvReturn.setOnClickListener(v -> {
                mDialog.dismiss();
                Status = "RETURN";
                alertDialogBox("Return", "Are you sure you want to return?",
                        this::ReturnAlertDialogBox);
            });

            tvReplace.setOnClickListener(v -> {
                mDialog.dismiss();
                Status = "REPLACE";
                alertDialogBox("Replace", "Are you sure you want to replace?",
                        this::ReturnAlertDialogBox);
            });

            tvDelete.setOnClickListener(v -> {
                mDialog.dismiss();
                Mode = "DELETE";
                alertDialogBox("Delete", "Are you sure you want to delete?",
                        this::orderPrintDelete);
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

    private void alertDialogBox(String title, String message, AlertDialogClick alertDialogClick) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", (dialogInterface, i) -> {
            alertDialogClick.OnYes();
        });
        builder.setNegativeButton("No", (dialogInterface, i) -> {

        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    interface AlertDialogClick {
        void OnYes();
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

        tvItemName = rDialog.findViewById(R.id.tvItemName);
        tvReason = rDialog.findViewById(R.id.tvReason);
        btnClose = rDialog.findViewById(R.id.btnClose);
        btnReturn = rDialog.findViewById(R.id.btnReturn);
        radioGroup = rDialog.findViewById(R.id.rgWastage);
        getReason();

        tvItemName.setText(itemName);
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {

                case R.id.rbWastageYes:
                    wastage = true;
                    break;
                case R.id.rbWastageNo:
                    wastage = false;
                    break;
            }
        });

        btnClose.setOnClickListener(v -> rDialog.dismiss());
        btnReturn.setOnClickListener(v -> {
            reason = tvReason.getText().toString();

            if (tvReason.getText().toString().equalsIgnoreCase("")) {
                Toast.makeText(this, "Please Write reason and wastage", Toast.LENGTH_SHORT).show();
            } else {
                rDialog.dismiss();
                orderReturnReplace();
            }
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
            loadAdapter();
        });
    }

    private void orderPrintDelete() {
        mOrderDetailActivityViewModel = ViewModelProviders.of(this).get(OrderDetailActivityViewModel.class);

        mOrderDetailActivityViewModel.getOrderPrintDeleteResponse(OrderDetailID, orderId, Mode)
                .observe(this, clsOrderPrintDeleteResponse -> {
                    mDialog.dismiss();
                    loadAdapter();
                    Toast.makeText(this, "Done successfully.", Toast.LENGTH_SHORT).show();
                });
    }

    private void orderReturnReplace() {
        mOrderDetailActivityViewModel = ViewModelProviders.of(this).get(OrderDetailActivityViewModel.class);

        mOrderDetailActivityViewModel.postReturnReplace(reason, wastage, OrderDetailID, orderId, Status)
                .observe(this, clsReturnReplace -> {
                    mDialog.dismiss();
                    loadAdapter();
                });
    }

    private void getReason() {
        mOrderDetailActivityViewModel = ViewModelProviders.of(this).get(OrderDetailActivityViewModel.class);

        mOrderDetailActivityViewModel.getReasonList().observe(this, clsReasonList -> {
            if (clsReasonList != null) {
                reasonArrayList = clsReasonList.getDATA();

                if (reasonArrayList.size() != 0) {

                    reasonArray = new String[reasonArrayList.size()];
                    reasonArrayList.toArray(reasonArray);

                    Gson gson = new Gson();
                    String jsonInString = gson.toJson(reasonArray);
                    Log.e("reasonArray", "getobjClsUserInfo---" + jsonInString);

                    ArrayAdapter<String> adapter = new ArrayAdapter<>
                            (this, android.R.layout.simple_list_item_1, reasonArray);
                    adapter.notifyDataSetChanged();

                    tvReason.setThreshold(1);
                    tvReason.setAdapter(adapter);
                    tvReason.setOnClickListener(v -> closeKeyBoard());
                }

                Gson gson = new Gson();
                String jsonInString = gson.toJson(reasonArray);
                Log.e("reasonArray", "else---" + jsonInString);
            }

        });
    }

    private void closeKeyBoard() {
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager iMM = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            iMM.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
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

                    initToolbar();

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
//                    binding.btnBill.setText("Bill : " + ClsGlobal.round(grandTotal, 2));

                    adapter.addOrderDetail(listItems, "OrderDetailActivity");
                    binding.rvOrderDetail.setAdapter(adapter);
                    binding.pb.setVisibility(View.GONE);
                    binding.swipeToRefresh.setRefreshing(false);
                }
            }
        });
    }

    private void Bill() {
        for (ClsOrderSummary clsOrderSummary1 : summaryList) {
            statusSet.add(clsOrderSummary1.getSTATUS());
        }
        ArrayList<String> strArray = new ArrayList<>();
        strArray.addAll(statusSet);

        boolean returnReplace = false;
        for (String objStatus : strArray) {
            if (objStatus.equalsIgnoreCase("RETURN") ||
                    objStatus.equalsIgnoreCase("REPLACE")) {
                returnReplace = true;
                break;
            }
        }

        if (returnReplace) {
            Intent intent1 = new Intent(this, ReturnReplaceActivity.class);
            startActivity(intent1);
        } else {
            Intent intent1 = new Intent(this, BillActivity.class);
            startActivity(intent1);
        }

//        for (int i = 0; i < strArray.size(); i++) {
//            if (strArray.get(i).equalsIgnoreCase("RETURN")){
//                Intent intent1 = new Intent(this, ReturnReplaceActivity.class);
//                startActivity(intent1);
//                break;
//            }else if (strArray.get(i).equalsIgnoreCase("REPLACE")){
//                Intent intent1 = new Intent(this, ReturnReplaceActivity.class);
//                startActivity(intent1);
//                break;
//            } else {
//                Intent intent1 = new Intent(this, BillActivity.class);
//                startActivity(intent1);
//                break;
//            }
//        }
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Order details");
        if (CounterType.equalsIgnoreCase("RETAIL")){
            getSupportActionBar().setSubtitle("Order No : "+orderNo + ", " + "Items : " + listItems.size());
        }else if (CounterType.equalsIgnoreCase("RESTAURANT")){
            getSupportActionBar().setSubtitle("Table : " + table_Number + ", " + "Items : " + listItems.size());
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bill, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;

            case R.id.menu_bill:
                Bill();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (CounterType.equalsIgnoreCase("RETAIL")){
            Intent intent = new Intent(this,RetailRecentOrdersActivity.class);
            startActivity(intent);
        }else {
            Intent intent = new Intent(this,TablesActivity.class);
            startActivity(intent);
        }
    }
}
