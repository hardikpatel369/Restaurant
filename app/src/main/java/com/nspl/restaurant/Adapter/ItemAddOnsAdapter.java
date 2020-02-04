package com.nspl.restaurant.Adapter;

import android.content.Context;
import androidx.databinding.DataBindingUtil;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.nspl.restaurant.R;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Menu.ClsAddon;
import com.nspl.restaurant.databinding.ItemAddOnsBinding;

import java.util.ArrayList;
import java.util.List;

public class ItemAddOnsAdapter extends RecyclerView.Adapter<ItemAddOnsAdapter.ViewHolder> {

    private List<ClsAddon> list = new ArrayList<>();
    private Context mContext;

    ItemAddOnsAdapter(Context context) {
        this.mContext = context;
    }

    void addAddOns(List<ClsAddon> list) {
        this.list = list;
    }


    @NonNull
    @Override
    public ItemAddOnsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        ItemAddOnsBinding binding = DataBindingUtil.inflate(
                layoutInflater, R.layout.item_add_ons, viewGroup, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAddOnsAdapter.ViewHolder viewHolder, int i) {
        ClsAddon current = list.get(i);
        viewHolder.binding.cbAddOns.setText(current.getnAME() + " : "+current.getpRICE());
    }

    @Override
    public int getItemCount() {
        if (list != null)
            return list.size();
        else
            return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemAddOnsBinding binding;

        ViewHolder(@NonNull ItemAddOnsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
