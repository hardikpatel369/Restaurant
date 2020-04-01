package com.nspl.restaurant.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.databinding.DataBindingUtil;

import android.graphics.Color;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.nspl.restaurant.R;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Tables.ClsTable;
import com.nspl.restaurant.databinding.TableItemsBinding;

import java.util.ArrayList;
import java.util.List;

public class TableAdapter extends RecyclerView.Adapter<TableAdapter.ViewHolder> {

    private List<ClsTable> tablesList = new ArrayList<>();
    private Context mContext;
    private TableOnClickListener mTableOnClickListener;

    public TableAdapter(Context context) {
        this.mContext = context;
    }

    public void SetOnCounterClickListener(TableOnClickListener tableOnClickListener) {
        this.mTableOnClickListener = tableOnClickListener;
    }

    public void AddItems(List<ClsTable> list) {
        this.tablesList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(mContext);
        TableItemsBinding binding = DataBindingUtil.inflate(
                layoutInflater, R.layout.table_items, viewGroup, false);
        return new ViewHolder(binding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ClsTable current = tablesList.get(i);

        String status = "Status: ";

        viewHolder.binding.TableNo.setText("No: " + current.getTABLENAMENUMBER());
        viewHolder.binding.TblStatus.setText(status);
        viewHolder.binding.TblNumberOfOrder.setText("Quantity: " + current.getTOTALQUANTITY());
        viewHolder.binding.TblPrice.setText("Amount: " + current.getTOTALAMOUNT());

        if (current.getSTATUS() != null && current.getSTATUS().equalsIgnoreCase("OCCUPIED")) {
            String statusValue =  "<font color='#F6BB42'>OCCUPIED</font>";
            viewHolder.binding.TblStatus.setText(Html.fromHtml(status+statusValue));
        } else {
            String statusValue =  "<font color='#37BC9B'>VACANT</font>";
            viewHolder.binding.TblStatus.setText(Html.fromHtml(status+statusValue));
        }

        viewHolder.BindClick(current, mTableOnClickListener, i);
    }

    @Override
    public int getItemCount() {
        return tablesList != null ? tablesList.size() : 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final TableItemsBinding binding;

        ViewHolder(TableItemsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void BindClick(ClsTable clsTable, TableOnClickListener tableOnClickListener, int position) {
            binding.LinearLayout.setOnClickListener(v -> tableOnClickListener.OnClick(clsTable, position));
        }
    }

    public interface TableOnClickListener {
        void OnClick(ClsTable clsTable, int position);
    }
}
