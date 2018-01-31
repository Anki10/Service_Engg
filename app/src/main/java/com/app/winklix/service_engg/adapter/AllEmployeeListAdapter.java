package com.app.winklix.service_engg.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.winklix.service_engg.Daily_Reports;

import com.app.winklix.service_engg.R;
import com.app.winklix.service_engg.activity.DailyReportActivity;
import com.app.winklix.service_engg.pojo.GetAllEmployeeListData;

import java.util.ArrayList;

/**
 * Created by Administrator on 1/3/2018.
 */

public class AllEmployeeListAdapter extends RecyclerView.Adapter<AllEmployeeListAdapter.ViewHolder>  {

    private ArrayList<GetAllEmployeeListData> arraylist;
    private Context context;

    public AllEmployeeListAdapter(Context context, ArrayList<GetAllEmployeeListData> list){

        this.arraylist = list;
        this.context = context;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View myview = LayoutInflater.from(context).inflate(R.layout.employee_item_row, parent, false);

        return new ViewHolder(myview);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final GetAllEmployeeListData data = arraylist.get(position);

        holder.ticket_no.setText("EmployeeAPIid : "+data.getEmployeeAPIid());
        holder.EmpMobNo.setText("EmpMobNo : "+data.getEmpMobNo());
        holder.EmpName.setText("EmpName :  "+data.getEmpName());
        holder.EmpEmailId.setText("EmpEmailId : "+data.getEmpEmailId());

        holder.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Daily_Reports.class);
                intent.putExtra("emp_id",data.getEmployeeAPIid());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arraylist.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView ticket_no,EmpMobNo,EmpName,EmpEmailId;
        private LinearLayout layoutItem;

        public ViewHolder(View itemView) {
            super(itemView);

            ticket_no = (TextView) itemView.findViewById(R.id.EmployeeAPIid);
            EmpMobNo = (TextView) itemView.findViewById(R.id.EmpMobNo);
            EmpName = (TextView) itemView.findViewById(R.id.EmpName);
            EmpEmailId = (TextView) itemView.findViewById(R.id.EmpEmailId);

            layoutItem = (LinearLayout) itemView.findViewById(R.id.layoutItem);
        }
    }
}
