package com.nspl.restaurant.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.nspl.restaurant.Adapter.CustomPagerAdapter;
import com.nspl.restaurant.Adapter.ViewPagerFixedAdapter;
import com.nspl.restaurant.R;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import java.util.List;

public class ImageActivity extends AppCompatActivity {

    ViewPagerFixedAdapter fixedAdapter;
    String ItemName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        List<String> images = getIntent().getStringArrayListExtra("imageUrl");
        ItemName = getIntent().getStringExtra("ItemName");
        initToolbar();

        ViewPager viewPager = findViewById(R.id.vp_photogallery);
        DotsIndicator dotsIndicator = findViewById(R.id.dots_indicator);
        ProgressBar pb = findViewById(R.id.pb);
        fixedAdapter = new ViewPagerFixedAdapter(getApplicationContext());

        pb.setVisibility(View.VISIBLE);

        if (viewPager != null) {
            viewPager.setAdapter(new CustomPagerAdapter(this, images));
            pb.setVisibility(View.GONE);
            Log.d("ImageViewPage", "after: " + images);
        }

        dotsIndicator.setViewPager(viewPager);
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(ItemName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
