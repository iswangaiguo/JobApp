package com.example.jobapp;

public class Work {
    private String title;
    private String item_location;
    private String item_postnum;
    private String item_type;
    private String item_company;
    private String posttime;
    private String resumeId;
    private String userId;
    private String jobdescrip;

    public Work(String title,String item_location,String item_postnum,String item_type,String item_company,String posttime,String resumeId,String userId,String jobdescrip) {
        this.title = title;
        this.item_location = item_location;
        this.item_postnum = item_postnum;
        this.item_type = item_type;
        this.item_company = item_company;
        this.posttime = posttime;
        this.resumeId = resumeId;
        this.userId = userId;
        this.jobdescrip = jobdescrip;
    }

    public String getTitle() {
        return title;
    }

    public String getItem_location() {
        return item_location;
    }

    public String getItem_postnum() {
        return item_postnum;
    }

    public String getItem_type() {
        return item_type;
    }

    public String getItem_company() {
        return item_company;
    }

    public String getPosttime() {
        return posttime;
    }

    public String getResumeId() {return resumeId;}

    public String getUserId() {return userId;}

    public String getJobdescrip() {return jobdescrip;}
}
