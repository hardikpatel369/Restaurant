package com.nspl.restaurant.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nspl.restaurant.Adapter.RetailRecentOrderAdapter;
import com.nspl.restaurant.R;
import com.nspl.restaurant.RetrofitApi.ApiClasses.City.ClsCity;
import com.nspl.restaurant.RetrofitApi.ApiClasses.CustomerInfo.ClsRetailCustomer;
import com.nspl.restaurant.RetrofitApi.ApiClasses.MobileNo.ClsMobileNo;
import com.nspl.restaurant.RetrofitApi.ApiClasses.RetailRecentOrder.ClsRetailRecentOrder;
import com.nspl.restaurant.ViewModel.ActivityViewModel.RetailRecentOrderActivityViewModel;
import com.nspl.restaurant.ViewModel.FragmentViewModel.BillDetailFragmentViewModel;
import com.nspl.restaurant.databinding.ActivityRetailRecentOrdersBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RetailRecentOrdersActivity extends AppCompatActivity implements LifecycleOwner {

    ActivityRetailRecentOrdersBinding binding;
    RetailRecentOrderAdapter adapter;
    private ClsRetailCustomer clsRetailCustomer = new ClsRetailCustomer();
    private RetailRecentOrderActivityViewModel retailRecentOrderActivityViewModel;
    private BillDetailFragmentViewModel billDetailFragmentViewModel;
    int departmentId;
    int counterId;
    int orderId;
    String CounterType;
    String BranchId;
    String mobile;
    String employeeName;
    String employeeCode;
    String cName ;
    String cCompany;
    String cGSTIN;
    String cCity;
    String cMobileNo;
    private Dialog mDialog;
    List<ClsRetailRecentOrder> list = new ArrayList<>();
    private ClsMobileNo clsMobileNo;
    private List<ClsCity> cityArrayList = new ArrayList<>();
    private ArrayList<String> cityStr = new ArrayList<>();

    EditText tv_mobile_number;
    EditText tv_customer_name;
    EditText tv_company_name;
    EditText tv_gst_number;
    AutoCompleteTextView tv_city;
    TextView tv_cancel;
    TextView tv_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_retail_recent_orders);
        binding.pb.setVisibility(View.VISIBLE);

        SharedPreferences sp = getSharedPreferences("CounterData", MODE_PRIVATE);
        departmentId = sp.getInt("departmentId", 0);
        counterId = sp.getInt("CounterId", 0);
        CounterType = sp.getString("CounterType", "");
        BranchId = sp.getString("BranchId", "");
        orderId = sp.getInt("OrderId", 0);

        SharedPreferences sha = getSharedPreferences("LoginDetails", MODE_PRIVATE);
        employeeName = sha.getString("FIRST_NAME", "");
        employeeCode = sha.getString("EMPLOYEE_CODE", "");

        initToolbar();

