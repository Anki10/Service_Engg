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
import com.app.winklix.service_engg.Call_Status_Activity;
import com.app.winklix.service_engg.R;

import java.util.ArrayList;

public class Call_Status_Adapter extends RecyclerView.Adapter<Call_Status_Adapter.MyHolder> {

    private ArrayList<Call_Status_Holder> userList;
    private Context mcontext;

    public class MyHolder extends RecyclerView.ViewHolder {
        LinearLayout layoutItem;
        TextView itemTextViewRegularSticky;
        TextView cbid;
        TextView CallStatus;
        TextView CallSolution;

        public MyHolder(View itemView) {
            super(itemView);

            itemTextViewRegularSticky = (TextView) itemView.findViewById(R.id.itemTextViewRegular);

            cbid = (TextView) itemView.findViewById(R.id.cbid);
            CallStatus = (TextView) itemView.findViewById(R.id.CallStatus);
            CallSolution = (TextView) itemView.findViewById(R.id.CallSolution);
            layoutItem = (LinearLayout) itemView.findViewById(R.id.layoutItem);

        }
    }

    public Call_Status_Adapter(Context montext, ArrayList<Call_Status_Holder> msgArrayList) {

        this.mcontext = montext;
        this.userList = msgArrayList;

    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View myview = LayoutInflater.from(mcontext).inflate(R.layout.service_engg_call_report, parent, false);

        return new MyHolder(myview);
    }


    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {
        final Call_Status_Holder mOdelq = userList.get(position);

      //  holder.itemTextViewRegularSticky.setText("Ticket No : 10" + position);
        holder.itemTextViewRegularSticky.setText("Ticket Id : " + mOdelq.getCallId());
        holder.cbid.setText("CBID : " + mOdelq.getCBID());
        holder.CallStatus.setText("Call Status: " + mOdelq.getCallStatus());
        holder.CallSolution.setText("Call Solution : " + mOdelq.getCallSolution());

        holder.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //  Bundle bundle = new Bundle();
               //   bundle.putInt("position", position);
              //    bundle.putString("autoId", mOdelq.getCall_status_Id());
                Intent in = new Intent(mcontext, Call_Status_Activity.class);
                in.putExtra("TicketId", mOdelq.getCllName());
                in.putExtra("TicketTime", mOdelq.getTicketTime());
                in.putExtra("callid",mOdelq.getCallId());
                in.putExtra("CllName",mOdelq.getCllName());
                in.putExtra("clientLocation",mOdelq.getclientLocation());
                in.putExtra("address",mOdelq.getaddress());
                in.putExtra("dates",mOdelq.getdates());
                in.putExtra("contactno",mOdelq.getcontactno());
                in.putExtra("call_category",mOdelq.getcall_category());
                in.putExtra("call_sub_category",mOdelq.getCallSubCategory());
                in.putExtra("model",mOdelq.getmodel());
                in.putExtra("contact_person_name",mOdelq.getcontact_person_name());
                in.putExtra("contact_person_no",mOdelq.getcontact_person_no());
                in.putExtra("cb_id",mOdelq.getcb_id());
              //  in.putExtra("call_transfer_at",mOdelq.getcall_transfer_at());
                in.putExtra("call_problem",mOdelq.getcall_problem());
                in.putExtra("TicketAPIid",mOdelq.getTicketAPIid());
                mcontext.startActivity(in);

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
