package com.example.huythanh.model;

import java.io.Serializable;

public class Contact implements Serializable {
    String ten;
    String phone;

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Contact() {
    }

    public Contact(String ten, String phone) {
        this.ten = ten;
        this.phone = phone;
    }
}
