package com.example.meetingsapp;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

public class Meeting implements Serializable {
    String title;
    String location;
    Date date;
    Time time;

    public Meeting(String title, String location, Date date, Time time) {
        this.title = title;
        this.location = location;
        this.date = date;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "title='" + title + '\'' +
                ", location='" + location + '\'' +
                ", date=" + date +
                ", time=" + time +
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

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}
