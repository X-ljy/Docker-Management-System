package com.ljy.springboot.docker.managementsystem.entity;

public class User {
    private String phonenumber;
    private String name;
    private String passwd;

    public String getNumberPhone() {
        return phonenumber;
    }

    public void setNumberPhone(String numberPhone) {
        this.phonenumber = numberPhone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

}
