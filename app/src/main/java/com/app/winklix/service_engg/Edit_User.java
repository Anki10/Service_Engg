package com.app.winklix.service_engg;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import util.Constant;
import util.CookieStore;
import util.NetWorkCheck;

/**
 * Created by Administrator on 1/3/2018.
 */

public class Edit_User extends AppCompatActivity implements View.OnClickListener {
    private Button btn_user_login;
    EditText cb_id, password,emp_id,name,contactno,emailid;
    String Scb_id,Semp_id,Spassword,Sname,Scontactno,Semailid;
    private Context context;
    private OkHttpClient client;
    ProgressDialog pd;
    private static final String TAG = "tag" ;
    JSONObject responseObj;
    String  mes;
    Request request;
    String EmployeeAPIid,CBID,EmpId,EmpName,EmpMobNo,EmpEmailId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__user);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        context = Edit_User.this;


        if (android.os.Build.VERSION.SDK_INT > 9)
        {StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();StrictMode.setThreadPolicy(policy);}
        client = new OkHttpClient();
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(30, TimeUnit.SECONDS);
        builder.readTimeout(30, TimeUnit.SECONDS);
        builder.writeTimeout(30, TimeUnit.SECONDS);
        client = builder.build();

        Intent in = getIntent();
        EmployeeAPIid = in.getExtras().getString("EmployeeAPIid");
        CBID = in.getExtras().getString("CBID");
        EmpId = in.getExtras().getString("EmpId");
        EmpName = in.getExtras().getString("EmpName");
        EmpMobNo = in.getExtras().getString("EmpMobNo");
        EmpEmailId = in.getExtras().getString("EmpEmailId");
        System.out.println("EmployeeAPIid"+EmployeeAPIid);

        cb_id = findViewById(R.id.cbid);
        password = findViewById(R.id.password);
        emp_id = findViewById(R.id.emp_id);
        name = findViewById(R.id.name);
        contactno = findViewById(R.id.contactno);
        emailid = findViewById(R.id.emailid);

        cb_id.setText(CBID);
        emp_id.setText(EmpId);
        name.setText(EmpName);
        contactno.setText(EmpMobNo);
        emailid.setText(EmpEmailId);


        btn_user_login =  findViewById(R.id.btn_user_login);
        btn_user_login.setOnClickListener(this);
    }



    @Override
    public void onClick(View view) {
        if (isValid()) {
            Scb_id = cb_id.getText().toString();
            Semp_id= emp_id.getText().toString();
            Spassword= password.getText().toString();
            Sname= name.getText().toString();
            Scontactno= contactno.getText().toString();
            Semailid= emailid.getText().toString();

            if (NetWorkCheck.checkConnection(this)) {
                AddUser();
            } else {
                Toast.makeText(this, "Internet connection is disable", Toast.LENGTH_LONG).show();
            }
        }

        // startActivity(new Intent(context,Admistrative_MainActivity.class));
    }

    private void AddUser() {
        new AsyncTask<String, Void, String>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                pd = new ProgressDialog(context);
                pd.setTitle("Edit User...");
                pd.setMessage("Please wait.");
                pd.setCancelable(false);
                pd.setIndeterminate(true);
                pd.show();
            }
            @Override
            protected String doInBackground(String... params) {

                return null;
            }
            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                RequestBody formBody = new FormBody.Builder()
                        .add("CBID", Scb_id)
                        .add("Password", Spassword)
                        .add("EmpId",Semp_id )
                        .add("EmpName", Sname)
                        .add("EmpMobNo", Scontactno)
                        .add("EmpEmailId", Semailid)
                        .add("_method", "PUT")
                        .build();
                request = new Request.Builder()
                        .url(Constant.BASE_API_URL+"employee/"+EmployeeAPIid)
                        .post(formBody)
                        .build();
                client.newCall(request).enqueue(loginCallback);
            }
        }.execute();
    }

    Callback loginCallback = new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            call.cancel();  }
        @Override
        public void onResponse(Call call, final Response response) throws IOException {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(new Runnable() {
                public void run() {
                    try {
                        responseObj = new JSONObject(response.body().string());
                        mes = responseObj.optString("success");
                        System.out.println("mes_mes"+mes);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if(mes!=null){

                        if(mes.equalsIgnoreCase("1")){
                            Toast.makeText(context,"Successfully Edit User",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(context,Admistrative_MainActivity.class));
                            finish();
                        }

                        else{ Toast.makeText(context,"Not Valid User",Toast.LENGTH_LONG).show();}
                    }
                    else{  Toast.makeText(context,"Server not responding!",Toast.LENGTH_LONG).show();}
                    Log.i(TAG, "responseObj: " + responseObj + mes);
                }
            }, 100 );
            if (pd != null) {
                pd.dismiss();
                pd = null;
            }
        }  };



    private boolean isValid() {
        final String cb_id1 = cb_id.getText().toString();
        final String mobile1 = contactno.getText().toString();
        final String pass1 =password.getText().toString();
        final String name1 =name.getText().toString();
        final String emp_id1 =emp_id.getText().toString();
        final String emailid1 =emailid.getText().toString();
        if (!isValidName(cb_id1)) {
            cb_id.setError("Invalid CB ID");
            return false;
        }
        else if (!isValidMobile(mobile1)) {
            contactno.setError("Invalid Mobile Number");
            return false;
        }
        else if (!isValidPassword(pass1)) {
            password.setError("Invalid Password");
            return false;
        }

        else if (!isValidEmail(emailid1)) {
            emailid .setError("Invalid Email");
            return false;
        }
        else if (!isValidName(emp_id1)) {
            emp_id .setError("Invalid Emp Id");
            return false;
        }
        else if (!isValidName(name1)) {
            name .setError("Name");
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
    private boolean isValidEmail(String email1) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email1);
        return matcher.matches();
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (pd != null) {
            pd.dismiss();
            pd = null;
        }
    }
}
