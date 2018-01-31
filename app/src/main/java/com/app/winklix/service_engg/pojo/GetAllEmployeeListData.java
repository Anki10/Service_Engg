package com.app.winklix.service_engg.pojo;

import java.lang.ref.SoftReference;

/**
 * Created by Administrator on 1/3/2018.
 */

public class GetAllEmployeeListData {

    private String EmployeeAPIid;
    private String CBID;
    private String EmpId;
    private String EmpName;
    private String EmpMobNo;
    private String EmpEmailId;
    private String Operation;

    public String getEmployeeAPIid() {
        return EmployeeAPIid;
    }

    public void setEmployeeAPIid(String employeeAPIid) {
        EmployeeAPIid = employeeAPIid;
    }

    public String getCBID() {
        return CBID;
    }

    public void setCBID(String CBID) {
        this.CBID = CBID;
    }

    public String getEmpId() {
        return EmpId;
    }

    public void setEmpId(String empId) {
        EmpId = empId;
    }

    public String getEmpName() {
        return EmpName;
    }

    public void setEmpName(String empName) {
        EmpName = empName;
    }

    public String getEmpMobNo() {
        return EmpMobNo;
    }

    public void setEmpMobNo(String empMobNo) {
        EmpMobNo = empMobNo;
    }

    public String getEmpEmailId() {
        return EmpEmailId;
    }

    public void setEmpEmailId(String empEmailId) {
        EmpEmailId = empEmailId;
    }

    public String getOperation() {
        return Operation;
    }

    public void setOperation(String operation) {
        Operation = operation;
    }


}
