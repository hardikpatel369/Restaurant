package com.nspl.restaurant.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nspl.restaurant.Global.ClsGlobal;
import com.nspl.restaurant.R;
import com.nspl.restaurant.RetrofitApi.ApiClasses.City.ClsCity;
import com.nspl.restaurant.RetrofitApi.ApiClasses.GenerateBill.ClsBillDetail;
import com.nspl.restaurant.RetrofitApi.ApiClasses.GenerateBill.ClsCustomerDetail;
import com.nspl.restaurant.RetrofitApi.ApiClasses.GenerateBill.ClsPaymentDetail;
import com.nspl.restaurant.RetrofitApi.ApiClasses.MobileNo.ClsMobileNo;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Order.ClsOrderSummary;
import com.nspl.restaurant.RetrofitApi.ApiClasses.tax.ClsTaxSlab;
import com.nspl.restaurant.ViewModel.ActivityViewModel.TablesActivityViewModel;
import com.nspl.restaurant.ViewModel.FragmentViewModel.BillDetailFragmentViewModel;
import com.nspl.restaurant.databinding.FragmentBillDetailBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;

public class BillDetailFragment extends Fragment {

    private ClsBillDetail clsBillDetail = null;
    private ClsCustomerDetail clsCustomerDetail = null;
    private ClsPaymentDetail clsPaymentDetail = null;
    private boolean menuIsInflated;
    private FragmentBillDetailBinding binding;
    private BillDetailFragmentViewModel billDetailFragmentViewModel;
    private String branch_id;
    private int orderId;
    private int counterId;
    private int departmentId;
    private int table_id;
    private String counterType;
    private String table_no;
    private String orderNo;
    private String branch_code;
    private String employee_code;
    private String employee_name;
    private boolean apply_tax;
    private String tax_type;
    private ClsTaxSlab clsTaxSlabs;
    private ClsMobileNo clsMobileNo;
    private double total = 0.0;
    private double parcelCharge = 0.0;
    private double replace = 0.0;
    private double addReturnReplace = 0.0;
    private double grandTotal;
    private double discount = 0.00;
    private double discountAmount = 0.00;
    private double taxPercent = 0.00;
    private double taxableAmount = 0.00;
    private double cgst = 0.00;
    private double sgst = 0.00;
    private double igst = 0.00;
    private double cgstValue = 0.00;
    private double sgstValue = 0.00;
    private double igstValue = 0.00;
    private double totalTax = 0.00;
    private double receivableAmount = 0.00;
    private double paidAmount = 0.00;
    private double change = 0.00;
    private List<Integer> orderDetailId = new ArrayList<>();
    private List<ClsOrderSummary> summaryList = new ArrayList<>();

    private List<ClsCity> cityArrayList = new ArrayList<>();
    private ArrayList<String> cityStr = new ArrayList<>();

    AutoCompleteTextView tvPaymentDetails;
    private String[] paymentDetailArray;
    private List<String> paymentDetailList = new ArrayList<>();

