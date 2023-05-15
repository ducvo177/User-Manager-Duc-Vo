package com.example.notesapp.models;

public class FirebaseModel {

    private String name;
    private String email;
    private String time;

    public FirebaseModel(){

    }

    public FirebaseModel(String title, String time, String email){
        this.name = title;
        this.time = time;
        this.email = email;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


}


