package com.nspl.restaurant.ViewModel.ActivityViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.nspl.restaurant.Global.Repository;
import com.nspl.restaurant.RetrofitApi.ApiClasses.OrderActions.ClsConfirmOrderResponse;
import com.nspl.restaurant.RetrofitApi.ApiClasses.OrderActions.ClsOrderPrintDeleteResponse;
import com.nspl.restaurant.RetrofitApi.ApiClasses.OrderActions.ClsReasonList;
import com.nspl.restaurant.RetrofitApi.ApiClasses.OrderActions.ClsReturnReplace;

public class OrderDetailActivityViewModel extends AndroidViewModel {

    private Repository mRepository;

    public OrderDetailActivityViewModel(@NonNull Application application) {
        super(application);

        this.mRepository = new Repository(application);
    }

    public LiveData<ClsConfirmOrderResponse> getConfirmOrderResponse(int orderId){
        return mRepository.GetOrderConfirm(orderId);
    }

    public LiveData<ClsOrderPrintDeleteResponse> getOrderPrintDeleteResponse( int OrderDetailID,
                                                                              int OrderID,String Mode){
        return mRepository.GetOrderPrintDelete(OrderDetailID,OrderID,Mode);
    }

    public LiveData<ClsReturnReplace> postReturnReplace(String remark,boolean wastage,int OrderDetailID,
                                                        int OrderID,String status){
        return mRepository.PostReturnReplaceOrder(remark,wastage,OrderDetailID,OrderID,status);
    }

    public LiveData<ClsReasonList> getReasonList(){
        return mRepository.GetReasonList();
    }

}
