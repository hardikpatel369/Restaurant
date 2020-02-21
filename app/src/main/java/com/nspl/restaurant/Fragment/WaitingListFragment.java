package com.nspl.restaurant.Fragment;


import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.nspl.restaurant.Adapter.WaitingPersonRecycalAdapter;
import com.nspl.restaurant.R;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Waiting.ClsWaitingList;
import com.nspl.restaurant.ViewModel.FragmentViewModel.WaitingFragmentViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class WaitingListFragment extends Fragment implements WaitingPersonRecycalAdapter.onWaitingItemClickListener {
    private Context context;
    private WaitingPersonRecycalAdapter adapter;
    private WaitingFragmentViewModel waitingFragmentViewModel;
    private List<ClsWaitingList> waitingList = new ArrayList<>();
    private MenuItem item1;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private Dialog mDialog;
    public static  ClsWaitingList clsWaitingList2=new ClsWaitingList();
    public WaitingListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_waiting_list, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView rvWaiting = view.findViewById(R.id.rvWaiting);
        try {
            if(WaitingFragment.item1.isVisible()){
                WaitingFragment.item1.setVisible(false);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        mSwipeRefreshLayout = view.findViewById(R.id.swipeToRefresh);
        setHasOptionsMenu(true);
//        item1 = view.findViewById(R.id.refresh);

        rvWaiting.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
        adapter = new WaitingPersonRecycalAdapter(getActivity(),this);
        rvWaiting.setAdapter(adapter);

        dataCalling();
        // mSwipeRefreshLayout.setOnRefreshListener(this);
      mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
          @Override
          public void onRefresh() {
                        dataCalling();
                        adapter.notifyDataSetChanged();
                          mSwipeRefreshLayout.setRefreshing(false);

                  }
      });

    }

    private void dataCalling() {
        waitingFragmentViewModel =  ViewModelProviders.of(this)
                .get(WaitingFragmentViewModel.class);


        waitingFragmentViewModel.getWaitingResponse(0).observe(Objects.requireNonNull(getActivity()), clsWaitingResponse -> {

            Log.e("--URL--", "onViewCreated: "+clsWaitingResponse);
            if(clsWaitingResponse != null){
                waitingList = clsWaitingResponse.getDATA();
                try {
                    if (waitingList.size() !=0){
                        adapter.AddItems(waitingList);
                    }
                }catch (Exception e){

                }

            }

        });
    }

    @Override
    public void onWaitingItemClick(ClsWaitingList clsWaitingList) {
        mDialog =new Dialog(getActivity());
        clsWaitingList2=clsWaitingList;
        //Log.e("--clsWaitingList2--", "after: " +clsWaitingList2 );

        if (mDialog != null && mDialog.isShowing()) return;
        mDialog = new Dialog(getActivity());

        mDialog.setContentView(R.layout.dialog_waiting_item_click);
        mDialog.setCanceledOnTouchOutside(true);
        Objects.requireNonNull(mDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        mDialog.setCancelable(true);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(Objects.requireNonNull(mDialog.getWindow()).getAttributes());
        lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
        mDialog.getWindow().setAttributes(lp);

        TextView tvDone,tvUpdate,tvDelete;
        tvDone=mDialog.findViewById(R.id.tvDone);
        tvUpdate=mDialog.findViewById(R.id.tvUpdate);
        tvDelete=mDialog.findViewById(R.id.tvDelete);

        tvDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Done", Toast.LENGTH_SHORT).show();
                mDialog.dismiss();
            }
        });
        tvUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(v.getContext(), "Update", Toast.LENGTH_SHORT).show();
                //clsWaitingList2=clsWaitingList;

//                Log.e("--clsWaitingList2--", "after list frgment: " +clsWaitingList.getFoodType());

              //  waitingFragmentViewModel.setDate(clsWaitingList);

                WaitingPagerFragment.viewPager.setCurrentItem(0, true);

              // WaitingFragment waitingFragment = new WaitingFragment();
               // waitingFragment.setCls(clsWaitingList);
                /* Fragment fragment = new WaitingFragment();

                Bundle bundle=new Bundle();

                bundle.putInt("WaitingID",clsWaitingList.getWaitingID());
                bundle.putString("CustomerNo",clsWaitingList.getCustomerNo());
                bundle.putInt("Persons",clsWaitingList.getPersons());
                bundle.putString("CustomerName",clsWaitingList.getCustomerName());
                bundle.putInt("ExpectedWaitingTime",clsWaitingList.getExpectedWaitingTime());
                bundle.putString("FoodType",clsWaitingList.getFoodType());
                fragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.view_pager,
                fragment).commit();

*/
                mDialog.dismiss();
            }
        });
        tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Delete", Toast.LENGTH_SHORT).show();
                mDialog.dismiss();
            }
        });
        mDialog.show();

    }


//    @Override
//    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
//        menu.clear(); // clears all menu items..
//        getActivity().getMenuInflater().inflate(R.menu.refresh, menu);
//        super.onCreateOptionsMenu(menu, inflater);
//        item1=menu.findItem(R.id.refresh);
//        item1.setVisible(true);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//
//        //adapter.notifyItemInserted(waitingList.size()-1);
//        adapter.notifyDataSetChanged();
//        Toast.makeText(getContext(), "Refresh", Toast.LENGTH_SHORT).show();
//        return super.onOptionsItemSelected(item);
//    }


}
