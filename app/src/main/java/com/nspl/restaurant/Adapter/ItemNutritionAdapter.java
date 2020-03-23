package com.nspl.restaurant.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.nspl.restaurant.R;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Menu.ClsNutrition;
import com.nspl.restaurant.databinding.ItemNutritionBinding;

import java.util.ArrayList;
import java.util.List;

public class ItemNutritionAdapter extends RecyclerView.Adapter<ItemNutritionAdapter.ViewHolder> {

    private Context mContext;
    private List<ClsNutrition> list = new ArrayList<>();

    public ItemNutritionAdapter(Context context) {
        this.mContext = context;
    }

    public void addNutrition(List<ClsNutrition> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ItemNutritionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        ItemNutritionBinding binding = DataBindingUtil.inflate(
                layoutInflater, R.layout.item_nutrition, parent, false);
        return new ViewHolder(binding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ItemNutritionAdapter.ViewHolder holder, int position) {
        ClsNutrition current = list.get(position);
        holder.binding.tvName.setText(current.getnAME());
        holder.binding.tvValue.setText(" : " + current.getvALUE());
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
        ItemNutritionBinding binding;

        ViewHolder(@NonNull ItemNutritionBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
