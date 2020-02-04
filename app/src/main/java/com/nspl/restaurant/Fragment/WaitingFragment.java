package com.nspl.restaurant.Fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;


import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.chip.ChipGroup;
import com.nspl.restaurant.Adapter.WaitingPagerAdapter;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Waiting.ClsWaitingPerson;
import com.nspl.restaurant.R;
import com.nspl.restaurant.ViewModel.FragmentViewModel.WaitingFragmentViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class WaitingFragment extends Fragment /*implements WaitingPersonRecycalAdapter.OnItemClick*/ {
    //    FragmentWaitingBinding binding;
    // ListView lvPerson;
    ArrayList<ClsWaitingPerson> persons;
    View bgView;
    WaitingFragmentViewModel waitingFragmentViewModel;
    EditText edtMobile,CustomerName,etRequest;
    ChipGroup chipGroupPerson, choiceChipTimeGroup;
    String strChipPerson,strChipTime,strMobile,strCustomerName,strRequest,strPreference;
    RadioGroup rgPreference;
    RadioButton rbAny, rbVeg, rbNonVeg;
    ImageButton btnUpDown;
    WaitingPagerAdapter adapter;
    //private LinearLayout _bottomSheetLayout;
    List<ClsWaitingPerson> waitingPersonList = new ArrayList<>();


    public WaitingFragment() {
        // Required empty public constructor
    }
//app:layout_behavior="com.google.android.material.bottomsheet.BottomSheetBehavior"



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

//        waitingFragmentViewModel.getWaitingResponse().observe(this,clsWaitingResponse -> {
//            if( clsWaitingResponse != null){
////               waitingPersonList =clsWaitingResponse.
//                //TODO  panding Response
//            }
//        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_waiting, container, false);

   

        main(v);

        return v;
    }

    private void main(View view) {

        chipGroupPerson = view.findViewById(R.id.choice_chip_group);
        choiceChipTimeGroup = view.findViewById(R.id.choiceChipTimeGroup);


        rgPreference = view.findViewById(R.id.rgPreference);
        edtMobile = view.findViewById(R.id.edtMobile);
        CustomerName = view.findViewById(R.id.CustomerName);
        etRequest = view.findViewById(R.id.etRequest);
        rbAny = view.findViewById(R.id.rbAny);
        rbVeg = view.findViewById(R.id.rbVeg);
        rbNonVeg = view.findViewById(R.id.rbNonVeg);
        btnUpDown = view.findViewById(R.id.btnUpDown);

        bgView = view.findViewById(R.id.bgVisible);

      //  btn_dialog = view.findViewById(R.id.btn_dialog);

        rgPreference.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (rgPreference.getCheckedRadioButtonId()) {

                    case R.id.rbAny:
                        Toast.makeText(getContext(), rbAny.getText().toString(), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rbVeg:
                        Toast.makeText(getContext(), rbVeg.getText().toString(), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rbNonVeg:
                        Toast.makeText(getContext(), rbNonVeg.getText().toString(), Toast.LENGTH_SHORT).show();
                        break;

                }
            }
        });



        addChips(20);
        addTime(24);

//        binding.llRadio.findViewById(R.id.radio_group);

//        Collections.reverse(persons);
//        binding.rvPerson.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, true));


//        WaitingPersonRecycalAdapter adapter = new WaitingPersonRecycalAdapter(persons);
//        binding.rvPerson.setAdapter(adapter);


        //addRadioButtons(20);

