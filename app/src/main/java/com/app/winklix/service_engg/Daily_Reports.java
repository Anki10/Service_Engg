package com.app.winklix.service_engg;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.app.winklix.service_engg.activity.DailyReportActivity;
import com.app.winklix.service_engg.adapter.Daily_Reports_Adapter;
import com.app.winklix.service_engg.api.RestClient;
import com.app.winklix.service_engg.pojo.DataListLatLongResponse;
import com.app.winklix.service_engg.pojo.GetAdminLatLongResponse;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import util.MyDialog;

public class Daily_Reports extends AppCompatActivity {
    private RecyclerView daily_reports_recyclerview;
    private Context context;
    private Daily_Reports_Adapter adapter;
    private ArrayList<DataListLatLongResponse> arraylist;
    private String emp_id;
    private RecyclerView.LayoutManager mLayoutManager;
    private MyDialog dialog;
    private LinearLayout employee_empty_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily__reports);
        context = Daily_Reports.this;

        dialog = new MyDialog(this);

        employee_empty_view = (LinearLayout) findViewById(R.id.employee_empty_view);

        arraylist = new ArrayList<DataListLatLongResponse>();

        daily_reports_recyclerview = (RecyclerView)findViewById(R.id.daily_reports_recyclerview);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        emp_id = getIntent().getStringExtra("emp_id");

        Daily_Report();


    }
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);

    }

    private void Daily_Report(){
        dialog.ShowProgressDialog();
        RestClient.get().GetLatLong(emp_id, new Callback<GetAdminLatLongResponse>() {
            @Override
            public void success(GetAdminLatLongResponse getAdminLatLongResponse, Response response) {

                if (getAdminLatLongResponse.getData().size() > 0){
                    employee_empty_view.setVisibility(View.GONE);
                } else {
                    employee_empty_view.setVisibility(View.VISIBLE);
                }

                System.out.println("xxx sucess");

                arraylist.addAll(getAdminLatLongResponse.getData());

                daily_reports_recyclerview.setHasFixedSize(true);
                mLayoutManager = new LinearLayoutManager(Daily_Reports.this);
                daily_reports_recyclerview.setLayoutManager(mLayoutManager);

                adapter = new Daily_Reports_Adapter(Daily_Reports.this,arraylist);
                daily_reports_recyclerview.setAdapter(adapter);

                dialog.CancelProgressDialog();
            }

            @Override
            public void failure(RetrofitError error) {
                System.out.println("xxx faill");
            }
        });
    }
}
