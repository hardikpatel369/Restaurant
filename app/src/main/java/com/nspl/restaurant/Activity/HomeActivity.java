package com.nspl.restaurant.Activity;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.nspl.restaurant.Fragment.CounterFragment;
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
                .add(R.id.container,fragment)
                .commit();
    }

    private void setupBottomNavigation() {

        mBinding.bottomNavigation.setOnNavigationItemSelectedListener(item -> {

            switch (item.getItemId()) {
                case R.id.action_home:
                    CounterFragment();
                    return true;
                case R.id.action_tracker:
                    loadTrackerFragment();
                    return true;
                case R.id.action_lead:
                    loadLeadFragment();
                    return true;
                case R.id.action_inquiry:
                    loadInquiryFragment();
                    return true;
                case R.id.action_settings:
                    loadSettingsFragment();
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
                .replace(R.id.fragment_frame, new CounterFragment())
                .disallowAddToBackStack().commit();

    }

    private void loadfranchiseFragment() {

//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.fragment_frame, new FranchiseFragment())
//                .disallowAddToBackStack().commit();

    }

    private void loadTrackerFragment() {


//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.fragment_frame, new TrackerFragment())
//                .disallowAddToBackStack().commit();

//        TrackerFragment fragment = TrackerFragment.newInstance();
//        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//        ft.replace(R.id.fragment_frame, fragment);
//        ft.commit();
    }

    private void loadLeadFragment() {


//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.fragment_frame, new DashboardFragment())
//                .disallowAddToBackStack().commit();

//        LeadListActivity fragment = LeadListActivity.newInstance();
//        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//        ft.replace(R.id.fragment_frame, fragment);
//        ft.commit();
    }

    private void loadInquiryFragment() {


//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.fragment_frame, new InquiryFragment())
//                .disallowAddToBackStack().commit();

//        InquiryFragment fragment = InquiryFragment.newInstance();
//        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//        ft.replace(R.id.fragment_frame, fragment);
//        ft.commit();
    }

    private void loadSettingsFragment() {


//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.fragment_frame, new SettingsFragment())
//                .disallowAddToBackStack().commit();


//        SettingsFragment fragment = SettingsFragment.newInstance();
//        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//        ft.replace(R.id.fragment_frame, fragment);
//        ft.commit();


    }

}
