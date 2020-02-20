package com.nspl.restaurant.Activity;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.core.widget.NestedScrollView;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.nspl.restaurant.Fragment.CounterFragment;
import com.nspl.restaurant.Fragment.SettingsFragment;
import com.nspl.restaurant.Fragment.WaitingPagerFragment;
import com.nspl.restaurant.R;
import com.nspl.restaurant.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {

    ActivityHomeBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mBinding = DataBindingUtil.setContentView(this,
                R.layout.activity_home);

        setupBottomNavigation();
        overridePendingTransition(0, 0);

        Animation animation = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        mBinding.container.startAnimation(animation);

        Fragment fragment = new CounterFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, fragment)
                .commit();
    }

    private void setupBottomNavigation() {

        mBinding.bottomNavigation.setOnNavigationItemSelectedListener(item -> {

            switch (item.getItemId()) {
                case R.id.action_home:
                    CounterFragment();
                    getSupportActionBar().setTitle("Restaurant");
                    return true;

                case R.id.action_waiting:
                    WaitingFragment();
                    getSupportActionBar().setTitle("Waiting");
                    return true;

                case R.id.action_lead:
                    loadLeadFragment();
                    getSupportActionBar().setTitle("Test");
                    return true;

                case R.id.action_inquiry:
                    loadInquiryFragment();
                    getSupportActionBar().setTitle("Test");
                    return true;

                case R.id.action_settings:
                    loadSettingsFragment();
                    getSupportActionBar().setTitle("Settings");
                    return true;
            }
            return false;
        });


        mBinding.nestedScrollView.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener)
                (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
                    if (scrollY < oldScrollY) { // up
                        animateNavigation(false);
                        animateSearchBar(false);
                    }
                    if (scrollY > oldScrollY) { // down
                        animateNavigation(true);
                        animateSearchBar(true);
                    }
                });
    }

    boolean isNavigationHide = false;

    private void animateNavigation(final boolean hide) {
        if (isNavigationHide && hide || !isNavigationHide && !hide) return;
        isNavigationHide = hide;
        int moveY = hide ? (2 * mBinding.bottomNavigation.getHeight()) : 0;
        mBinding.bottomNavigation.animate().translationY(moveY).setStartDelay(100).setDuration(300).start();
    }


    boolean isSearchBarHide = false;

    private void animateSearchBar(final boolean hide) {
        if (isSearchBarHide && hide || !isSearchBarHide && !hide) return;
        isSearchBarHide = hide;
//        int moveY = hide ? -(2 * search_bar.getHeight()) : 0;
//        search_bar.animate().translationY(moveY).setStartDelay(100).setDuration(300).start();
    }

    private void CounterFragment() {

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new CounterFragment())
                .disallowAddToBackStack().commit();
    }

    private void WaitingFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new WaitingPagerFragment())
                .disallowAddToBackStack().commit();
    }

    private void loadLeadFragment() {

    }

    private void loadInquiryFragment() {

    }

    private void loadSettingsFragment() {

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new SettingsFragment())
                .disallowAddToBackStack().commit();
    }

}
