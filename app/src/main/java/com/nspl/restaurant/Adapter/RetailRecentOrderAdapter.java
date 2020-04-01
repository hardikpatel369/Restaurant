package com.nspl.restaurant.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.nspl.restaurant.R;
import com.nspl.restaurant.RetrofitApi.ApiClasses.RetailRecentOrder.ClsRetailRecentOrder;
import com.nspl.restaurant.databinding.ItemRetailRecentOrderBinding;

import java.util.ArrayList;
import java.util.List;

public class RetailRecentOrderAdapter extends RecyclerView.Adapter<RetailRecentOrderAdapter.ViewHolder> {

    private List<ClsRetailRecentOrder> list = new ArrayList<>();
    private Context mContext;

    public RetailRecentOrderAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void addOrder(List<ClsRetailRecentOrder> list) {
        this.list = list;
        notifyDataSetChanged();
    }
//////////////////////////////////
    public interface RetailRecentOrderOnClickListener {
        void OnClick(ClsRetailRecentOrder clsRetailRecentOrder, int position);
    }
    private RetailRecentOrderOnClickListener clickListener;

    public void SetOnItemListClickListener(RetailRecentOrderOnClickListener clickListener) {
        this.clickListener = clickListener;
    }
//////////////////////////////////
    public interface OrderDetailOnClickListener {
        void OnClick(ClsRetailRecentOrder clsRetailRecentOrder, int position);
    }
    private OrderDetailOnClickListener orderOnClickListener;

    public void SetOnOrderDetailClickListener(OrderDetailOnClickListener orderOnClickListener) {
        this.orderOnClickListener = orderOnClickListener;
    }
//////////////////////////////////
    public interface CustomerInfoOnClickListener {
        void OnClick(ClsRetailRecentOrder clsRetailRecentOrder, int position);
    }
    private CustomerInfoOnClickListener infoOnClickListener;

    public void SetOnCustomerInfoClickListener(CustomerInfoOnClickListener infoOnClickListener) {
        this.infoOnClickListener = infoOnClickListener;
    }

    @NonNull
    @Override
    public RetailRecentOrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        ItemRetailRecentOrderBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(context), R.layout.item_retail_recent_order, parent, false);
        return new ViewHolder(binding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RetailRecentOrderAdapter.ViewHolder holder, int position) {
        ClsRetailRecentOrder current = list.get(position);

        holder.binding.tvOrderId.setText(current.getORDERNO());
        holder.binding.tvOrderPrice.setText(current.getAMOUNT()+" ("+current.getITEM()+")");
        holder.binding.tvName.setText(current.getCUSTOMER());
        holder.binding.tvMobileNumber.setText(current.getMOBILENO());
        holder.binding.tvOrderDetail.setText("<< Order Detail >>");

        if (current.getMOBILENO() == null ||
                current.getMOBILENO().equalsIgnoreCase("")){
            holder.binding.ivCustomerInfo.setImageResource(R.drawable.ic_person_grey);
        }

        holder.binding.ivDelete.setOnClickListener(view -> {
            Toast.makeText(mContext, "this API is pending...", Toast.LENGTH_SHORT).show();
        });

        holder.BindClick(current, clickListener, position);
        holder.BindClickOrderDetail(current, orderOnClickListener, position);
        holder.BindClickCustomerInfo(current, infoOnClickListener, position);
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemRetailRecentOrderBinding binding;

        public ViewHolder(@NonNull ItemRetailRecentOrderBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void BindClick(ClsRetailRecentOrder clsRetailRecentOrder,RetailRecentOrderOnClickListener clickListener, int position) {
            binding.CardView.setOnClickListener(v -> clickListener.OnClick(clsRetailRecentOrder, position));
        }

        void BindClickOrderDetail(ClsRetailRecentOrder clsRetailRecentOrder,OrderDetailOnClickListener orderOnClickListener, int position) {
            binding.tvOrderDetail.setOnClickListener(v -> orderOnClickListener.OnClick(clsRetailRecentOrder, position));
        }

        void BindClickCustomerInfo(ClsRetailRecentOrder clsRetailRecentOrder,CustomerInfoOnClickListener infoOnClickListener, int position) {
            binding.ivCustomerInfo.setOnClickListener(v -> infoOnClickListener.OnClick(clsRetailRecentOrder, position));
        }
    }
}
