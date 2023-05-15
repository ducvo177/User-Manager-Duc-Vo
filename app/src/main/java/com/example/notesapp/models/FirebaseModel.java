package com.example.notesapp.models;

public class FirebaseModel {

    private String title;
    private String content;
    private String time;

    public FirebaseModel(){

    }

    public FirebaseModel(String title, String time, String content){
        this.title = title;
        this.time = time;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}


