package com.nspl.restaurant.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.nspl.restaurant.R;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Kitchen.ClsKitchen;
import com.nspl.restaurant.databinding.ItemKitchenBinding;

import java.util.ArrayList;
import java.util.List;

public class KitchenAdapter extends RecyclerView.Adapter<KitchenAdapter.ViewHolder> {

    private Context mContext;
    private List<ClsKitchen> list = new ArrayList<>();

    public void AddItems(List<ClsKitchen> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public KitchenAdapter(Context context) {
        this.mContext = context;
    }

    public interface OnKitchenClickListener {
        void onClick(ClsKitchen clsKitchen, int position);
    }
    private OnKitchenClickListener clickListener;

    public void SetOnClickListener(OnKitchenClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public KitchenAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        ItemKitchenBinding binding = DataBindingUtil.inflate(
                layoutInflater, R.layout.item_kitchen, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull KitchenAdapter.ViewHolder holder, int position) {
        ClsKitchen current = list.get(position);

        holder.binding.tvKitchenName.setText(current.getKITCHENNAME());
        holder.binding.tvKitchenSection.setText(current.getSECTION());

        holder.BindClick(current, clickListener, position);
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemKitchenBinding binding;

        public ViewHolder(@NonNull ItemKitchenBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        void BindClick(ClsKitchen clsKitchen, OnKitchenClickListener clickListener, int position) {
            binding.CardView.setOnClickListener(v -> clickListener.onClick(clsKitchen, position));
        }
    }
}
