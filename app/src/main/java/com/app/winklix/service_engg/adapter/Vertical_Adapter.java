package com.app.winklix.service_engg.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.winklix.service_engg.Call_Detail_Activity;
import com.app.winklix.service_engg.Edit_Details_Activity;
import com.app.winklix.service_engg.R;

import java.util.ArrayList;

public class Vertical_Adapter extends RecyclerView.Adapter<Vertical_Adapter.MyHolder> {

    private ArrayList<Call_detail_Holder> userList;
    private Context mcontext;


    public class MyHolder extends RecyclerView.ViewHolder {
        LinearLayout layoutItem;
        ImageView imageViews;
        TextView itemTextViewRegularSticky;
        TextView client_name;
        TextView client_loc;
        TextView datess;
        ImageView editMore;
        PopupMenu popupMenu;


        public MyHolder(View itemView) {
            super(itemView);

            itemTextViewRegularSticky = (TextView) itemView.findViewById(R.id.itemTextViewRegular);
            client_name = (TextView) itemView.findViewById(R.id.client_name);
            client_loc = (TextView) itemView.findViewById(R.id.client_loc);
            layoutItem = (LinearLayout) itemView.findViewById(R.id.layoutItem);
            datess = (TextView) itemView.findViewById(R.id.datess);
            editMore = (ImageView) itemView.findViewById(R.id.editMoreOption);
            popupMenu = new PopupMenu(mcontext, editMore);
            popupMenu.inflate(R.menu.pop_menu);
        }
    }

    public Vertical_Adapter(Context montext, ArrayList<Call_detail_Holder> msgArrayList) {

        this.mcontext = montext;
        this.userList = msgArrayList;

    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View myview = LayoutInflater.from(mcontext).inflate(R.layout.service_engg_item_row1, parent, false);

        return new MyHolder(myview);
    }


    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {
        final Call_detail_Holder mOdelq = userList.get(position);

        holder.itemTextViewRegularSticky.setText("Ticket No. : "+mOdelq.getCallId());
        holder.client_name.setText("Client : "+mOdelq.getCllName());
        holder.client_loc.setText("Client Location : "+mOdelq.getclientLocation());

        String split_date= mOdelq.getdates();
        holder.datess.setText("Date : "+split_date);

        holder.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("position", position);
                bundle.putString("autoId", mOdelq.getCallId());

             //   mcontext.startActivity(new Intent(mcontext, Call_Detail_Activity.class));
                Intent in = new Intent(mcontext, Call_Detail_Activity.class);
                in.putExtra("TicketTime", mOdelq.getTicketTime());
                in.putExtra("callid",mOdelq.getCallId());
                in.putExtra("CllName",mOdelq.getCllName());
                in.putExtra("clientLocation",mOdelq.getclientLocation());
                in.putExtra("address",mOdelq.getaddress());
                in.putExtra("dates",mOdelq.getdates());
                in.putExtra("contactno",mOdelq.getcontactno());
                in.putExtra("call_category",mOdelq.getcall_category());
                in.putExtra("model",mOdelq.getmodel());
                in.putExtra("contact_person_name",mOdelq.getcontact_person_name());
                in.putExtra("contact_person_no",mOdelq.getcontact_person_no());
                in.putExtra("cb_id",mOdelq.getcb_id());
                in.putExtra("call_transfer_at",mOdelq.getcall_transfer_at());
                in.putExtra("call_problem",mOdelq.getcall_problem());
                in.putExtra("TicketAPIid",mOdelq.getTicketAPIid());

                mcontext.startActivity(in);
               // ((Activity)mcontext).finish();

                holder.editMore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        holder.popupMenu.show();
                    }
                });
                holder.popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.menu_update:
                                Intent in = new Intent(mcontext, Edit_Details_Activity.class);
                                in.putExtra("TicketAPIid",mOdelq.getTicketAPIid());
                                mcontext.startActivity(in);
                             //  ((Activity)mcontext).finish();
                              //  mcontext.startActivity(new Intent(mcontext, Edit_Details_Activity.class));
                                break;
                        }
                        return false;
                    }
                });

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
