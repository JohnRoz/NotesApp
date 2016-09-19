package com.example.user1.notesapp;

import java.util.Date;

/**
 * Created by USER1 on 15/09/2016.
 */
public class Note implements Comparable {
    private String id;
    private String title;
    private Date dateLastModified;
    private String text;

    public Note(String id) {
        this.id = id;
        this.title = "New Note";
        this.dateLastModified = new Date();
        this.text = "";
    }

    public Note(String id, String title, Date dateLastModified, String text) {
        this.id = id;
        this.title = title;
        this.dateLastModified = dateLastModified;
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

    public Date getDateLastModified() {
        return dateLastModified;
    }

    public void setDateLastModified(Date dateLastModified) {
        this.dateLastModified = dateLastModified;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public int compareTo(Object o) {
        return getDateLastModified().compareTo(((Note) o).getDateLastModified());
    }

    @Override
    public String toString() {
        return text;
    }
}
