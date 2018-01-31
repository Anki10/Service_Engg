package com.app.winklix.service_engg.adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.winklix.service_engg.Edit_User;
import com.app.winklix.service_engg.MapsActivityMain;
import com.app.winklix.service_engg.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import util.Constant;
import util.CookieStore;

public class AdmistrativeVertical_Adapter extends RecyclerView.Adapter<AdmistrativeVertical_Adapter.MyHolder> {

    private ArrayList<Employee_details_Holder> userList;
    private Context mcontext;
    private OkHttpClient client;
    ProgressDialog pd;
    private static final String TAG = "tag" ;
    JSONObject responseObj;
    String  mes;
    Request request;

    public class MyHolder extends RecyclerView.ViewHolder {
        LinearLayout layoutItem;
        ImageView imageViews;
        TextView itemTextViewRegularSticky;

        TextView EmpId;
        TextView EmpName;
        TextView EmpMobNo;
        TextView EmpEmailId;
        TextView operation;
        Button edit,delete;


        public MyHolder(View itemView) {
            super(itemView);

            if (android.os.Build.VERSION.SDK_INT > 9)
            {StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();StrictMode.setThreadPolicy(policy);}
            client = new OkHttpClient();
            client = new OkHttpClient.Builder()
                    .cookieJar(new CookieStore())
                    .build();


            itemTextViewRegularSticky = (TextView) itemView.findViewById(R.id.CBID);
            EmpId = (TextView) itemView.findViewById(R.id.EmpId);
            EmpName = (TextView) itemView.findViewById(R.id.EmpName);
            EmpMobNo = (TextView) itemView.findViewById(R.id.EmpMobNo);
            operation = (TextView) itemView.findViewById(R.id.operation);
            EmpEmailId = (TextView) itemView.findViewById(R.id.EmpEmailId);
            edit =  itemView.findViewById(R.id.edit);
            delete =  itemView.findViewById(R.id.delete);

            layoutItem = (LinearLayout) itemView.findViewById(R.id.layoutItem);

        }
    }

    public AdmistrativeVertical_Adapter(Context montext, ArrayList<Employee_details_Holder> msgArrayList) {

        this.mcontext = montext;
        this.userList = msgArrayList;

    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View myview = LayoutInflater.from(mcontext).inflate(R.layout.admistrative_item_row1, parent, false);

        return new MyHolder(myview);
    }


    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {
        final Employee_details_Holder mOdelq = userList.get(position);

        holder.itemTextViewRegularSticky.setText("CBID : " + mOdelq.getCBID());
        holder.EmpId.setText("EmpId : " + mOdelq.getEmpId());
        holder.EmpName.setText("EmpName : " + mOdelq.getEmpName());
        holder.EmpMobNo.setText("EmpMobNo : " + mOdelq.getEmpMobNo());
        holder.EmpEmailId.setText("EmpEmailId : " + mOdelq.getEmpEmailId());

        if(mOdelq.getOperation().equalsIgnoreCase("E")){
            holder.operation.setText("EDIT");
        }
        else if(mOdelq.getOperation().equalsIgnoreCase("A")){
            holder.operation.setText("ADD");
        }
        else  if (mOdelq.getOperation().equalsIgnoreCase("D")){
            holder.operation.setText("DELETE");
        }

        holder.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(mcontext, MapsActivityMain.class);
                in.putExtra("EmployeeAPIid",mOdelq.getEmployeeAPIid());
                mcontext.startActivity(in);
            }
        });

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  Intent in = new Intent(mcontext, Edit_User.class);
                  in.putExtra("EmployeeAPIid",mOdelq.getEmployeeAPIid());
                  in.putExtra("CBID",mOdelq.getCBID());
                  in.putExtra("EmpId",mOdelq.getEmpId());
                  in.putExtra("EmpName",mOdelq.getEmpName());
                  in.putExtra("EmpMobNo",mOdelq.getEmpMobNo());
                  in.putExtra("EmpEmailId",mOdelq.getEmpEmailId());
                  mcontext.startActivity(in);
                ((Activity)mcontext).finish();
            }
        });



        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    new AsyncTask<String, Void, String>() {
                        @Override
                        protected void onPreExecute() {
                            super.onPreExecute();
                            pd = new ProgressDialog(mcontext);
                            pd.setTitle("Delete...");
                            pd.setMessage("Please wait.");
                            pd.setCancelable(false);
                            pd.setIndeterminate(true);
                            pd.show();
                        }
                        @Override
                        protected String doInBackground(String... params) {
                            RequestBody formBody = new FormBody.Builder()
                                    .add("_method", "DELETE")
                                    .build();
                            request = new Request.Builder()
                                    .url(Constant.BASE_API_URL+"employee/"+mOdelq.getEmployeeAPIid())
                                    .post(formBody)
                                    .build();
                            client.newCall(request).enqueue(loginCallback);
                            return null;
                        }
                        @Override
                        protected void onPostExecute(String result) {
                            super.onPostExecute(result);
                        }
                    }.execute();
                }

                Callback loginCallback = new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        try {
                            Log.i(TAG, "login failed: " + call.execute().code());
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }   }
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
                                        Toast.makeText(mcontext,"Operation change to Delete",Toast.LENGTH_LONG).show();
                                        holder.operation.setText("DELETE");
                                        // startActivity(new Intent(context,Admistrative_MainActivity.class));
                                        //finish();
                                    }

                                    else{ Toast.makeText(mcontext,"Not Valid User",Toast.LENGTH_LONG).show();}
                                }
                                else{  Toast.makeText(mcontext,"Server not responding!",Toast.LENGTH_LONG).show();}
                                Log.i(TAG, "responseObj: " + responseObj + mes);
                            }
                        }, 100 );
                        if (pd != null) {
                            pd.dismiss();
                            pd = null;
                        }
                    }  };

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