    public BillDetailFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        billDetailFragmentViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity()))
                .get(BillDetailFragmentViewModel.class);

        SharedPreferences sp = getActivity().getSharedPreferences("CounterData", MODE_PRIVATE);
        branch_id = sp.getString("BranchId", "");
        orderId = sp.getInt("OrderId", 0);

        table_id = sp.getInt("TABLE_ID", 0);
        table_no = sp.getString("TABLE_NO", "");
        counterId = sp.getInt("CounterId", 0);
        departmentId = sp.getInt("departmentId", 0);
        counterType = sp.getString("CounterType", "");
        orderNo = sp.getString("OrderNo", "");

        SharedPreferences sps = getActivity().getSharedPreferences("LoginDetails", MODE_PRIVATE);
        branch_code = sps.getString("BRANCH_CODE","");
        employee_code = sps.getString("EMPLOYEE_CODE","");
        employee_name = sps.getString("FULL_NAME","");

        Log.d("xyz", "onCreate: ");

    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_bill_detail, container, false);

        initToolbar();
        binding.expandableLayout0.setOnExpansionUpdateListener((expansionFraction, state) -> {
        });
        binding.llCustomerInfo.setOnClickListener(v -> onClick());

        binding.tvDiscountAmount.setText("0.00");

        orderSummary();
        mobileNo();
        city();
        paymentDetail();
        Log.d("xyz", "onCreateView: ");

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @SuppressLint("SetTextI18n")
    private void taxSlab() {

        billDetailFragmentViewModel.getTaxSlab(branch_id).observe(getViewLifecycleOwner(), clsTaxSlabResponse -> {

            clsTaxSlabs = new ClsTaxSlab();
            if (clsTaxSlabResponse != null) {
                clsTaxSlabs = clsTaxSlabResponse.getDATA();

                apply_tax = clsTaxSlabs.getActive();
                tax_type = clsTaxSlabs.getTAXTYPE();

                cgst = clsTaxSlabs.getCGST();
                sgst = clsTaxSlabs.getSGST();
                igst = clsTaxSlabs.getIGST();

                //taxPercent
                taxPercent = cgst + sgst + igst;

//                binding.tvCgst.setText(cgst + "%");
//                binding.tvSgst.setText(sgst + "%");
//                binding.tvIgst.setText(igst + "%");

                binding.ivClearCustomerInfo.setOnClickListener(v -> {
                    binding.tvCustomerName.setText("");
                    binding.tvCity.setText("");
                    binding.tvCompanyName.setText("");
                    binding.tvGstNumber.setText("");
                    binding.tvMobileNumber.setText("");
                    binding.tvPerson.setText("");
                });

                binding.ivClearPayment.setOnClickListener(v -> {
                    binding.tvDiscount.setText("");
                    binding.tvPaidAmount.setText("");
                    binding.tvPaymentDetails.setText("");
                    binding.tvRemark.setText("");
                    binding.rgPaymentDetail.clearCheck();
                });

                if (clsTaxSlabs.getTAXTYPE().equalsIgnoreCase("Exclusive")) {

                    binding.tvDiscount.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if (binding.tvDiscount.length() >= 1 && binding.tvDiscount.length() < 3) {
                                discount = Double.parseDouble(binding.tvDiscount.getText().toString());

                                //discount amount
                                discountAmount = (grandTotal * discount) / 100;
                                binding.tvDiscountAmount.setText(ClsGlobal.round(discountAmount, 2));

                                //taxable amount
                                taxableAmount = (grandTotal - discountAmount);
                                binding.tvTaxableAmount.setText(ClsGlobal.round(taxableAmount, 2));

                                //tax
                                cgstValue = (taxableAmount * cgst) / 100;
                                sgstValue = (taxableAmount * sgst) / 100;
                                igstValue = (taxableAmount * igst) / 100;

//                                binding.tvCgstValue.setText(ClsGlobal.round(cgstValue, 3));
//                                binding.tvSgstValue.setText(ClsGlobal.round(sgstValue, 3));
//                                binding.tvIgstValue.setText(ClsGlobal.round(igstValue, 3));

                                binding.tvCgst.setText("CGST " + "(" + cgst + "%) : ₹ " + ClsGlobal.round(cgstValue, 3));
                                binding.tvSgst.setText("SGST " + "(" + sgst + "%) : ₹ " + ClsGlobal.round(sgstValue, 3));
                                binding.tvIgst.setText("IGST " + "(" + igst + "%) : ₹ " + ClsGlobal.round(igstValue, 3));

                                //total Tax
                                totalTax = cgstValue + sgstValue + igstValue;
                                binding.tvTotalTax.setText(ClsGlobal.round(totalTax, 2));

                                //receivable amount
                                receivableAmount = grandTotal + parcelCharge - discountAmount + totalTax;
                                binding.tvReceivableAmount.setText(ClsGlobal.round(receivableAmount, 0));

                                //change
                                binding.tvChange.setText(ClsGlobal.round(-receivableAmount, 0));

                            } else if (binding.tvDiscount.length() == 3) {
                                binding.tvTotalTax.setText("0");
                                binding.tvTaxableAmount.setText("0");
                                binding.tvReceivableAmount.setText("0");
                                binding.tvChange.setText("0");
//                                binding.tvCgstValue.setText("0");
//                                binding.tvSgstValue.setText("0");
//                                binding.tvIgstValue.setText("0");

                            } else {
                                //discount amount
                                discountAmount = 0.00;
                                binding.tvDiscountAmount.setText(ClsGlobal.round(discountAmount, 2));

                                //taxable amount
                                taxableAmount = (grandTotal - discountAmount);
                                binding.tvTaxableAmount.setText(ClsGlobal.round(taxableAmount, 2));
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                        }

                    });

                    binding.tvPaidAmount.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if (binding.tvPaidAmount.length() >= 1) {
                                binding.tvChange.setText(ClsGlobal.round(-receivableAmount, 0));

                                //paid amount
                                paidAmount = Double.parseDouble(binding.tvPaidAmount.getText().toString());

                                //change
                                change = paidAmount - receivableAmount;
                                binding.tvChange.setText(ClsGlobal.round(change, 0));

                            } else if (binding.tvPaidAmount.length() == 0) {
                                binding.tvChange.setText(ClsGlobal.round(-receivableAmount, 0));
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                        }
                    });

                    //taxable amount
                    taxableAmount = (grandTotal - discountAmount);
                    binding.tvTaxableAmount.setText(ClsGlobal.round(taxableAmount, 2));

                    //tax
                    cgstValue = (taxableAmount * cgst) / 100;
                    sgstValue = (taxableAmount * sgst) / 100;
                    igstValue = (taxableAmount * igst) / 100;

//                    binding.tvCgstValue.setText(ClsGlobal.round(cgstValue, 3));
//                    binding.tvSgstValue.setText(ClsGlobal.round(sgstValue, 3));
//                    binding.tvIgstValue.setText(ClsGlobal.round(igstValue, 3));

                    binding.tvCgst.setText("CGST " + "(" + cgst + "%) : ₹ " + ClsGlobal.round(cgstValue, 3));
                    binding.tvSgst.setText("SGST " + "(" + sgst + "%) : ₹ " + ClsGlobal.round(sgstValue, 3));
                    binding.tvIgst.setText("IGST " + "(" + igst + "%) : ₹ " + ClsGlobal.round(igstValue, 3));

                    //totalTax
                    totalTax = cgstValue + sgstValue + igstValue;
                    binding.tvTotalTax.setText(ClsGlobal.round(totalTax, 2));

                    //receivable amount
                    receivableAmount = grandTotal + parcelCharge - discountAmount + totalTax;
                    binding.tvReceivableAmount.setText(ClsGlobal.round(receivableAmount, 0));

                    //change
                    binding.tvChange.setText(ClsGlobal.round(-receivableAmount, 0));

                } else if (clsTaxSlabs.getTAXTYPE().equalsIgnoreCase("Inclusive")) {

                    binding.tvDiscount.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if (binding.tvDiscount.length() >= 1 && binding.tvDiscount.length() < 3) {
                                discount = Double.parseDouble(binding.tvDiscount.getText().toString());

                                //discount amount
                                discountAmount = (grandTotal * discount) / 100;
                                binding.tvDiscountAmount.setText(ClsGlobal.round(discountAmount, 2));

                                //taxable amount
                                taxableAmount = (grandTotal - discountAmount) / (100 + taxPercent) * 100;
                                binding.tvTaxableAmount.setText(ClsGlobal.round(taxableAmount, 2));

                                //tax
                                cgstValue = (taxableAmount * cgst) / 100;
                                sgstValue = (taxableAmount * sgst) / 100;
                                igstValue = (taxableAmount * igst) / 100;

//                                binding.tvCgstValue.setText(ClsGlobal.round(cgstValue, 3));
//                                binding.tvSgstValue.setText(ClsGlobal.round(sgstValue, 3));
//                                binding.tvIgstValue.setText(ClsGlobal.round(igstValue, 3));

                                binding.tvCgst.setText("CGST " + "(" + cgst + "%) : ₹ " + ClsGlobal.round(cgstValue, 3));
                                binding.tvSgst.setText("SGST " + "(" + sgst + "%) : ₹ " + ClsGlobal.round(sgstValue, 3));
                                binding.tvIgst.setText("IGST " + "(" + igst + "%) : ₹ " + ClsGlobal.round(igstValue, 3));

                                //total Tax
                                totalTax = cgstValue + sgstValue + igstValue;
                                binding.tvTotalTax.setText(ClsGlobal.round(totalTax, 2));

                                //receivable amount
                                receivableAmount = grandTotal + parcelCharge - discountAmount;
                                binding.tvReceivableAmount.setText(ClsGlobal.round(receivableAmount, 0));

                                //change
                                binding.tvChange.setText(ClsGlobal.round(-receivableAmount, 0));

                            } else if (binding.tvDiscount.length() == 3) {
                                binding.tvTotalTax.setText("0");
                                binding.tvTaxableAmount.setText("0");
                                binding.tvReceivableAmount.setText("0");
                                binding.tvChange.setText("0");
                                binding.tvDiscountAmount.setText(ClsGlobal.round(grandTotal,2));
//                                binding.tvCgstValue.setText("0");
//                                binding.tvSgstValue.setText("0");
//                                binding.tvIgstValue.setText("0");

                            } else {
                                //discount amount
                                discountAmount = 0.00;
                                binding.tvDiscountAmount.setText(ClsGlobal.round(discountAmount, 2));

                                //taxable amount
                                taxableAmount = (grandTotal - discountAmount) / (100 + taxPercent) * 100;
                                binding.tvTaxableAmount.setText(ClsGlobal.round(taxableAmount, 2));
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                        }

                    });
                    binding.tvPaidAmount.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if (binding.tvPaidAmount.length() >= 1) {
                                binding.tvChange.setText(ClsGlobal.round(-receivableAmount, 0));

                                //paid amount
                                paidAmount = Double.parseDouble(binding.tvPaidAmount.getText().toString());

                                //change
                                change = paidAmount - receivableAmount;
                                binding.tvChange.setText(ClsGlobal.round(change, 0));

                            } else if (binding.tvPaidAmount.length() == 0) {
                                binding.tvChange.setText(ClsGlobal.round(-receivableAmount, 0));
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                        }
                    });

                    //taxable amount
                    taxableAmount = (grandTotal - discountAmount) / (100 + taxPercent) * 100;
                    binding.tvTaxableAmount.setText(ClsGlobal.round(taxableAmount, 2));

                    //tax
                    cgstValue = (taxableAmount * cgst) / 100;
                    sgstValue = (taxableAmount * sgst) / 100;
                    igstValue = (taxableAmount * igst) / 100;

