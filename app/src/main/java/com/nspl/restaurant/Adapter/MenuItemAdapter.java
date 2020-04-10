package com.nspl.restaurant.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.nspl.restaurant.Activity.ImageActivity;
import com.nspl.restaurant.R;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Menu.ClsCustomCategory;

import java.util.ArrayList;
import java.util.List;

public class MenuItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int HEADER_VIEW = 0;
    private static final int MENU_VIEW = 1;
    private Context mContext;
    private List<ClsCustomCategory> list = new ArrayList<>();

    public MenuItemAdapter(Context mContext ) {
        this.mContext = mContext;
    }

    public void AddCategory(List<ClsCustomCategory> list){
        this.list = list;
        notifyDataSetChanged();
    }

    public interface ItemClickListener {
        void OnClick(ClsCustomCategory clsCustomCategory, int position);
    }
    private ItemClickListener itemClickListener;

    public void SetOnItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType){
            case 0:
                View categoryView = inflater.inflate(R.layout.category_name, parent, false);
                viewHolder = new HeaderViewHolder(categoryView);
                break;
            case 1:
                View nameView = inflater.inflate(R.layout.category_item_name, parent, false);
                viewHolder = new MenuHolder(nameView);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ClsCustomCategory current = list.get(position);

        switch (holder.getItemViewType()){

            case 0:
                HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
                headerViewHolder.Category_name.setText(current.getcATEGORYNAME());
                headerViewHolder.Category_name.setClickable(false);
                break;

            case 1:
                MenuHolder menuHolder = (MenuHolder) holder;
                menuHolder.tvItemName.setText(current.getnAME());

                    menuHolder.tvItemName.setText(current.getnAME());

                    menuHolder.tvItemName.setText(current.getnAME());

                    if (current.getfOODTYPE() != null && current.getfOODTYPE().equalsIgnoreCase("VEG")) {
                        menuHolder.ivType.setImageResource(R.drawable.veg);
                    } else if (current.getfOODTYPE() != null && current.getfOODTYPE().equalsIgnoreCase("NON-VEG")) {
                        menuHolder.ivType.setImageResource(R.drawable.nonveg);
                    }

                    if (current.getItemIMAGE() != null && current.getItemIMAGE().size() != 0) {
                        menuHolder.iv_item_img.setVisibility(View.VISIBLE);
                    } else {
                        menuHolder.iv_item_img.setVisibility(View.GONE);
                    }

                    menuHolder.iv_item_img.setOnClickListener(v -> {
                        List<String> images = current.getItemIMAGE();

                        Intent intent = new Intent(mContext, ImageActivity.class);
                        intent.putStringArrayListExtra("imageUrl", (ArrayList<String>) images);
                        intent.putExtra("ItemName", current.getnAME());
                        mContext.startActivity(intent);
                    });

                menuHolder.BindClick(current, itemClickListener, position);

                break;
        }
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position).isHeader()) {
            return HEADER_VIEW;
        } else {
            return MENU_VIEW;
        }
    }

    public class MenuHolder extends RecyclerView.ViewHolder {
        TextView tvItemName;
        ImageView ivType,iv_item_img;
        CardView cardView;

        MenuHolder(@NonNull View itemView) {
            super(itemView);
            tvItemName = itemView.findViewById(R.id.tvItemName);
            ivType = itemView.findViewById(R.id.ivType);
            iv_item_img = itemView.findViewById(R.id.iv_item_img);
            cardView = itemView.findViewById(R.id.CardView);
        }

        void BindClick(ClsCustomCategory clsCustomCategory,
                       ItemClickListener itemClickListener, int position) {
            cardView.setOnClickListener(v ->
                    itemClickListener.OnClick(clsCustomCategory, position));
        }
    }

    public static class HeaderViewHolder extends RecyclerView.ViewHolder {
        TextView Category_name;

        HeaderViewHolder(View itemView) {
            super(itemView);
            Category_name = itemView.findViewById(R.id.Category_name);
        }
    }
}