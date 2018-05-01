package com.example.jobapp;

public class Person {
    private String name;
    private String local;
    private String school;
    private String phone;
    private String introduce;

    public Person(String name, String local, String school, String phone, String introduce) {
        this.name = name;
        this.local = local;
        this.school = school;
        this.phone = phone;
        this.introduce = introduce;
    }

    public String getName() {
        return name;
    }

    public String getLocal() {
        return local;
    }

    public String getSchool() {
        return school;
    }

    public String getPhone() {
        return phone;
    }

    public String getIntroduce() {
        return introduce;
    }
}
