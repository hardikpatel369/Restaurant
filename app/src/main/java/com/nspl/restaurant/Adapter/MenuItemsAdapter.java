package com.nspl.restaurant.Adapter;

import android.content.Context;
import androidx.databinding.DataBindingUtil;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nspl.restaurant.Activity.ImageActivity;
import com.nspl.restaurant.R;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Menu.ClsItem;
import com.nspl.restaurant.databinding.MenuItemsBinding;

import java.util.ArrayList;
import java.util.List;

public class MenuItemsAdapter extends RecyclerView.Adapter<MenuItemsAdapter.ViewHolder> {
    private List<ClsItem> list = new ArrayList<>();
    private Context mContext;
//    private ItemSizeAdapter adpItemSize;

    public interface MenuItemsOnClickListener {
        void OnClick(ClsItem clsItem, int position);
    }
    private MenuItemsOnClickListener menuItemsOnClickListener;

    void SetOnItemListClickListener(MenuItemsOnClickListener menuItemsOnClickListener) {
        this.menuItemsOnClickListener = menuItemsOnClickListener;
    }

    void addItems(List<ClsItem> _itemList) {
        this.list = _itemList;
    }

    MenuItemsAdapter(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        MenuItemsBinding binding = DataBindingUtil.inflate(
                layoutInflater, R.layout.menu__items, viewGroup, false);

//        adpItemSize = new ItemSizeAdapter(mContext);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuItemsAdapter.ViewHolder viewHolder, int i) {
        ClsItem current = list.get(i);

        viewHolder.binding.tvItemName.setText(current.getnAME());

        if (current.getfOODTYPE() != null && current.getfOODTYPE().equalsIgnoreCase("VEG")) {
            viewHolder.binding.ivType.setImageResource(R.drawable.veg);
        } else if (current.getfOODTYPE() != null && current.getfOODTYPE().equalsIgnoreCase("NON-VEG")) {
            viewHolder.binding.ivType.setImageResource(R.drawable.nonveg);
        }

        if (current.getItemIMAGE() != null && current.getItemIMAGE().size() != 0) {
            viewHolder.binding.ivItemImg.setVisibility(View.VISIBLE);
        } else {
            viewHolder.binding.ivItemImg.setVisibility(View.GONE);
        }

        viewHolder.binding.ivItemImg.setOnClickListener(v -> {
            List<String> images = current.getItemIMAGE();
            Log.d("ImageViewPage", "MenuItemsAdapter: " + images);

            Intent intent = new Intent(mContext, ImageActivity.class);
            intent.putStringArrayListExtra("imageUrl", (ArrayList<String>) images);
            intent.putExtra("ItemName",current.getnAME());
            mContext.startActivity(intent);
        });

        /* Horizontal Scrollview of size */

//        List<ClsSize> _listSize = current.getsIZES();
//        adpItemSize.addSize(_listSize, "MenuItemsAdapter");
//        viewHolder.binding.RvSIZE.setAdapter(adpItemSize);
//        viewHolder.binding.RvSIZE.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));

        viewHolder.BindClick(current, menuItemsOnClickListener, i);
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

    class ViewHolder extends RecyclerView.ViewHolder {
        private final MenuItemsBinding binding;

        ViewHolder(@NonNull MenuItemsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void BindClick(ClsItem clsItem, MenuItemsAdapter.MenuItemsOnClickListener menuItemsOnClickListener, int position) {
            binding.CardView.setOnClickListener(v -> menuItemsOnClickListener.OnClick(clsItem, position));
        }
    }
}