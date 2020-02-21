package com.nspl.restaurant.Fragment;


import androidx.lifecycle.ViewModelProviders;
import android.content.Intent;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

public class CounterFragment extends Fragment {

    private FragmentCounterBinding mBinding;
    private CounterFragmentViewModel mCounterFragmentViewModel;
    private List<ClsCounterData> counterList = new ArrayList<>();
    List<ClsLoginResponseData> clsLoginResponseDatas = new ArrayList<>();
    ClsLoginResponseData clsLoginResponseData;
    private CounterAdapter mCounterAdapter;
//    private ClsUserInfo obj = new ClsUserInfo();


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

            startActivity(new Intent(getActivity(), TablesActivity.class)
                    .putExtra("Mode", clsCounterData.getCOUNTERTYPE())
                    .putExtra("CounterId", clsCounterData.getCOUNTERID())
                    .putExtra("BranchId", clsCounterData.getbRANCHID())
                    .putExtra("departmentId", clsCounterData.getDEPARTMENTID())
                    .putExtra("CounterType",clsCounterData.getCOUNTERTYPE()));
        });

    }
}
