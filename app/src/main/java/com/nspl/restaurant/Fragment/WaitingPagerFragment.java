package com.nspl.restaurant.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nspl.restaurant.Adapter.WaitingPagerAdapter;
import com.nspl.restaurant.R;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Waiting.ClsWaitingList;


import java.util.ArrayList;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class WaitingPagerFragment extends Fragment {
    private ArrayList<Fragment> fragments;
       public static ViewPager viewPager;
    public  static ClsWaitingList waitingObj;

    public WaitingPagerFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_wating_pager, container, false);
        viewPager = v.findViewById(R.id.view_pager);


        getFragments();
        getData();

        return v;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    private void getFragments() {

        fragments = new ArrayList<>();
        fragments.add(new WaitingFragment());
        fragments.add(new WaitingListFragment());

        WaitingPagerAdapter adapter = new WaitingPagerAdapter(
                Objects.requireNonNull(getActivity()).getSupportFragmentManager());
        viewPager.setAdapter(adapter);
    }

    public  ClsWaitingList  getData(){
        Bundle bundle= getArguments();
        waitingObj= new ClsWaitingList();
        if(bundle!=null){
            waitingObj.setWaitingID(bundle.getInt("WaitingID"));
            waitingObj.setWaitingNo(bundle.getInt("WaitingNo"));
            waitingObj.setCustomerNo(bundle.getString("CustomerNo"));

            waitingObj.setPersons(bundle.getInt("Persons"));
            waitingObj.setSpecialRequest(bundle.getString("SpecialRequest"));
            waitingObj.setCustomerName(bundle.getString("CustomerName"));
            waitingObj.setExpectedWaitingTime(bundle.getInt("ExpectedWaitingTime"));
            waitingObj.setFoodType(bundle.getString("FoodType"));
            return  waitingObj;
        }else {

            waitingObj=null;
            return  waitingObj;

        }
    }

}
