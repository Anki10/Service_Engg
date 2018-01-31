package com.app.winklix.service_engg;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.app.winklix.service_engg.api.RestClient;
import com.app.winklix.service_engg.pojo.GetPendingStatusPojo;
import com.app.winklix.service_engg.pojo.GetSyncDataPojo;
import com.app.winklix.service_engg.track_service_engg.LatLongUpdateService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import retrofit.Callback;
import retrofit.RetrofitError;
import util.Constant;
import util.Methods;
import util.NetWorkCheck;
import util.RecyclerTouchListener;
import com.app.winklix.service_engg.adapter.Call_detail_Holder;
import com.app.winklix.service_engg.adapter.Vertical_Adapter;

import util.PermissionUtils;

public class MainActivity extends AppCompatActivity implements PermissionUtils.PermissionResultCallback {
    RecyclerView dashboard_recycle;
    private Context context;


    private Vertical_Adapter verticalAdapter;
    private ArrayList<Call_detail_Holder> service_eng_list;


    public static final String TAG = "TAG";
    Button dashboard_update;
    ArrayList<String> permissions = new ArrayList<>();
    PermissionUtils permissionUtils;
    boolean isPermissionGranted;
    static   String EmployeeAPIid,send_location_txt;
    public static MainActivity activity;
    private Boolean location = false;
    ProgressBar _proProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        activity = this;

        EmployeeAPIid =  Methods.getEmployeeAPIid(this);
        send_location_txt =  Methods.getLocationText(this);


        _proProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dashboard_update = (Button) findViewById(R.id.dashboard_update);
        dashboard_update.setText(send_location_txt);
        permissionUtils = new PermissionUtils(MainActivity.this);
        permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
        permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        permissionUtils.check_permission(permissions, "Need GPS permission for getting your location", 1);




        dashboard_recycle = (RecyclerView) findViewById(R.id.dashboard_recycle);
        service_eng_list = new ArrayList<>();

        dashboard_recycle.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), dashboard_recycle, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Call_detail_Holder actor = service_eng_list.get(position);
                //   Toast.makeText(getApplicationContext(), actor.getTitle() + " is selected!", Toast.LENGTH_SHORT).show();
                //   String desc = actor.getdes();
                //  String image = actor.getImage();
                //  String link = actor.getLink();
            }
            @Override
            public void onLongClick(View view, int position) {
            }
        }));



        verticalAdapter = new Vertical_Adapter(context, service_eng_list);
        LinearLayoutManager horizontallayoutmaneger = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        dashboard_recycle.setLayoutManager(horizontallayoutmaneger);


        if (NetWorkCheck.checkConnection(this)) {
            JSON_HTTP_CALL();
        } else {
            Toast.makeText(this, "Internet connection is disable", Toast.LENGTH_LONG).show();
        }


        dashboard_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, LatLongUpdateService.class);
                startService(i);

                Methods.saveLocationText(MainActivity.this,"Send Location");
                String loca = Methods.getLocationText(view.getContext());
                dashboard_update.setText(loca);

               /* if (Methods.getLocationText(view.getContext()).equals("Send Location")){
                    Methods.saveLocationText(MainActivity.this,"Stop Location");
                    dashboard_update.setText("Stop Location");

                } else {
                    Intent i = new Intent(MainActivity.this, LatLongUpdateService.class);
                    stopService(i);
                    Methods.saveLocationText(MainActivity.this,"Send Location");
                    dashboard_update.setText("Send Location");
                }*/
            }
        });

    }

    public void JSON_HTTP_CALL(){
        final String url = Constant.BASE_API_URL+"ticket/"+EmployeeAPIid;
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
// prepare the Request
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject json = new JSONObject(response.toString());
                            if (json != null) {
                                if (json.length() > 0) {
                                    JSONArray array = json.getJSONArray("data");
                                    int lenArray = array.length();
                                    if (lenArray > 0) {
                                        for (int jIndex = 0; jIndex < lenArray; jIndex++) {

                                            Call_detail_Holder actor = new Call_detail_Holder();
                                            JSONObject innerObject = array.getJSONObject(jIndex);
                                            String id = innerObject.getString("TicketId");
                                            String TicketAPIid = innerObject.getString("TicketAPIid");
                                            String client = innerObject.getString("ClientName");
                                            String location = innerObject.getString("ClientLocation");
                                            String date = innerObject.getString("TicketDate");
                                            String TicketTime = innerObject.getString("TicketTime");

                                            String contactno = innerObject.getString("ClientContactNo");
                                            String call_category = innerObject.getString("CallCategory");
                                            String model = innerObject.getString("Model");
                                            String contact_person_name = innerObject.getString("ContactPerson");
                                            String contact_person_no = innerObject.getString("ContactPersonNo");

                                            // String call_transfer_at = innerObject.getString("call_transfer_at");
                                            String call_problem = innerObject.getString("CallProblem");
                                            String address = innerObject.getString("ClientAddress");


                                            actor.setaddress(address);
                                            actor.setTicketTime(TicketTime);
                                            actor.setcontactno(contactno);
                                            actor.setcall_category(call_category);
                                            actor.setmodel(model);
                                            actor.setcontact_person_name(contact_person_name);
                                            actor.setcontact_person_no(contact_person_no);
                                            //  actor.setcall_transfer_at(call_transfer_at);
                                            actor.setcall_problem(call_problem);
                                            actor.setTicketAPIid(TicketAPIid);
                                            actor.setCallId(id);
                                            actor.setclientLocation(location);
                                            actor.setCllName(client);
                                            actor.setdates(date);

                                            service_eng_list.add(actor);

                                        }

                                        dashboard_recycle.setAdapter(verticalAdapter);
                                        verticalAdapter.notifyDataSetChanged();
                                    }
                                }
                            } else {
                            }
                        } catch (JSONException e) {
                            Log.i(TAG, "" + e.getLocalizedMessage());
                        }
                        Log.d("Response", response.toString());
                        _proProgressBar.setVisibility(View.GONE);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        {  Toast.makeText(context,"Internet is slow! or Server not responding!",Toast.LENGTH_LONG).show();}
                        _proProgressBar.setVisibility(View.GONE);
                    }
                }
        );

        queue.add(getRequest);
        _proProgressBar.setVisibility(View.VISIBLE);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        permissionUtils.onRequestPermissionsResult(requestCode,permissions,grantResults);
    }

    @Override
    public void PermissionGranted(int request_code) {
        Log.i("PERMISSION","GRANTED");
        isPermissionGranted=true;
    }
    @Override
    public void PartialPermissionGranted(int request_code, ArrayList<String> granted_permissions) {
        Log.i("PERMISSION PARTIALLY","GRANTED");}
    @Override
    public void PermissionDenied(int request_code) {
        Log.i("PERMISSION","DENIED");
    }
    @Override
    public void NeverAskAgain(int request_code) {
        Log.i("PERMISSION","NEVER ASK AGAIN");
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_notification) {
            return true;
        }else if (id == R.id.action_logout){
            logoutDialog();
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
}
