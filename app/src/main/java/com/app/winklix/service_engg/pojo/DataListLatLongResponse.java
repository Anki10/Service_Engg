package com.app.winklix.service_engg.pojo;

/**
 * Created by Administrator on 1/3/2018.
 */

public class DataListLatLongResponse {

    private String TrackDateTime;
    private String LocationLat;
    private String LocationLong;

    public String getTrackDateTime() {
        return TrackDateTime;
    }

    public void setTrackDateTime(String trackDateTime) {
        TrackDateTime = trackDateTime;
    }

    public String getLocationLat() {
        return LocationLat;
    }

    public void setLocationLat(String locationLat) {
        LocationLat = locationLat;
    }

    public String getLocationLong() {
        return LocationLong;
    }

    public void setLocationLong(String locationLong) {
        LocationLong = locationLong;
    }

}
