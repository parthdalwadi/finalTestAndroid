package com.example.parth_c0766346_feandroid;

import java.io.Serializable;

public class Contact implements Serializable {

    private int id;
    private String first_name, last_name, phone, address;


    public Contact(int id, String first_name, String last_name, String phone, String address) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone = phone;
        this.address = address;
    }

    public int getId() {
        return id;
    }
    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }
}
