package com.nspl.restaurant.Fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Toast;

import com.nspl.restaurant.Adapter.WaitingPagerAdapter;
import com.nspl.restaurant.R;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class WaitingPagerFragment extends Fragment {
    private ArrayList<Fragment> fragments;
    ViewPager viewPager;
    View view1;

    public WaitingPagerFragment() {
        // Required empty public constructor
    }


//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        Toast.makeText(getContext(), "onCreate", Toast.LENGTH_SHORT).show();
//     //   getFragments();
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_wating_pager, container, false);
        viewPager = v.findViewById(R.id.view_pager);


        getFragments();

        return v;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

      //  Toast.makeText(getContext(), "onViewCreated", Toast.LENGTH_SHORT).show();


    }

    private void getFragments() {

        fragments = new ArrayList<>();
        fragments.add(new WaitingFragment());
        fragments.add(new WaitingListFragment());
        WaitingPagerAdapter adapter = new WaitingPagerAdapter(Objects.requireNonNull(getActivity()).getSupportFragmentManager());
        viewPager.setAdapter(adapter);
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        getFragments();
//        Toast.makeText(getContext(), "onResume", Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
////        getFragments(view1);
//        Toast.makeText(getContext(), "onStart", Toast.LENGTH_SHORT).show();
//
//    }
//
//    @Override
//    public void onAttach(@NonNull Context context) {
//        super.onAttach(context);
//        Toast.makeText(getContext(), "onAttach", Toast.LENGTH_SHORT).show();
//
//    }
//
//    @Override
//    public void onAttachFragment(@NonNull Fragment childFragment) {
//        super.onAttachFragment(childFragment);
//        Toast.makeText(getContext(), "onAttachFragment", Toast.LENGTH_SHORT).show();
//
//    }


}
