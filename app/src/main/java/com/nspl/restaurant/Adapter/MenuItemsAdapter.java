package com.nspl.restaurant.Adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.nspl.restaurant.R;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Menu.ClsItem;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Menu.ClsSize;
import com.nspl.restaurant.databinding.MenuItemsBinding;

import java.util.ArrayList;
import java.util.List;

public class MenuItemsAdapter extends RecyclerView.Adapter<MenuItemsAdapter.ViewHolder> {
    private List<ClsItem> list = new ArrayList<>();
    private Context mContext;
    private ItemSizeAdapter adpItemSize;
    // private MenuItemsAdapter.MenuOnClickListener mMenuOnClickListener;

    MenuItemsAdapter(Context context) {
        this.mContext = context;
        adpItemSize = new ItemSizeAdapter(this.mContext);
    }

    void addItems(List<ClsItem> _itemList) {
        this.list = _itemList;
        Log.e("--itemSIZE--", "itemSIZE COUNT:".concat("" + this.list.size()));
    }

//    public void SetOnMenuClickListener(MenuAdapter.MenuOnClickListener menuOnClickListener) {
//        this.mMenuOnClickListener = menuOnClickListener;
//    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        MenuItemsBinding binding = DataBindingUtil.inflate(
                layoutInflater, R.layout.menu__items, viewGroup, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuItemsAdapter.ViewHolder viewHolder, int i) {

        Log.d("check", "onBindViewHolder  MenuItemsAdapter ");
        ClsItem current = list.get(i);
        Log.d("check", "current " + current.getnAME());
        viewHolder.binding.tvItemName.setText(current.getnAME());

        if(current.getfOODTYPE() != null && current.getfOODTYPE().equalsIgnoreCase("VEG")){
            viewHolder.binding.ivType.setImageResource(R.drawable.veg);
        }else if(current.getfOODTYPE() != null && current.getfOODTYPE().equalsIgnoreCase("NON-VEG")){
            viewHolder.binding.ivType.setImageResource(R.drawable.nonveg);
        }
//        viewHolder.binding.tvItemName.setText(current.getnAME().concat(", TYPE:").concat(current.getfOODTYPE()));

        List<ClsSize> _listSize = current.getsIZES();

        adpItemSize.addItems(_listSize);
        viewHolder.binding.RvSIZE.setAdapter(adpItemSize);
        viewHolder.binding.RvSIZE.setLayoutManager(new GridLayoutManager(mContext,3));

//        mRecyclerView.setLayoutManager(new LinearLayoutManager(co, LinearLayoutManager.HORIZONTAL, true));


//        adpItemSize.notifyDataSetChanged();

        //String _SIZE = "FULL ".concat(" RS.550.23");
        //fill size adp here

        //follow this same method in all adp view Method...!

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final MenuItemsBinding binding;

        ViewHolder(@NonNull MenuItemsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