//                    binding.tvCgstValue.setText(ClsGlobal.round(cgstValue, 3));
//                    binding.tvSgstValue.setText(ClsGlobal.round(sgstValue, 3));
//                    binding.tvIgstValue.setText(ClsGlobal.round(igstValue, 3));

                    binding.tvCgst.setText("CGST " + "(" + cgst + "%) : ₹ " + ClsGlobal.round(cgstValue, 3));
                    binding.tvSgst.setText("SGST " + "(" + sgst + "%) : ₹ " + ClsGlobal.round(sgstValue, 3));
                    binding.tvIgst.setText("IGST " + "(" + igst + "%) : ₹ " + ClsGlobal.round(igstValue, 3));

                    //totalTax
                    totalTax = cgstValue + sgstValue + igstValue;
                    binding.tvTotalTax.setText(ClsGlobal.round(totalTax, 2));

                    //receiveable amount
                    receivableAmount = grandTotal + parcelCharge - discountAmount;
                    binding.tvReceivableAmount.setText(ClsGlobal.round(receivableAmount, 0));

                    //change
                    binding.tvChange.setText(ClsGlobal.round(-receivableAmount, 0));
                }
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void tax() {
        //taxable amount
        taxableAmount = (grandTotal - discountAmount) / (100 + taxPercent) * 100;
        binding.tvTaxableAmount.setText(ClsGlobal.round(taxableAmount, 2));

        //tax
        cgstValue = (taxableAmount * cgst) / 100;
        sgstValue = (taxableAmount * sgst) / 100;
        igstValue = (taxableAmount * igst) / 100;

//        binding.tvCgstValue.setText(ClsGlobal.round(cgstValue, 3));
//        binding.tvSgstValue.setText(ClsGlobal.round(sgstValue, 3));
//        binding.tvIgstValue.setText(ClsGlobal.round(igstValue, 3));

        binding.tvCgst.setText("CGST " + "(" + cgst + "%) : ₹ " + ClsGlobal.round(cgstValue, 3));
        binding.tvSgst.setText("SGST " + "(" + sgst + "%) : ₹ " + ClsGlobal.round(sgstValue, 3));
        binding.tvIgst.setText("IGST " + "(" + igst + "%) : ₹ " + ClsGlobal.round(igstValue, 3));

        //totalTax
        totalTax = cgstValue + sgstValue + igstValue;
        binding.tvTotalTax.setText(ClsGlobal.round(totalTax, 2));

        //receiveable amount
        receivableAmount = grandTotal + parcelCharge - discountAmount;
        binding.tvReceivableAmount.setText(ClsGlobal.round(receivableAmount, 0));

        //change
        binding.tvChange.setText(ClsGlobal.round(-receivableAmount, 0));
    }

    private void orderSummary() {
        TablesActivityViewModel mTablesActivityViewModel = ViewModelProviders.of(this).get(TablesActivityViewModel.class);

        mTablesActivityViewModel.getOrderSummaryResponse(orderId).observe(getActivity(), clsOrderSummaryResponse -> {

            if (clsOrderSummaryResponse != null) {
                summaryList = clsOrderSummaryResponse.getDATA();

                for (int i = 0; i < summaryList.size(); i++) {
                    total += summaryList.get(i).getTOTALAMOUNT();
                    orderDetailId.add(summaryList.get(i).getORDERDETAILID());

                    if (summaryList.get(i).getPARCELCHARGES() != null) {
                        parcelCharge += summaryList.get(i).getPARCELCHARGES();
                    }

                    if (summaryList.get(i).getSTATUS().equalsIgnoreCase("RETURN") ||
                            summaryList.get(i).getSTATUS().equalsIgnoreCase("REPLACE")) {
                        replace += summaryList.get(i).getTOTALAMOUNT();
                    }

                    if (summaryList.get(i).getISSTATUSCHECK() != null &&
                            summaryList.get(i).getISSTATUSCHECK().equals(true)) {
                        addReturnReplace += summaryList.get(i).getTOTALAMOUNT();
                        Log.d("Bill", "addReturnReplace: " + addReturnReplace);
                    }
                }
                grandTotal = total - replace + addReturnReplace;
                binding.tvNetAmount.setText(ClsGlobal.round(grandTotal, 2));
                binding.tvTotalParcelCharges.setText(ClsGlobal.round(parcelCharge, 2));
            }
            taxSlab();
        });
    }

    private void mobileNo() {

        binding.tvMobileNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (binding.tvMobileNumber.length() == 10) {
                    String mobile = binding.tvMobileNumber.getText().toString();

                    billDetailFragmentViewModel.getCustomerName(mobile).observe(getViewLifecycleOwner(), clsMobileNoResponse -> {

                        clsMobileNo = new ClsMobileNo();
                        if (clsMobileNoResponse != null) {
                            clsMobileNo = clsMobileNoResponse.getDATA();

                            if (clsMobileNo != null) {
                                String customerName = clsMobileNo.getName();
                                String company = clsMobileNo.getCompany();
                                String city = clsMobileNo.getCity();
                                String gstNo = clsMobileNo.getGSTIN();
                                binding.tvCustomerName.setText(customerName);
                                binding.tvCity.setText(city);
                                binding.tvCompanyName.setText(company);
                                binding.tvGstNumber.setText(gstNo);
                            } else {
                                Toast.makeText(getContext(), "Mobile number not found.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void city() {
        billDetailFragmentViewModel.getCity().observe(getViewLifecycleOwner(), clsCityResponse -> {
            if (clsCityResponse != null) {
                cityArrayList = clsCityResponse.getDATA();

                if (cityArrayList.size() != 0) {

                    for (ClsCity clsCity : cityArrayList) {
                        cityStr.add(clsCity.getCITY());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>
                            (getContext(), android.R.layout.simple_list_item_1, cityStr);
                    adapter.notifyDataSetChanged();

                    binding.tvCity.setThreshold(1);
                    binding.tvCity.setAdapter(adapter);
                    binding.tvCity.setOnClickListener(v -> closeKeyBoard());
                }
            }
        });
    }

    private void paymentDetail() {
        billDetailFragmentViewModel.GetPaymentDetailList().observe(getViewLifecycleOwner(), clsPayDetail -> {
            if (clsPayDetail != null) {
                paymentDetailList = clsPayDetail.getDATA();
                if (paymentDetailList.size() != 0) {

                    paymentDetailArray = new String[paymentDetailList.size()];
                    paymentDetailList.toArray(paymentDetailArray);

                    Gson gson = new Gson();
                    String jsonInString = gson.toJson(paymentDetailArray);
                    Log.e("reasonArray", "getobjClsUserInfo---" + jsonInString);

                    ArrayAdapter<String> adapter = new ArrayAdapter<>
                            (getActivity(), android.R.layout.simple_list_item_1, paymentDetailArray);
                    adapter.notifyDataSetChanged();

                    binding.tvPaymentDetails.setThreshold(1);
                    binding.tvPaymentDetails.setAdapter(adapter);
                    binding.tvPaymentDetails.setOnClickListener(v -> closeKeyBoard());
                }
            }
        });
    }

    private void BillDetail() {

        ClsCustomerDetail clsCustomerDetail = new ClsCustomerDetail();

        clsCustomerDetail.setName(binding.tvCustomerName.getText().toString());
        clsCustomerDetail.setMobileNo(binding.tvMobileNumber.getText().toString());
        clsCustomerDetail.setCity(binding.tvCity.getText().toString());
        clsCustomerDetail.setGSTIN(binding.tvGstNumber.getText().toString());
        clsCustomerDetail.setCompany(binding.tvCompanyName.getText().toString());

        ClsPaymentDetail clsPaymentDetail = new ClsPaymentDetail();

        clsPaymentDetail.setMobileNo(binding.tvMobileNumber.getText().toString());
        clsPaymentDetail.setOrderNo(orderNo);
        clsPaymentDetail.setTableNo(table_no);
        clsPaymentDetail.setTokenNo(null);
        clsPaymentDetail.setDepartmentID(departmentId);
        clsPaymentDetail.setCounterID(counterId);
        clsPaymentDetail.setCounterType(counterType);
        clsPaymentDetail.setNetAmount(Double.valueOf(binding.tvNetAmount.getText().toString()));
        clsPaymentDetail.setDISCPER(Integer.valueOf(0+binding.tvDiscount.getText().toString()));
        clsPaymentDetail.setDiscount(Double.valueOf(binding.tvDiscountAmount.getText().toString()));
        clsPaymentDetail.setTaxableAmount(Double.valueOf(binding.tvTaxableAmount.getText().toString()));
        clsPaymentDetail.setAPPLYTAX(apply_tax);
        clsPaymentDetail.setTAXTYPE(tax_type);
        clsPaymentDetail.setCGSTPER(cgst);
        clsPaymentDetail.setSGSTPER(sgst);
        clsPaymentDetail.setIGSTPER(igst);
        clsPaymentDetail.setCGST(cgstValue);
        clsPaymentDetail.setSGST(sgstValue);
        clsPaymentDetail.setIGST(igstValue);
        clsPaymentDetail.setTaxAmout(Double.valueOf(binding.tvTotalTax.getText().toString()));
        clsPaymentDetail.setPaybleAmount(Double.valueOf(binding.tvPaidAmount.getText().toString()));
        clsPaymentDetail.setRountOff(0.00);
        clsPaymentDetail.setReceiveableAmount(Double.valueOf(binding.tvReceivableAmount.getText().toString()));
        clsPaymentDetail.setPaymentMode("cash");
        clsPaymentDetail.setPaymentDetail(binding.tvPaymentDetails.getText().toString());
        clsPaymentDetail.setOrderId(orderId);
        clsPaymentDetail.setBranchID(Integer.valueOf(branch_id));
        clsPaymentDetail.setBRANCHCODE(branch_code);

        ClsBillDetail clsBillDetail = new ClsBillDetail();

//        int nop = Integer.parseInt(binding.tvPerson.getText().toString());

        clsBillDetail.setoRDERID(orderId);
        clsBillDetail.settABLENO(table_no);
        clsBillDetail.settABLEID(table_id);
        clsBillDetail.setnOP(Integer.valueOf(0+binding.tvPerson.getText().toString()));
        clsBillDetail.setoRDERREMARK(binding.tvRemark.getText().toString());
        clsBillDetail.setuPDATETABLESTATUS(false);
        clsBillDetail.seteMPLOYEECODE(employee_code);
        clsBillDetail.seteMPLOYEENAME(employee_name);

        Gson gson = new Gson();
        String customerDetail = gson.toJson(clsCustomerDetail);
        Log.e("--Bill-Post--", "customerDetail---" + customerDetail);
        clsBillDetail.setCustomerDetail(customerDetail);

        String paymentDetail = gson.toJson(clsPaymentDetail);
        Log.e("--Bill-Post--", "paymentDetail---" + paymentDetail);
        clsBillDetail.setPaymentDetail(paymentDetail);

        String orderdetailId = gson.toJson(orderDetailId);
        Log.e("--Bill-Post--", "orderdetailId---" + orderdetailId);
        clsBillDetail.set_OrderDetailList(orderdetailId);

        String clsbill = gson.toJson(clsBillDetail);
        Log.e("--Bill-Post--", "clsBillDetail---" + clsbill);

        BillDetailFragmentViewModel billDetailFragmentViewModel =
                ViewModelProviders.of(this).get(BillDetailFragmentViewModel.class);

        billDetailFragmentViewModel.PostBillDetail(clsBillDetail).observe(this,clsBillDetail1 -> {
            if (clsBillDetail1 == null){
                Toast.makeText(getActivity(), "DATA NOT FOUND", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void closeKeyBoard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager iMM = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            iMM.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void onClick() {
        if (binding.expandableLayout0.isExpanded()) {
            binding.expandableLayout0.collapse();
            binding.ivExpand.setImageResource(R.drawable.ic_expand_more);
        } else {
            binding.expandableLayout0.expand();
            binding.ivExpand.setImageResource(R.drawable.ic_expand_less);
        }
    }

    private void initToolbar() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(binding.toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Bill");
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        Log.d("xyz", "onCreateOptionsMenu: ");
        if (!menuIsInflated) {
            Log.d("xyz", "onCreateOptionsMenu: " + "--if--");
            binding.toolbar.inflateMenu(R.menu.save_print);
            menuIsInflated = true;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.bill_save_print:
                BillDetail();
                Toast.makeText(getActivity(), "Save", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
