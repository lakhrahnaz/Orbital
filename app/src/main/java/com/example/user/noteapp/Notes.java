package com.example.user.noteapp;

public class Notes {

    private String title;
    private String body;

    public Notes() {
    }

    public Notes(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public String getTitle() {

        return title;
    }

    public String getBody() {

        return body;
    }
}
