package com.nspl.restaurant.Adapter;

import android.annotation.SuppressLint;
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
    public static List<ClsAddon> listAddons = new ArrayList<>();

    private Context mContext;

    public interface OnAddonsClickListener {
        void onAddonsClick();
    }
    private OnAddonsClickListener onAddonsClickListener;

    public ItemAddOnsAdapter(Context context,OnAddonsClickListener onAddonsClickListener) {
        this.mContext = context;
        this.onAddonsClickListener = onAddonsClickListener;
        notifyDataSetChanged();
    }

    public void addAddOns(List<ClsAddon> list) {
        listAddons = this.list = list;
    }


    @NonNull
    @Override
    public ItemAddOnsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        ItemAddOnsBinding binding = DataBindingUtil.inflate(
                layoutInflater, R.layout.item_add_ons, viewGroup, false);
        return new ViewHolder(binding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ItemAddOnsAdapter.ViewHolder viewHolder, int i) {
        ClsAddon current = list.get(i);
        viewHolder.binding.cbAddOns.setText(current.getnAME());
        viewHolder.binding.tvAmount.setText("" + current.getpRICE());
        viewHolder.binding.cbAddOns.setOnCheckedChangeListener((buttonView, isChecked) -> {
            current.setSelected(isChecked);
            listAddons.set(i, current);
            onAddonsClickListener.onAddonsClick();
        });

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

    class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemAddOnsBinding binding;

        ViewHolder(@NonNull ItemAddOnsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
