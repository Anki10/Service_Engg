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
import com.app.winklix.service_engg.R;

import java.util.ArrayList;

public class Monthly_Reports_Adapter extends RecyclerView.Adapter<Monthly_Reports_Adapter.MyHolder> {

    private ArrayList<Monthly_Reports_Holder> userList;
    private Context mcontext;

    public class MyHolder extends RecyclerView.ViewHolder {
        LinearLayout layoutItem;
        TextView itemTextViewRegularSticky;

        public MyHolder(View itemView) {
            super(itemView);

            itemTextViewRegularSticky = (TextView) itemView.findViewById(R.id.itemTextViewRegular);
            layoutItem = (LinearLayout) itemView.findViewById(R.id.layoutItem);

        }
    }

    public Monthly_Reports_Adapter(Context montext, ArrayList<Monthly_Reports_Holder> msgArrayList) {

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
        final Monthly_Reports_Holder mOdelq = userList.get(position);

        holder.itemTextViewRegularSticky.setText("Ticket No : 10" + position);

        holder.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("position", position);
                bundle.putString("autoId", mOdelq.getMonthly_report_Id());
                mcontext.startActivity(new Intent(mcontext, Call_Detail_Activity.class));

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
