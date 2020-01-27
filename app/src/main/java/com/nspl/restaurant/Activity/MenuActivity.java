package com.nspl.restaurant.Activity;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nspl.restaurant.Adapter.MenuAdapter;
import com.nspl.restaurant.DataModel.ClsMenuItems;
import com.nspl.restaurant.R;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Menu.ClsCategorys;
import com.nspl.restaurant.RetrofitApi.ApiClasses.Menu.ClsDataMenu;
import com.nspl.restaurant.ViewModel.ActivityViewModel.MenuActivityViewModel;
import com.nspl.restaurant.databinding.ActivityMenuBinding;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    ActivityMenuBinding mBinding;
    String _menuName;
    List<ClsDataMenu> dataMenus = new ArrayList<>();
    List<ClsCategorys> lstCategorys = new ArrayList<>();
    List<ClsMenuItems> menuItems = new ArrayList<>();

    MenuActivityViewModel mMenuActivityViewModel;
    String TableNo = "", TableStatus = "";
    private MenuAdapter mMenuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setTitle("Menu");
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_menu);
        mBinding.rv.setLayoutManager(new LinearLayoutManager(MenuActivity.this));

        mMenuActivityViewModel = ViewModelProviders.of(this).get(MenuActivityViewModel.class);

        mMenuAdapter = new MenuAdapter(MenuActivity.this);

        TableNo = getIntent().getStringExtra("TableNo");
        TableStatus = getIntent().getStringExtra("TableStatus");

//        Log.e("MenuActivity", TableNo);
//        Log.e("MenuActivity", TableStatus);

        mMenuActivityViewModel.getMenuResponse().observe(this, clsMenuResponse -> {

            if (clsMenuResponse != null) {
                dataMenus = clsMenuResponse.getmDataMenu();

                for (ClsDataMenu currentDataMenu : dataMenus) {
                    _menuName = currentDataMenu.getmMenu().getNAME();
                    setTitle(_menuName);

//                    int _menuID = currentDataMenu.getmMenu().getID();

                    lstCategorys = currentDataMenu.getmCATEGORYS();
                    mMenuAdapter.addItems(lstCategorys);
                    mBinding.rv.setAdapter(mMenuAdapter);

//                    mMenuAdapter.AddItems(lstCategorys);
//                    for (ClsCategorys current : lstCategorys) {
//                        Log.e("Menu", "Categoryname : -" + current.getcATEGORYNAME());
//
//                        if (!current.getcATEGORYNAME().equalsIgnoreCase("")) {
//                            Log.e("jsonInString", "getcATEGORYNAME");
////                        listMenu.add(new ClsMenuItems());
//                            items = current.getiTEMS();
//
//
//                            List<String> listItemName = new ArrayList<>();
//                            Log.d("--itemList--", "uppper: " + items);
//                            if (items.size() != 0) {
//                                Log.d("--itemList--", "inner: " + items);
//                                for (ClsItem itemName : items) {
//                                    listItemName.add(itemName.getnAME());
//                                }
//
//                            }
//
//                            Gson gson = new Gson();
//                            String jsonInString = gson.toJson(listItemName);
//                            Log.e("listItemName", jsonInString);
//
//                            for (ClsItem currentItems : current.getiTEMS()) {
////                            menuItems.add(new ClsMenuItems(true, current.getcATEGORYNAME(),
////                                    currentItems.getiTEMID(), currentItems.getnAME(), currentItems.getkITCHENID()
////                                    , currentItems.getkITCHENSECTION(), currentItems.getnUTRITION()
////                                    , currentItems.getnUTRITIONTITLE()
////                                    , currentItems.getfOODTYPE(), currentItems.getpARCELCHARGES(), currentItems.getsIZES()
////                                    , currentItems.getaDDONS()
////                                    , currentItems.getcOMMENTS(), currentItems.getnUTRITIONS(), currentItems.getiMAGES()));
//
//
//                                menuItems.add(new ClsMenuItems(true, current.getcATEGORYNAME(),
//                                        currentItems.getiTEMID(), currentItems.getnAME()));
//
//
//                            }
//                        }
//                    }
                }

                Gson gson = new Gson();
                String jsonInString = gson.toJson(menuItems);
                Log.e("--Menu--", "Item: " + jsonInString);
            }


       /*     if(clsMenuResponse != null){
                dataMenus = clsMenuResponse.getmDataMenu();

                for(ClsDataMenu currentDataMenu : dataMenus){
                    categorys = currentDataMenu.getmCATEGORYS();
                    mMenuAdapter.AddItems(categorys);
                }

                for (ClsCategorys currentCategorys : categorys){
                    Log.e("Menu","Categoryname : -" + currentCategorys.getcATEGORYNAME());

                    if(currentCategorys.getcATEGORYNAME() != null){
                        items = currentCategorys.getiTEMS();

                        for(ClsItem currentItems : items){
                            Log.e("Item","ItemsName : -" + currentItems.getnAME());

                        }
                    }

                }

            }
*/

        });

        mMenuAdapter.SetOnMenuClickListener((clsTable, position) -> {
            Toast.makeText(this, "Category Name", Toast.LENGTH_LONG).show();

        });
    }
}
