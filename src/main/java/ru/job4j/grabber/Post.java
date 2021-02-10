package ru.job4j.grabber;

import java.time.LocalDateTime;

public class Post {
    private int id;
    private String name;
    private String desc;
    private LocalDateTime dateTime;
    private String url;

    public Post(String name, String desc, String url, LocalDateTime dateTime) {
        this.name = name;
        this.desc = desc;
        this.dateTime = dateTime;
        this.url = url;
    }

    public Post(int id, String name, String desc, String url, LocalDateTime dateTime) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.dateTime = dateTime;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Post{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", desc='" + desc + '\''
                + ", dateTime=" + dateTime
                + ", url='" + url + '\''
                + '}';
    }
}
