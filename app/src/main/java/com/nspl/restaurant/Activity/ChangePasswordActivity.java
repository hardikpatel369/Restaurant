package com.nspl.restaurant.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.nspl.restaurant.DataModel.ClsUserInfo;
import com.nspl.restaurant.Global.ClsGlobal;
import com.nspl.restaurant.R;
import com.nspl.restaurant.databinding.ActivityChangePasswordBinding;

public class ChangePasswordActivity extends AppCompatActivity {
    ActivityChangePasswordBinding binding;
    String strOldPass;
//    Toolbar toolbar;
    Button btn_submit;
    EditText edt_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        btn_submit=findViewById(R.id.btn_submit);
       // getSupportActionBar().hide();

//        toolbar = findViewById(R.id.toolbar);
      /*  edt_password = findViewById(R.id.edt_password);
        btn_submit = findViewById(R.id.btn_submit);
*/

//        if (toolbar != null) {
//            setSupportActionBar(toolbar);
//        }
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        ClsUserInfo clsUserInfo = ClsGlobal.getUserInfo(ChangePasswordActivity.this);


        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String oldPass=  binding.edtPassword.getText().toString();
                Toast.makeText(ChangePasswordActivity.this, oldPass, Toast.LENGTH_SHORT).show();
//                String strBtn=binding.btnSubmit.getText().toString();
//                if(strBtn.equals("Submit")){
//                    binding.btnSubmit.setText("Change Password");
//                    c
//                }
                if (oldPass.equals(strOldPass)) {

                } else {
                    Toast.makeText(ChangePasswordActivity.this, "Please Check old Password", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
