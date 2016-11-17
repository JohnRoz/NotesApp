package com.example.user1.notesapp;

import java.io.Serializable;

/**
 * Created by USER1 on 15/09/2016.
 */
public class Note implements Serializable {
    private String id;
    private String parseObjectID;
    private String title;
    private String text;



    public Note(String title, String text) {
        this.id = "";
        this.parseObjectID = "";
        this.title = title;
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getParseObjectID() { return parseObjectID; }

    public void setParseObjectID(String parseObjectID) { this.parseObjectID = parseObjectID; }

    @Override
    public String toString() {
        return title;
    }
}
