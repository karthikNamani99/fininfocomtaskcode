package com.d.fininfocomtask.actvities;

public class UserRegister {

    String uName,uCity,uAge;

    public UserRegister(String uName, String uCity, String uAge) {
        this.uName = uName;
        this.uCity = uCity;
        this.uAge = uAge;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getuCity() {
        return uCity;
    }

    public void setuCity(String uCity) {
        this.uCity = uCity;
    }

    public String getuAge() {
        return uAge;
    }

    public void setuAge(String uAge) {
        this.uAge = uAge;
    }
}
