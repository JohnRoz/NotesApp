package com.example.user1.notesapp;

import java.util.Date;

/**
 * Created by USER1 on 15/09/2016.
 */
public interface NoteService {
    void editNote(Note note);
    void createNewNote(Note note);
    void deleteNote(Note note);
    void getNoteText(Note note);
    String GetNoteTitle(Note note);
    Date getDateModified(Note note);
    String[] getIdList();



    interface Callback {
        void doSomething(String s);
    }

}
