package com.nspl.restaurant.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.nspl.restaurant.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomPagerAdapter extends PagerAdapter {


    private static final String TAG = "ImageViewPage";
    private LayoutInflater mLayoutInflater;
    private List<String> mResources;

    public CustomPagerAdapter(Context context, List<String> resources) {
        mResources = resources;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mResources.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View itemView = mLayoutInflater.inflate(R.layout.pager_item, container, false);
        ImageView ivPhoto = itemView.findViewById(R.id.iv_photo);

        String images = ("".concat("http://").concat(mResources.get(position)));

        Picasso.get()
                .load(images)
                .placeholder(R.drawable.ic_image_black)
                .error(R.drawable.ic_error)
                .into(ivPhoto);

        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        Log.d(TAG, "destroyItem() called with: " + "container = [" + container + "], position = [" + position
                + "], object = [" + object + "]");
        container.removeView((LinearLayout) object);
    }
}
