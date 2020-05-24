package com.example.tellycaller;

public class FollowModel {

    String Name;
    String Mobile_num;
    String Status;
    String Appointment_Dt_Tm;
    String Remarks;

    public FollowModel(){

    }

    public FollowModel(String Name, String Mobile_num, String Status, String Appointment_Dt_Tm, String Remarks) {
        this.Name = Name;
        this.Mobile_num = Mobile_num;
        this.Status = Status;
        this.Appointment_Dt_Tm = Appointment_Dt_Tm;
        this.Remarks = Remarks;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getMobile_num() {
        return Mobile_num;
    }

    public void setMobile_num(String mobile_num) {
        Mobile_num = mobile_num;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getAppointment_Dt_Tm() {
        return Appointment_Dt_Tm;
    }

    public void setAppointment_Dt_Tm(String appointment_Dt_Tm) {
        Appointment_Dt_Tm = appointment_Dt_Tm;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
    }
}
