package com.app.winklix.service_engg.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.winklix.service_engg.Call_Detail_Activity;
import com.app.winklix.service_engg.MapActivity;
import com.app.winklix.service_engg.R;
import com.app.winklix.service_engg.pojo.DataListLatLongResponse;

import java.util.ArrayList;

public class Daily_Reports_Adapter extends RecyclerView.Adapter<Daily_Reports_Adapter.MyHolder> {

    private ArrayList<DataListLatLongResponse> userList;
    private Context mcontext;

    public class MyHolder extends RecyclerView.ViewHolder {
        LinearLayout layoutItem;
        TextView employee_time;

        public MyHolder(View itemView) {
            super(itemView);

            employee_time = (TextView) itemView.findViewById(R.id.employee_time);
            layoutItem = (LinearLayout) itemView.findViewById(R.id.layoutItem);

        }
    }

    public Daily_Reports_Adapter(Context montext, ArrayList<DataListLatLongResponse> msgArrayList) {

        this.mcontext = montext;
        this.userList = msgArrayList;

    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View myview = LayoutInflater.from(mcontext).inflate(R.layout.service_engg_reports_row, parent, false);

        return new MyHolder(myview);
    }


    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {
        final DataListLatLongResponse mOdelq = userList.get(position);

        holder.employee_time.setText("Track time : "+mOdelq.getTrackDateTime());

        holder.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(mcontext, MapActivity.class);
               intent.putExtra("lat",mOdelq.getLocationLat());
               intent.putExtra("lon",mOdelq.getLocationLong());
               intent.putExtra("time",mOdelq.getTrackDateTime());
               mcontext.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    @Override
    public int getItemViewType(int position) {


        return super.getItemViewType(position);
    }
}