//        binding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                Log.e("ckeck","checkedId:- "+checkedId);
//                // This will get the radiobutton that has changed in its check state
//                RadioButton checkedRadioButton = group.findViewById(checkedId);
//                // This puts the value (true/false) into the variable
//
//                // If the radiobutton that has changed in check state is now checked...
//                if (checkedRadioButton.isChecked())
//                {
//                    // Changes the textview's text to "Checked: example radiobutton text"
////                    tv.setText("Checked:" + checkedRadioButton.getText());
//                    Log.e("ckeck","get values:- "+checkedRadioButton.getText());
//                }
//            }
//        });
        btnUpDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomDialog();

                //_bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });


    }


    BottomSheetDialog mDialog;



    private void addTime(int num) {
        for (int row = 0; row < 1; row++) {

            int paddingDp = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, 10,
                    getResources().getDisplayMetrics()
            );
            Chip chipNone = new Chip(getActivity());
            chipNone.setText("None");
            chipNone.setId(0);
            chipNone.isClickable();
            chipNone.setCheckable(true);
            chipNone.isCheckedIconVisible();
//                chip.setPadding(paddingDp,paddingDp,paddingDp,paddingDp);
//                chip.setGravity(Gravity.CENTER);
            choiceChipTimeGroup.addView(chipNone);
          //getChip("None","T",0);
            for (int i = 1; i <= num; i++) {
                int time = i * 5;
                Chip chip = new Chip(getActivity());

                chip.setText(time + " Min");
                chip.setId(i);
                chip.isClickable();
                chip.setCheckable(true);

//                chip.setPadding(paddingDp,paddingDp,paddingDp,paddingDp);
//                chip.setGravity(Gravity.CENTER);
                choiceChipTimeGroup.addView(chip);
             //  getChip(String.valueOf(time),"T",i);
            }

            // chipGroupPerson.canScrollHorizontally(1);
            choiceChipTimeGroup.setSingleSelection(true);
            choiceChipTimeGroup.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(ChipGroup chipGroup, int i) {
                    Chip chip = chipGroup.findViewById(i);
                    if (chip != null) {
                        if (!chip.isChecked()) {
                            strChipTime = chip.getText().toString();//
                            Toast.makeText(getContext(), chip.getText() + "    E1", Toast.LENGTH_SHORT).show();
                        } else {
                            strChipTime = chip.getText().toString();//
                            Toast.makeText(getContext(), chip.getText() + "", Toast.LENGTH_SHORT).show();

                        }
                    } else {
                        Toast.makeText(getContext(), "null", Toast.LENGTH_SHORT).show();

                    }
                }
            });


            // ((ViewGroup) binding.llRadio.findViewById(R.id.radio_group)).addView(ll);
        }

    }



    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.clear(); // clears all menu items..
        getActivity().getMenuInflater().inflate(R.menu.logout, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.saveWaitingData){
            Toast.makeText(getContext(), "Save Data", Toast.LENGTH_SHORT).show();

        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("ResourceAsColor")
    public void addChips(int number) {
        for (int row = 0; row < 1; row++) {

            int paddingDp = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, 10,
                    getResources().getDisplayMetrics()
            );
           Chip chipNone = new Chip(getActivity());
            chipNone.setText("None");
            chipNone.setId(0);
            chipNone.isClickable();
            chipNone.setCheckable(true);
            chipNone.setClickable(true);
           // chipNone.hasSelection();
            //chipNone.isSelected();
//                chip.setPadding(paddingDp,paddingDp,paddingDp,paddingDp);
//                chip.setGravity(Gravity.CENTER);
            chipGroupPerson.addView(chipNone);
             // getChip("none","C",0);

            for (int i = 1; i <= number; i++) {
                Chip chip = new Chip(getActivity());
//
                chip.setText(i + "");
                chip.setId(i);
                chip.isClickable();
                chip.setCheckable(true);

//                chip.setBackgroundColor(R.color.black);
//                chip.setChipBackgroundColorResource(R.color.test);


                chipGroupPerson.addView(chip);
               // getChip(String.valueOf(i),"C",i);
            }

            // chipGroupPerson.canScrollHorizontally(1);
            chipGroupPerson.setSingleSelection(true);
            chipGroupPerson.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(ChipGroup chipGroup, int i) {
                    Chip chip = chipGroup.findViewById(i);
                    if (chip != null) {
                        if (!chip.isChecked()) {
                            strChipPerson = chip.getText().toString();//
                            Toast.makeText(getContext(), chip.getText() + "per", Toast.LENGTH_SHORT).show();
                        } else {
                            strChipPerson = chip.getText().toString();//
                            Toast.makeText(getContext(), chip.getText() + " Persons", Toast.LENGTH_SHORT).show();

                        }
                    } else {
                        Toast.makeText(getContext(), "null", Toast.LENGTH_SHORT).show();

                    }
                }
            });


            // ((ViewGroup) binding.llRadio.findViewById(R.id.radio_group)).addView(ll);
        }
    }

//    @SuppressLint("NewApi")
//    public void addRadioButtons(int number) {
//        for (int row = 0; row < 1; row++) {
//            RadioGroup ll = new RadioGroup(getActivity());
//            ll.setOrientation(LinearLayout.HORIZONTAL);
//            RadioButton rdbtnNone = new RadioButton(getActivity());
//            rdbtnNone.setId(0);
//            rdbtnNone.setText("None");
////            rdbtnNone.setLayoutParams(R.layout.persons);
//            ll.addView(rdbtnNone);
//            for (int i = 1; i <= number; i++) {
//                RadioButton rdbtn = new RadioButton(getActivity());
//                rdbtn.setId(View.generateViewId());
//                rdbtn.setText(String.valueOf(rdbtn.getId()));
//
//                ll.addView(rdbtn);
//            }
//            ((ViewGroup) binding.llRadio.findViewById(R.id.radio_group)).addView(ll);
//        }
//    }


    void bottomDialog() {
        View view = getLayoutInflater().inflate(R.layout.waiting_customer_bottom, null);
        mDialog = new BottomSheetDialog(getActivity());
        mDialog.setContentView(view);
        mDialog.setCancelable(true);
        mDialog.show();
    }

    @SuppressLint("ResourceAsColor")
    private Chip getChip(String text,String c,int i) {
        final Chip chip = new Chip(getActivity());
        chip.setCheckable(true);
        chip.setClickable(true);
        chip.setId(i);
        chip.setChipDrawable(ChipDrawable.createFromResource(getActivity(),
                R.xml.tag_chip));
//        chip.setBackgroundColor(R.color.colorPrimary);
//        chip.setChipBackgroundColorResource(R.color.colorPrimary);
        chip.setText(text);
        if(c.equals("T")){
            choiceChipTimeGroup.addView(chip);
        }else {
            chipGroupPerson.addView(chip);
        }
        return chip;
    }

}
