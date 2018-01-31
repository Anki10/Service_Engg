package com.app.winklix.service_engg.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.winklix.service_engg.Add_User;
import com.app.winklix.service_engg.Call_Status;
import com.app.winklix.service_engg.Daily_Reports;
import com.app.winklix.service_engg.Monthly_Reports;
import com.app.winklix.service_engg.R;
import com.app.winklix.service_engg.Service_engg_Reports;
import com.app.winklix.service_engg.activity.DailyReportActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * Created by ABC on 1/30/2017.
 */

public class Menu_adapter extends RecyclerView.Adapter<Menu_adapter.MyHolder> {

    private ArrayList<MenuHolder> userList;
    private Context mcontext;
    //private M_Shared_Pref m_shared_pref;

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView text;
        ImageView img;


        public MyHolder(View itemView) {
            super(itemView);

            text = (TextView) itemView.findViewById(R.id.text);
            img = (ImageView) itemView.findViewById(R.id.drawer_item_icon);

        }
    }

    public Menu_adapter(Context montext, ArrayList<MenuHolder> msgArrayList) {

        this.mcontext = montext;
        this.userList = msgArrayList;

    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View myview = LayoutInflater.from(mcontext).inflate(R.layout.navigation_menu, parent, false);
       // m_shared_pref = new M_Shared_Pref(mcontext);


        return new MyHolder(myview);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {
        final MenuHolder mOdelq = userList.get(position);

        holder.text.setText(mOdelq.getTitleName());
        Picasso.with(mcontext).load(userList.get(position).getIcons()).into(holder.img);
        holder.text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("id", String.valueOf(position));
                bundle.putString("userName", String.valueOf(mOdelq.getTitleName()));


                switch (position) {
                    case 0:
                        mcontext.startActivity(new Intent(mcontext, Add_User.class));

                        break;
//                        case 1:
//                        mcontext.startActivity(new Intent(mcontext, Service_engg_Reports.class));
//                        break;
                    case 1:
                        mcontext.startActivity(new Intent(mcontext, DailyReportActivity.class));
                        break;
                    case 2:
                        mcontext.startActivity(new Intent(mcontext, DailyReportActivity.class));
                        break;
                    case 3:
                        mcontext.startActivity(new Intent(mcontext, Call_Status.class));
                        break;



                }
            }
        });
    }

    private void share() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mcontext);
        builder.setTitle("Rate us...");
        builder.setMessage("* * * * *");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                //   m_shared_pref.setPrefranceValue(App_Api.IsLoggedIn, false);
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Company Name");
                sharingIntent.putExtra(Intent.EXTRA_TEXT, "Check all updates regarding Company on this app. App link will be updated soon... ");
                mcontext.startActivity(Intent.createChooser(sharingIntent, "Share via"));

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();

                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Company Name");
                sharingIntent.putExtra(Intent.EXTRA_TEXT, "Check all updates regarding Company on this app. App link will be updated soon... ");
                mcontext.startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });
        builder.create().show();

    }

    private void contact() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mcontext);
        builder.setTitle("Rate us...");
        builder.setMessage("Would you like to rate us?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                //   m_shared_pref.setPrefranceValue(App_Api.IsLoggedIn, false);
               // mcontext.startActivity(new Intent(mcontext, Contacts_us.class));

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();

               // mcontext.startActivity(new Intent(mcontext, Contacts_us.class));
            }
        });
        builder.create().show();
    }

    private void logoutDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(mcontext);
        builder.setTitle("Logout...");
        builder.setMessage("Are you sure to Logout ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
             //   m_shared_pref.setPrefranceValue(App_Api.IsLoggedIn, false);
               // mcontext.startActivity(new Intent(mcontext, Signu_in_Actvity.class));


            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.create().show();
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
