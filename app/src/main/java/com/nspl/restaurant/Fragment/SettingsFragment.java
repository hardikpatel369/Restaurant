package com.nspl.restaurant.Fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import com.nspl.restaurant.Activity.ChangePasswordActivity;
import com.nspl.restaurant.Activity.MainActivity;
import com.nspl.restaurant.Activity.ProfileActivity;
import com.nspl.restaurant.DataModel.ClsUserInfo;
import com.nspl.restaurant.Global.ClsGlobal;
import com.nspl.restaurant.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {
    ClsUserInfo getUserInfo;
LinearLayout llProfile,llLogOut,llChangePass;
//    FragmentSettingsBinding binding;
    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);
//        binding=FragmentSettingsBinding.inflate(inflater,container,false);
//
//       return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        llProfile=view.findViewById(R.id.llProfile);
        llLogOut=view.findViewById(R.id.llLogOut);
        llChangePass=view.findViewById(R.id.llChangePass);
        llProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ProfileActivity.class));
            }
        });
        llLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    AlertDialog.Builder builder=new AlertDialog.Builder(getContext());

                  //  View toastView=getLayoutInflater().inflate(R.layout.toast_view, null);
                   // builder.setView(toastView);

                    builder.setTitle("Log Out");
                    builder.setMessage("Do you want to Log Out ?");
                    builder.setCancelable(false);
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            getUserInfo = ClsGlobal.getUserInfo(getContext());
                            getUserInfo.setLOCAL_LOGIN_STATUS("DEACTIVATE");
                            ClsUserInfo clsUserInfo = new ClsUserInfo();
                            clsUserInfo.setLOCAL_LOGIN_STATUS("DEACTIVATE");

                            ClsGlobal.setUserInfo(clsUserInfo,getContext());

                            Intent intent = new Intent(getContext(), MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);

                            getActivity().finish();
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });

                    AlertDialog dialog=builder.create();
                    dialog.show();
                }

        });
        llChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ChangePasswordActivity.class));
            }
        });

    }
}
