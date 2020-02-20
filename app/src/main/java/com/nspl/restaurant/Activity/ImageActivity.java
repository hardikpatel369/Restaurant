package com.nspl.restaurant.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;
import com.nspl.restaurant.Adapter.CustomPagerAdapter;
import com.nspl.restaurant.Adapter.ViewPagerFixedAdapter;
import com.nspl.restaurant.R;

import java.util.List;

public class ImageActivity extends AppCompatActivity {

    ViewPagerFixedAdapter fixedAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        ViewPager viewPager = findViewById(R.id.vp_photogallery);
        TabLayout tabLayout = findViewById(R.id.tab_layout);

        fixedAdapter = new ViewPagerFixedAdapter(getApplicationContext());

        List<String> images = getIntent().getStringArrayListExtra("imageUrl");

        if (viewPager != null) {
            viewPager.setAdapter(new CustomPagerAdapter(this, images));
            Log.d("ImageViewPage", "after: " + images);
        }


        tabLayout.setupWithViewPager(viewPager);
    }
}
