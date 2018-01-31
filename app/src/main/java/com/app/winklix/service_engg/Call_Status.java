package com.app.winklix.service_engg;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.app.winklix.service_engg.adapter.Call_Status_Adapter;
import com.app.winklix.service_engg.adapter.Call_Status_Holder;
import com.app.winklix.service_engg.adapter.Call_detail_Holder;
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

public class Call_Status extends AppCompatActivity {
    private RecyclerView call_status_recyclerview;
    private Context context;

    private OkHttpClient client;
    private Call_Status_Adapter verticalAdapter;
    private ArrayList<Call_Status_Holder> service_eng_list;
    private static Response productresponse;
    public static final String TAG = "TAG";
    private LinearLayout employee_empty_view;
    private MyDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call__status);
        context = Call_Status.this;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dialog = new MyDialog(this);

        employee_empty_view = (LinearLayout) findViewById(R.id.employee_empty_view);

        call_status_recyclerview = (RecyclerView)findViewById(R.id.call_status_recyclerview);

        client = new OkHttpClient();
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(30, TimeUnit.SECONDS);
        builder.readTimeout(30, TimeUnit.SECONDS);
        builder.writeTimeout(30, TimeUnit.SECONDS);
        client = builder.build();

        if (NetWorkCheck.checkConnection(this)) {
            new GetDataTaskProduct().execute();
        } else {
            Toast.makeText(this, "Internet connection is disable", Toast.LENGTH_LONG).show();
        }

        service_eng_list = new ArrayList<>();
        verticalAdapter = new Call_Status_Adapter(context, service_eng_list);
        LinearLayoutManager horizontallayoutmaneger = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        call_status_recyclerview.setLayoutManager(horizontallayoutmaneger);
        call_status_recyclerview.setAdapter(verticalAdapter);
        verticalAdapter.notifyDataSetChanged();
    }



    class GetDataTaskProduct extends AsyncTask<Void, Void, Void> {
        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(Call_Status.this);
            dialog.setMessage("Please Wait");
            dialog.show();
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

                                Call_Status_Holder actor = new Call_Status_Holder();
                                JSONObject innerObject = array.getJSONObject(jIndex);
                                String CallStatus = innerObject.getString("CallStatus");
                                String CallSolution = innerObject.getString("CallSolution");


                                JSONObject TicketDetail = innerObject.getJSONObject("TicketDetail");

                                String id = TicketDetail.getString("TicketId");
                                String TicketAPIid = TicketDetail.getString("TicketAPIid");
                                String client = TicketDetail.getString("ClientName");
                                String location = TicketDetail.getString("ClientLocation");
                                String date = TicketDetail.getString("TicketDate");
                                String TicketTime = TicketDetail.getString("TicketTime");
                                String contactno = TicketDetail.getString("ClientContactNo");
                                String call_category = TicketDetail.getString("CallCategory");
                                String CallSubCategory = TicketDetail.getString("CallSubCategory");
                                String model = TicketDetail.getString("Model");
                                String contact_person_name = TicketDetail.getString("ContactPerson");
                                String contact_person_no = TicketDetail.getString("ContactPersonNo");
                                String call_problem = TicketDetail.getString("CallProblem");
                                String address = TicketDetail.getString("ClientAddress");

                                JSONObject EmployeeDetail = innerObject.getJSONObject("EmployeeDetail");
                                String CBID = EmployeeDetail.getString("CBID");

                                actor.setCBID(CBID);
                                actor.setCallSubCategory(CallSubCategory);
                                actor.setCallStatus(CallStatus);
                                actor.setCallSolution(CallSolution);
                                actor.setaddress(address);
                                actor.setTicketTime(TicketTime);
                                actor.setcontactno(contactno);
                                actor.setcall_category(call_category);
                                actor.setmodel(model);
                                actor.setcontact_person_name(contact_person_name);
                                actor.setcontact_person_no(contact_person_no);
                                actor.setcall_problem(call_problem);
                                actor.setTicketAPIid(TicketAPIid);
                                actor.setCallId(id);
                                actor.setclientLocation(location);
                                actor.setCllName(client);
                                actor.setdates(date);

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
                //    Snackbar.make(findViewById(R.id.parentLayout), "No Data Found", Snackbar.LENGTH_LONG).show();
                employee_empty_view.setVisibility(View.VISIBLE);
  //              Toast.makeText(getApplicationContext(), "No Data Found!", Toast.LENGTH_LONG).show();
            }

        }
    }

    public static JSONObject getDataFromWebproduct() {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(Constant.BASE_API_URL+"resolution")
                    .build();
            productresponse = client.newCall(request).execute();
            return new JSONObject(productresponse.body().string());
        } catch (@NonNull IOException | JSONException e) {
            Log.e(TAG, "" + e.getLocalizedMessage());
        }
        return null;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.call_status_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        } else if (id == R.id.call_data_action_sync) {
            GetSyncData();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void GetSyncData(){
        dialog.ShowProgressDialog();
        RestClient.get().GetPendingStatus(new Callback<GetPendingStatusPojo>() {
            @Override
            public void success(GetPendingStatusPojo getPendingStatusPojo, retrofit.client.Response response) {
                if (getPendingStatusPojo.getResolution().equals("0")){
                    dialog.CancelProgressDialog();
                    Toast.makeText(Call_Status.this,"No data remanining to sync",Toast.LENGTH_LONG).show();
                } else {
                    UpdateSyncData(getPendingStatusPojo.getResolution());
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
        RestClient.get().SyncData(new Object(), "resolution", pending, new Callback<GetSyncDataPojo>() {
            @Override
            public void success(GetSyncDataPojo getSyncDataPojo, retrofit.client.Response response) {
                dialog.CancelProgressDialog();
                service_eng_list.clear();
                verticalAdapter.notifyDataSetChanged();
                employee_empty_view.setVisibility(View.VISIBLE);

                System.out.println("xxxx1 sucess");

            }

            @Override
            public void failure(RetrofitError error) {
                System.out.println("xxxx1 faill");
            }
        });
    }

}
