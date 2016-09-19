package com.example.user1.notesapp;

import java.util.Date;

/**
 * Created by USER1 on 15/09/2016.
 */
public interface NoteService {
    void editNote(String id, String newText, String newTitle);
    void createNewNote(String text, String title);
    void deleteNote(String id);
    void getNoteText(String id);
    String GetNoteTitle(String id);
    Date getDateModified(String id);
    String[] getIdList();



    interface Callback {
        void doSomething(String s);
    }

}
