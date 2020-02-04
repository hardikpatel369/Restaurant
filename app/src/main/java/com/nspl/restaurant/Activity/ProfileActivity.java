package com.nspl.restaurant.Activity;


import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.Toolbar;

import com.nspl.restaurant.DataModel.ClsUserInfo;
import com.nspl.restaurant.Global.ClsGlobal;
import com.nspl.restaurant.R;


public class ProfileActivity extends AppCompatActivity {
//    ViewDataBinding binding;
    //Toolbar toolbar;
    TextView tvUserName,tvName,tvDsgn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tvName=findViewById(R.id.tvName);
        tvUserName=findViewById(R.id.tvUserName);
        tvDsgn=findViewById(R.id.tvDsgn);

//        binding = DataBindingUtil.setContentView(this,
//                R.layout.activity_profile);
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().hide();
       // toolbar = findViewById(R.id.toolbar);

        ClsUserInfo clsUserInfo = ClsGlobal.getUserInfo(ProfileActivity.this);

        tvUserName.setText(clsUserInfo.getEMPLOYEE_CODE());
        tvName.setText(clsUserInfo.getFULL_NAME());
        tvDsgn.setText(clsUserInfo.getDESIGNATION_NAME());

    }
}
