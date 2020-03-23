package com.nspl.restaurant.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.databinding.DataBindingUtil;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
    private int lastSelectedPosition = 0;
    private double cbValue;
    private int sizeId;
    private String sizeName;

    public interface OnRadioButtonClickListener {
        void onRadioButtonClick(double cbValue,int sizeId,String sizeName);
    }
    private OnRadioButtonClickListener onRadioButtonClickListener;

    public ItemSizeAdapter(Context context, OnRadioButtonClickListener _onRadioButtonClickListener) {
        this.mContext = context;
        this.onRadioButtonClickListener = _onRadioButtonClickListener;
    }

//    ItemSizeAdapter(Context context) {
//        this.mContext = context;
//    }

    public void addSize(List<ClsSize> _itemListSize, String mode) {
        this.list = _itemListSize;
        this.mode = mode;
        this.lastSelectedPosition = 0;
        notifyDataSetChanged();
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
//        viewHolder.binding.tvItemSize.setText(current.getsIZE() + " : " + current.getpRICE());
        viewHolder.binding.cbSize.setText(current.getsIZE());
        viewHolder.binding.tvAmount.setText("" + current.getpRICE());

        if (lastSelectedPosition == i) {
//            viewHolder.binding.cbSize.setChecked(true);
            cbValue = current.getpRICE();
        } else {
            viewHolder.binding.cbSize.setChecked(false);
        }

        viewHolder.binding.cbSize.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                lastSelectedPosition = i;
                cbValue = current.getpRICE();
                sizeId = current.getsIZEID();
                sizeName = current.getsIZE();
                notifyDataSetChanged();
                onRadioButtonClickListener.onRadioButtonClick(cbValue,sizeId,sizeName);
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

    class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemSizeBinding binding;

        ViewHolder(@NonNull ItemSizeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            if (mode.equalsIgnoreCase("MenuAdapter")) {
//                binding.rl2.setVisibility(View.GONE);
            }
            if (mode.equalsIgnoreCase("MenuItemsAdapter")) {
                binding.rl1.setVisibility(View.GONE);
            }
        }
    }
}
