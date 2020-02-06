package com.nspl.restaurant.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.databinding.DataBindingUtil;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nspl.restaurant.R;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Menu.ClsSize;
import com.nspl.restaurant.databinding.ItemSizeBinding;

import java.util.ArrayList;
import java.util.List;

public class ItemSizeAdapter extends RecyclerView.Adapter<ItemSizeAdapter.ViewHolder> {

    private List<ClsSize> list = new ArrayList<>();
    private Context mContext;
    private String mode = "";
    private int lastSelectedPosition = -1;
    private double cbValue;

    interface OnRadioButtonClickListener {
        void onRadioButtonClick(double cbValue);
    }

    private OnRadioButtonClickListener onRadioButtonClickListener;

    ItemSizeAdapter(Context context, OnRadioButtonClickListener onRadioButtonClickListener) {
        this.mContext = context;
        this.onRadioButtonClickListener = onRadioButtonClickListener;
    }

    ItemSizeAdapter(Context context) {
        this.mContext = context;
    }

    void addSize(List<ClsSize> _itemListSize, String mode) {
        this.list = _itemListSize;
        this.mode = mode;
//        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ItemSizeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        ItemSizeBinding binding = DataBindingUtil.inflate(
                layoutInflater, R.layout.item_size, viewGroup, false);
        return new ViewHolder(binding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ItemSizeAdapter.ViewHolder viewHolder, int i) {

        ClsSize current = list.get(i);
        Log.d("check", "current " + current.getsIZE());
        viewHolder.binding.tvItemSize.setText(current.getsIZE() + " : " + current.getpRICE());
        viewHolder.binding.cbSize.setText(current.getsIZE());
        viewHolder.binding.tvAmount.setText("" + current.getpRICE());

        viewHolder.binding.cbSize.setChecked(lastSelectedPosition == i);
        viewHolder.binding.cbSize.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                lastSelectedPosition = i;
                cbValue = current.getpRICE();
                notifyDataSetChanged();
                onRadioButtonClickListener.onRadioButtonClick(cbValue);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list != null)
            return list.size();
        else
            return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemSizeBinding binding;

        ViewHolder(@NonNull ItemSizeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            if (mode.equalsIgnoreCase("MenuAdapter")) {
                binding.rl2.setVisibility(View.GONE);
            }
            if (mode.equalsIgnoreCase("MenuItemsAdapter")) {
                binding.rl1.setVisibility(View.GONE);
            }
        }
    }
}
