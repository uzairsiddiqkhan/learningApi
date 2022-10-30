package com.example.apilearning;

public class modelAPI {
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPoetry() {
        return poetry;
    }

    public void setPoetry(String poetry) {
        this.poetry = poetry;
    }

    public String getPoetName() {
        return poetName;
    }

    public void setPoetName(String poetName) {
        this.poetName = poetName;
    }

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }

    String poetry , poetName, date_time;

    public modelAPI(int id, String poetry, String poetName, String date_time) {
        this.id = id;
        this.poetry = poetry;
        this.poetName = poetName;
        this.date_time = date_time;
    }
}
