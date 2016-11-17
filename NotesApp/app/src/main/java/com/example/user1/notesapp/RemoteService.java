package com.example.user1.notesapp;

import com.parse.ParseObject;

import java.util.Random;

/**
 * Created by USER1 on 17/11/2016.
 */
public class RemoteService implements NoteService {

    String TITLE_KEY = "Title";
    String TEXT_KEY = "Text";
    String ID_KEY = "ID";
    @Override
    public void updateNote(Note note) {
        ParseObject noteParseObject = new ParseObject("Note");
        noteParseObject.put(TITLE_KEY, note.getTitle());
        noteParseObject.put(TEXT_KEY, note.getText());
        noteParseObject.put(ID_KEY,note.getId());
        noteParseObject.saveInBackground();
    }

    @Override
    public void createNewNote(Note note) {
        note.setId(generateRandomId());

        ParseObject noteParseObject = new ParseObject("Note");
        noteParseObject.put(TITLE_KEY, note.getTitle());
        noteParseObject.put(TEXT_KEY, note.getText());
        noteParseObject.put(ID_KEY,note.getId());
        noteParseObject.saveInBackground();

    }

    @Override
    public void readNote(Note note, NoteCallback c) {

    }

    @Override
    public Note readNoteById(String id) {
        return null;
    }

    @Override
    public void getAllNotes(NoteArrayListCallback c) {

    }

    @Override
    public void deleteNote(Note note) {

    }

    @Override
    public String[] getIdList() {
        return new String[0];
    }

    //this function is designed to create the IDs of all the files by randomizing chars into 20 chars long Strings.
    public String generateRandomId(){

        //creates a new Random object
        Random rnd = new Random();

        //creates a char array that is made of all the alphabet (lower key + upper key) plus all the digits.
        char[] characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();

        //creates the initial, empty id string
        String id = "";

        /*Do this 20 times:
        * randomize a number from 0 to the length of the char array, characters
        * add the id string the character from the index of the randomized number*/
        for (int i = 0; i < 20; i++){
            id += characters[rnd.nextInt(characters.length)];
        }

        //return the 20 random chars long string
        return id;

    }
}
