package com.app.winklix.service_engg;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.app.winklix.service_engg.adapter.AdmistrativeVertical_Adapter;
import com.app.winklix.service_engg.adapter.Employee_details_Holder;
import com.app.winklix.service_engg.adapter.MenuHolder;
import com.app.winklix.service_engg.adapter.Menu_adapter;
import com.app.winklix.service_engg.adapter.Vertical_Adapter;
import com.app.winklix.service_engg.api.RestClient;
import com.app.winklix.service_engg.pojo.GetPendingStatusPojo;
import com.app.winklix.service_engg.pojo.GetSyncDataPojo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit.Callback;
import retrofit.RetrofitError;
import util.Constant;
import util.CookieStore;
import util.MyDialog;
import util.NetWorkCheck;

public class Admistrative_MainActivity extends AppCompatActivity {
    RecyclerView MyNavigationRecyclerVoew;
    private Context context;
    private OkHttpClient client;
    private AdmistrativeVertical_Adapter verticalAdapter;
    private ArrayList<Employee_details_Holder> service_eng_list;
    private static Response productresponse;
    public static final String TAG = "TAG";
    private LinearLayout employee_empty_view;
    private MyDialog dialog;


    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admistrative__main);

        employee_empty_view = (LinearLayout) findViewById(R.id.employee_empty_view);

        dialog = new MyDialog(this);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Admistartive");
        toolbar.setTitleTextColor(R.color.white);
        context = Admistrative_MainActivity.this;

        //--------------------------------------------------------------------------------------------
        client = new OkHttpClient();
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(30, TimeUnit.SECONDS);
        builder.readTimeout(30, TimeUnit.SECONDS);
        builder.writeTimeout(30, TimeUnit.SECONDS);
        client = builder.build();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));
        MyNavigationRecyclerVoew = (RecyclerView) findViewById(R.id.MyNavigationRecyclerVoew);



        ArrayList<MenuHolder> usersHolders = new ArrayList<>();

        MenuHolder usersHolder = new MenuHolder();
        Uri alllead = Uri.parse("android.resource://" + BuildConfig.APPLICATION_ID + "/" + R.drawable.ic_add_user_name);
        String s = alllead.toString();
        usersHolder.setTitleName("Add User");
        usersHolder.setIcons(s);
        usersHolders.add(usersHolder);

     /*   MenuHolder usersHolder1 = new MenuHolder();
        Uri users = Uri.parse("android.resource://" + BuildConfig.APPLICATION_ID + "/" + R.drawable.ic_formal_list_name);
        String ss = users.toString();
        usersHolder1.setTitleName("Reports of Service Engg");
        usersHolder1.setIcons(ss);
        usersHolders.add(usersHolder1);*/

        MenuHolder usersHolder2 = new MenuHolder();
        Uri user2 = Uri.parse("android.resource://" + BuildConfig.APPLICATION_ID + "/" + R.drawable.ic_view_list_name);
        String s2 = user2.toString();
        usersHolder2.setTitleName("Daily Report");
        usersHolder2.setIcons(s2);
        usersHolders.add(usersHolder2);
 MenuHolder usersHolder3 = new MenuHolder();
        Uri user3 = Uri.parse("android.resource://" + BuildConfig.APPLICATION_ID + "/" + R.drawable.ic_view_list_name);
        String s3 = user3.toString();
        usersHolder3.setTitleName("Monthly Report");
        usersHolder3.setIcons(s3);
        usersHolders.add(usersHolder3);
 MenuHolder usersHolder4 = new MenuHolder();
        Uri user4 = Uri.parse("android.resource://" + BuildConfig.APPLICATION_ID + "/" + R.drawable.ic_view_list_name);
        String s4 = user4.toString();
        usersHolder4.setTitleName("Call Report");
        usersHolder4.setIcons(s4);
        usersHolders.add(usersHolder4);



        Menu_adapter menu_adapter = new Menu_adapter(Admistrative_MainActivity.this, usersHolders);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        MyNavigationRecyclerVoew.setLayoutManager(layoutManager);
        MyNavigationRecyclerVoew.setAdapter(menu_adapter);

