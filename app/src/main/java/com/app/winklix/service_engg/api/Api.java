package com.app.winklix.service_engg.api;

import com.app.winklix.service_engg.pojo.GetAdminLatLongResponse;
import com.app.winklix.service_engg.pojo.GetAllEmployeeList;
import com.app.winklix.service_engg.pojo.GetLatLongResponse;
import com.app.winklix.service_engg.pojo.GetPendingStatusPojo;
import com.app.winklix.service_engg.pojo.GetSyncDataPojo;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by Administrator on 1/3/2018.
 */

public interface Api {

    @POST("/location")
    void LatLongUpdate(@Body Object object, @Query("EmployeeApIid") String EmployeeApIid, @Query("TrackDateTime") String TrackDateTime, @Query("LocationLat") Double LocationLat, @Query("LocationLong") Double LocationLong, Callback<GetLatLongResponse> callback);

    @GET("/location")
    void GetLatLong(@Query("EmployeeAPIid") String EmployeeAPIid, Callback<GetAdminLatLongResponse> callback);

    @GET("/allemployee")
    void GetAllEmployeeReport(Callback<GetAllEmployeeList> callback);

    @GET("/pending")
    void GetPendingStatus(Callback<GetPendingStatusPojo>callback);

    @POST("/syncnow")
    void SyncData(@Body Object object, @Query("table") String table ,@Query("pending") String pending, Callback<GetSyncDataPojo> callback);

}
