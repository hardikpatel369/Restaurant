package com.nspl.restaurant.Fragment;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nspl.restaurant.Activity.TablesActivity;
import com.nspl.restaurant.Adapter.CounterAdapter;
import com.nspl.restaurant.DataModel.ClsUserInfo;
import com.nspl.restaurant.Global.ClsGlobal;
import com.nspl.restaurant.R;
import com.nspl.restaurant.RetrofitApi.ApiClasses.ClsLoginResponseData;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Counters.ClsCounterData;
import com.nspl.restaurant.ViewModel.FragmentViewModel.CounterFragmentViewModel;
import com.nspl.restaurant.databinding.FragmentCounterBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CounterFragment extends Fragment {

    FragmentCounterBinding mBinding;
    CounterFragmentViewModel mCounterFragmentViewModel;
    List<ClsCounterData> counterList = new ArrayList<>();
    List<ClsLoginResponseData> clsLoginResponseDatas = new ArrayList<>();
    ClsLoginResponseData clsLoginResponseData;
    CounterAdapter mCounterAdapter;
    ClsUserInfo obj = new ClsUserInfo();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.e("onCreate", "onCreate call");
        mCounterFragmentViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity()))
                .get(CounterFragmentViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_counter, container, false);

        // Inflate the layout for this fragment
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.e("onViewCreated", "onViewCreated call");

        mBinding.rv.setLayoutManager(new LinearLayoutManager(getContext()));
        mCounterAdapter = new CounterAdapter(getActivity());
        mBinding.rv.setAdapter(mCounterAdapter);

        obj = ClsGlobal.getUserInfo(getActivity());

        mCounterFragmentViewModel.getCountersResponse().observe(this, clsCounterResponse -> {

            if (clsCounterResponse != null) {
                counterList = clsCounterResponse.getdATA();

                if (counterList.size() != 0) {
                    mCounterAdapter.AddItems(counterList);
                }
            }
        });

        mCounterAdapter.SetOnCounterClickListener((clsCounterData, position) -> {



            Log.e("--mode--", "_departmentID: " + clsCounterData.getDEPARTMENTID());

            startActivity(new Intent(getActivity(), TablesActivity.class)
                    .putExtra("Mode", clsCounterData.getCOUNTERTYPE())
                    .putExtra("CounterId", clsCounterData.getCOUNTERID())
                    .putExtra("BranchId", obj.getBRANCH_ID())
                    .putExtra("departmentId", clsCounterData.getDEPARTMENTID()));
        });

    }
}
