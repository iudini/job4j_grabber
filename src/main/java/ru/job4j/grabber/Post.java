package ru.job4j.grabber;

import java.time.LocalDateTime;

public class Post {
    private String name;
    private String desc;
    private LocalDateTime dateTime;
    private String url;

    public Post(String name, String desc, LocalDateTime dateTime, String url) {
        this.name = name;
        this.desc = desc;
        this.dateTime = dateTime;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
