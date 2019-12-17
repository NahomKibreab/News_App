package com.nknahom.newsapp;

public class News {
    private String sectionName;
    private String date;
    private String title;
    private String webUrl;
    private String author_first_name;
    private String author_last_name;

    public News(String sectionName, String date, String title, String webUrl, String author_first_name, String author_last_name) {
        this.sectionName = sectionName;
        this.date = date;
        this.title = title;
        this.webUrl = webUrl;
        this.author_first_name = author_first_name;
        this.author_last_name = author_last_name;
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

    public String getAuthor_first_name() {
        return author_first_name;
    }

    public String getAuthor_last_name() {
        return author_last_name;
    }
}
