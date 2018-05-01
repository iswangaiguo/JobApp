package com.example.jobapp;

public class Postwill {
    String userId,userName,willname,locat,money,willdata,phone,school,professional,workexperience;

    public Postwill(String userId, String userName, String willname, String locat, String money, String willdata, String phone, String school, String professional, String workexperience) {
        this.userId = userId;
        this.userName = userName;
        this.willname = willname;
        this.locat = locat;
        this.money = money;
        this.willdata = willdata;
        this.phone = phone;
        this.school = school;
        this.professional = professional;
        this.workexperience = workexperience;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getWillname() {
        return willname;
    }

    public String getLocat() {
        return locat;
    }

    public String getMoney() {
        return money;
    }

    public String getWilldata() {
        return willdata;
    }

    public String getPhone() {
        return phone;
    }

    public String getSchool() {
        return school;
    }

    public String getProfessional() {
        return professional;
    }

    public String getWorkexperience() {
        return workexperience;
    }
}
