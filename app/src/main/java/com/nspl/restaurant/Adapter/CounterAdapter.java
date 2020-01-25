package com.nspl.restaurant.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.nspl.restaurant.R;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Counters.ClsCounterData;
import com.nspl.restaurant.databinding.CounterItemsBinding;

import java.util.ArrayList;
import java.util.List;

public class CounterAdapter extends RecyclerView.Adapter<CounterAdapter.ViewHolder> {


    private List<ClsCounterData> counterList = new ArrayList<>();
    private Context mContext;
    private CounterOnClickListener mCounterOnClickListener;

    public CounterAdapter(Context context){
        this.mContext = context;
    }

    public void AddItems(List<ClsCounterData> counterList){
        this.counterList = counterList;
        notifyDataSetChanged();
    }

    public void SetOnCounterClickListener(CounterOnClickListener counterOnClickListener){
        this.mCounterOnClickListener = counterOnClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(mContext);
        CounterItemsBinding binding = DataBindingUtil.inflate(
                layoutInflater, R.layout.counter_items, viewGroup, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ClsCounterData current = counterList.get(i);
        viewHolder.bind(current);
        viewHolder.BindClick(current,mCounterOnClickListener,i);
    }

    @Override
    public int getItemCount() {
        return counterList.size();
    }

     static class ViewHolder extends RecyclerView.ViewHolder{

        private final CounterItemsBinding binding;

        ViewHolder(CounterItemsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @SuppressLint("SetTextI18n")
        void bind(ClsCounterData current) {
            binding.CounterName.setText(current.getCOUNTERNAME());
            binding.CounterType.setText("Counter Type: ".concat(current.getCOUNTERTYPE()));
            binding.DepartmentName.setText("Department Name: " .concat(current.getDEPARTMENTNAME()));
            binding.MenuName.setText("Menu Name: " .concat(current.getMENUNAME()));
        }

        void BindClick(ClsCounterData clsCounterData,
                       CounterOnClickListener counterOnClickListener,int position){
            binding.LinearLayout.setOnClickListener(v ->
                    counterOnClickListener.OnClick(clsCounterData,position));
        }
    }

    public interface CounterOnClickListener{
        void OnClick(ClsCounterData clsCounterData, int position);
    }
}
