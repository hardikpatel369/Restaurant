package com.nspl.restaurant.Adapter;

import androidx.annotation.NonNull;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.nspl.restaurant.R;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Waiting.ClsWaitingList;

import java.util.ArrayList;
import java.util.List;

public class WaitingPersonRecycalAdapter extends RecyclerView.Adapter<WaitingPersonRecycalAdapter.PersonHolder> {

    Context context;
    private ClsWaitingList currentClick;
    public  interface  onWaitingItemClickListener{
        void onWaitingItemClick (ClsWaitingList clsWaitingList);
    }
    private  static onWaitingItemClickListener listener;
    private List<ClsWaitingList> list = new ArrayList<>();
    public WaitingPersonRecycalAdapter(Context context, onWaitingItemClickListener listener) {
        this.context = context;
        this.listener = listener;
    }

    public void AddItems(List<ClsWaitingList> list ){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PersonHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.persons, viewGroup, false);
        return new PersonHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull PersonHolder personHolder, int i) {
        //  String person = persons.get(i);
        ClsWaitingList current = list.get(i);
        Log.e("--URL--", "onBindViewHolder: "+current );
        personHolder.tvNo.setText("Waiting No: "+current.getWaitingNo());
        if(current.getCustomerNo()==null) {
            personHolder.tvMobile.setText("");
        }else {
            personHolder.tvMobile.setText("" + current.getCustomerNo());
        }
        if(current.getPersons()!=null) {
            personHolder.tvPerson.setText(""+current.getPersons());
        }else {
            personHolder.tvPerson.setText(0+"");

        }

        if(current.getCustomerName()!=null) {
            personHolder.tvName.setText(""+current.getCustomerName());

        }else {
            personHolder.tvName.setText("");

        }
        if(current.getExpectedWaitingTime()!=null) {
            personHolder.tvTime.setText(""+current.getExpectedWaitingTime()+" Min");

        }else {
            personHolder.tvTime.setText("0 Min");

        }
        if(current.getFoodType()!=null) {
            personHolder.tvPreference.setText(""+current.getFoodType());

        }else {
            personHolder.tvPreference.setText("");
        }
        if(current.getSpecialRequest()!=null) {
            personHolder.tvRequest.setText(""+current.getSpecialRequest());

        }else {
            personHolder.tvRequest.setText("");
        }

        personHolder.itemView.setOnClickListener(v -> {

            currentClick = list.get(i);
            listener.onWaitingItemClick(currentClick);
        });
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    class PersonHolder extends RecyclerView.ViewHolder {
        TextView tvNo, tvMobile, tvPerson, tvName, tvTime, tvPreference,tvRequest;

        PersonHolder(@NonNull View itemView) {
            super(itemView);
            tvNo = itemView.findViewById(R.id.tvNo);
            tvMobile = itemView.findViewById(R.id.tvMobile);
            tvPerson = itemView.findViewById(R.id.tvPerson);
            tvName = itemView.findViewById(R.id.tvName);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvPreference = itemView.findViewById(R.id.tvPreference);
            tvRequest = itemView.findViewById(R.id.tvRequest);
        }
    }
}
