package com.nspl.restaurant.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.nspl.restaurant.R;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Kitchen.ClsKitchenSection;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Kitchen.KitchenDiffUtil;
import com.nspl.restaurant.databinding.ItemKitchenSectionBinding;

import java.util.ArrayList;
import java.util.List;

public class KitchenSectionAdapter extends RecyclerView.Adapter<KitchenSectionAdapter.ViewHolder> {

    private List<ClsKitchenSection> list = new ArrayList<>();
    private KitchenAddonsAdapter addonsAdapter;
    private Context mContext;

    public KitchenSectionAdapter(Context mContext) {
        this.mContext = mContext;
        addonsAdapter = new KitchenAddonsAdapter(mContext);
    }

    public void addOrderDetail(List<ClsKitchenSection> list) {
        this.list = list;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public KitchenSectionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        ItemKitchenSectionBinding binding = DataBindingUtil.inflate(
                layoutInflater, R.layout.item_kitchen_section, parent, false);

        addonsAdapter = new KitchenAddonsAdapter(mContext);
        return new ViewHolder(binding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull KitchenSectionAdapter.ViewHolder holder, int position) {
        ClsKitchenSection current = list.get(position);

        holder.binding.tvTableToken.setText(current.getTABLENAMENUMBER());
        holder.binding.tvItemName.setText(current.getQUANTITY()+"x "+current.getITEMNAME());
        holder.binding.tvSize.setText(current.getSIZE());
        holder.binding.tvTime.setText(current.getORDERTIME());

        if (current.getFOODTYPE() != null && current.getFOODTYPE().equalsIgnoreCase("VEG")) {
            holder.binding.ivType.setImageResource(R.drawable.veg);
        } else if (current.getFOODTYPE() != null && current.getFOODTYPE().equalsIgnoreCase("NON-VEG")) {
            holder.binding.ivType.setImageResource(R.drawable.nonveg);
        }

        if (current.getCOMMENTS() == null || current.getCOMMENTS().equals("")) {
           holder.binding.llComments.setVisibility(View.GONE);
        } else {
            holder.binding.tvComments.setText(current.getCOMMENTS());
        }

        if (current.getListAddons() != null && current.getListAddons().size() != 0) {
            Gson gson = new Gson();
            String jsonInString = gson.toJson(current);
            Log.e("listItems", "KitchenListItems---" + jsonInString);

            addonsAdapter.addItems(current.getListAddons());
            holder.binding.rvAddons.setAdapter(addonsAdapter);
            holder.binding.rvAddons.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        }
    }

    public void updateList(List<ClsKitchenSection> sectionList){
        final KitchenDiffUtil diffUtil = new KitchenDiffUtil(this.list,sectionList);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffUtil);

        this.list.clear();
        this.list.addAll(sectionList);
        diffResult.dispatchUpdatesTo(this);
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemKitchenSectionBinding binding;

        public ViewHolder(@NonNull ItemKitchenSectionBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
