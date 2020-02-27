package com.nspl.restaurant.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.tabs.TabLayout;
import com.nspl.restaurant.Adapter.CustomPagerAdapter;
import com.nspl.restaurant.Adapter.ViewPagerFixedAdapter;
import com.nspl.restaurant.R;

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
        TabLayout tabLayout = findViewById(R.id.tab_layout);

        fixedAdapter = new ViewPagerFixedAdapter(getApplicationContext());

        if (viewPager != null) {
            viewPager.setAdapter(new CustomPagerAdapter(this, images));
            Log.d("ImageViewPage", "after: " + images);
        }

        tabLayout.setupWithViewPager(viewPager);
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(ItemName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                Intent intent = new Intent(ImageActivity.this, MenuActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ImageActivity.this, MenuActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
