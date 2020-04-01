package com.nspl.restaurant.Fragment;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nspl.restaurant.Activity.OrderDetailActivity;
import com.nspl.restaurant.Adapter.ItemOrderDetailAdapter;
import com.nspl.restaurant.Global.ClsGlobal;
import com.nspl.restaurant.R;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Order.ClsOrderSummary;
import com.nspl.restaurant.ViewModel.ActivityViewModel.TablesActivityViewModel;

import java.util.ArrayList;
import java.util.List;

import java8.util.stream.Collectors;
import java8.util.stream.StreamSupport;

import static android.content.Context.MODE_PRIVATE;

public class BillOrderDetailFragment extends Fragment {

    private Toolbar toolbar;
    private ItemOrderDetailAdapter adapter;
    private Context context;
    private int orderId;
    private RecyclerView rvItemOrder;
    private TextView tvTotal;
    private double total = 0.0;
    private double addReturnReplace = 0.0;
    private double parcleCharge = 0.0;
    private double replace = 0.0;
    private List<ClsOrderSummary> summaryList = new ArrayList<>();
    private List<ClsOrderSummary> _listItemAddons = new ArrayList<>();
    private List<ClsOrderSummary> listItems = new ArrayList<>();
    ProgressBar pb;

    public BillOrderDetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bill_order_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SharedPreferences sp = getActivity().getSharedPreferences("CounterData", MODE_PRIVATE);
        orderId = sp.getInt("OrderId", 0);

        rvItemOrder = view.findViewById(R.id.rv_order_detail);
        tvTotal = view.findViewById(R.id.tvTotal);
        toolbar = view.findViewById(R.id.toolbar);
        pb = view.findViewById(R.id.pb);
        pb.setVisibility(View.VISIBLE);
        initToolbar();

        adapter = new ItemOrderDetailAdapter(context);
        rvItemOrder.setLayoutManager(new LinearLayoutManager(context));
        loadAdapter();

        adapter.SetOnOrderDetailClickListener((clsOrderSummary, position) -> {
//            Toast.makeText(getContext(),clsOrderSummary.getITEMNAME(), Toast.LENGTH_SHORT).show();
        });
    }

    private void loadAdapter() {
        TablesActivityViewModel mTablesActivityViewModel = ViewModelProviders.of(this).get(TablesActivityViewModel.class);

        mTablesActivityViewModel.getOrderSummaryResponse(orderId).observe(getActivity(), clsOrderSummaryResponse -> {

            if (clsOrderSummaryResponse != null) {
                summaryList = clsOrderSummaryResponse.getDATA();

                if (summaryList.size() != 0) {
                    listItems = StreamSupport.stream(summaryList)
                            .filter(s -> s.getISADDON().equals(false))
                            .collect(Collectors.toList());

                    Gson gson = new Gson();
                    String jsonInString = gson.toJson(listItems);
                    Log.e("Bill", "Item---" + jsonInString);

                    for (ClsOrderSummary _ObjItem : listItems) {

                        _listItemAddons = StreamSupport.stream(summaryList)
                                .filter(s -> s.getISADDON().equals(true) && s.getREFRANCEITEMID().equals(_ObjItem.getREFRANCEITEMID()))
                                .collect(Collectors.toList());

                        _ObjItem.setListAddons(_listItemAddons);
                        listItems.set(listItems.indexOf(_ObjItem), _ObjItem);

                        Gson gson1 = new Gson();
                        String jsonInString1 = gson1.toJson(_listItemAddons);
                        Log.e("Bill", "Addon---" + jsonInString1);
                    }

                    for (int i = 0; i < summaryList.size(); i++) {
                        total += summaryList.get(i).getTOTALAMOUNT();

                        if (summaryList.get(i).getPARCELCHARGES() != null) {
                            parcleCharge += summaryList.get(i).getPARCELCHARGES();
                            Log.d("Bill", "parcleCharge: " + parcleCharge);
                        }

                        if (summaryList.get(i).getSTATUS().equalsIgnoreCase("RETURN") ||
                                summaryList.get(i).getSTATUS().equalsIgnoreCase("REPLACE")) {
                            replace += summaryList.get(i).getTOTALAMOUNT();
                            Log.d("Bill", "replace: " + replace);
                        }

                        if (summaryList.get(i).getISSTATUSCHECK() != null &&
                                summaryList.get(i).getISSTATUSCHECK().equals(true)) {
                            addReturnReplace += summaryList.get(i).getTOTALAMOUNT();
                            Log.d("Bill", "addReturnReplace: " + addReturnReplace);
                        }

                        Log.d("Bill", "loadAdapter: " + summaryList.get(i).getTOTALAMOUNT());
                    }

                    List<ClsOrderSummary> listItemsAddReturnReplace = new ArrayList<>();
                    for (ClsOrderSummary _ObjItem : listItems) {
                        if (_ObjItem.getSTATUS().equalsIgnoreCase("PENDING")
                                || _ObjItem.getSTATUS().equalsIgnoreCase("CONFIRM PENDING")
                                || _ObjItem.getSTATUS().equalsIgnoreCase("COMPLETE")
                        ) {
                            listItemsAddReturnReplace.add(_ObjItem);
                        }else if (_ObjItem.getISSTATUSCHECK() != null &&
                                _ObjItem.getISSTATUSCHECK().equals(true)){
                            listItemsAddReturnReplace.add(_ObjItem);
                        }
                    }
                    Add();

                    adapter.addOrderDetail(listItemsAddReturnReplace, "BillOrderDetailFragment");
                    rvItemOrder.setAdapter(adapter);
                    pb.setVisibility(View.GONE);
                }
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void Add() {
        double grandTotal = total + parcleCharge - replace + addReturnReplace;
        Log.d("replaceReturn", "grandTotal: ");
        tvTotal.setText("Bill : " + ClsGlobal.round(grandTotal, 2));
    }

    private void initToolbar() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Order Detail");
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent1 = new Intent(getActivity(), OrderDetailActivity.class);
                startActivity(intent1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
