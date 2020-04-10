package com.nspl.restaurant.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.nspl.restaurant.Activity.ImageActivity;
import com.nspl.restaurant.R;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Menu.ClsCategorys;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Menu.ClsItem;
import com.nspl.restaurant.databinding.ItemCategoryItemsBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CategoryItemsAdapter extends RecyclerView.Adapter<CategoryItemsAdapter.ViewHolder> {

    private List<ClsItem> list = new ArrayList<>();
    private Context mContext;

    public CategoryItemsAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void addCategoryItemsAdapter(List<ClsItem> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public interface CategoryItemsOnClickListener {
        void OnClick(ClsItem clsItem, int position);
    }
    private CategoryItemsOnClickListener clickListener;

    public void SetOnItemListClickListener(CategoryItemsOnClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public CategoryItemsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        ItemCategoryItemsBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(context), R.layout.item_category_items, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryItemsAdapter.ViewHolder holder, int position) {
        ClsItem current = list.get(position);

        holder.binding.tvItemName.setText(current.getnAME());

        if (current.getfOODTYPE() != null && current.getfOODTYPE().equalsIgnoreCase("VEG")) {
            holder.binding.ivVegNon.setImageResource(R.drawable.veg);
        } else if (current.getfOODTYPE() != null && current.getfOODTYPE().equalsIgnoreCase("NON-VEG")) {
            holder.binding.ivVegNon.setImageResource(R.drawable.nonveg);
        }

//        if (current.getiMAGES() != null && !current.getiMAGES().isEmpty()){
//            String image = ("http://".concat(current.getiMAGES()));
//            Picasso.get()
//                    .load(image)
//                    .placeholder(R.drawable.ic_image_black)
//                    .error(R.drawable.ic_error)
//                    .into(holder.binding.ivCategoryItem);
//        }

        holder.binding.ivCategoryItem.setOnClickListener(view -> {
            List<String> images = current.getItemIMAGE();

            Intent intent = new Intent(mContext, ImageActivity.class);
            intent.putStringArrayListExtra("imageUrl", (ArrayList<String>) images);
            intent.putExtra("ItemName", current.getnAME());
            mContext.startActivity(intent);
        });

        holder.BindClick(current, clickListener, position);
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemCategoryItemsBinding binding;

        public ViewHolder(@NonNull ItemCategoryItemsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        void BindClick(ClsItem clsItem, CategoryItemsOnClickListener clickListener, int position) {
            binding.CardView.setOnClickListener(v -> clickListener.OnClick(clsItem, position));
        }
    }
}
