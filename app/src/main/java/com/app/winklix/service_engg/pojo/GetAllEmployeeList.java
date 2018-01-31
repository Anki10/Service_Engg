package com.app.winklix.service_engg.pojo;

import java.util.ArrayList;

/**
 * Created by Administrator on 1/3/2018.
 */

public class GetAllEmployeeList {

  private ArrayList<GetAllEmployeeListData> data;
    private String success;

    public ArrayList<GetAllEmployeeListData> getData() {
        return data;
    }

    public void setData(ArrayList<GetAllEmployeeListData> data) {
        this.data = data;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }


}
