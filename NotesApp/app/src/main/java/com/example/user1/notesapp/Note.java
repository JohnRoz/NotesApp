package com.example.user1.notesapp;

import java.util.Date;

/**
 * Created by USER1 on 15/09/2016.
 */
public class Note /*implements Comparable*/ {
    private String id;
    private String title;
    //private Date dateLastModified;
    private String text;



    public Note(String title, String text) {
        this.id = "";
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

    /*@Override
    public int compareTo(Object o) {
        return getDateLastModified().compareTo(((Note) o).getDateLastModified());
    }*/

    @Override
    public String toString() {
        return text;
    }
}
