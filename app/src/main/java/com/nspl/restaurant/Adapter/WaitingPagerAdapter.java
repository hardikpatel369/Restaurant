package com.nspl.restaurant.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.nspl.restaurant.Fragment.WaitingFragment;
import com.nspl.restaurant.Fragment.WaitingListFragment;

public class WaitingPagerAdapter extends FragmentStatePagerAdapter {
//    private ArrayList<Fragment> fragments;

    /*public WaitingPagerAdapter(@NonNull FragmentManager fm, int behavior,ArrayList<Fragment> fragments) {
        super(fm, behavior);
        this.fragments=fragments;

    }

    */
    private Fragment[] childFragments;

    public WaitingPagerAdapter(FragmentManager fm) {
        super(fm);
        childFragments = new Fragment[]{
                new WaitingFragment(), //0
                new WaitingListFragment() //1//2
        };
    }


//    public WaitingPagerAdapter(FragmentManager manager,ArrayList<Fragment> fragments) {
//
//        super(manager);
//        this.fragments=fragments;
//    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        return childFragments[position];
    }

    @Override
    public int getCount() {
        return childFragments.length;
    }

//    public void addFragment(Fragment fragment) {
//        fragments.add(fragment);
//    }


}
