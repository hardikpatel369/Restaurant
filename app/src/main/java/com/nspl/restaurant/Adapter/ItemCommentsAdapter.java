package com.nspl.restaurant.Adapter;

import android.content.Context;

import androidx.databinding.DataBindingUtil;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.nspl.restaurant.R;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Menu.ClsComment;
import com.nspl.restaurant.databinding.ItemCommentsBinding;

import java.util.ArrayList;
import java.util.List;

public class ItemCommentsAdapter extends RecyclerView.Adapter<ItemCommentsAdapter.ViewHolder> {

    private List<ClsComment> list = new ArrayList<>();
    static List<ClsComment> listComments = new ArrayList<>();

    private List<String> stringList = new ArrayList<>();
    private String cbValue;
    private Context mContext;

    interface OnCommentListener{
        void onCommentClick();
    }
    private OnCommentListener onCommentListener;

    ItemCommentsAdapter(Context context, OnCommentListener onCommentListener) {
        this.mContext = context;
        this.onCommentListener = onCommentListener;
    }

    void addComments(List<ClsComment> list) {
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
//            cbValue = current.getSORTNAME();
//
//            if (isChecked){
//                stringList.add(cbValue);
//            }else{
//                stringList.remove(cbValue);
//            }

            current.setSelected(isChecked);
            listComments.set(i,current);

            onCommentListener.onCommentClick();
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
        private final ItemCommentsBinding binding;

        ViewHolder(@NonNull ItemCommentsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
