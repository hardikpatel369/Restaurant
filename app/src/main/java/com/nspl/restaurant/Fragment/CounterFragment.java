package com.nspl.restaurant.Fragment;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;

import androidx.databinding.DataBindingUtil;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.nspl.restaurant.Activity.TablesActivity;
import com.nspl.restaurant.Adapter.CounterAdapter;
import com.nspl.restaurant.R;
import com.nspl.restaurant.RetrofitApi.ApiClasses.ClsLoginResponseData;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Counters.ClsCounterData;
import com.nspl.restaurant.ViewModel.FragmentViewModel.CounterFragmentViewModel;
import com.nspl.restaurant.databinding.FragmentCounterBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;

public class CounterFragment extends Fragment {

    private FragmentCounterBinding mBinding;
    private CounterFragmentViewModel mCounterFragmentViewModel;
    private List<ClsCounterData> counterList = new ArrayList<>();
    List<ClsLoginResponseData> clsLoginResponseDatas = new ArrayList<>();
    ClsLoginResponseData clsLoginResponseData;
    private CounterAdapter mCounterAdapter;

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

        initToolbar();
        return mBinding.getRoot();
    }

    private void initToolbar() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(mBinding.toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Main Counter");
//        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.e("onViewCreated", "onViewCreated call");

        mBinding.pb.setVisibility(View.VISIBLE);
        mBinding.rv.setLayoutManager(new LinearLayoutManager(getContext()));
        mCounterAdapter = new CounterAdapter(getActivity());
        mBinding.rv.setAdapter(mCounterAdapter);

//        obj = ClsGlobal.getUserInfo(getActivity());

        mCounterFragmentViewModel.getCountersResponse().observe(getViewLifecycleOwner(), clsCounterResponse -> {

            if (clsCounterResponse != null) {
                counterList = clsCounterResponse.getdATA();

                if (counterList.size() != 0) {
                    mCounterAdapter.AddItems(counterList);
                    mBinding.pb.setVisibility(View.GONE);
                }
            }
        });

        mCounterAdapter.SetOnCounterClickListener((clsCounterData, position) -> {

            Log.e("--mode--", "_departmentID(counter): " + clsCounterData.getDEPARTMENTID());

            SharedPreferences sharedPreferences=getActivity().getSharedPreferences("CounterData",MODE_PRIVATE);
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putInt("departmentId",clsCounterData.getDEPARTMENTID());
            editor.putInt("CounterId",clsCounterData.getCOUNTERID());
            editor.putString("BranchId",clsCounterData.getbRANCHID());
            editor.putString("CounterType",clsCounterData.getCOUNTERTYPE());
            editor.apply();

            startActivity(new Intent(getActivity(), TablesActivity.class));
        });

    }
}
