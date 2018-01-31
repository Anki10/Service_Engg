package com.app.winklix.service_engg;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by Administrator on 1/3/2018.
 */

public class Call_Status_Activity extends AppCompatActivity {
    TextView ticketno,ticketdate,tickettime,clientname,cleintaddress,clientlocation,cleintcategary,models,callproblem,contactperson;

    Calendar dates;
    EditText CBID,call_transfer_at;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.call_status_edit);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
       // String cb_id = in.getExtras().getString("cb_id");
        //String call_transfer_at = in.getExtras().getString("call_transfer_at");
        String call_problem = in.getExtras().getString("call_problem");
        String address = in.getExtras().getString("address");



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

      /*  CBID=  (EditText)findViewById(R.id.CBID);
        call_transfer_at=  (EditText)findViewById(R.id.call_transfer);
        call_transfer_at.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimePicker();
            }
        });*/
    }

    public void showDateTimePicker() {
        final Calendar currentDate = Calendar.getInstance();
        dates = Calendar.getInstance();
        new DatePickerDialog(Call_Status_Activity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, final int year, final int monthOfYear, final int dayOfMonth) {
                dates.set(year, monthOfYear, dayOfMonth);
                //dates.setMinDate(System.currentTimeMillis() - 1000);
                call_transfer_at.setText((monthOfYear+1)+ "/" +dayOfMonth+ "/" +year);
            }
        },
                currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE)).show();
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);

    }
}
