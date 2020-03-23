package com.nspl.restaurant.Adapter;

import android.content.Context;

import androidx.databinding.DataBindingUtil;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.nspl.restaurant.R;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Menu.ClsComment;
import com.nspl.restaurant.databinding.ItemCommentsBinding;

import java.util.ArrayList;
import java.util.List;

public class ItemCommentsAdapter extends RecyclerView.Adapter<ItemCommentsAdapter.ViewHolder> {

    private List<ClsComment> list = new ArrayList<>();
    public static List<ClsComment> listComments = new ArrayList<>();

    private Context mContext;

    public ItemCommentsAdapter(Context context) {
        this.mContext = context;
    }

    public void addComments(List<ClsComment> list) {
        listComments = this.list = list;
    }

    @NonNull
    @Override
    public ItemCommentsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        ItemCommentsBinding binding = DataBindingUtil.inflate(
                layoutInflater, R.layout.item_comments, viewGroup, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemCommentsAdapter.ViewHolder viewHolder, int i) {
        ClsComment current = list.get(i);
        viewHolder.binding.cbComments.setText(current.getSORTNAME());
        viewHolder.binding.cbComments.setOnCheckedChangeListener((buttonView, isChecked) -> {

            current.setSelected(isChecked);
            listComments.set(i,current);

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
        private final ItemCommentsBinding binding;

        ViewHolder(@NonNull ItemCommentsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