//        retailRecentOrderActivityViewModel = ViewModelProviders.of(this).get(RetailRecentOrderActivityViewModel.class);
//        billDetailFragmentViewModel = ViewModelProviders.of(this).get(BillDetailFragmentViewModel.class);

        binding.rvRetailRecentOrder.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RetailRecentOrderAdapter(this);
        binding.rvRetailRecentOrder.setAdapter(adapter);

        binding.fabAdd.setColorFilter(Color.WHITE);
        binding.fabAdd.setOnClickListener(view -> {
            SharedPreferences sharedPreferences = getSharedPreferences("CounterData", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("OrderId", 0);
            editor.apply();

            Intent intent = new Intent(this, CategorysActivity.class);
            startActivity(intent);
        });

        adapter.SetOnItemListClickListener((clsRetailRecentOrder, position) -> {

            SharedPreferences sharedPreferences = getSharedPreferences("CounterData", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("OrderNo", clsRetailRecentOrder.getORDERNO());
            editor.putInt("OrderId", clsRetailRecentOrder.getORDERID());
            editor.apply();

            Intent intent = new Intent(this, CategorysActivity.class);
            startActivity(intent);
        });

        adapter.SetOnOrderDetailClickListener((clsRetailRecentOrder, position) -> {

            SharedPreferences sharedPreferences = getSharedPreferences("CounterData", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("OrderId", clsRetailRecentOrder.getORDERID());
            editor.putString("OrderNo", clsRetailRecentOrder.getORDERNO());
            editor.apply();

            Intent intent = new Intent(this, OrderDetailActivity.class);
            startActivity(intent);
        });

        adapter.SetOnCustomerInfoClickListener((clsRetailRecentOrder, position) -> {

            if (mDialog != null && mDialog.isShowing()) return;
            mDialog = new Dialog(this);
            mDialog.setContentView(R.layout.dialog_customer_info);
            mDialog.setCanceledOnTouchOutside(true);
            Objects.requireNonNull(mDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            mDialog.setCancelable(true);
            mDialog.show();
            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            lp.copyFrom(Objects.requireNonNull(mDialog.getWindow()).getAttributes());
            lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
            mDialog.getWindow().setAttributes(lp);

            tv_mobile_number =  mDialog.findViewById(R.id.tv_mobile_number);
            tv_customer_name =  mDialog.findViewById(R.id.tv_customer_name);
            tv_company_name =  mDialog.findViewById(R.id.tv_company_name);
            tv_gst_number =  mDialog.findViewById(R.id.tv_gst_number);
            tv_city = mDialog.findViewById(R.id.tv_city);
            tv_cancel = mDialog.findViewById(R.id.tv_cancel);
            tv_save = mDialog.findViewById(R.id.tv_save);

            city();
            mobileNo();

            tv_customer_name.setText(clsRetailRecentOrder.getCUSTOMER());
            tv_mobile_number.setText(clsRetailRecentOrder.getMOBILENO());

            tv_save.setOnClickListener(view -> {

                cName = tv_customer_name.getText().toString();
                cCompany = tv_company_name.getText().toString();
                cMobileNo = tv_mobile_number.getText().toString();
                cGSTIN = tv_gst_number.getText().toString();
                cCity = tv_city.getText().toString();

                Log.d("customerInfo", "onCreate: "+cName+" "+cCompany+" "+cGSTIN+" "+
                        cCity+" "+cMobileNo+" "+employeeName+" "+employeeCode+" "+BranchId+" "+orderId);
                postCustomer();
                mDialog.dismiss();
            });

            tv_cancel.setOnClickListener(view -> {
                mDialog.dismiss();
            });
        });

        loadAdapter();

        binding.swipeToRefresh.setOnRefreshListener(() -> {
            binding.swipeToRefresh.setRefreshing(true);
            loadAdapter();
        });
    }

    private void postCustomer() {

        clsRetailCustomer.setName(cName);
        clsRetailCustomer.setCompany(cCompany);
        clsRetailCustomer.setGSTIN(cGSTIN);
        clsRetailCustomer.setCity(cCity);
        clsRetailCustomer.setMobileNo(cMobileNo);
        clsRetailCustomer.setEmployeeName(employeeName);
        clsRetailCustomer.setEmployeeCode(employeeCode);
        clsRetailCustomer.setBranchID(BranchId);
        clsRetailCustomer.setOrderID(orderId);

        Gson gson = new Gson();
        String jsonInString = gson.toJson(clsRetailCustomer);
        Log.e("--URL--", "clsRetailCustomer---" + jsonInString);

        retailRecentOrderActivityViewModel = ViewModelProviders.of(this).get(RetailRecentOrderActivityViewModel.class);

        retailRecentOrderActivityViewModel.postRetailCustomer(clsRetailCustomer).observe(this, clsRetailCustomerResponse ->  {

            if (clsRetailCustomerResponse != null) {
                Toast.makeText(this, clsRetailCustomerResponse.getMESSAGE(), Toast.LENGTH_SHORT).show();
                loadAdapter();
            }else{
                Toast.makeText(this, "Something went wrong!!!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadAdapter() {
        retailRecentOrderActivityViewModel = ViewModelProviders.of(this).get(RetailRecentOrderActivityViewModel.class);

        retailRecentOrderActivityViewModel.getRetailRecentOrder(counterId, departmentId).observe(this, clsRetailRecentOrderResponse -> {

            if (clsRetailRecentOrderResponse != null) {
                list = clsRetailRecentOrderResponse.getDATA();

                if (list != null) {
                    adapter.addOrder(list);
                    binding.pb.setVisibility(View.GONE);
                    binding.swipeToRefresh.setRefreshing(false);
                }
            }
        });
    }

    private void city() {
        billDetailFragmentViewModel = ViewModelProviders.of(this).get(BillDetailFragmentViewModel.class);

        billDetailFragmentViewModel.getCity().observe(this, clsCityResponse -> {
            if (clsCityResponse != null) {
                cityArrayList = clsCityResponse.getDATA();

                if (cityArrayList.size() != 0) {

                    for (ClsCity clsCity : cityArrayList) {
                        cityStr.add(clsCity.getCITY());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>
                            (this, android.R.layout.simple_list_item_1, cityStr);
                    adapter.notifyDataSetChanged();

                    tv_city.setThreshold(1);
                    tv_city.setAdapter(adapter);
                    tv_city.setOnClickListener(v -> closeKeyBoard());
                }
            }
        });
    }

    private void mobileNo() {

        tv_mobile_number.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (tv_mobile_number.length() == 10) {
                    mobile = tv_mobile_number.getText().toString();
                    getName();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void getName() {
        billDetailFragmentViewModel = ViewModelProviders.of(this).get(BillDetailFragmentViewModel.class);

        billDetailFragmentViewModel.getCustomerName(mobile).observe(this, clsMobileNoResponse -> {

            clsMobileNo = new ClsMobileNo();
            if (clsMobileNoResponse != null) {
                clsMobileNo = clsMobileNoResponse.getDATA();

                if (clsMobileNo != null) {
                    String customerName = clsMobileNo.getName();
                    String company = clsMobileNo.getCompany();
                    String city = clsMobileNo.getCity();
                    String gstNo = clsMobileNo.getGSTIN();
                    tv_customer_name.setText(customerName);
                    tv_city.setText(city);
                    tv_company_name.setText(company);
                    tv_gst_number.setText(gstNo);
                } else {
                    Toast.makeText(RetailRecentOrdersActivity.this, "Mobile number not found.", Toast.LENGTH_SHORT).show();
                }
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

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(CounterType);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed()  {
        Intent intent = new Intent(this,HomeActivity.class);
        startActivity(intent);
    }
}
