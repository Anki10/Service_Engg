package com.app.winklix.service_engg.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.app.winklix.service_engg.R;
import com.app.winklix.service_engg.adapter.AllEmployeeListAdapter;
import com.app.winklix.service_engg.api.RestClient;
import com.app.winklix.service_engg.pojo.GetAllEmployeeList;
import com.app.winklix.service_engg.pojo.GetAllEmployeeListData;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import util.MyDialog;

/**
 * Created by Administrator on 1/3/2018.
 */

public class DailyReportActivity extends AppCompatActivity {

    private RecyclerView employeeListRecyclerView;
    private AllEmployeeListAdapter adapter;
    private ArrayList<GetAllEmployeeListData> list;
    private RecyclerView.LayoutManager mLayoutManager;
    private MyDialog dialog;
    private LinearLayout employee_empty_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dailyreport);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dialog = new MyDialog(this);

        employee_empty_view = (LinearLayout) findViewById(R.id.employee_empty_view);

        employeeListRecyclerView = (RecyclerView) findViewById(R.id.employeeListRecyclerView);

        list = new ArrayList<GetAllEmployeeListData>();

        GetEmployeeList();

    }

    private void GetEmployeeList(){

        dialog.ShowProgressDialog();

        RestClient.get().GetAllEmployeeReport(new Callback<GetAllEmployeeList>() {
            @Override
            public void success(GetAllEmployeeList getAllEmployeeList, Response response) {

                if (getAllEmployeeList.getData().size() > 0){
                    employee_empty_view.setVisibility(View.GONE);
                } else {
                    employee_empty_view.setVisibility(View.VISIBLE);
                }

                list.addAll(getAllEmployeeList.getData());

                employeeListRecyclerView.setHasFixedSize(true);
                mLayoutManager = new LinearLayoutManager(DailyReportActivity.this);
                employeeListRecyclerView.setLayoutManager(mLayoutManager);

                adapter = new AllEmployeeListAdapter(DailyReportActivity.this,list);
                employeeListRecyclerView.setAdapter(adapter);

                dialog.CancelProgressDialog();
            }

            @Override
            public void failure(RetrofitError error) {
                System.out.println("xxx faill");

                dialog.CancelProgressDialog();
            }
        });

    }
}
