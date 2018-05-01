package com.example.jobapp;

public class Record {
    String title,location,number,type,companyName;

    public Record(String title, String location, String number, String type, String companyName) {
        this.title = title;
        this.location = location;
        this.number = number;
        this.type = type;
        this.companyName = companyName;
    }

    public String getTitle() {
        return title;
    }

    public String getLocation() {
        return location;
    }

    public String getNumber() {
        return number;
    }

    public String getType() {
        return type;
    }

    public String getCompanyName() {
        return companyName;
    }
}
