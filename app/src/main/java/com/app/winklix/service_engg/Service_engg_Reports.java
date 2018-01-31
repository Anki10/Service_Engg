package com.app.winklix.service_engg;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.app.winklix.service_engg.adapter.Service_Engg_Reports_Adapter;
import com.app.winklix.service_engg.adapter.Service_Engg_Reports_Holder;

import java.util.ArrayList;

public class Service_engg_Reports extends AppCompatActivity {

    private  RecyclerView engg_reports_recyclerview;
    private Context context;
    private Service_Engg_Reports_Adapter serviceEnggReportsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_engg__reports);
        context = Service_engg_Reports.this;

        engg_reports_recyclerview = (RecyclerView)findViewById(R.id.engg_reports_recyclerview);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ArrayList<Service_Engg_Reports_Holder>  ServiceEnggList = new ArrayList<>();

        for (int s=0; s<20; s++){
            Service_Engg_Reports_Holder serviceEnggReportsHolder = new Service_Engg_Reports_Holder();
            serviceEnggReportsHolder.setEngg_Name("name" + s);
            serviceEnggReportsHolder.setEngg_Id("" + s);
            ServiceEnggList.add(serviceEnggReportsHolder);

            serviceEnggReportsAdapter = new Service_Engg_Reports_Adapter(context,ServiceEnggList);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
            engg_reports_recyclerview.setLayoutManager(linearLayoutManager);
            engg_reports_recyclerview.setAdapter(serviceEnggReportsAdapter);
            serviceEnggReportsAdapter.notifyDataSetChanged();

        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);

    }
}
