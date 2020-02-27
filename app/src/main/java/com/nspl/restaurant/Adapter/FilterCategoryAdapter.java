package com.nspl.restaurant.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.nspl.restaurant.R;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Menu.ClsCategorys;
import com.nspl.restaurant.databinding.FilterCategoryBinding;

import java.util.ArrayList;
import java.util.List;

public class FilterCategoryAdapter extends RecyclerView.Adapter<FilterCategoryAdapter.ViewHolder> {
    private Context mContext;
    private List<ClsCategorys> list = new ArrayList<>();
    private String cName;

//    public FilterCategoryAdapter(Context mContext) {
//        this.mContext = mContext;
//    }

    public FilterCategoryAdapter(Context mContext, OnItemClickListenerCategory onItemClickListener) {
        this.mContext = mContext;
        this.onItemClickListener = onItemClickListener;
    }

//    public void SetOnClickListener(OnItemClickListenerCategory onItemClickListener) {
//        this.onItemClickListener = onItemClickListener;
//    }

    public interface OnItemClickListenerCategory {
         void OnclickOK(String cName);
    }
    private OnItemClickListenerCategory onItemClickListener;

    public void addCategory(List<ClsCategorys> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public FilterCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        FilterCategoryBinding binding = DataBindingUtil.inflate(
                layoutInflater, R.layout.filter_category, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FilterCategoryAdapter.ViewHolder holder, int position) {
        ClsCategorys current = list.get(position);
        holder.binding.tvFilterCategory.setText(current.getcATEGORYNAME());

        holder.itemView.setOnClickListener(v -> {
            cName = current.getcATEGORYNAME();

            Log.d("Test-----", "onBindViewHolder: "+onItemClickListener);
            if (this.onItemClickListener != null){
                this.onItemClickListener.OnclickOK(cName);
                Log.d("Test-----", "FilterCategoryAdapter: "+cName);
            }
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

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final FilterCategoryBinding binding;

        public ViewHolder(@NonNull FilterCategoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
