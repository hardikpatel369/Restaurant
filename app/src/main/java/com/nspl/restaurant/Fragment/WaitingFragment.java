package com.nspl.restaurant.Fragment;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;
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
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class WaitingFragment extends Fragment /*implements WaitingPersonRecycalAdapter.OnItemClick*/ {

    private final String MY_PREF_FILE = "user_details";
    private SharedPreferences sharedPreferences;
    private static final String SPLoginDetails = "LoginDetails";
    private Chip chipPersonNone, chipTimeNone;
    public static int saveOrUpdate = 0;
    private TextView tvNo, tvMobile, tvPerson, tvName, tvTime, tvPreference, tvRequest;
    private String[] requestArray;
    private HorizontalScrollView horizontalScrollView, horizontalScroll2;
    private List<ClsWaitingList> waitingList = new ArrayList<>();
    private List<String> requestArrayList = new ArrayList<>();
    private WaitingFragmentViewModel waitingFragmentViewModel;
    private EditText etMobile, etCustomerName, etRequest;
    private ChipGroup chipGroupPerson, choiceChipTimeGroup;
    private int strChipTime = 0, intChipPerson = 0;
    private RadioGroup rgPreference;
    private RadioButton rbAny, rbVeg, rbNonVeg;
    private int waitingPosition;
    private ImageButton ibNext;
    private Button btnSave, btnReset;
    private ProgressDialog pd;
    private AutoCompleteTextView actv;
    private ImageButton ibClearName, ibClearMobile, ibClearRequest;
    private NestedScrollView scrollViewAll;
    public static LinearLayout llWaitingBottomDialog;


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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_waiting, container, false);
        waitingFragmentViewModel = new ViewModelProvider(this)
                .get(WaitingFragmentViewModel.class);
        initToolbar(v);

        getActivity().getWindow()
                .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        pd = new ProgressDialog(getActivity());
        pd.setMessage("loading...");

        main(v);
        if (WaitingPagerFragment.waitingObj != null) {

            if (WaitingPagerFragment.waitingObj.getCustomerNo() != null) {
                etMobile.setText(WaitingPagerFragment.waitingObj.getCustomerNo() + "");
            } else {
                etMobile.setText("");
            }
            if (WaitingPagerFragment.waitingObj.getCustomerName() != null)
                etCustomerName.setText(WaitingPagerFragment.waitingObj.getCustomerName());
            if (WaitingPagerFragment.waitingObj.getSpecialRequest() != null)
                etRequest.setText(WaitingPagerFragment.waitingObj.getSpecialRequest());


            v.post(() -> {
                Chip chip = chipGroupPerson.findViewById(WaitingPagerFragment.waitingObj.getPersons());
                chip.setChecked(true);

                Chip chip2 = choiceChipTimeGroup.findViewById(WaitingPagerFragment.waitingObj.getExpectedWaitingTime() / 5);
                chip2.setChecked(true);
            });


            if (WaitingPagerFragment.waitingObj.getFoodType() != null) {

                String food = (WaitingPagerFragment.waitingObj.getFoodType());

                if (food.equals("Any")) {
                    rbAny.setChecked(true);
                } else if (food.equals("Veg")) {
                    rbVeg.setChecked(true);
                } else if (food.equals("Non-Veg")) {
                    rbNonVeg.setChecked(true);
                }
            }
        } else {
            resetData();
        }
        if (waitingList.isEmpty()) {
            llWaitingBottomDialog.setVisibility(View.GONE);
        }
        return v;
    }

    private void initToolbar(View view) {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_alarm_black_24dp);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Waiting");
    }

    private void main(View view) {

        chipGroupPerson = view.findViewById(R.id.choice_chip_group);
        choiceChipTimeGroup = view.findViewById(R.id.choiceChipTimeGroup);

        rgPreference = view.findViewById(R.id.rgPreference);
        etMobile = view.findViewById(R.id.etMobile);

        chipGroupPerson = view.findViewById(R.id.choice_chip_group);
        choiceChipTimeGroup = view.findViewById(R.id.choiceChipTimeGroup);
        horizontalScrollView = view.findViewById(R.id.horizontalScrollView);
        horizontalScroll2 = view.findViewById(R.id.horizontalScroll2);
        rgPreference = view.findViewById(R.id.rgPreference);
        ibClearRequest = view.findViewById(R.id.ibClearRequest);
        ibClearMobile = view.findViewById(R.id.ibClearMobile);
        ibClearName = view.findViewById(R.id.ibClearName);
        etMobile = view.findViewById(R.id.etMobile);
        btnSave = view.findViewById(R.id.btnSave);
        btnReset = view.findViewById(R.id.btnReset);
        actv = view.findViewById(R.id.etRequest);
        llWaitingBottomDialog = view.findViewById(R.id.llWaitingBottomDialog);
        etCustomerName = view.findViewById(R.id.CustomerName);
        etRequest = view.findViewById(R.id.etRequest);
        rbAny = view.findViewById(R.id.rbAny);
        rbVeg = view.findViewById(R.id.rbVeg);
        rbNonVeg = view.findViewById(R.id.rbNonVeg);
        ibNext = view.findViewById(R.id.ibNext);
        sharedPreferences = getContext().getSharedPreferences(MY_PREF_FILE, getContext().MODE_PRIVATE);
        tvNo = view.findViewById(R.id.tvNo);
        tvMobile = view.findViewById(R.id.tvMobile);
        tvPerson = view.findViewById(R.id.tvPerson);
        tvRequest = view.findViewById(R.id.tvRequest);
        tvName = view.findViewById(R.id.tvName);
        tvTime = view.findViewById(R.id.tvTime);
        tvPreference = view.findViewById(R.id.tvPreference);
        scrollViewAll = view.findViewById(R.id.scrollViewAll);

        ibClearRequest.setOnClickListener(v -> etRequest.setText(""));
        ibClearName.setOnClickListener(v -> etCustomerName.setText(""));
        ibClearMobile.setOnClickListener(v -> etMobile.setText(""));
        getRequest();

        btnSave.setOnClickListener(v -> {
            if (LOGINVALIDATION()) {
                if (saveOrUpdate == 1) {
                    Toast.makeText(getActivity(), "Save", Toast.LENGTH_SHORT).show();
                    insertWaiting();
                } else if (saveOrUpdate == 2) {
                    Toast.makeText(getActivity(), "update", Toast.LENGTH_SHORT).show();
                    updateWaiting();
                } else {
                    Toast.makeText(getActivity(), "Someting Wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnReset.setOnClickListener(v -> resetData());
        getWaitingList();
        addChipsPerson(20);
        addChipsTime(24);
        chipTimeNone.setChecked(true);
        chipPersonNone.setChecked(true);

        ibNext.setOnClickListener(v -> next());

    }

    private void updateWaiting() {
        pd.show();

        SharedPreferences mPreferences = getContext().getSharedPreferences(SPLoginDetails, MODE_PRIVATE);
        ClsWaitingResponse clsWaitingResponse = new ClsWaitingResponse();

        if (WaitingPagerFragment.waitingObj.getWaitingID() != null) {
            clsWaitingResponse.setWaitingID(WaitingPagerFragment.waitingObj.getWaitingID());
        }
        clsWaitingResponse.setWaitingNo(WaitingPagerFragment.waitingObj.getWaitingNo());
        clsWaitingResponse.setCustomerName(String.valueOf(etCustomerName.getText()));

        if (etMobile.getText().toString().isEmpty()) {
            clsWaitingResponse.setCustomerNo(null);
        } else {
            clsWaitingResponse.setCustomerNo(String.valueOf(etMobile.getText()));
        }

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
        if (etRequest.getText().toString().isEmpty()) {
            clsWaitingResponse.setSpecialRequest("");
        } else {
            clsWaitingResponse.setSpecialRequest(String.valueOf(etRequest.getText()));
        }

        clsWaitingResponse.setPersons(intChipPerson);
        clsWaitingResponse.setEmployeeName(mPreferences.getString("FULL_NAME", "Not found"));
        clsWaitingResponse.setEmployeeCode(mPreferences.getString("EMPLOYEE_CODE", "Not found"));
        clsWaitingResponse.setMode("UPDATE");

        Gson gson = new Gson();
        String jsonInString = gson.toJson(clsWaitingResponse);
        Log.e("--Waiting--", "GsonObj from update Waiting  : " + jsonInString);

        waitingFragmentViewModel.updateWaiting(clsWaitingResponse).observe(getActivity(), clsWaitingResponse1 -> {
            if (clsWaitingResponse1 != null) {
                Toast.makeText(getContext(), clsWaitingResponse1.getMESSAGE(), Toast.LENGTH_SHORT).show();


                if (clsWaitingResponse1.getMESSAGE().equalsIgnoreCase("SUCCESS.")) {
                    resetData();
                    pd.dismiss();
                    getWaitingList();
                    getRequest();
                } else {
                    pd.dismiss();
                    Toast.makeText(getContext(), clsWaitingResponse1.getMESSAGE(), Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                pd.dismiss();
            }
        });
    }

    private void getWaitingList() {
        try {
            waitingFragmentViewModel = ViewModelProviders.of(this).get(WaitingFragmentViewModel.class);


            waitingFragmentViewModel.getWaitingResponse(0).observe(Objects.requireNonNull(getActivity()), clsWaitingResponse -> {

                waitingPosition = sharedPreferences.getInt("waitingPosition", 0);

                Log.e("--URL--", "onViewCreated: " + clsWaitingResponse);
                if (clsWaitingResponse != null) {
                    waitingList = clsWaitingResponse.getDATA();
                    setWaitingData();
                } else {
                    //on waiting
                }

            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getRequest() {
        waitingFragmentViewModel = ViewModelProviders.of(this)
                .get(WaitingFragmentViewModel.class);

        waitingFragmentViewModel.getRequestResponse().observe(getActivity(), clsWaitingResponse -> {
            if (clsWaitingResponse != null) {

                requestArrayList = clsWaitingResponse.getDATA();
                try {
                    if (requestArrayList.size() != 0) {
                        Gson gson = new Gson();

                        requestArray = new String[requestArrayList.size()];
                        requestArrayList.toArray(requestArray);

                        String jsonInString = gson.toJson(requestArray);
                        Log.e("requestArray", "getobjClsUserInfo---" + jsonInString);

                        try {
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                    (getContext(), android.R.layout.simple_list_item_1, requestArray);
                            adapter.notifyDataSetChanged();

                            actv.setThreshold(1);//will start working from first character
                            actv.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
                            actv.setTextColor(Color.BLACK);
                            actv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    closeKeyBoard();
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    } else {
                        Gson gson = new Gson();
                        String jsonInString = gson.toJson(requestArray);
                        Log.e("requestArray", "getobjClsUserInfo---" + jsonInString);

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {
                Gson gson = new Gson();
                String jsonInString = gson.toJson(requestArray);
                Log.e("requestArray", "getobjClsUserInfo---" + jsonInString);

            }

        });
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
                    pd.dismiss();
                    getWaitingList();
                } else {
                    pd.dismiss();
                    Toast.makeText(getContext(), clsWaitingResponse1.getMESSAGE(), Toast.LENGTH_SHORT).show();
                    getWaitingList();
                }

            } else {
                Toast.makeText(getContext(), clsWaitingResponse1.getMESSAGE(), Toast.LENGTH_SHORT).show();
                pd.dismiss();
                getWaitingList();

            }
        });
    }

    private void next() {
        getWaitingList();

        if (waitingList.size() != 0) {
            ClsWaitingList clsWaitingList = waitingList.get(0);
            completeFromWebWaiting(clsWaitingList.getWaitingID());
            setWaitingData();
        }
    }

    @SuppressLint("SetTextI18n")
    private void setWaitingData() {
        getWaitingList();
        if (waitingList.size() != 0) {
            ClsWaitingList clsWaitingList = waitingList.get(0);
            tvNo.setText("Waiting No : " + clsWaitingList.getWaitingNo());
            if (clsWaitingList.getCustomerNo() != null)
                tvMobile.setText("" + clsWaitingList.getCustomerNo());
            if (clsWaitingList.getPersons() != null)
                tvPerson.setText("" + clsWaitingList.getPersons());
            if (clsWaitingList.getSpecialRequest() != null)
                tvRequest.setText("" + clsWaitingList.getSpecialRequest());
            if (clsWaitingList.getCustomerName() != null)
                tvName.setText("" + clsWaitingList.getCustomerName());
            if (clsWaitingList.getExpectedWaitingTime() != null)
                tvTime.setText("" + clsWaitingList.getExpectedWaitingTime());
            if (clsWaitingList.getFoodType() != null)
                tvPreference.setText("" + clsWaitingList.getFoodType());
            llWaitingBottomDialog.setVisibility(View.VISIBLE);
        } else {
            llWaitingBottomDialog.setVisibility(View.GONE);

        }

    }


    private void closeKeyBoard() {

        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager iMM = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            iMM.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    private boolean LOGINVALIDATION() {

        boolean result;

        if (!ClsGlobal.CheckInternetConnection(getContext())) {
            Toast.makeText(getContext(), getString(R.string.NoInternet),
                    Toast.LENGTH_SHORT).show();
            return false;
        } else {
            if (!etMobile.getText().toString().isEmpty()) {
                if (etMobile.getText().toString().length() == 10) {
                    result = true;
                } else {
                    Toast.makeText(getContext(), "Please enter valid mobile number",
                            Toast.LENGTH_SHORT).show();
                    return false;
                }
            } else if (!etCustomerName.getText().toString().isEmpty()) {
                return true;
            } else {
                Toast.makeText(getContext(), "Please enter mobile number or customer name",
                        Toast.LENGTH_SHORT).show();
                result = false;
            }
        }
        return result;
    }

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
                choiceChipTimeGroup.addView(chip);
            }

            choiceChipTimeGroup.setSingleSelection(true);
            choiceChipTimeGroup.setOnCheckedChangeListener((chipGroup, i) -> {
                closeKeyBoard();

                Chip chip = chipGroup.findViewById(i);
                if (chip != null)
                    horizontalScroll2.smoothScrollTo(chip.getLeft() - (chip.getPaddingLeft() * 4),
                            chip.getTop());
                try {
                    if (!chip.isSelected()) {
                        chip.setCheckedIconResource(R.drawable.ic_check_circle_green_24dp);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (chip != null) {
                    String[] separated = chip.getText().toString().split(" ");
                    if (!chip.isChecked()) {
                        chip.setCheckedIconResource(R.drawable.ic_check_circle_green_24dp);

                        strChipTime = Integer.parseInt(separated[0].trim());

                    } else if (chip.getText().equals("None")) {
                        strChipTime = 0;
                    } else {
                        strChipTime = Integer.parseInt(separated[0].trim());
                    }
                }
            });
        }
    }

    private void insertWaiting() {

        SharedPreferences mPreferences = getContext().getSharedPreferences(SPLoginDetails, MODE_PRIVATE);
        ClsWaitingResponse clsWaitingResponse = new ClsWaitingResponse();
        clsWaitingResponse.setWaitingID(0);
        String mode = "INSERT";
        clsWaitingResponse.setCustomerName(String.valueOf(etCustomerName.getText()));
        if (etMobile.getText().toString().isEmpty()) {
            clsWaitingResponse.setCustomerNo(null);
        } else {
            clsWaitingResponse.setCustomerNo(String.valueOf(etMobile.getText()));
        }
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
        if (etRequest.getText().toString().isEmpty()) {
            clsWaitingResponse.setSpecialRequest("");
        } else {
            clsWaitingResponse.setSpecialRequest(String.valueOf(etRequest.getText()));
        }

        clsWaitingResponse.setPersons(intChipPerson);
        clsWaitingResponse.setEmployeeName(mPreferences.getString("FULL_NAME", "Not found"));
        clsWaitingResponse.setEmployeeCode(mPreferences.getString("EMPLOYEE_CODE", "Not found"));
        clsWaitingResponse.setMode(mode);

        Gson gson = new Gson();
        String jsonInString = gson.toJson(clsWaitingResponse);
        Log.e("--Waiting--", "GsonObj from insertWaiting SUCCESS : " + jsonInString);

        pd.show();

        waitingFragmentViewModel.insertWaiting(clsWaitingResponse).observe(getActivity(), clsWaitingResponse1 -> {

            if (clsWaitingResponse1 != null) {

                Toast.makeText(getContext(), clsWaitingResponse1.getMESSAGE(), Toast.LENGTH_SHORT).show();
                if (clsWaitingResponse1.getMESSAGE().equals("SUCCESS.")) {
                    resetData();
                    pd.dismiss();
                    getWaitingList();
                    getRequest();
                } else {
                    pd.dismiss();
                    Toast.makeText(getContext(), clsWaitingResponse1.getMESSAGE(), Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                pd.dismiss();
            }
        });
    }


    private void resetData() {
        etMobile.setText("");
        etCustomerName.setText("");
        etRequest.setText("");
        rbAny.setChecked(true);
        chipTimeNone.setChecked(true);
        chipPersonNone.setChecked(true);
        etMobile.requestFocus();
        horizontalScrollView.fullScroll(HorizontalScrollView.FOCUS_LEFT);
        horizontalScroll2.fullScroll(HorizontalScrollView.FOCUS_LEFT);
        scrollViewAll.fullScroll(View.FOCUS_UP);
        saveOrUpdate = 1;
        closeKeyBoard();
    }

    @SuppressLint("ResourceAsColor")
    private void addChipsPerson(int number) {
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

                chip.setChipDrawable(ChipDrawable.createFromResource(getActivity(),
                        R.xml.tag_chip));
                if (chip.isCloseIconVisible()) {
                    chip.setCloseIconVisible(false);
                }
                chipGroupPerson.addView(chip);
            }

            chipGroupPerson.setSingleSelection(true);
            chipGroupPerson.setOnCheckedChangeListener((chipGroup, i) -> {
                closeKeyBoard();
                Chip chip = chipGroup.findViewById(i);
                if (chip != null)
                    horizontalScrollView.smoothScrollTo(chip.getLeft() - (chip.getPaddingLeft()*4),
                            chip.getTop());

                try {
                    if (!chip.isSelected()) {
                        chip.setCheckedIconResource(R.drawable.ic_check_circle_green_24dp);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (chip != null) {
                    String[] separated = chip.getText().toString().split(" ");

                    if (!chip.isChecked()) {
                        intChipPerson = Integer.parseInt(separated[0].trim());//
                    } else if (chip.getText().equals("None")) {
                        intChipPerson = 0;
                    } else {
                        intChipPerson = Integer.parseInt(separated[0].trim());//
                    }
                }
            });
        }
    }

}
