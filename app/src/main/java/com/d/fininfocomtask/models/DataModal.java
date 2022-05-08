package com.d.fininfocomtask.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class DataModal extends RealmObject {
    // on below line we are creating our variables
    // and with are using primary key for our id.

    @PrimaryKey
    private long id;
    private String uName;
    private String uAge;
    private String uCity;


    // on below line we are
    // creating an empty constructor.
    public DataModal() {
    }

    // below line we are
    // creating getter and setters.

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getuAge() {
        return uAge;
    }

    public void setuAge(String uAge) {
        this.uAge = uAge;
    }

    public String getuCity() {
        return uCity;
    }

    public void setuCity(String uCity) {
        this.uCity = uCity;
    }
}
