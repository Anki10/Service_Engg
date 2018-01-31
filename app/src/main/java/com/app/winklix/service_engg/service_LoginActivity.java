package com.app.winklix.service_engg;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Message;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.os.Handler;
import android.os.Looper;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import util.Constant;
import util.CookieStore;
import util.Methods;
import util.NetWorkCheck;


public class service_LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private Context context;
    private Button btn_user_login;
    EditText cb_id, phone, password;
    String Scb_id,Sphone,Spassword;
    ProgressBar _proProgressBar;

     static String EmployeeAPIid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = service_LoginActivity.this;

        if (android.os.Build.VERSION.SDK_INT > 9)
        {StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();StrictMode.setThreadPolicy(policy);}

        _proProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        btn_user_login = findViewById(R.id.btn_user_login);
        cb_id = findViewById(R.id.cb_id);
        phone = findViewById(R.id.phone);
        password = findViewById(R.id.password);
        btn_user_login.setOnClickListener(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
       if (isValid()) {
            Scb_id = cb_id.getText().toString();
            Sphone= phone.getText().toString();
            Spassword= password.getText().toString();

            if (NetWorkCheck.checkConnection(this)) {
                RequestQueue queue = Volley.newRequestQueue(service_LoginActivity.this);
                String url = Constant.BASE_API_URL+"login";

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
                                            EmployeeAPIid = json.optString("EmployeeAPIid");

                                            Methods.saveEmployeeAPIid(service_LoginActivity.this,EmployeeAPIid);

                                            SharedPreferences sp1 = getApplicationContext().getSharedPreferences("userlogin", Context.MODE_PRIVATE);
                                            SharedPreferences.Editor editor1=sp1.edit();
                                            editor1.putString("user","service");
                                            editor1.commit();
                                            Toast.makeText(context,"successfully login",Toast.LENGTH_LONG).show();
                                            startActivity(new Intent(context,MainActivity.class));
                                            finish();
                                        }
                                        else{ Toast.makeText(context,"Not Valid User",Toast.LENGTH_LONG).show();}
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
                        {  Toast.makeText(context,"Internet is slow! or Server not responding!",Toast.LENGTH_LONG).show();}
                        _proProgressBar.setVisibility(View.GONE);
                    }
                }) {
                    //adding parameters to the request
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("CBID", Scb_id);
                        params.put("EmpId", Sphone);
                        params.put("Password", Spassword);
                        params.put("DeviceId", "1234");

                        return params;
                    }
                };
                queue.add(stringRequest);
                _proProgressBar.setVisibility(View.VISIBLE);
            } else {
                Toast.makeText(this, "Internet connection is disable", Toast.LENGTH_LONG).show();
            }
        }
    }






    private boolean isValid() {
        final String name3 = cb_id.getText().toString();
        final String mobile3 = phone.getText().toString();
        final String pass3 =password.getText().toString();
        if (!isValidName(name3)) {
            cb_id.setError("Invalid CB ID");
            return false;
        }
        else if (!isValidName(mobile3)) {
            phone.setError("Invalid Employee Id");
            return false;
        }
        else if (!isValidPassword(pass3)) {
            password.setError("Invalid Password");
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
    private boolean isValidMobile(String mobile1) {
        if (mobile1 != null && mobile1.length() > 9  && mobile1.length() < 13 ) {
            return true;
        }
        return false;
    }
    private boolean isValidPassword(String pass1) {
        if (pass1 != null && pass1.length() > 2) {
            return true;
        }
        return false;
    }



}
