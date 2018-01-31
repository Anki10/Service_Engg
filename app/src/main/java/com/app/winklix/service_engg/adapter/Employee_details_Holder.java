package com.app.winklix.service_engg.adapter;

/**
 * Created by Administrator on 1/3/2018.
 */

public class Employee_details_Holder  {
        String CBID,EmpId,EmpName,EmpMobNo,EmpEmailId, Operation,EmployeeAPIid;


        public String getEmployeeAPIid() {return EmployeeAPIid;}
        public void setEmployeeAPIid(String EmployeeAPIid) {this.EmployeeAPIid = EmployeeAPIid;}

        public String getOperation() {return Operation;}
        public void setOperation(String Operation) {this.Operation = Operation;}

        public String getCBID() {return CBID;}
        public void setCBID(String CBID) {this.CBID = CBID;}

        public String getEmpId() {
            return EmpId;
        }
        public void setEmpId(String EmpId) {this.EmpId = EmpId;}

        public String getEmpName() {
            return EmpName;
        }
        public void setEmpName(String EmpName) {this.EmpName = EmpName;}

        public String getEmpMobNo() {
            return EmpMobNo;
        }
        public void setEmpMobNo(String EmpMobNo) {this.EmpMobNo = EmpMobNo;}

        public String getEmpEmailId() {
            return EmpEmailId;
        }
        public void setEmpEmailId(String EmpEmailId) {this.EmpEmailId = EmpEmailId;}


    }

