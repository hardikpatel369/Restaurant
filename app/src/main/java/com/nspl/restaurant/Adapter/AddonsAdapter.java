package com.nspl.restaurant.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.nspl.restaurant.R;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Order.ClsOrderSummary;
import com.nspl.restaurant.databinding.ItemAddonsDetailBinding;

import java.util.ArrayList;
import java.util.List;

public class AddonsAdapter extends RecyclerView.Adapter<AddonsAdapter.ViewHolder> {

    private List<ClsOrderSummary> list =new ArrayList<>();
    private Context mContext;

    public AddonsAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void addItems(List<ClsOrderSummary> list) {
//        this.list.clear();
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        ItemAddonsDetailBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(context), R.layout.item_addons_detail, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ClsOrderSummary current = list.get(position);
        holder.binding.tvAddOnsName.setText(current.getITEMNAME());
        holder.binding.tvAddOnsPrice.setText(String.valueOf(current.getPRICE()));
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public int getItemViewType(int position)
    {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemAddonsDetailBinding binding;

        public ViewHolder(@NonNull ItemAddonsDetailBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
