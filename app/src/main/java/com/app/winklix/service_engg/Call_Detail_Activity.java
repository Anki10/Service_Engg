package com.app.winklix.service_engg;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Call_Detail_Activity extends AppCompatActivity {
TextView cbid, ticketno,ticketdate,tickettime,clientname,cleintaddress,clientlocation,cleintcategary,models,callproblem,contactperson;
Button reply;String TicketAPIid;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call__detail_);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        context = Call_Detail_Activity.this;
        Intent in = getIntent();
        String callid = in.getExtras().getString("callid");
        String CllName = in.getExtras().getString("CllName");
        String clientLocation = in.getExtras().getString("clientLocation");
        String dates = in.getExtras().getString("dates");
        String TicketTime = in.getExtras().getString("TicketTime");
        String contactno = in.getExtras().getString("contactno");
        String call_category = in.getExtras().getString("call_category");
        String model = in.getExtras().getString("model");
        String contact_person_name = in.getExtras().getString("contact_person_name");
        String contact_person_no = in.getExtras().getString("contact_person_no");
        String cb_id = in.getExtras().getString("cb_id");
        String call_transfer_at = in.getExtras().getString("call_transfer_at");
        String call_problem = in.getExtras().getString("call_problem");
        String address = in.getExtras().getString("address");
        TicketAPIid = in.getExtras().getString("TicketAPIid");


        ticketno = (TextView)findViewById(R.id.ticketno);
        ticketdate = (TextView)findViewById(R.id.ticketdate);
        tickettime = (TextView)findViewById(R.id.tickettime);
        clientname = (TextView)findViewById(R.id.clientname);
        cleintaddress = (TextView)findViewById(R.id.cleintaddress);
        clientlocation = (TextView)findViewById(R.id.clientlocation);
        cleintcategary = (TextView)findViewById(R.id.cleintcategary);
        models = (TextView)findViewById(R.id.model);
        callproblem = (TextView)findViewById(R.id.callproblem);
        contactperson = (TextView)findViewById(R.id.contactperson);
      //  cbid = (TextView)findViewById(R.id.cbid);

       // cbid.setText(cb_id);
        ticketno.setText(callid);
        ticketdate.setText(dates);
        tickettime.setText(TicketTime);
        clientname.setText(CllName);
        cleintaddress.setText(address);
        clientlocation.setText(clientLocation);
        cleintcategary.setText(call_category);
        models.setText(model);
        callproblem.setText(call_problem);
        contactperson.setText(contact_person_no);

        System.out.println("callid"+callid);

        reply =(Button)findViewById(R.id.reply);
        reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(context, Edit_Details_Activity.class);
                in.putExtra("TicketAPIid",TicketAPIid);
                startActivity(in);
              //  finish();
            }
        });

    }
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);

    }
}
