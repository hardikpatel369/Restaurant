package com.nspl.restaurant.ViewModel.FragmentViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.nspl.restaurant.Global.Repository;
import com.nspl.restaurant.RetrofitApi.ApiClasses.City.ClsCityResponse;
import com.nspl.restaurant.RetrofitApi.ApiClasses.GenerateBill.ClsBillDetail;
import com.nspl.restaurant.RetrofitApi.ApiClasses.GenerateBill.ClsBillDetailResponse;
import com.nspl.restaurant.RetrofitApi.ApiClasses.MobileNo.ClsMobileNoResponse;
import com.nspl.restaurant.RetrofitApi.ApiClasses.PaymentDetails.ClsPayDetail;
import com.nspl.restaurant.RetrofitApi.ApiClasses.tax.ClsTaxSlabResponse;

public class BillDetailFragmentViewModel extends AndroidViewModel {

    private Repository mRepository;

    public BillDetailFragmentViewModel(@NonNull Application application) {
        super(application);

        this.mRepository = new Repository(application);
    }

    public LiveData<ClsTaxSlabResponse> getTaxSlab(String branch_id){
        return  mRepository.GetTaxSlab(branch_id);
    }

    public LiveData<ClsMobileNoResponse> getCustomerName(String mobileNo){
        return  mRepository.GetCustomerName(mobileNo);
    }

    public LiveData<ClsCityResponse> getCity(){
        return mRepository.GetCity();
    }

    public LiveData<ClsPayDetail> GetPaymentDetailList(){
        return mRepository.GetPaymentDetailList();
    }

    public LiveData<ClsBillDetailResponse> PostBillDetail(ClsBillDetail clsBillDetail){
        return mRepository.PostBillDetail(clsBillDetail);
    }

}
