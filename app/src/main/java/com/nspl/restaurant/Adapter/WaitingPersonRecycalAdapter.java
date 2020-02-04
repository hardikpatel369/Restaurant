package com.nspl.restaurant.Adapter;

import androidx.annotation.NonNull;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.nspl.restaurant.RetrofitApi.ApiClasses.Waiting.ClsWaitingPerson;
import com.nspl.restaurant.R;

import java.util.ArrayList;

public class WaitingPersonRecycalAdapter extends RecyclerView.Adapter<WaitingPersonRecycalAdapter.PersonHolder> {

    ArrayList<ClsWaitingPerson> waitingPeople;

    private boolean isSelected[];


    public interface OnItemClick {
        void onClick(String person);
    }

    OnItemClick listner;

    public WaitingPersonRecycalAdapter(ArrayList<ClsWaitingPerson> waitingPeople/*, OnItemClick listner*/) {
        this.waitingPeople = waitingPeople;
//        this.listner = listner;


      //  isSelected = new boolean[persons.size()];


    }

    @NonNull
    @Override
    public PersonHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.persons, viewGroup, false);
        PersonHolder viewHolder = new PersonHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PersonHolder personHolder, int i) {
      //  String person = persons.get(i);




    }

    @Override
    public int getItemCount() {

        return 0;//persons.size();
    }

    public class PersonHolder extends RecyclerView.ViewHolder {
        TextView tvNo,tvMobile,tvPerson,tvName,tvTime,tvPreference;

        public PersonHolder(@NonNull View itemView) {
            super(itemView);
            tvNo=itemView.findViewById(R.id.tvNo);
            tvMobile=itemView.findViewById(R.id.tvMobile);
            tvPerson=itemView.findViewById(R.id.tvPerson);
            tvName=itemView.findViewById(R.id.tvName);
            tvTime=itemView.findViewById(R.id.tvTime);
            tvPreference=itemView.findViewById(R.id.tvPreference);
        }
    }
}
