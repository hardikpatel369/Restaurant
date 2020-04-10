package com.nspl.restaurant.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.nspl.restaurant.R;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Kitchen.ClsKitchenSection;
import com.nspl.restaurant.databinding.ItemAddonBinding;

import java.util.ArrayList;
import java.util.List;

public class KitchenAddonsAdapter extends RecyclerView.Adapter<KitchenAddonsAdapter.ViewHolder> {

    private List<ClsKitchenSection> list = new ArrayList<>();
    private Context mContext;

    public KitchenAddonsAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void addItems(List<ClsKitchenSection> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public KitchenAddonsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        ItemAddonBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(context), R.layout.item_addon, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull KitchenAddonsAdapter.ViewHolder holder, int position) {
        ClsKitchenSection current = list.get(position);
        holder.binding.tvAddons.setText(current.getADDON());
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
        private ItemAddonBinding binding;

        public ViewHolder(@NonNull ItemAddonBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
