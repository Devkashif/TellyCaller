package com.example.tellycaller;


public class CustomerModel {

     String Name;
     String Mobile_num;


   public CustomerModel(){

   }

    public CustomerModel(String Name, String Mobile_num) {
        this.Name = Name;
        this.Mobile_num = Mobile_num;
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
}
