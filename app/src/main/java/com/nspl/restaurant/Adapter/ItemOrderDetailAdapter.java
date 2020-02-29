package com.nspl.restaurant.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.nspl.restaurant.R;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Order.ClsOrderSummary;
import com.nspl.restaurant.databinding.ItemOrderDetailBinding;

import java.util.ArrayList;
import java.util.List;

public class ItemOrderDetailAdapter extends RecyclerView.Adapter<ItemOrderDetailAdapter.ViewHolder> {

    private List<ClsOrderSummary> list = new ArrayList<>();
    private Context mContext;
    private AddonsAdapter addonsAdapter;

    public ItemOrderDetailAdapter(Context mContext) {
        this.mContext = mContext;
        addonsAdapter = new AddonsAdapter(mContext);
    }

    public void addOrderDetail(List<ClsOrderSummary> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public interface OrderDetailClickListener {
        void OnClick(ClsOrderSummary clsOrderSummary, int position);
    }

    private OrderDetailClickListener orderDetailClickListener;

    public void SetOnOrderDetailClickListener(OrderDetailClickListener orderDetailClickListener) {
        this.orderDetailClickListener = orderDetailClickListener;
    }

    @NonNull
    @Override
    public ItemOrderDetailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        ItemOrderDetailBinding binding = DataBindingUtil.inflate(
                layoutInflater, R.layout.item_order_detail, parent, false);

        addonsAdapter = new AddonsAdapter(mContext);
        return new ViewHolder(binding);
    }

    @SuppressLint({"SetTextI18n", "ResourceAsColor"})
    @Override
    public void onBindViewHolder(@NonNull ItemOrderDetailAdapter.ViewHolder holder, int position) {

        ClsOrderSummary current = list.get(position);

        holder.binding.tvItemName.setText("" + current.getITEMNAME());
        holder.binding.tvItemQuantity.setText("" + current.getQUANTITY());
        holder.binding.tvItemSize.setText("" + current.getSIZE());
        holder.binding.tvItemPrize.setText("" + (current.getPRICE()));
        holder.binding.tvItemStatus.setText("" + (current.getSTATUS()));

        if (current.getListAddons() != null && current.getListAddons().size() != 0) {
            Gson gson = new Gson();
            String jsonInString = gson.toJson(current);
            Log.e("listItems", "listItems---" + jsonInString);

            addonsAdapter.addItems(current.getListAddons());
            holder.binding.tvAddOns.setVisibility(View.VISIBLE);
            holder.binding.view4.setVisibility(View.VISIBLE);
            holder.binding.rvAddOnsDetail.setAdapter(addonsAdapter);
            holder.binding.rvAddOnsDetail.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        }else {
            holder.binding.tvAddOns.setVisibility(View.GONE);
            holder.binding.view4.setVisibility(View.GONE);
        }

        if (current.getSTATUS().equalsIgnoreCase("PENDING")){
            holder.binding.tvItemStatus.setTextColor(Color.parseColor("#F6BB42"));
        }else if (current.getSTATUS().equalsIgnoreCase("COMPLETE")){
            holder.binding.tvItemStatus.setTextColor(Color.parseColor("#37BC9B"));
        }else if (current.getSTATUS().equalsIgnoreCase("REPLACE")){
            holder.binding.tvItemStatus.setTextColor(Color.parseColor("#3BAFDA"));
        }else if (current.getSTATUS().equalsIgnoreCase("RETURN")){
            holder.binding.tvItemStatus.setTextColor(Color.parseColor("#DA4453"));
        }else if (current.getSTATUS().equalsIgnoreCase("CONFIRM PENDING")){
            holder.binding.tvItemStatus.setTextColor(Color.parseColor("#967ADC"));
        }else{
            holder.binding.tvItemStatus.setTextColor(Color.parseColor("#000000"));
        }

        holder.BindClick(current, orderDetailClickListener, position);
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemOrderDetailBinding binding;

        public ViewHolder(@NonNull ItemOrderDetailBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void BindClick(ClsOrderSummary clsOrderSummary,
                       OrderDetailClickListener orderDetailClickListener, int position) {
            binding.CardView.setOnClickListener(v ->
                    orderDetailClickListener.OnClick(clsOrderSummary, position));
        }
    }
}
