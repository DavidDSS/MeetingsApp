package com.example.meetingsapp;

import java.util.Date;

public class Meeting {
    String title;
    String location;
    Date date;

    public Meeting(String title, String location, Date date) {
        this.title = title;
        this.location = location;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "title='" + title + '\'' +
                ", location='" + location + '\'' +
                ", date=" + date +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
