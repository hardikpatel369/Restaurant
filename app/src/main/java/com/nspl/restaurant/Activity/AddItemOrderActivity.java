package com.nspl.restaurant.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nspl.restaurant.Adapter.ItemAddOnsAdapter;
import com.nspl.restaurant.Adapter.ItemCommentsAdapter;
import com.nspl.restaurant.Adapter.ItemNutritionAdapter;
import com.nspl.restaurant.Adapter.ItemSizeAdapter;
import com.nspl.restaurant.Global.ClsGlobal;
import com.nspl.restaurant.R;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Menu.ClsAddon;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Menu.ClsComment;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Menu.ClsCustomCategory;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Menu.ClsNutrition;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Menu.ClsSize;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Order.ClsOrder;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Order.ClsOrderDetail;
import com.nspl.restaurant.ViewModel.ActivityViewModel.AddItemOrderActivityViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.nspl.restaurant.Adapter.ItemAddOnsAdapter.listAddons;
import static com.nspl.restaurant.Adapter.ItemCommentsAdapter.listComments;

public class AddItemOrderActivity extends AppCompatActivity implements ItemSizeAdapter.OnRadioButtonClickListener,
        ItemAddOnsAdapter.OnAddonsClickListener {

    ClsOrder addOrder = new ClsOrder();
    ClsCustomCategory clsCustomCategory = new ClsCustomCategory();
    AddItemOrderActivityViewModel addItemOrderActivityViewModel;
    List<ClsOrderDetail> addOrderDetail = new ArrayList<>();
    private TextView tvNoOfOrder;
    private TextView tvTotal;
    private ItemNutritionAdapter nutritionAdapter;
    private double cbAddonsValue = 0.0;
    private double cbSizeValue = 0.0;
    private double parcelCharge = 0.0;
    private int table_id;
    private String branchId;
    private String counterType;
    private String orderNo;
    private String sizeName;
    private String parcelOrNot = "SERVE";
    private int counterId;
    private int departmentId;
    private int orderId;
    private int sizeId;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item_order);

        Intent intent = getIntent();
        clsCustomCategory = (ClsCustomCategory) intent.getSerializableExtra("ClsCustomCategory");
        Gson gson = new Gson();
        String jsonInString = gson.toJson(clsCustomCategory);
        Log.e("clsCustomCategory", "onCreate---" + jsonInString);

        initToolbar();

        ItemSizeAdapter sizeAdapter = new ItemSizeAdapter(this, this);
        ItemCommentsAdapter commentsAdapter = new ItemCommentsAdapter(this);
        nutritionAdapter = new ItemNutritionAdapter(this);
        ItemAddOnsAdapter addOnsAdapter = new ItemAddOnsAdapter(this, this);

        tvTotal = findViewById(R.id.tvTotal);
        tvNoOfOrder = findViewById(R.id.tvNoOfOrder);
        TextView btnMinus = findViewById(R.id.btnMinus);
        TextView btnPlus = findViewById(R.id.btnPlus);
        Button btnAddOrder = findViewById(R.id.btnAddOrder);
        RecyclerView rvSize = findViewById(R.id.rvSize);
        RecyclerView rvAddOns = findViewById(R.id.rvAddOns);
        RecyclerView rvComments = findViewById(R.id.rvComments);
        TextView empty_view1 = findViewById(R.id.empty_view1);
        TextView empty_view2 = findViewById(R.id.empty_view2);
        TextView empty_view3 = findViewById(R.id.empty_view3);
        View view1 = findViewById(R.id.view1);
        View view2 = findViewById(R.id.view2);
        View view3 = findViewById(R.id.view3);
        CheckBox cbParcel = findViewById(R.id.cbParcel);

        SharedPreferences sp = getSharedPreferences("CounterData", MODE_PRIVATE);
        table_id = sp.getInt("TABLE_ID", 0);
        counterId = sp.getInt("CounterId", 0);
        departmentId = sp.getInt("departmentId", 0);
        branchId = sp.getString("BranchId", "");
        counterType = sp.getString("CounterType", "");
        orderId = sp.getInt("OrderId", 0);
        orderNo = sp.getString("OrderNo", "");

        parcelCharge = 0.0;
        cbSizeValue = 0.0;
        cbAddonsValue = 0.0;

        for (ClsAddon _ObjAdon : listAddons
        ) {
            _ObjAdon.setSelected(false);
            listAddons.set(listAddons.indexOf(_ObjAdon), _ObjAdon);
        }
        for (ClsComment _ObjComments : listComments) {
            _ObjComments.setSelected(false);
            listComments.set(listComments.indexOf(_ObjComments), _ObjComments);
        }

        tvTotal.setText("Total : " + cbSizeValue);

        if (clsCustomCategory.getpARCELPERQUANTITY()) {
            cbParcel.setText("Parcel ".concat(String.valueOf(clsCustomCategory.getpARCELCHARGE())).concat("(Q)"));
        } else {
            cbParcel.setText("Parcel ".concat(String.valueOf(clsCustomCategory.getpARCELCHARGE())));
        }

        cbParcel.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                parcelCharge = clsCustomCategory.getpARCELCHARGE();
                parcelOrNot = "PARCEL";
            } else {
                parcelCharge = 0.0;
            }
            Add();
        });

        btnPlus.setOnClickListener(v -> {
            String number = tvNoOfOrder.getText().toString();
            int num = Integer.parseInt(number);
            num++;
            tvNoOfOrder.setText(String.valueOf(num));
            Add();
        });

        btnMinus.setOnClickListener(v -> {
            String number = tvNoOfOrder.getText().toString();
            int num = Integer.parseInt(number);
            num--;
            if (num <= 1) {
                num = 1;
            }
            tvNoOfOrder.setText(String.valueOf(num));
            Add();
        });

        List<ClsSize> size = clsCustomCategory.getsIZES();
        if (size.isEmpty()) {
            rvSize.setVisibility(View.GONE);
            view1.setVisibility(View.VISIBLE);
            empty_view1.setVisibility(View.VISIBLE);
        } else {
            rvSize.setVisibility(View.VISIBLE);
            view1.setVisibility(View.GONE);
            empty_view1.setVisibility(View.GONE);
            sizeAdapter.addSize(size, "MenuAdapter");
            rvSize.setAdapter(sizeAdapter);
            rvSize.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        }

        List<ClsAddon> addons = clsCustomCategory.getaDDONS();
        if (addons.isEmpty()) {
            rvAddOns.setVisibility(View.GONE);
            view2.setVisibility(View.VISIBLE);
            empty_view2.setVisibility(View.VISIBLE);
        } else {
            rvAddOns.setVisibility(View.VISIBLE);
            view2.setVisibility(View.GONE);
            empty_view2.setVisibility(View.GONE);
            addOnsAdapter.addAddOns(addons);
            rvAddOns.setAdapter(addOnsAdapter);
            rvAddOns.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        }

        List<ClsComment> comments = clsCustomCategory.getcOMMENTS();
        if (comments.isEmpty()) {
            rvComments.setVisibility(View.GONE);
            view3.setVisibility(View.VISIBLE);
            empty_view3.setVisibility(View.VISIBLE);
        } else {
            rvComments.setVisibility(View.VISIBLE);
            view3.setVisibility(View.GONE);
            empty_view3.setVisibility(View.GONE);
            commentsAdapter.addComments(comments);
            rvComments.setAdapter(commentsAdapter);
            rvComments.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        }

        btnAddOrder.setOnClickListener(v -> {

                    if (cbSizeValue != 0.00) {
                        AddOrderDetail();
                        Intent intent1 = new Intent(this, MenuActivity.class);
                        startActivity(intent1);
                    } else {
                        Toast.makeText(this, "Please select the size.", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    private void AddOrderDetail() {

        SharedPreferences sharedPreferences = getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        String fullName = sharedPreferences.getString("FULL_NAME", "Not found");
        String employeeCode = sharedPreferences.getString("EMPLOYEE_CODE", "Not Found");

        int itemId = clsCustomCategory.getiTEMID();
        String itemName = clsCustomCategory.getnAME();
        boolean parclePerQuantity = clsCustomCategory.getpARCELPERQUANTITY();

        //Add item
        addOrder.setoRDERID(orderId);
        addOrder.setoRDERNO(orderNo);
        addOrder.setoRDERTYPE(counterType);
        addOrder.settABLEID(table_id);
        addOrder.setfULLNAME(fullName);
        addOrder.seteMPLOYEECODE(employeeCode);
        addOrder.setdEPARTMENTID(departmentId);
        addOrder.setbRANCHID(Integer.valueOf((branchId)));
        addOrder.setcOUNTERID(counterId);

        int quantity = Integer.parseInt(tvNoOfOrder.getText().toString());

        ClsOrderDetail _ObjItem = new ClsOrderDetail();

        //Add Comments
        String _comments = "";
        if (listComments != null && listComments.size() != 0) {
            List<String> _listComments = new ArrayList<>();
            for (ClsComment _Comment : listComments) {
                if (_Comment.getSelected()) {
                    _listComments.add(_Comment.getSORTNAME());
                }
            }
            _comments = TextUtils.join(",", _listComments);
        }

        _ObjItem.setoRDERID(orderId);
        _ObjItem.setoRDERNO(orderNo);
        _ObjItem.setsIZEID(sizeId);
        _ObjItem.setsIZE(sizeName);
        _ObjItem.setiTEMID(itemId);
        _ObjItem.setiTEMNAME(itemName);
        _ObjItem.setaDDONID(0);
        _ObjItem.setaDDON("");
        _ObjItem.setcOMMENTS(_comments);
        _ObjItem.setpRICE(cbSizeValue);
        _ObjItem.setqUANTITY(quantity);
        _ObjItem.setpARCELCHARGES(parcelCharge);
        _ObjItem.setpARCELPERQUANTITY(parclePerQuantity);
        _ObjItem.settOTALAMOUNT((cbSizeValue) * quantity);
        _ObjItem.setiSADDON(false);
        _ObjItem.setoRDERTYPE(parcelOrNot);
        _ObjItem.setrEMARK("");

        addOrderDetail.add(_ObjItem);

        //add Addons
        for (ClsAddon _ObjAddon : listAddons) {
            if (_ObjAddon.getSelected()) {

                _ObjItem = new ClsOrderDetail();
                _ObjItem.setoRDERID(orderId);
                _ObjItem.setoRDERNO(orderNo);
                _ObjItem.setsIZEID(sizeId);
                _ObjItem.setsIZE(sizeName);
                _ObjItem.setiTEMID(itemId);
                _ObjItem.setiTEMNAME(_ObjAddon.getnAME());
                _ObjItem.setaDDONID(_ObjAddon.getaDDONID());
                _ObjItem.setaDDON(_ObjAddon.getnAME());
                _ObjItem.setpRICE(_ObjAddon.getpRICE());
                _ObjItem.setqUANTITY(quantity);
                _ObjItem.setpARCELCHARGES(parcelCharge);
                _ObjItem.setpARCELPERQUANTITY(parclePerQuantity);
                _ObjItem.settOTALAMOUNT((quantity * (_ObjAddon.getpRICE())));
                _ObjItem.setiSADDON(true);
                _ObjItem.setoRDERTYPE(parcelOrNot);
                _ObjItem.setrEMARK("");

                addOrderDetail.add(_ObjItem);
            }
        }

        Gson gson = new Gson();
        String _itemListJson = gson.toJson(addOrderDetail);
        Log.e("--URL--", "AddOrderList---" + _itemListJson);

        addOrder.setItemList(_itemListJson);

        gson = new Gson();
        String jsonInString = gson.toJson(addOrder);
        Log.e("--URL--", "getobjClsUserInfo---" + jsonInString);

        addItemOrderActivityViewModel = ViewModelProviders.of(this).get(AddItemOrderActivityViewModel.class);

        addItemOrderActivityViewModel.PostItemOrder(addOrder).observe(this, clsOrderResponse -> {
            if (clsOrderResponse != null) {
                if (clsOrderResponse.getMESSAGE().equalsIgnoreCase("SUCCESS")) {
                    Toast.makeText(this, "SUCCESS.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, clsOrderResponse.getMESSAGE(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onRadioButtonClick(double cbSizeValue, int sizeId, String sizeName) {
        this.cbSizeValue = cbSizeValue;
        this.sizeId = sizeId;
        this.sizeName = sizeName;
        Log.i("grandTotal", "onRadioButtonClick: " + cbSizeValue);
        Add();
    }

    @Override
    public void onAddonsClick() {
        cbAddonsValue = 0.0;
        for (ClsAddon _objAddon : listAddons) {
            if (_objAddon.getSelected()) {
                cbAddonsValue += _objAddon.getpRICE();
            }
        }
        Log.i("grandTotal", "onAddonsClick: " + cbAddonsValue);
        Add();
    }

    @SuppressLint("SetTextI18n")
    private void Add() {
        Log.d("grandTotal", " Add ");
        double _price = cbSizeValue;
        double _quantity = Double.parseDouble(tvNoOfOrder.getText().toString());
        double _parcelCharges = parcelCharge;
        double _totalAddons = cbAddonsValue;
        double grandTotal;

        if (clsCustomCategory.getpARCELPERQUANTITY()) {
            grandTotal = (_price * _quantity) + (_totalAddons * _quantity) + (_parcelCharges * _quantity);
            Log.d("grandTotal", "(_price * _quantity): " + (_price * _quantity));
            Log.d("grandTotal", "(_totalAddons * _quantity): " + (_totalAddons * _quantity));
            Log.d("grandTotal", "(_parcelCharges * _quantity): " + (_parcelCharges * _quantity));
            Log.d("grandTotal", "Add(Q): " + grandTotal);
        } else {
            grandTotal = (_price * _quantity) + (_totalAddons * _quantity) + _parcelCharges;
            Log.d("grandTotal", "Add: " + grandTotal);
        }
        tvTotal.setText("Total : " + ClsGlobal.round(grandTotal, 2));
        tvTotal.setTag(grandTotal);
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(clsCustomCategory.getnAME());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.info, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
            public boolean onOptionsItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case android.R.id.home:

                        Intent intent = new Intent(AddItemOrderActivity.this, MenuActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                        break;

            case R.id.NutritionInfo:

                Dialog dialog = new Dialog(AddItemOrderActivity.this);
                dialog.setContentView(R.layout.dialog_nutrition_info);
                dialog.setCanceledOnTouchOutside(true);
                Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.setCancelable(true);
                dialog.show();
                WindowManager.LayoutParams params = new WindowManager.LayoutParams();
                params.copyFrom(Objects.requireNonNull(dialog.getWindow()).getAttributes());
                params.width = ViewGroup.LayoutParams.MATCH_PARENT;
                dialog.getWindow().setAttributes(params);

                TextView tvNutritionTitle = dialog.findViewById(R.id.tvNutritionTitle);
                RecyclerView rvNutritionValues = dialog.findViewById(R.id.rvNutritionValues);

                String nutritionTitle = String.valueOf(clsCustomCategory.getnUTRITIONTITLE());

                if (nutritionTitle.equalsIgnoreCase("null")) {
                    tvNutritionTitle.setText("Don't have any Nutrition");
                } else {
                    tvNutritionTitle.setText((nutritionTitle));
                }

                List<ClsNutrition> nutrition = clsCustomCategory.getnUTRITIONS();
                nutritionAdapter.addNutrition(nutrition);
                rvNutritionValues.setAdapter(nutritionAdapter);
                rvNutritionValues.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}