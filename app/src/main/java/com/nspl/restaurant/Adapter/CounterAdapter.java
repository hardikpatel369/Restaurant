package com.nspl.restaurant.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.databinding.DataBindingUtil;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
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

    public CounterAdapter(Context context) {
        this.mContext = context;
    }

    public void AddItems(List<ClsCounterData> counterList) {
        this.counterList = counterList;
        notifyDataSetChanged();
    }

    public void SetOnCounterClickListener(CounterOnClickListener counterOnClickListener) {
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
        viewHolder.BindClick(current, mCounterOnClickListener, i);
    }

    @Override
    public int getItemCount() {
        return counterList != null ? counterList.size() : 0;
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

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final CounterItemsBinding binding;

        ViewHolder(CounterItemsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @SuppressLint("SetTextI18n")
        void bind(ClsCounterData current) {
            binding.CounterName.setText(current.getCOUNTERNAME());
            binding.CounterType.setText("Counter Type: ".concat(current.getCOUNTERTYPE()));
            binding.DepartmentName.setText("Department: ".concat(current.getDEPARTMENTNAME()));
            binding.MenuName.setText("Menu: ".concat(current.getMENUNAME()));

            if (current.getCOUNTERTYPE().equalsIgnoreCase("RESTAURANT")) {
                binding.LinearLayout.setBackgroundColor(Color.parseColor("#a6a6ff"));
            }
            if (current.getCOUNTERTYPE().equalsIgnoreCase("RETAIL")) {
                binding.LinearLayout.setBackgroundColor(Color.parseColor("#ff9999"));
            }
        }

        void BindClick(ClsCounterData clsCounterData,
                       CounterOnClickListener counterOnClickListener, int position) {
            binding.LinearLayout.setOnClickListener(v ->
                    counterOnClickListener.OnClick(clsCounterData, position));
        }
    }

    public interface CounterOnClickListener {
        void OnClick(ClsCounterData clsCounterData, int position);
    }
}
