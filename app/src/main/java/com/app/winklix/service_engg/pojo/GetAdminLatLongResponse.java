package com.app.winklix.service_engg.pojo;

import java.util.ArrayList;

/**
 * Created by Administrator on 1/3/2018.
 */

public class GetAdminLatLongResponse {

    private ArrayList<DataListLatLongResponse> data;
    private LinksLatLongResponse links;
    private String success;

    public ArrayList<DataListLatLongResponse> getData() {
        return data;
    }

    public void setData(ArrayList<DataListLatLongResponse> data) {
        this.data = data;
    }

    public LinksLatLongResponse getLinks() {
        return links;
    }

    public void setLinks(LinksLatLongResponse links) {
        this.links = links;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }




}
