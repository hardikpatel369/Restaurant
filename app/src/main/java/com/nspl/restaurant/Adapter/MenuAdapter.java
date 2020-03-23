package com.nspl.restaurant.Adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;

import androidx.databinding.DataBindingUtil;

import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nspl.restaurant.Global.ApiClient;
import com.nspl.restaurant.Global.ClsGlobal;
import com.nspl.restaurant.R;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Menu.ClsAddon;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Menu.ClsCategorys;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.nspl.restaurant.RetrofitApi.ApiClasses.Menu.ClsComment;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Menu.ClsItem;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Menu.ClsNutrition;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Menu.ClsSize;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Order.ClsOrder;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Order.ClsOrderDetail;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Order.ClsOrderResponse;
import com.nspl.restaurant.RetrofitApi.Interface.InterfaceOrder;
import com.nspl.restaurant.databinding.DialogItemOrderBinding;
import com.nspl.restaurant.databinding.MenuCategoriesBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.nspl.restaurant.Adapter.ItemAddOnsAdapter.listAddons;
import static com.nspl.restaurant.Adapter.ItemCommentsAdapter.listComments;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder>
        implements ItemSizeAdapter.OnRadioButtonClickListener,
        ItemAddOnsAdapter.OnAddonsClickListener {

    private ClsItem items;
    private List<ClsCategorys> list = new ArrayList<>();
    private List<ClsCategorys> listBackup = new ArrayList<>();
    private List<ClsCategorys> listFilter = new ArrayList<>();
    private List<ClsSize> size = new ArrayList<>();
    private List<ClsAddon> addons = new ArrayList<>();
    private List<ClsComment> comments = new ArrayList<>();
    private List<ClsNutrition> nutrition = new ArrayList<>();
    private Context mContext;
    private Dialog mDialog, dialog;
    private MenuOnClickListener mMenuOnClickListener;
    private MenuItemsAdapter menuItemsAdapter;
    private TextView tvItemName, tvNoOfOrder, tvTotal, empty_view1, empty_view2, empty_view3;
    private View view1, view2, view3;
    private TextView tvNutritionTitle,btnMinus,btnPlus;
    private RecyclerView rvNutritionValues;
    private RecyclerView rvSize, rvAddOns, rvComments;
    private Button btnAddOrder;
    private CheckBox cbParcel;
    private ImageView ivNutritionInfo;
    private ItemSizeAdapter sizeAdapter;
    private ItemAddOnsAdapter addOnsAdapter;
    private ItemCommentsAdapter commentsAdapter;
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

    public MenuAdapter(Context context, int table_id, int counterId, int departmentId,
                       String branchId, String counterType, int orderId, String orderNo) {
        this.mContext = context;
        this.table_id = table_id;
        this.counterId = counterId;
        this.departmentId = departmentId;
        this.branchId = branchId;
        this.counterType = counterType;
        this.orderId = orderId;
        this.orderNo = orderNo;
    }

    public void addItems(List<ClsCategorys> _categoryList) {
        this.list = _categoryList;
        notifyDataSetChanged();
    }

    public void SetOnMenuClickListener(MenuOnClickListener menuOnClickListener) {
        this.mMenuOnClickListener = menuOnClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        MenuCategoriesBinding binding = DataBindingUtil.inflate(
                layoutInflater, R.layout.menu_categories, viewGroup, false);

        DialogItemOrderBinding orderBinding = DataBindingUtil.inflate(
                layoutInflater, R.layout.dialog_item_order, viewGroup, false);

        sizeAdapter = new ItemSizeAdapter(mContext, this);
        commentsAdapter = new ItemCommentsAdapter(mContext);
        nutritionAdapter = new ItemNutritionAdapter(mContext);
        menuItemsAdapter = new MenuItemsAdapter(mContext);
        addOnsAdapter = new ItemAddOnsAdapter(mContext,this);
        return new ViewHolder(binding, orderBinding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ClsCategorys current = list.get(i);

        viewHolder.binding.CategoryName.setText(current.getcATEGORYNAME());
        List<ClsItem> listItems = current.getiTEMS();

        menuItemsAdapter.addItems(listItems);
        viewHolder.binding.RvItems.setAdapter(menuItemsAdapter);
        viewHolder.binding.RvItems.setLayoutManager(new LinearLayoutManager(mContext));

        menuItemsAdapter.SetOnItemListClickListener((_objItem, position) -> {
            this.items = _objItem;

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


            if (mDialog != null && mDialog.isShowing()) return;
            mDialog = new Dialog(mContext);
            mDialog.setContentView(R.layout.dialog_item_order);
            mDialog.setCanceledOnTouchOutside(true);
            Objects.requireNonNull(mDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            mDialog.setCancelable(true);
            mDialog.show();
            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            lp.copyFrom(Objects.requireNonNull(mDialog.getWindow()).getAttributes());
            lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
            mDialog.getWindow().setAttributes(lp);

            tvItemName = mDialog.findViewById(R.id.tvItemName);
            tvTotal = mDialog.findViewById(R.id.tvTotal);
            tvNoOfOrder = mDialog.findViewById(R.id.tvNoOfOrder);
            btnMinus = mDialog.findViewById(R.id.btnMinus);
            btnPlus = mDialog.findViewById(R.id.btnPlus);
            btnMinus = mDialog.findViewById(R.id.btnMinus);
            btnAddOrder = mDialog.findViewById(R.id.btnAddOrder);
            rvSize = mDialog.findViewById(R.id.rvSize);
            rvAddOns = mDialog.findViewById(R.id.rvAddOns);
            rvComments = mDialog.findViewById(R.id.rvComments);
            empty_view1 = mDialog.findViewById(R.id.empty_view1);
            empty_view2 = mDialog.findViewById(R.id.empty_view2);
            empty_view3 = mDialog.findViewById(R.id.empty_view3);
            view1 = mDialog.findViewById(R.id.view1);
            view2 = mDialog.findViewById(R.id.view2);
            view3 = mDialog.findViewById(R.id.view3);
            cbParcel = mDialog.findViewById(R.id.cbParcel);
            ivNutritionInfo = mDialog.findViewById(R.id.ivNutritionInfo);

            tvTotal.setText("Total : "+cbSizeValue);

            if (items.getpARCELPERQUANTITY()) {
                cbParcel.setText("Parcel ".concat(String.valueOf(items.getpARCELCHARGE())).concat("(Q)"));
            } else {
                cbParcel.setText("Parcel ".concat(String.valueOf(items.getpARCELCHARGE())));
            }

            cbParcel.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    parcelCharge = _objItem.getpARCELCHARGE();
                    parcelOrNot = "PARCEL";
                } else {
                    parcelCharge = 0.0;
                }
                Add();
            });

            tvItemName.setText(_objItem.getnAME());

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

            size = _objItem.getsIZES();
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
                rvSize.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
            }

            addons = _objItem.getaDDONS();
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
                rvAddOns.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
            }

            comments = _objItem.getcOMMENTS();
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
                rvComments.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
            }

            ivNutritionInfo.setOnClickListener(v -> {

                if (dialog != null && dialog.isShowing()) return;
                dialog = new Dialog(mContext);
                dialog.setContentView(R.layout.dialog_nutrition_info);
                dialog.setCanceledOnTouchOutside(true);
                Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.setCancelable(true);
                dialog.show();
                WindowManager.LayoutParams params = new WindowManager.LayoutParams();
                params.copyFrom(Objects.requireNonNull(dialog.getWindow()).getAttributes());
                params.width = ViewGroup.LayoutParams.MATCH_PARENT;
                dialog.getWindow().setAttributes(params);

                tvNutritionTitle = dialog.findViewById(R.id.tvNutritionTitle);
                rvNutritionValues = dialog.findViewById(R.id.rvNutritionValues);

                String nutritionTitle = String.valueOf(_objItem.getnUTRITIONTITLE());

                if (nutritionTitle.equalsIgnoreCase("null")) {
                    tvNutritionTitle.setText("Don't have any Nutrition");
                } else {
                    tvNutritionTitle.setText((nutritionTitle));
                }

                nutrition = _objItem.getnUTRITIONS();
                nutritionAdapter.addNutrition(nutrition);
                rvNutritionValues.setAdapter(nutritionAdapter);
                rvNutritionValues.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
            });

            btnAddOrder.setOnClickListener(v -> {
                        AddOrderDetail();
                        mDialog.dismiss();
                    }
            );
        });

        viewHolder.BindClick(current, mMenuOnClickListener, i);
    }

    private void AddOrderDetail() {

        ClsOrder addOrder = new ClsOrder();
        List<ClsOrderDetail> addOrderDetail = new ArrayList<>();

        SharedPreferences sharedPreferences = mContext
                .getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        String fullName = sharedPreferences.getString("FULL_NAME", "Not found");
        String employeeCode = sharedPreferences.getString("EMPLOYEE_CODE", "Not Found");

        int itemId = items.getiTEMID();
        String itemName = items.getnAME();
        boolean parclePerQuantity = items.getpARCELPERQUANTITY();

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


        InterfaceOrder interfaceOrder = ApiClient.getRetrofitInstance().create(InterfaceOrder.class);
        Call<ClsOrderResponse> call = interfaceOrder.addOrder(addOrder);

        call.enqueue(new Callback<ClsOrderResponse>() {
            @Override
            public void onResponse(Call<ClsOrderResponse> call, Response<ClsOrderResponse> response) {
                Log.d("--AddOrder--", "onResponseBody: " + response.body());
                Log.d("--AddOrder--", "onResponseCode: " + response.code());

                Gson gson = new Gson();
                String jsonInString = gson.toJson(response.body());
                Log.d("--URL--", "onResponse-------: " + jsonInString);

                Toast.makeText(mContext, "Add order successfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ClsOrderResponse> call, Throwable t) {
                Log.d("--URL--", "onFailure: "+t.toString());
                Toast.makeText(mContext, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onRadioButtonClick(double cbSizeValue, int sizeId, String sizeName) {
        this.cbSizeValue = cbSizeValue;
        this.sizeId = sizeId;
        this.sizeName = sizeName;
        Log.i("grandTotal", "onRadioButtonClick: " + cbSizeValue);
        Add();
    }

    @SuppressLint("SetTextI18n")
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

//    @Override
//    public void OnClick(ClsCategorys clsCategorys, int position) {
//        String cName = clsCategorys.getcATEGORYNAME();
//
//        listFilter.clear();
//
//        if (cName.equalsIgnoreCase("all")) {
//            //remove filters
//            this.list = this.listBackup;
//            //notifyDataSetChanged();
//        } else {
//            for (ClsCategorys _objCategory : this.list) {
//                if (_objCategory.getcATEGORYNAME().equalsIgnoreCase(cName)) {
//                    listFilter.add(_objCategory);
//                    break;
//                }
//            }
//            this.list = this.listFilter;
//            // notifyDataSetChanged();
//        }
//
//        Log.e(TAG, "filterSIZE: " + this.listFilter.size());
//        //fill adapter
//    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final MenuCategoriesBinding binding;
        private final DialogItemOrderBinding orderBinding;

        ViewHolder(MenuCategoriesBinding binding, DialogItemOrderBinding orderBinding) {
            super(binding.getRoot());
            this.binding = binding;
            this.orderBinding = orderBinding;
        }

        void BindClick(ClsCategorys clsCategorys, MenuOnClickListener menuOnClickListener, int position) {
            binding.LinearLayout.setOnClickListener(v -> menuOnClickListener.OnClick(clsCategorys, position));
        }
    }

    public interface MenuOnClickListener {
        void OnClick(ClsCategorys clsTable, int position);
    }

    @SuppressLint("SetTextI18n")
    private void Add() {
        Log.d("grandTotal", " Add ");
        double _price = cbSizeValue;
        double _quantity = Double.parseDouble(tvNoOfOrder.getText().toString());
        double _parcelCharges = parcelCharge;
        double _totalAddons = cbAddonsValue;
        double grandTotal;

        if (items.getpARCELPERQUANTITY()) {
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
}