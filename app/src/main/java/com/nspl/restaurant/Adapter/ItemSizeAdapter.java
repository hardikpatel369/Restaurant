package com.nspl.restaurant.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.databinding.DataBindingUtil;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.nspl.restaurant.R;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Menu.ClsSize;
import com.nspl.restaurant.databinding.ItemSizeBinding;

import java.util.ArrayList;
import java.util.List;

public class ItemSizeAdapter extends RecyclerView.Adapter<ItemSizeAdapter.ViewHolder> {

    private List<ClsSize> list = new ArrayList<>();
    private Context mContext;

    public ItemSizeAdapter(Context context) {
        this.mContext = context;
    }

    void addSize(List<ClsSize> _itemListSize) {
        this.list = _itemListSize;
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

//        if (i == 0) {
//            viewHolder.binding.tvItemSize.setBackgroundColor(Color.parseColor("#2F8BAD"));
//        } else if (i == 1) {
//            viewHolder.binding.tvItemSize.setBackgroundColor(Color.parseColor("#7761AE"));
//        } else if (i == 2) {
//            viewHolder.binding.tvItemSize.setBackgroundColor(Color.parseColor("#C39434"));
//        } else if (i == 3) {
//            viewHolder.binding.tvItemSize.setBackgroundColor(Color.parseColor("#7761AE"));
//        } else if (i == 4) {
//            viewHolder.binding.tvItemSize.setBackgroundColor(Color.parseColor("#7761AE"));
//        } else {
//            viewHolder.binding.tvItemSize.setBackgroundColor(Color.parseColor("#BA3A47"));
//        }
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
        }
    }
}
