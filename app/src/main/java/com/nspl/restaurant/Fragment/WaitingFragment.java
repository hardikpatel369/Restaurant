package com.nspl.restaurant.Fragment;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.chip.ChipGroup;
import com.google.gson.Gson;
import com.nspl.restaurant.Global.ClsGlobal;
import com.nspl.restaurant.Global.Repository;
import com.nspl.restaurant.R;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Waiting.ClsWaitingList;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Waiting.ClsWaitingResponse;
import com.nspl.restaurant.ViewModel.FragmentViewModel.WaitingFragmentViewModel;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class WaitingFragment extends Fragment /*implements WaitingPersonRecycalAdapter.OnItemClick*/ {

    Repository mRepository;
    public static MenuItem item1;
    ProgressBar progressBar;
    final String MY_PREF_FILE = "user_details";
    SharedPreferences sharedPreferences;
    private static final String SPLoginDetails = "LoginDetails";
    Chip chipPersonNone, chipTimeNone;
    View bgView;
    TextView tvNo, tvMobile, tvPerson, tvName, tvTime, tvPreference;
    private List<ClsWaitingList> waitingList = new ArrayList<>();
    private Button btnNext;
    WaitingFragmentViewModel waitingFragmentViewModel;
    EditText etMobile, etCustomerName, etRequest;
    ChipGroup chipGroupPerson, choiceChipTimeGroup;
    //  String  strMobile="", strCustomerName="",  strRequest="",  employeeName="", employeeCode="", mode="";
    int strChipTime = 0, branchID = 0, intChipPerson = 0;
    RadioGroup rgPreference;
    RadioButton rbAny, rbVeg, rbNonVeg;
    int waitingPosition;
    ClsWaitingList clsWaitingList;
    //  ImageButton btnUpDown;
    // View v;
    public static View view1;

    public WaitingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Toast.makeText(getContext(), "onAttach", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        Toast.makeText(getContext(), "onCreate", Toast.LENGTH_SHORT).show();
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
        Toast.makeText(getContext(), "onCreateView", Toast.LENGTH_SHORT).show();
        View v = inflater.inflate(R.layout.fragment_waiting, container, false);
        waitingFragmentViewModel = new ViewModelProvider(this)
                .get(WaitingFragmentViewModel.class);

        main(v);
        progressBar.setVisibility(View.GONE);

        return v;
    }



  /*  @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Toast.makeText(getContext(), "onViewCreated", Toast.LENGTH_SHORT).show();
         view1=view;
        main(view);
        progressBar.setVisibility(View.GONE);
    }*/

    @Override
    public void onStart() {
        super.onStart();
        Toast.makeText(getContext(), "onStart", Toast.LENGTH_SHORT).show();

    }


    public  void setCls(ClsWaitingList clsWaitingList) {
        this.clsWaitingList = clsWaitingList;

        Gson gson = new Gson();
        String jsonInString = gson.toJson(clsWaitingList);
        Log.e("--gson--", "clsWaitingList wf: " + jsonInString);

        // CharSequence[] cs = String[] {"String to CharSequence"};
        // CharSequence cs = clsWaitingList.getCustomerNo();
        etMobile.setText(clsWaitingList.getCustomerNo());

//        setUpdateData();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Toast.makeText(getContext(), "onViewCreated", Toast.LENGTH_SHORT).show();

    }

    private void main(View view) {

        try {
            if (!WaitingFragment.item1.isVisible()) {
                WaitingFragment.item1.setVisible(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        chipGroupPerson = view.findViewById(R.id.choice_chip_group);
        choiceChipTimeGroup = view.findViewById(R.id.choiceChipTimeGroup);

        item1 = view.findViewById(R.id.saveWaitingData);
        rgPreference = view.findViewById(R.id.rgPreference);
        etMobile = view.findViewById(R.id.etMobile);

/*

        if (clsWaitingList != null) {
            // Log.e("--clsWaitingList2--", "after food type: " +clsWaitingList.getFoodType());
//            main(v);


            if (clsWaitingList.getCustomerNo() != null) {
                etMobile.setText("".concat(clsWaitingList.getCustomerNo()));
            }
        }

*/
        if (clsWaitingList != null) {

            Log.e("--clsWaitingList2--", "getCustomerNo: " + clsWaitingList.getCustomerNo());



        }

        etCustomerName = view.findViewById(R.id.CustomerName);
        etRequest = view.findViewById(R.id.etRequest);
        rbAny = view.findViewById(R.id.rbAny);
        rbVeg = view.findViewById(R.id.rbVeg);
        rbNonVeg = view.findViewById(R.id.rbNonVeg);
        //  btnUpDown = view.findViewById(R.id.btnUpDown);
        progressBar = view.findViewById(R.id.progressBar);
        bgView = view.findViewById(R.id.bgVisible);
        btnNext = view.findViewById(R.id.btnNext);
        sharedPreferences = getContext().getSharedPreferences(MY_PREF_FILE, getContext().MODE_PRIVATE);
        tvNo = view.findViewById(R.id.tvNo);
        tvMobile = view.findViewById(R.id.tvMobile);
        tvPerson = view.findViewById(R.id.tvPerson);
        tvName = view.findViewById(R.id.tvName);
        tvTime = view.findViewById(R.id.tvTime);
        tvPreference = view.findViewById(R.id.tvPreference);
        //  btn_dialog = view.findViewById(R.id.btn_dialog);

        waitingFragmentViewModel = ViewModelProviders.of(this).get(WaitingFragmentViewModel.class);


        waitingFragmentViewModel.getWaitingResponse(0).observe(Objects.requireNonNull(getActivity()), clsWaitingResponse -> {

            waitingPosition = sharedPreferences.getInt("waitingPosition", 0);

            Log.e("--URL--", "onViewCreated: " + clsWaitingResponse);
            if (clsWaitingResponse != null) {
                waitingList = clsWaitingResponse.getDATA();
                setWaitingDate();
            }
        });
        addChipsPerson(20);
        addChipsTime(24);
        chipTimeNone.setChecked(true);
        chipPersonNone.setChecked(true);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                next();
                WaitingPagerFragment.viewPager.setCurrentItem(1, true);


            }
        });

        //Bundle bundle= getArguments();
        // tvNo.setText(""+bundle.getInt("WaitingID"));
        Log.e("--clsWaitingList2--", "befor: " + WaitingListFragment.clsWaitingList2);


        // Toast.makeText(getContext(), String.valueOf(bundle.getInt("Persons")), Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onResume() {
        super.onResume();
        //setUpdateData();
        Toast.makeText(getContext(), "onResume", Toast.LENGTH_SHORT).show();

    }

/*

    @SuppressLint("SetTextI18n")
    private void setUpdateData() {
        if (clsWaitingList != null) {
            // Log.e("--clsWaitingList2--", "after food type: " +clsWaitingList.getFoodType());
            // Log.e("--clsWaitingList2--", "after Customer no: " +clsWaitingList.getCustomerNo());
            main(v);


            if (clsWaitingList.getCustomerNo() != null) {
                etMobile.setText("".concat(clsWaitingList.getCustomerNo()));
            }
            //etCustomerName.setText("".concat(clsWaitingList.getCustomerName()));
//         chipGroupPerson.findViewById((WaitingListFragment.clsWaitingList2.);
            //chipGroupPerson.check(WaitingListFragment.clsWaitingList2.getPersons());
            //  String food = (clsWaitingList.getFoodType());
            // Toast.makeText(getActivity(), clsWaitingList.getFoodType(), Toast.LENGTH_SHORT).show();
//            if (food.equals("Any")) {
//                rbAny.setChecked(true);
//            } else if (food.equals("Veg")) {
//                rbVeg.setChecked(true);
//            } else {
//              //  rbNonVeg.setChecked(true);
//            }

//            int timeIndex = (clsWaitingList.getExpectedWaitingTime() / 5);
//            choiceChipTimeGroup.check(timeIndex);
        } else {
            Toast.makeText(getContext(), "null ogj", Toast.LENGTH_SHORT).show();
        }
    }

*/

    private void next() {
        if (waitingList.size() != 0) {
            waitingPosition = sharedPreferences.getInt("waitingPosition", 0);
            waitingPosition = waitingPosition + 1;

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("waitingPosition", waitingPosition);
            editor.commit();


            if (waitingPosition < waitingList.size()) {
                ClsWaitingList clsWaitingList = waitingList.get(waitingPosition);

                tvNo.setText("Waiting No : " + clsWaitingList.getWaitingID());
                tvMobile.setText("" + clsWaitingList.getCustomerNo());
                tvPerson.setText("" + clsWaitingList.getPersons());
                tvName.setText("" + clsWaitingList.getCustomerName());
                tvTime.setText("" + clsWaitingList.getExpectedWaitingTime());
                tvPreference.setText("" + clsWaitingList.getFoodType());

            } else {
                Toast.makeText(getContext(), "Waiting list was over", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void closeKeyBoard() {

        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager iMM = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            iMM.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    private boolean LOGINVALIDATION() {

        boolean result = true;

        if (!ClsGlobal.CheckInternetConnection(getContext())) {
            Toast.makeText(getContext(), getString(R.string.NoInternet),
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        return result;
    }

    BottomSheetDialog mDialog;


    private void addChipsTime(int num) {

        for (int row = 0; row < 1; row++) {
            chipTimeNone = new Chip(getActivity());
            chipTimeNone.setText("None");
            chipTimeNone.setId(0);
            chipTimeNone.isClickable();
            chipTimeNone.setCheckable(true);

            chipTimeNone.setChipDrawable(ChipDrawable.createFromResource(getActivity(),
                    R.xml.tag_chip));
            if (chipTimeNone.isCloseIconVisible()) {
                chipTimeNone.setCloseIconVisible(false);
            }
            choiceChipTimeGroup.addView(chipTimeNone);

            for (int i = 1; i <= num; i++) {
                int time = i * 5;
                Chip chip = new Chip(getActivity());

                chip.setText(time + " Min");
                chip.setId(i);
                chip.isClickable();
                chip.setCheckable(true);

                chip.setChipDrawable(ChipDrawable.createFromResource(getActivity(),
                        R.xml.tag_chip));
                if (chip.isCloseIconVisible()) {
                    chip.setCloseIconVisible(false);
                }
//
                choiceChipTimeGroup.addView(chip);
                //  getChip(String.valueOf(time),"T",i);
            }

            // chipGroupPerson.canScrollHorizontally(1);
            choiceChipTimeGroup.setSingleSelection(true);
            choiceChipTimeGroup.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(ChipGroup chipGroup, int i) {
                    closeKeyBoard();

                    Chip chip = chipGroup.findViewById(i);
                    if (chip != null) {
                        String[] separated = chip.getText().toString().split(" ");
//                        strChipTime=Integer.parseInt(separated[0]);
                        if (!chip.isChecked()) {
                            strChipTime = Integer.parseInt(separated[0].trim());

                            //Toast.makeText(getContext(), chip.getText() + "    E1", Toast.LENGTH_SHORT).show();
                        } else if (chip.getText().equals("None")) {
                            strChipTime = 0;
                            //Toast.makeText(getContext(), "Now ", Toast.LENGTH_SHORT).show();
                        } else {
                            strChipTime = Integer.parseInt(separated[0].trim());
                            //Toast.makeText(getContext(), chip.getText() + "", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        //Toast.makeText(getContext(), "null", Toast.LENGTH_SHORT).show();

                    }
                }
            });


            // ((ViewGroup) binding.llRadio.findViewById(R.id.radio_group)).addView(ll);
        }

    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.clear();
        getActivity().getMenuInflater().inflate(R.menu.logout, menu);
        super.onCreateOptionsMenu(menu, inflater);
        item1 = menu.findItem(R.id.saveWaitingData);
        item1.setVisible(true);
    }

    public void setWaitingDate() {
        try {
            if (waitingList.size() != 0) {
                ClsWaitingList clsWaitingList = waitingList.get(waitingPosition);
                tvNo.setText("Waiting No : " + clsWaitingList.getWaitingID());
                tvMobile.setText("" + clsWaitingList.getCustomerNo());
                tvPerson.setText("" + clsWaitingList.getPersons());
                tvName.setText("" + clsWaitingList.getCustomerName());
                tvTime.setText("" + clsWaitingList.getExpectedWaitingTime());
                tvPreference.setText("" + clsWaitingList.getFoodType());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.saveWaitingData) {
            if (LOGINVALIDATION()) {

                insertWaiting();

            }
        }
        return super.onOptionsItemSelected(item);
    }


    public void insertWaiting() {

        progressBar.setVisibility(View.VISIBLE);

        SharedPreferences mPreferences = getContext().getSharedPreferences(SPLoginDetails, MODE_PRIVATE);
        ClsWaitingResponse clsWaitingResponse = new ClsWaitingResponse();
        clsWaitingResponse.setWaitingID(0);
        String mode = "INSERT";
        clsWaitingResponse.setCustomerName(String.valueOf(etCustomerName.getText()));
        clsWaitingResponse.setCustomerNo(String.valueOf(etMobile.getText()));
        clsWaitingResponse.setExpectedWaitingTime(strChipTime);

        String strFoodType = "";
        if (rbVeg.isChecked()) {
            strFoodType = rbVeg.getText().toString();
            closeKeyBoard();
        } else if (rbAny.isChecked()) {
            strFoodType = rbAny.getText().toString();
        } else if (rbNonVeg.isChecked()) {
            strFoodType = rbNonVeg.getText().toString();
            closeKeyBoard();
        }

        clsWaitingResponse.setFoodType(strFoodType);

        clsWaitingResponse.setBranchID(Integer.parseInt(mPreferences.getString("BRANCH_ID", "Not found")));
        clsWaitingResponse.setSpecialRequest(String.valueOf(etRequest.getText()));
        clsWaitingResponse.setPersons(intChipPerson);
        clsWaitingResponse.setEmployeeName(mPreferences.getString("FULL_NAME", "Not found"));
        clsWaitingResponse.setEmployeeCode(mPreferences.getString("EMPLOYEE_CODE", "Not found"));
        clsWaitingResponse.setMode(mode);

        Gson gson = new Gson();
        String jsonInString = gson.toJson(clsWaitingResponse);
        Log.e("--Waiting--", "GsonObj from insertWaiting SUCCESS : " + jsonInString);


        waitingFragmentViewModel.insertWaiting(clsWaitingResponse).observe(getActivity(), clsWaitingResponse1 -> {

            if (clsWaitingResponse1 != null) {
                Toast.makeText(getContext(), clsWaitingResponse1.getMESSAGE(), Toast.LENGTH_SHORT).show();
                if (clsWaitingResponse1.getMESSAGE().equals("SUCCESS.")) {
                    resetData();
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getContext(), clsWaitingResponse1.getMESSAGE(), Toast.LENGTH_SHORT).show();

                } else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getContext(), clsWaitingResponse1.getMESSAGE(), Toast.LENGTH_SHORT).show();

                }

            } else {
                Toast.makeText(getContext(), clsWaitingResponse1.getMESSAGE(), Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);

            }
//            Gson gson = new Gson();
//            String jsonInString = gson.toJson(clsWaitingResponse);
            Log.e("--URL--", "GsonObj from insertWaiting : " + jsonInString);

        });



    }


    public void resetData() {
        etMobile.setText("");
        etCustomerName.setText("");
        etRequest.setText("");
        rbAny.setChecked(true);
        chipTimeNone.setChecked(true);
        chipPersonNone.setChecked(true);
    }

    @SuppressLint("ResourceAsColor")
    public void addChipsPerson(int number) {
        for (int row = 0; row < 1; row++) {


            chipPersonNone = new Chip(getActivity());
            chipPersonNone.setText("None");
            chipPersonNone.setId(0);
            chipPersonNone.isClickable();
            chipPersonNone.setCheckable(true);
            chipPersonNone.setChipDrawable(ChipDrawable.createFromResource(getActivity(),
                    R.xml.tag_chip));
            if (chipPersonNone.isCloseIconVisible()) {
                chipPersonNone.setCloseIconVisible(false);
            }
            chipGroupPerson.addView(chipPersonNone);

            for (int i = 1; i <= number; i++) {
                Chip chip = new Chip(getActivity());

                chip.setText(i + "");
                chip.setId(i);
                chip.isClickable();
                chip.setCheckable(true);

//                chip.setBackgroundColor(R.color.black);
//                chip.setChipBackgroundColorResource(R.color.test);
                chip.setChipDrawable(ChipDrawable.createFromResource(getActivity(),
                        R.xml.tag_chip));
                if (chip.isCloseIconVisible()) {
                    chip.setCloseIconVisible(false);
                }
                chipGroupPerson.addView(chip);
            }

            chipGroupPerson.setSingleSelection(true);
            chipGroupPerson.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(ChipGroup chipGroup, int i) {
                    closeKeyBoard();
                    Chip chip = chipGroup.findViewById(i);
                    if (chip != null) {
                        String[] separated = chip.getText().toString().split(" ");

                        if (!chip.isChecked()) {
                            intChipPerson = Integer.parseInt(separated[0].trim());//
                            //  Toast.makeText(getContext(), chip.getText() + "per", Toast.LENGTH_SHORT).show();
                        } else if (chip.getText().equals("None")) {
                            intChipPerson = 0;
                            //Toast.makeText(getContext(), chip.getText() + " Persons", Toast.LENGTH_SHORT).show();

                        } else {
                            intChipPerson = Integer.parseInt(separated[0].trim());//
                            //Toast.makeText(getContext(), chip.getText() + " Persons", Toast.LENGTH_SHORT).show();

                        }
                    } else {
                        //Toast.makeText(getContext(), "null", Toast.LENGTH_SHORT).show();

                    }
                }
            });


            // ((ViewGroup) binding.llRadio.findViewById(R.id.radio_group)).addView(ll);
        }
    }


    void bottomDialog() {
        View view = getLayoutInflater().inflate(R.layout.waiting_customer_bottom, null);
        mDialog = new BottomSheetDialog(getActivity());
        mDialog.setContentView(view);
        mDialog.setCancelable(true);
        mDialog.show();
    }


    /* @SuppressLint("ResourceAsColor")
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
*/
}
