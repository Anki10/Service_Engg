package com.app.winklix.service_engg;

import android.Manifest;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.provider.SyncStateContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;


import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import util.Constant;
import util.Methods;
import util.NetWorkCheck;
import util.Utility;


public class Edit_Details_Activity extends AppCompatActivity implements
        View.OnClickListener {

    ImageView imageUpload;
    Button upload,submit;
    EditText call_status;
    private String userChoosenTask;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    String encodedImage;
    File destination;
    String destination1 ="";
    private Context context;
    ProgressBar _proProgressBar;

    EditText txtDate, txtTime, endTime,txtSolution;
    private int mYear, mMonth, mDay, mHour, mMinute;
    String EmployeeAPIid;
    String Sdate,Sstart_time,Send_time,Scomments,Sstatus,TicketAPIid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rply_call_data);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        context=Edit_Details_Activity.this;

        EmployeeAPIid =  Methods.getEmployeeAPIid(this);
        Intent in = getIntent();
        TicketAPIid = in.getExtras().getString("TicketAPIid");
        System.out.println("string_cbid"+EmployeeAPIid +" : "+TicketAPIid);

        if (android.os.Build.VERSION.SDK_INT > 9)
        {StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();StrictMode.setThreadPolicy(policy);}


        txtDate = findViewById(R.id.txtDate);
        txtTime = findViewById(R.id.txtTime);
        endTime = findViewById(R.id.endTime);
        txtSolution = findViewById(R.id.txtSolution);
        submit = findViewById(R.id.submit);

        txtDate.setOnClickListener(this);
        txtTime.setOnClickListener(this);
        endTime.setOnClickListener(this);
        submit.setOnClickListener(this);


        _proProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        upload = findViewById(R.id.upload);
        call_status = findViewById(R.id.call_status);
        call_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(Edit_Details_Activity.this, call_status);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.call_status, popup.getMenu());
                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {

                        String type = item.getTitle().toString();
                        switch(type) {
                            case "Pending":
                                call_status .setText("Pending");
                                Sstatus = "Pending";
                                Toast.makeText(Edit_Details_Activity.this,item.getTitle(),Toast.LENGTH_SHORT).show();
                                break;

                            case "Transfer":
                                call_status .setText("Transfer");
                                Sstatus = "Transfer";
                                Toast.makeText(Edit_Details_Activity.this,item.getTitle(),Toast.LENGTH_SHORT).show();
                                break;

                            case "Resolved":
                                call_status .setText("Resolved");
                                Sstatus = "Resolved";
                                Toast.makeText(Edit_Details_Activity.this,item.getTitle(),Toast.LENGTH_SHORT).show();
                                break;
                        }
                        return true;
                    }
                });
                popup.show();//showing popup menu
            }
        });

        imageUpload = findViewById(R.id.imageview);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == txtDate) {
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            txtDate.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                        }
                    }, mYear, mMonth, mDay);datePickerDialog.show();
        }

        if (v == txtTime) {
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            txtTime.setText(hourOfDay + ":" + minute);
                        }}, mHour, mMinute, false);
            timePickerDialog.show();
        }
        if (v == endTime) {
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            endTime.setText(hourOfDay + ":" + minute);
                        }}, mHour, mMinute, false);
            timePickerDialog.show();
        }

        if (v == submit) {
            if (isValid()) {
                if (NetWorkCheck.checkConnection(this)) {
                    Sdate = txtDate.getText().toString();
                    Sstart_time = txtTime.getText().toString();
                    Send_time = endTime.getText().toString();
                    Scomments = txtSolution.getText().toString();

                    RequestQueue queue = Volley.newRequestQueue(Edit_Details_Activity.this);
                    String url = Constant.BASE_API_URL+"resolution";

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    // create JSON obj from string
                                    try {
                                        JSONObject json = new JSONObject(response);
                                        String success = json.getString("success");


                                        if(success!=null){
                                            if(success.equalsIgnoreCase("1")){
                                                Toast.makeText(context,"Successfully update call data",Toast.LENGTH_LONG).show();
                                                Intent in1 = new Intent(context, MainActivity.class);
                                                in1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                startActivity(in1);
                                             //   finish();
                                            }
                                            else{ Toast.makeText(context,success,Toast.LENGTH_LONG).show();}
                                        }

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


                                    System.out.println("response_response"+response);
                                    _proProgressBar.setVisibility(View.GONE);
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            {

                                Toast.makeText(context,"Internet is slow! or Server not responding!",Toast.LENGTH_LONG).show();}
                            _proProgressBar.setVisibility(View.GONE);
                        }
                    }) {
                        //adding parameters to the request
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();

                            params.put("EmployeeAPIid", EmployeeAPIid);
                            params.put("TicketAPIid", TicketAPIid);
                            params.put("TakeCompleteDate", Sdate);
                            params.put("TakeCompleteStartTime", Sstart_time);
                            params.put("TakeCompleteEndTime", Send_time);
                            params.put("CallSolution", Scomments);
                            params.put("ImageCaptured", encodedImage);
                            params.put("CallStatus", Sstatus);
                            return params;
                        }
                    };
                    // Add the request to the RequestQueue.
                    queue.add(stringRequest);
                    _proProgressBar.setVisibility(View.VISIBLE);

                } else {
                    Toast.makeText(this, "Internet connection is disable", Toast.LENGTH_LONG).show();
                }
            }
        }
    }






    private void selectImage() {
        final CharSequence[] items = {"Take Photo", "Choose from Library", "Cancel"};
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(Edit_Details_Activity.this);
        builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result = Utility.checkPermission(Edit_Details_Activity.this);
                if (items[item].equals("Take Photo")) {
                    userChoosenTask = "Take Photo";
                    if (result)
                        cameraIntent();
                } else if (items[item].equals("Choose from Library")) {
                    userChoosenTask = "Choose from Library";
                    if (result)
                        galleryIntent();
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
    private void galleryIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE);
    }
    private void cameraIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data);
            else if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data);
        }
    }
    private void onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 70, bytes);
        destination = new File(Environment.getExternalStorageDirectory(), System.currentTimeMillis() + ".jpg");
        System.out.println("destination_destination"+String.valueOf(destination));
        destination1 =String.valueOf(destination);

        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Bitmap converetdImage = getResizedBitmap(thumbnail, 300);
        imageUpload.setImageBitmap(converetdImage);
        imageUpload.setVisibility(View.VISIBLE);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        converetdImage.compress(Bitmap.CompressFormat.PNG, 70, outputStream);
        byte[] b = outputStream.toByteArray();
        encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
        System.out.println("capture"+encodedImage);
    }
    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {
        Bitmap bm=null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Bitmap converetdImage = getResizedBitmap(bm, 300);
        imageUpload.setImageBitmap(converetdImage);
        imageUpload.setVisibility(View.VISIBLE);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        converetdImage.compress(Bitmap.CompressFormat.PNG, 70, outputStream);
        byte[] b = outputStream.toByteArray();
        encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
        System.out.println("gallery"+encodedImage);
    }

    public void showToast(String message)
    {Toast.makeText(this,message,Toast.LENGTH_SHORT).show();}

    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();
        float bitmapRatio = (float)width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }



    private boolean isValid() {
        final String txtDate1 = txtDate.getText().toString();
        final String txtTime1 = txtTime.getText().toString();
        final String endTime1 =endTime.getText().toString();
        final String txtSolution1 =txtSolution.getText().toString();
        final String call_status1 =call_status.getText().toString();
        if (!isValidName(txtDate1)) {
            txtDate.setError("Invalid Date");
            return false;
        }
        else if (!isValidName(txtTime1)) {
            txtTime.setError("Invalid Start Time");
            return false;
        }
        else if (!isValidName(endTime1)) {
            endTime.setError("Invalid End Time");
            return false;
        }
        else if (!isValidName(txtSolution1)) {
            txtSolution.setError("Invalid Solution");
            return false;
        }else if (!isValidName(call_status1)) {
            call_status.setError("Invalid call status");
            return false;
        }
        else if (!isValidName(encodedImage)) {
            call_status.setError("Invalid call status");
            Toast.makeText(getApplicationContext(),"Empty image",Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private boolean isValidName(String name1) {
        if (name1 != null && name1.length() > 0) {
            return true;
        }
        return false;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);

    }
}
