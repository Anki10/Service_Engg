package com.app.winklix.service_engg;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.app.winklix.service_engg.adapter.Monthly_Reports_Adapter;
import com.app.winklix.service_engg.adapter.Monthly_Reports_Holder;

import java.util.ArrayList;

public class Monthly_Reports extends AppCompatActivity {
    private RecyclerView monthly_reports_recyclerview;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly__reports);
        context = Monthly_Reports.this;

        monthly_reports_recyclerview = (RecyclerView) findViewById(R.id.monthly_reports_recyclerview);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ArrayList<Monthly_Reports_Holder> monthly_reports_list = new ArrayList<>();
        for (int m = 0; m < 20; m++){
            Monthly_Reports_Holder monthly_reports_holder = new Monthly_Reports_Holder();
            monthly_reports_holder.setMonthly_report_Id("id" + m);
            monthly_reports_holder.setMonthly_report_Name("name" + m);
            monthly_reports_list.add(monthly_reports_holder);

            Monthly_Reports_Adapter monthly_reports_adapter = new Monthly_Reports_Adapter(context,monthly_reports_list);
            LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
            monthly_reports_recyclerview.setLayoutManager(linearLayoutManager2);
            monthly_reports_recyclerview.setAdapter(monthly_reports_adapter);
            monthly_reports_adapter.notifyDataSetChanged();
        }


    }
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);

    }
}
