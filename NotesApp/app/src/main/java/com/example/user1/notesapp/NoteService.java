package com.example.user1.notesapp;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by USER1 on 15/09/2016.
 */
public interface NoteService {
    void updateNote(Note note);
    void createNewNote(Note note);
    void readNoteByNote(Note note);
    Note readNoteById (String id);
    ArrayList<Note> getAllNotes();
    void deleteNote(Note note);
    //Date getDateModified(Note note);
    String[] getIdList();



    interface Callback {
        void doSomething(String s);
    }

}
