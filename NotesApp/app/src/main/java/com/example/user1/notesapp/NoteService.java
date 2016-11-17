package com.example.user1.notesapp;

import java.util.ArrayList;

/**
 * Created by USER1 on 15/09/2016.
 */
public interface NoteService {
    void updateNote(final Note note);
    void createNewNote(final Note note);
    Note readNote(final Note note);
    Note readNoteById (final String id);
    void getAllNotes(final NoteArrayListCallback c);
    void deleteNote(final Note note);
    String[] getIdList();



    interface NoteCallback {
        void returnNote(final Note note);
    }
    interface NoteArrayListCallback {
        void returnArrayList(final ArrayList<Note> notesArrayList);
    }

}
