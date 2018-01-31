package com.app.winklix.service_engg.adapter;

/**
 * Created by vishal_keedu on 8/29/17.
 */

public class Call_detail_Holder {
    String callId,TicketAPIid,TicketTime,cllName,clientLocation, dates , contactno,call_category,model,contact_person_name,contact_person_no,cb_id
            ,call_transfer_at, call_problem, address;


    public String getTicketTime() {return TicketTime;}
    public void setTicketTime(String TicketTime) {this.TicketTime = TicketTime;}

    public String getTicketAPIid() {return TicketAPIid;}
    public void setTicketAPIid(String TicketAPIid) {this.TicketAPIid = TicketAPIid;}

    public String getaddress() {
        return address;
    }
    public void setaddress(String address) {this.address = address;}

    public String getcall_problem() {
        return call_problem;
    }
    public void setcall_problem(String call_problem) {this.call_problem = call_problem;}

    public String getcall_transfer_at() {
        return call_transfer_at;
    }
    public void setcall_transfer_at(String call_transfer_at) {this.call_transfer_at = call_transfer_at;}

    public String getcb_id() {
        return cb_id;
    }
    public void setcb_id(String cb_id) {this.cb_id = cb_id;}

    public String getcontact_person_no() {
        return contact_person_no;
    }
    public void setcontact_person_no(String contact_person_no) {this.contact_person_no = contact_person_no;}

    public String getcontact_person_name() {
        return contact_person_name;
    }
    public void setcontact_person_name(String contact_person_name) {this.contact_person_name = contact_person_name;}

    public String getmodel() {
        return model;
    }
    public void setmodel(String model) {this.model = model;}

    public String getcall_category() {
        return call_category;
    }
    public void setcall_category(String call_category) {
        this.call_category = call_category;
    }

    public String getcontactno() {
        return contactno;
    }
    public void setcontactno(String contactno) {
        this.contactno = contactno;
    }

    public String getCallId() {
        return callId;
    }
    public void setCallId(String callId) {
        this.callId = callId;
    }

    public String getCllName() {
        return cllName;
    }

    public void setCllName(String cllName) {
        this.cllName = cllName;
    }

    public String getclientLocation() {
        return clientLocation;
    }

    public void setclientLocation(String callImage) {
        this.clientLocation = callImage;
    }

    public String getdates() {
        return dates;
    }

    public void setdates(String dates) {
        this.dates = dates;
    }
}
