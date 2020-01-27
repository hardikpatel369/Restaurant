package com.nspl.restaurant.Adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.nspl.restaurant.R;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Menu.ClsComment;
import com.nspl.restaurant.databinding.ItemCommentsBinding;

import java.util.ArrayList;
import java.util.List;

public class ItemCommentsAdapter extends RecyclerView.Adapter<ItemCommentsAdapter.ViewHolder> {

    private List<ClsComment> list = new ArrayList<>();
    private Context mContext;

    ItemCommentsAdapter(Context context) {
        this.mContext = context;
    }

    void addComments(List<ClsComment> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ItemCommentsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        ItemCommentsBinding binding = DataBindingUtil.inflate(
                layoutInflater, R.layout.item_add_ons, viewGroup, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemCommentsAdapter.ViewHolder viewHolder, int i) {
        ClsComment current = list.get(i);
        viewHolder.binding.tvComments.setText(current.getnAME());
    }

    @Override
    public int getItemCount() {
        if (list != null)
            return list.size();
        else
            return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemCommentsBinding binding;

        ViewHolder(@NonNull ItemCommentsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