//--------------------------------------------------------------------------------




        MyNavigationRecyclerVoew  = (RecyclerView)findViewById(R.id.dashboard_recycle1);
        service_eng_list = new ArrayList<>();


        verticalAdapter = new AdmistrativeVertical_Adapter(context, service_eng_list);
        LinearLayoutManager horizontallayoutmaneger = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        MyNavigationRecyclerVoew.setLayoutManager(horizontallayoutmaneger);
        MyNavigationRecyclerVoew.setAdapter(verticalAdapter);
        verticalAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (NetWorkCheck.checkConnection(this)) {
            new GetDataTaskProduct().execute();
        } else {
            Toast.makeText(this, "Internet connection is disable", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_notification) {
            return true;
        }else if (id == R.id.action_logout){
             logoutDialog();
            return true;
        } else if (id == R.id.action_sync) {
            GetSyncData();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logoutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Logout...");
        builder.setMessage("Are you sure to Logout ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                //   m_shared_pref.setPrefranceValue(App_Api.IsLoggedIn, false);
                 //startActivity(new Intent(context, Admistrative_loginActivity.class));
                SharedPreferences preferences = getSharedPreferences("userlogin", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.commit();
                startActivity(new Intent(context, CategoryActivity.class));
                finish();

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.create().show();
    }

    private void GetSyncData(){
        dialog.ShowProgressDialog();
        RestClient.get().GetPendingStatus(new Callback<GetPendingStatusPojo>() {
            @Override
            public void success(GetPendingStatusPojo getPendingStatusPojo, retrofit.client.Response response) {
                if (getPendingStatusPojo.getEmployee().equals("0")){
                    Toast.makeText(Admistrative_MainActivity.this,"No data remanining to sync",Toast.LENGTH_LONG).show();
                    dialog.CancelProgressDialog();
                } else {
                    UpdateSyncData(getPendingStatusPojo.getEmployee());
                }

                System.out.println("xxxx sucess");
            }

            @Override
            public void failure(RetrofitError error) {
                System.out.println("xxxx faill");
                dialog.CancelProgressDialog();
            }
        });
    }

    private void UpdateSyncData(String pending){
        RestClient.get().SyncData(new Object(), "employee", pending, new Callback<GetSyncDataPojo>() {
            @Override
            public void success(GetSyncDataPojo getSyncDataPojo, retrofit.client.Response response) {
                dialog.CancelProgressDialog();

                service_eng_list.clear();
                verticalAdapter.notifyDataSetChanged();
                employee_empty_view.setVisibility(View.VISIBLE);
                System.out.println("xxxx1 sucess");

//                if (NetWorkCheck.checkConnection(Admistrative_MainActivity.this)) {
//                    new GetDataTaskProduct().execute();
//                } else {
//                    Toast.makeText(Admistrative_MainActivity.this, "Internet connection is disable", Toast.LENGTH_LONG).show();
//                }
            }

            @Override
            public void failure(RetrofitError error) {
                System.out.println("xxxx1 faill");

                dialog.CancelProgressDialog();
            }
        });
    }


    class GetDataTaskProduct extends AsyncTask<Void, Void, Void> {
        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(Admistrative_MainActivity.this);
            dialog.setMessage("Please Wait");
            dialog.show();

            service_eng_list.clear();
        }

        @Nullable
        @Override
        protected Void doInBackground(Void... params) {
            JSONObject jsonObject = getDataFromWebproduct();
            try {
                if (jsonObject != null) {
                    if (jsonObject.length() > 0) {
                        JSONArray array = jsonObject.getJSONArray("data");
                        int lenArray = array.length();
                        if (lenArray > 0) {
                            for (int jIndex = 0; jIndex < lenArray; jIndex++) {

                                Employee_details_Holder actor = new Employee_details_Holder();
                                JSONObject innerObject = array.getJSONObject(jIndex);
                                String EmployeeAPIid = innerObject.getString("EmployeeAPIid");
                                String CBID = innerObject.getString("CBID");
                                String EmpId = innerObject.getString("EmpId");
                                String EmpName = innerObject.getString("EmpName");
                                String EmpMobNo = innerObject.getString("EmpMobNo");
                                String EmpEmailId = innerObject.getString("EmpEmailId");
                                String Operation = innerObject.getString("Operation");


                                actor.setEmployeeAPIid(EmployeeAPIid);
                                actor.setCBID(CBID);
                                actor.setEmpId(EmpId);
                                actor.setEmpName(EmpName);
                                actor.setEmpMobNo(EmpMobNo);
                                actor.setEmpEmailId(EmpEmailId);
                                actor.setOperation(Operation);

                                service_eng_list.add(actor);

                            }
                        }
                    }
                } else {
                }
            } catch (JSONException je) {
                Log.i(TAG, "" + je.getLocalizedMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();
            if (service_eng_list.size() > 0) {
                employee_empty_view.setVisibility(View.GONE);
                verticalAdapter.notifyDataSetChanged();
            } else {
      //          Toast.makeText(getApplicationContext(), "No Data Found!", Toast.LENGTH_LONG).show();
                employee_empty_view.setVisibility(View.VISIBLE);
            }

        }
    }

    public static JSONObject getDataFromWebproduct() {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(Constant.BASE_API_URL+"employee")
                    .build();
            productresponse = client.newCall(request).execute();
            return new JSONObject(productresponse.body().string());
        } catch (@NonNull IOException | JSONException e) {
            Log.e(TAG, "" + e.getLocalizedMessage());
        }
        return null;
    }


}
