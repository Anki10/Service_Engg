package com.app.winklix.service_engg;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import util.Constant;
import util.NetWorkCheck;

public class MapsActivityMain extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    static  String EmployeeAPIid;
    private static Response productresponse;
    private OkHttpClient client;
    public static final String TAG = "TAG";
    String TrackDateTime,LocationLat,LocationLong;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        client = new OkHttpClient();
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(30, TimeUnit.SECONDS);
        builder.readTimeout(30, TimeUnit.SECONDS);
        builder.writeTimeout(30, TimeUnit.SECONDS);
        client = builder.build();

        Intent in = getIntent();
        EmployeeAPIid = in.getExtras().getString("EmployeeAPIid");
        System.out.println("EmployeeAPIid"+EmployeeAPIid);

        if (NetWorkCheck.checkConnection(this)) {
            new GetDataMap().execute();
        } else {
            Toast.makeText(this, "Internet connection is disable", Toast.LENGTH_LONG).show();
        }
    }


    class GetDataMap extends AsyncTask<Void, Void, Void> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(MapsActivityMain.this);
            dialog.setMessage("Please Wait");
            dialog.show();
        }

        @Nullable
        @Override
        protected Void doInBackground(Void... params) {

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            JSONObject jsonObject = getDataFromWebproduct();
            try {
                if (jsonObject != null) {
                    if (jsonObject.length() > 0) {
                        JSONArray array = jsonObject.getJSONArray("data");
                        int lenArray = array.length();
                        if (lenArray > 0) {
                            for (int jIndex = 0; jIndex < lenArray; jIndex++) {

                                JSONObject innerObject = array.getJSONObject(jIndex);
                                TrackDateTime = innerObject.getString("TrackDateTime");
                                LocationLat = innerObject.getString("LocationLat");
                                LocationLong = innerObject.getString("LocationLong");

                                addMarkerToMap(LocationLat, LocationLong, TrackDateTime);

                            }
                        }
                    }
                } else {
                }
            } catch (JSONException je) {
                Log.i(TAG, "" + je.getLocalizedMessage());
            }
            dialog.dismiss();
        }
    }

    public void addMarkerToMap(String latitude, String longitude, String title)
    {
        double lat = Double.parseDouble(latitude);
        double lng = Double.parseDouble(longitude);

        MarkerOptions marker = new MarkerOptions().position(new LatLng(lat, lng))
                .title("Employee Time : "+title);
        marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));

        CameraPosition cameraPosition = new CameraPosition.Builder().target(
                new LatLng(lat, lng)).zoom(16).build();

        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        mMap.addMarker(marker);
    }

    public static JSONObject getDataFromWebproduct() {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(Constant.BASE_API_URL+"location?EmployeeAPIid="+EmployeeAPIid)
                    .build();
            productresponse = client.newCall(request).execute();
            return new JSONObject(productresponse.body().string());
        } catch (@NonNull IOException | JSONException e) {
            Log.e(TAG, "" + e.getLocalizedMessage());
        }
        return null;
    }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

    /*    // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(28.5355, 77.3910);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Noida"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/
    }
}
