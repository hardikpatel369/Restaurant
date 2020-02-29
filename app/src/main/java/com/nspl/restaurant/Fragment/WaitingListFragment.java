package com.nspl.restaurant.Fragment;


import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nspl.restaurant.Adapter.WaitingPersonRecycalAdapter;
import com.nspl.restaurant.R;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Waiting.ClsWaitingList;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Waiting.ClsWaitingResponse;
import com.nspl.restaurant.ViewModel.FragmentViewModel.WaitingFragmentViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class WaitingListFragment extends Fragment implements WaitingPersonRecycalAdapter.onWaitingItemClickListener {
    private Context context;
    public static WaitingPersonRecycalAdapter adapter;
    private WaitingFragmentViewModel waitingFragmentViewModel;
    private List<ClsWaitingList> waitingList = new ArrayList<>();
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private Dialog mDialog;
    private Toolbar toolbar;
    private TextView tvNoRecord;
    private ProgressDialog pd;

    public WaitingListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_waiting_list, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar = view.findViewById(R.id.toolbar);
//        subTitle="Total Waiting : "+waitingList.size();
        initToolbar();
        RecyclerView rvWaiting = view.findViewById(R.id.rvWaiting);

        tvNoRecord = view.findViewById(R.id.tvNoRecord);
        tvNoRecord.setVisibility(View.GONE);
        if(waitingList.size()==0){
            tvNoRecord.setVisibility(View.VISIBLE);
        }
        pd = new ProgressDialog(getActivity());
        pd.setMessage("loading...");



        mSwipeRefreshLayout = view.findViewById(R.id.swipeToRefresh);

        rvWaiting.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        adapter = new WaitingPersonRecycalAdapter(getActivity(), this);
        rvWaiting.setAdapter(adapter);

        dataCalling();
        swipeRefresh();
        WaitingPagerFragment.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int state) {}
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            public void onPageSelected(int position) {
                if(position==1){
                    refresh();
                }
            }
        });

    }

    private void swipeRefresh() {
        mSwipeRefreshLayout.setOnRefreshListener(() -> refresh());
    }


    private void initToolbar() {
        toolbar.setNavigationIcon(R.drawable.ic_alarm_black_24dp);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Waiting List");
        ((AppCompatActivity) getActivity()).getSupportActionBar().setSubtitle("Total Waiting : "+waitingList.size());
    }

    private void refresh() {
        dataCalling();
        adapter.notifyDataSetChanged();
        initToolbar();
        pd.dismiss();
        mSwipeRefreshLayout.setRefreshing(false);
    }

    public void waitingOver(int waitingId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Done ?");
        builder.setMessage("Is customer's waiting over ?");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", (dialogInterface, i) -> completeFromWebWaiting(waitingId));
        builder.setNegativeButton("No", (dialogInterface, i) -> {
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void completeFromWebWaiting(int waitingId) {
        pd.show();
        ClsWaitingResponse clsWaitingResponse = new ClsWaitingResponse();
        clsWaitingResponse.setWaitingID(waitingId);
        clsWaitingResponse.setMode("COMPLETE");

        Gson gson = new Gson();
        String jsonInString = gson.toJson(clsWaitingResponse);
        Log.e("--Waiting--", "GsonObj from complete Waiting SUCCESS : " + jsonInString);


        waitingFragmentViewModel.completeWaiting(clsWaitingResponse).observe(getActivity(), clsWaitingResponse1 -> {
            if (clsWaitingResponse1 != null) {
                Toast.makeText(getContext(), clsWaitingResponse1.getMESSAGE(), Toast.LENGTH_SHORT).show();
                if (clsWaitingResponse1.getMESSAGE().equals("SUCCESS.")) {
                    refresh();
                    initToolbar();
                } else {
                    Toast.makeText(getContext(), clsWaitingResponse1.getMESSAGE(), Toast.LENGTH_SHORT).show();
                    refresh();
                }
            } else {
                Toast.makeText(getContext(), clsWaitingResponse1.getMESSAGE(), Toast.LENGTH_SHORT).show();
                refresh();
            }
        });
    }

    private void deleteWaiting(int waitingId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Delete ?");
        builder.setMessage("Are you sure you want to Delete ?");/*customer waiting from list */
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", (dialogInterface, i) -> {
            deleteFromWebWaiting(waitingId);

        });
        builder.setNegativeButton("No", (dialogInterface, i) -> {
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void deleteFromWebWaiting(int waitingId) {

        pd.show();
        ClsWaitingResponse clsWaitingResponse = new ClsWaitingResponse();
        clsWaitingResponse.setWaitingID(waitingId);
        clsWaitingResponse.setMode("DELETE");

        Gson gson = new Gson();
        String jsonInString = gson.toJson(clsWaitingResponse);
        Log.e("--Waiting--", "GsonObj from insertWaiting SUCCESS : " + jsonInString);

        waitingFragmentViewModel.deleteWaiting(clsWaitingResponse).observe(getActivity(), clsWaitingResponse1 -> {
            if (clsWaitingResponse1 != null) {
                Toast.makeText(getContext(), clsWaitingResponse1.getMESSAGE(), Toast.LENGTH_SHORT).show();
                if (clsWaitingResponse1.getMESSAGE().equals("SUCCESS.")) {
                    refresh();
                    initToolbar();
                    if(waitingList.isEmpty()) {
                        WaitingFragment.llWaitingBottomDialog.setVisibility(View.GONE);
                        initToolbar();
                    }
                } else {
                    Toast.makeText(getContext(), clsWaitingResponse1.getMESSAGE(), Toast.LENGTH_SHORT).show();
                    refresh();
                }
            } else {
                Toast.makeText(getContext(), clsWaitingResponse1.getMESSAGE(), Toast.LENGTH_SHORT).show();
                refresh();
            }
            Log.e("--URL--", "GsonObj from insertWaiting : " + jsonInString);
        });
    }


    private void dataCalling() {
        waitingFragmentViewModel = ViewModelProviders.of(this)
                .get(WaitingFragmentViewModel.class);

        waitingFragmentViewModel.getWaitingResponse(1).observe(Objects.requireNonNull(getActivity()), clsWaitingResponse -> {

            if (clsWaitingResponse != null) {
                waitingList = clsWaitingResponse.getDATA();
                try {
                    if (waitingList.size() != 0) {
                        addList();
                        tvNoRecord.setVisibility(View.GONE);
                    } else {
                        addList();
                        tvNoRecord.setVisibility(View.VISIBLE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                addList();
            }
        });
    }

    private void addList() {
        adapter.AddItems(waitingList);
        mSwipeRefreshLayout.setRefreshing(false);
        refresh();
    }

    @Override
    public void onWaitingItemClick(ClsWaitingList objWaiting) {
        mDialog = new Dialog(getActivity());

        if ( mDialog.isShowing()) return;//mDialog != null &&
        mDialog = new Dialog(getActivity());

        mDialog.setContentView(R.layout.dialog_waiting_item_click);
        mDialog.setCanceledOnTouchOutside(true);
        Objects.requireNonNull(mDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        mDialog.setCancelable(true);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(Objects.requireNonNull(mDialog.getWindow()).getAttributes());
        lp.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        mDialog.getWindow().setAttributes(lp);

        TextView tvDone, tvUpdate, tvDelete, tvWaitingId;
        tvDone = mDialog.findViewById(R.id.tvDone);
        tvUpdate = mDialog.findViewById(R.id.tvUpdate);
        tvDelete = mDialog.findViewById(R.id.tvDelete);
        tvWaitingId = mDialog.findViewById(R.id.tvWaitingId);
        tvWaitingId.setText("Waiting No. "+objWaiting.getWaitingNo());


        tvDone.setOnClickListener(v -> {
            mDialog.dismiss();
            waitingOver(objWaiting.getWaitingID());
        });

        tvUpdate.setOnClickListener(v -> {
            Fragment fragment = new WaitingPagerFragment();

            Bundle bundle = new Bundle();
            bundle.putInt("WaitingID", objWaiting.getWaitingID());
            bundle.putInt("WaitingNo", objWaiting.getWaitingNo());
            if(objWaiting.getCustomerNo()!=null)
                bundle.putString("CustomerNo", objWaiting.getCustomerNo());
            bundle.putString("SpecialRequest", objWaiting.getSpecialRequest());
            if(objWaiting.getPersons()!=null) {
                bundle.putInt("Persons", objWaiting.getPersons());
            }else {
                bundle.putInt("Persons", 0);
            }
            bundle.putString("CustomerName", objWaiting.getCustomerName());
            if(objWaiting.getExpectedWaitingTime()!=null)
                bundle.putInt("ExpectedWaitingTime", objWaiting.getExpectedWaitingTime());
            bundle.putString("FoodType", objWaiting.getFoodType());
            fragment.setArguments(bundle);
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_frame,
                    fragment).commit();
            WaitingFragment.saveOrUpdate=2;

            mDialog.dismiss();
        });
        tvDelete.setOnClickListener(v -> {
            mDialog.dismiss();
            deleteWaiting(objWaiting.getWaitingID());
        });
        mDialog.show();

    }

}
