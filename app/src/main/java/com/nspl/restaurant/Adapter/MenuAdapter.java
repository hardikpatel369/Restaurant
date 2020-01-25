package com.nspl.restaurant.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.nspl.restaurant.R;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Menu.ClsCategorys;

import java.util.ArrayList;
import java.util.List;

import com.nspl.restaurant.RetrofitApi.ApiClasses.Menu.ClsItem;
import com.nspl.restaurant.databinding.MenuCategoriesBinding;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {

    private List<ClsCategorys> list = new ArrayList<>();
    private Context mContext;
    private MenuOnClickListener mMenuOnClickListener;
    private MenuItemsAdapter adpItems;


    public MenuAdapter(Context context) {
        this.mContext = context;
        adpItems = new MenuItemsAdapter(this.mContext);
    }

    public void addItems(List<ClsCategorys> _categoryList) {
        this.list = _categoryList;
//        notifyDataSetChanged();
    }


    public void SetOnMenuClickListener(MenuOnClickListener menuOnClickListener) {
        this.mMenuOnClickListener = menuOnClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(mContext);

        MenuCategoriesBinding binding = DataBindingUtil.inflate(
                layoutInflater, R.layout.menu_categories, viewGroup, false);
        return new ViewHolder(binding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ClsCategorys current = list.get(i);
        viewHolder.binding.CategoryName.setText(current.getcATEGORYNAME());
        List<ClsItem> _listItems = current.getiTEMS();

        adpItems.addItems(_listItems);
        viewHolder.binding.RvItems.setAdapter(adpItems);
        viewHolder.binding.RvItems.setLayoutManager(new LinearLayoutManager(mContext));

//        adpItems.notifyDataSetChanged();

        //follow this same method in all adp view Method...!

//        Gson gson = new Gson();
//        String jsonInString = gson.toJson(_listItems);
//        Log.e("--itemGSON--", "itemGSON:" .concat(current.getcATEGORYNAME()).concat(": ").concat( jsonInString));


//        for (ClsMenuItems obj :lstMenuItems) {
//            viewHolder.binding.ItemName.setText("Item Name: ".concat(obj.getnAME()));
//        }


        viewHolder.BindClick(current, mMenuOnClickListener, i);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final MenuCategoriesBinding binding;

        ViewHolder(MenuCategoriesBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void BindClick(ClsCategorys clsCategorys, MenuOnClickListener menuOnClickListener, int position) {
            binding.LinearLayout.setOnClickListener(v -> menuOnClickListener.OnClick(clsCategorys, position));
        }
    }

    public interface MenuOnClickListener {
        void OnClick(ClsCategorys clsTable, int position);

    }
}
