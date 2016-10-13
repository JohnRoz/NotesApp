package com.example.user1.notesapp;

import java.util.ArrayList;

/**
 * Created by USER1 on 15/09/2016.
 */
public interface NoteService {
    void updateNote(Note note);
    void createNewNote(Note note);
    void readNote(Note note, NoteCallback c);
    Note readNoteById (String id);
    void getAllNotes(NoteArrayListCallback c);
    void deleteNote(Note note);
    //Date getDateModified(Note note);
    String[] getIdList();



    interface NoteCallback {
        void returnNote(Note note);
    }
    interface NoteArrayListCallback {
        void returnArrayList(ArrayList<Note> notesArrayList);
    }

}
