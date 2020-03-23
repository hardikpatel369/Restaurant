package com.nspl.restaurant.Fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

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

import com.nspl.restaurant.Activity.MenuActivity;
import com.nspl.restaurant.Activity.TablesActivity;
import com.nspl.restaurant.Adapter.CounterAdapter;
import com.nspl.restaurant.R;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Login.ClsLoginResponseData;
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
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.e("onViewCreated", "onViewCreated call");

        mBinding.pb.setVisibility(View.VISIBLE);
        mBinding.rv.setLayoutManager(new LinearLayoutManager(getContext()));
        mCounterAdapter = new CounterAdapter(getActivity());
        mBinding.rv.setAdapter(mCounterAdapter);

        loadAdapter();

        mBinding.swipeToRefresh.setOnRefreshListener(() -> {
            mBinding.swipeToRefresh.setRefreshing(true);
            loadAdapter();
        });

        mCounterAdapter.SetOnCounterClickListener((clsCounterData, position) -> {

            Log.e("--mode--", "_departmentID(counter): " + clsCounterData.getDEPARTMENTID());

            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("CounterData", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("departmentId", clsCounterData.getDEPARTMENTID());
            editor.putInt("CounterId", clsCounterData.getCOUNTERID());
            editor.putString("BranchId", clsCounterData.getbRANCHID());
            editor.putString("CounterType", clsCounterData.getCOUNTERTYPE());
            editor.apply();

            if (clsCounterData.getCOUNTERTYPE().equalsIgnoreCase("Restaurant"))
                startActivity(new Intent(getActivity(), TablesActivity.class));
            else
                startActivity(new Intent(getActivity(), MenuActivity.class));
        });
    }

    private void loadAdapter() {

        mCounterFragmentViewModel.getCountersResponse().observe(getViewLifecycleOwner(), clsCounterResponse -> {

            if (clsCounterResponse != null) {
                counterList = clsCounterResponse.getdATA();

                if (counterList.size() != 0) {
                    mCounterAdapter.AddItems(counterList);
                    mBinding.pb.setVisibility(View.GONE);
                    mBinding.swipeToRefresh.setRefreshing(false);
                }
            }
        });
    }
}
