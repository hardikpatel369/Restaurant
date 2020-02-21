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
    ClsWaitingList currentClick;
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
        personHolder.tvNo.setText("Waiting No : "+current.getWaitingID());
        personHolder.tvMobile.setText(""+current.getCustomerNo());
        personHolder.tvPerson.setText(""+current.getPersons());
        personHolder.tvName.setText(""+current.getCustomerName());
        personHolder.tvTime.setText(""+current.getExpectedWaitingTime()+" Min");
        personHolder.tvPreference.setText(""+current.getFoodType());

        personHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                currentClick = list.get(i);

              /*  PopupMenu popupMenu=new PopupMenu(v.getContext(), v);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    popupMenu.setGravity(Gravity.CENTER);
                }else {
                   // popupMenu.setGravity(Gravity.CENTER);
                }
                popupMenu.getMenuInflater().inflate(R.menu.waiting_item_click, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.action_done:
                                Toast.makeText(v.getContext(), "Done", Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.action_update:
                                Toast.makeText(v.getContext(), "Update", Toast.LENGTH_SHORT).show();
                                Fragment fragment = new WaitingFragment();

//                                FragmentManager.beginTransaction().replace(R.id.fragment_frame, fragment).commit();

                                return true;
                            case R.id.action_delete:
                                Toast.makeText(v.getContext(), "Delete", Toast.LENGTH_SHORT).show();
                                return true;
                        }
                        return false;
                    }
                });
                popupMenu.show();*/
                listener.onWaitingItemClick(currentClick);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    class PersonHolder extends RecyclerView.ViewHolder {
        TextView tvNo, tvMobile, tvPerson, tvName, tvTime, tvPreference;

        PersonHolder(@NonNull View itemView) {
            super(itemView);
            tvNo = itemView.findViewById(R.id.tvNo);
            tvMobile = itemView.findViewById(R.id.tvMobile);
            tvPerson = itemView.findViewById(R.id.tvPerson);
            tvName = itemView.findViewById(R.id.tvName);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvPreference = itemView.findViewById(R.id.tvPreference);
        }
    }
}
