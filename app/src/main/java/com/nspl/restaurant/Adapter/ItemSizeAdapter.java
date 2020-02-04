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
    private String rbValue;

    interface OnRadioButtonClickListener {
        void onRadioButtonClick(String rbValue);
    }
    private OnRadioButtonClickListener onRadioButtonClickListener;

    public ItemSizeAdapter(Context context, OnRadioButtonClickListener onRadioButtonClickListener) {
        this.mContext = context;
        this.onRadioButtonClickListener = onRadioButtonClickListener;
    }

    public ItemSizeAdapter(Context context){
        this.mContext = context;
    }

    void addSize(List<ClsSize> _itemListSize,String mode) {
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
        viewHolder.binding.rbSize.setText(current.getsIZE() + " : " + current.getpRICE());
        viewHolder.binding.rbSize.setChecked(lastSelectedPosition == i);
        viewHolder.binding.rbSize.setOnClickListener(v -> {
           lastSelectedPosition = i;
           rbValue = String.valueOf(current.getpRICE());
           notifyDataSetChanged();

           onRadioButtonClickListener.onRadioButtonClick(rbValue);
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
            if (mode.equalsIgnoreCase("MenuAdapter")){
                binding.tvItemSize.setVisibility(View.GONE);
            }
            if(mode.equalsIgnoreCase("MenuItemsAdapter")){
                binding.rbSize.setVisibility(View.GONE);
            }
        }
    }
}
