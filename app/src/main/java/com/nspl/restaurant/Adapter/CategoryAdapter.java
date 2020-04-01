package com.nspl.restaurant.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.nspl.restaurant.R;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Menu.ClsCategorys;
import com.nspl.restaurant.databinding.ItemCategoryBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private List<ClsCategorys> list = new ArrayList<>();
    private Context mContext;

    public CategoryAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void addCategory(List<ClsCategorys> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public interface CategoryItemsOnClickListener {
        void OnClick(ClsCategorys clsCategorys, int position);
    }
    private CategoryItemsOnClickListener clickListener;

    public void SetOnItemListClickListener(CategoryItemsOnClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        ItemCategoryBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(context), R.layout.item_category, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
        ClsCategorys current = list.get(position);

        holder.binding.tvCategoryName.setText(current.getcATEGORYNAME());

//        if (current.getcATEGORYIMAGE() != null &&
//                !current.getcATEGORYIMAGE().isEmpty()) {
//
////            String image = ("http://".concat(current.getcATEGORYIMAGE()));
//            String image = ("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcS9zxc4UZ81g6DD5wxOmcawrfXCsSnbt0S1se_xGKUpBCECHK4g");
//            Picasso.get()
//                    .load(image)
//                    .placeholder(R.drawable.ic_image_black)
//                    .error(R.drawable.ic_error)
//                    .into(holder.binding.ivCategory);
//        }

        if(position % 3 == 0){
            holder.binding.background.setBackgroundColor(Color.parseColor("#F2DF493B"));
        }else if (position % 3 == 1){
            holder.binding.background.setBackgroundColor(Color.parseColor("#F24B90A0"));
        }else if (position % 3 == 2) {
            holder.binding.background.setBackgroundColor(Color.parseColor("#F273A05B"));
        }

        if (position % 9 ==0){
            holder.binding.ivCategory.setImageResource(R.drawable.a);
        }else if (position % 9 ==1){
            holder.binding.ivCategory.setImageResource(R.drawable.b);
        }else if (position % 9 ==2){
            holder.binding.ivCategory.setImageResource(R.drawable.c);
        }else if (position % 9 ==3){
            holder.binding.ivCategory.setImageResource(R.drawable.d);
        }else if (position % 9 ==4){
            holder.binding.ivCategory.setImageResource(R.drawable.e);
        }else if (position % 9 ==5){
            holder.binding.ivCategory.setImageResource(R.drawable.f);
        }else if (position % 9 ==6){
            holder.binding.ivCategory.setImageResource(R.drawable.g);
        }else if (position % 9 ==7){
            holder.binding.ivCategory.setImageResource(R.drawable.h);
        }else if (position % 9 ==8){
            holder.binding.ivCategory.setImageResource(R.drawable.i);
        }

            holder.BindClick(current, clickListener, position);
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemCategoryBinding binding;

        public ViewHolder(@NonNull ItemCategoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void BindClick(ClsCategorys clsCategorys, CategoryItemsOnClickListener clickListener, int position) {
            binding.relativeLayout.setOnClickListener(v -> clickListener.OnClick(clsCategorys, position));
        }
    }
}
