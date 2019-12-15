package com.nknahom.newsapp;

public class News {
    private String sectionName;
    private String date;
    private String title;
    private String webUrl;

    public News(String sectionName, String date, String title, String webUrl) {
        this.sectionName = sectionName;
        this.date = date;
        this.title = title;
        this.webUrl = webUrl;
    }

    public String getSectionName() {
        return sectionName;
    }

    public String getDate() {
        return date;
    }

    public String getTitle() {
        return title;
    }

    public String getWebUrl() {
        return webUrl;
    }
}
