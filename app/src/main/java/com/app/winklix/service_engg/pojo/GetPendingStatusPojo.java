package com.app.winklix.service_engg.pojo;

/**
 * Created by Administrator on 1/5/2018.
 */

public class GetPendingStatusPojo {

    private String employee;
    private String resolution;
    private String tickets;
    private String PendingSync;

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getTickets() {
        return tickets;
    }

    public void setTickets(String tickets) {
        this.tickets = tickets;
    }

    public String getPendingSync() {
        return PendingSync;
    }

    public void setPendingSync(String pendingSync) {
        PendingSync = pendingSync;
    }
}
