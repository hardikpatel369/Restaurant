package com.nspl.restaurant.Adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import androidx.databinding.DataBindingUtil;
import android.graphics.drawable.ColorDrawable;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nspl.restaurant.R;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Menu.ClsAddon;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Menu.ClsCategorys;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.nspl.restaurant.RetrofitApi.ApiClasses.Menu.ClsComment;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Menu.ClsItem;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Menu.ClsSize;
import com.nspl.restaurant.databinding.DialogItemOrderBinding;
import com.nspl.restaurant.databinding.MenuCategoriesBinding;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> implements ItemSizeAdapter.OnRadioButtonClickListener {

    private List<ClsCategorys> list = new ArrayList<>();
    private List<ClsSize> size = new ArrayList<>();
    private List<ClsAddon> addons = new ArrayList<>();
    private List<ClsComment> comments = new ArrayList<>();
    private Context mContext;
    private MenuOnClickListener mMenuOnClickListener;
    private MenuItemsAdapter adpItems;
    private TextView tvItemName, tvNoOfOrder, tvTotal, empty_view1, empty_view2, empty_view3;
    private RecyclerView rvSize, rvAddOns, rvComments;
    private Button btnMinus, btnPlus, btnAddOrder;
    private ItemSizeAdapter sizeAdapter;
    private CheckBox cbParcel;
    private ItemAddOnsAdapter addOnsAdapter;
    private ItemCommentsAdapter commentsAdapter;
    private ArrayList<String> strList;
    private String parcelCharge;

    public MenuAdapter(Context context) {
        this.mContext = context;
        adpItems = new MenuItemsAdapter(this.mContext);

        List<ClsItem> items = new ArrayList<>();
        Log.d("items------", "MenuAdapter: "+ items.toString());
    }

    public void addItems(List<ClsCategorys> _categoryList) {
        this.list = _categoryList;
//        notifyDataSetChanged();
    }

    public void SetOnMenuClickListener(MenuOnClickListener menuOnClickListener) {
        this.mMenuOnClickListener = menuOnClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(mContext);

        MenuCategoriesBinding binding = DataBindingUtil.inflate(
                layoutInflater, R.layout.menu_categories, viewGroup, false);

        DialogItemOrderBinding orderBinding = DataBindingUtil.inflate(
                layoutInflater, R.layout.dialog_item_order, viewGroup, false);

        sizeAdapter = new ItemSizeAdapter(mContext,this);
        addOnsAdapter = new ItemAddOnsAdapter(mContext);
        commentsAdapter = new ItemCommentsAdapter(mContext);

        return new ViewHolder(binding, orderBinding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ClsCategorys current = list.get(i);
        viewHolder.binding.CategoryName.setText(current.getcATEGORYNAME());
        List<ClsItem> listItems = current.getiTEMS();

        adpItems.addItems(listItems);
        viewHolder.binding.RvItems.setAdapter(adpItems);
        viewHolder.binding.RvItems.setLayoutManager(new LinearLayoutManager(mContext));

//        adpItems.notifyDataSetChanged();

        Gson gson = new Gson();
        String jsonInString = gson.toJson(listItems);
        Log.e("--List--", "Item: " + jsonInString);

        adpItems.SetOnItemListClickListener((clsItem, position) -> {

            final Dialog mDialog = new Dialog(mContext);
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
            cbParcel = mDialog.findViewById(R.id.cbParcel);

            tvItemName.setText(clsItem.getnAME());

            btnPlus.setOnClickListener(v -> {
                String number = tvNoOfOrder.getText().toString();
                int num = Integer.parseInt(number);
                num++;
                tvNoOfOrder.setText(String.valueOf(num));
            });

            btnMinus.setOnClickListener(v -> {
               String number = tvNoOfOrder.getText().toString();
               int num = Integer.parseInt(number);
               num--;
               if(num<=1){ num=1; }
               tvNoOfOrder.setText(String.valueOf(num));
            });

            size = clsItem.getsIZES();
            if (size.isEmpty()) {
                rvSize.setVisibility(View.GONE);
                empty_view1.setVisibility(View.VISIBLE);
            } else {
                rvSize.setVisibility(View.VISIBLE);
                empty_view1.setVisibility(View.GONE);
                sizeAdapter.addSize(size, "MenuAdapter");
                rvSize.setAdapter(sizeAdapter);
                rvSize.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
            }

            addons = clsItem.getaDDONS();
            if (addons.isEmpty()) {
                rvAddOns.setVisibility(View.GONE);
                empty_view2.setVisibility(View.VISIBLE);
            } else {
                rvAddOns.setVisibility(View.VISIBLE);
                empty_view2.setVisibility(View.GONE);
                addOnsAdapter.addAddOns(addons);
                rvAddOns.setAdapter(addOnsAdapter);
                rvAddOns.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
            }

            comments = clsItem.getcOMMENTS();
            if (comments.isEmpty()) {
                rvComments.setVisibility(View.GONE);
                empty_view3.setVisibility(View.VISIBLE);
            } else {
                rvComments.setVisibility(View.VISIBLE);
                empty_view3.setVisibility(View.GONE);
                commentsAdapter.addComments(comments);
                rvComments.setAdapter(commentsAdapter);
                rvComments.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
            }
        });

        viewHolder.BindClick(current, mMenuOnClickListener, i);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onRadioButtonClick(String rbValue) {
        tvTotal.setText("Total : "+rbValue);
    }

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
}
