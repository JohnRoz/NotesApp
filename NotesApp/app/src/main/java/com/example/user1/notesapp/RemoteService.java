package com.example.user1.notesapp;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by USER1 on 17/11/2016.
 */
public class RemoteService implements NoteService {

    String TITLE_KEY = "Title";
    String TEXT_KEY = "Text";
    String ID_KEY = "ID";

    public RemoteService() {
    }

    @Override
    public void updateNote(final Note note) {
        ParseObject noteParseObject = new ParseObject("notes");
        noteParseObject.put(TITLE_KEY, note.getTitle());
        noteParseObject.put(TEXT_KEY, note.getText());
        noteParseObject.put(ID_KEY,note.getId());
        noteParseObject.saveInBackground();
    }

    @Override
    public void createNewNote(final Note note) {
        note.setId(generateRandomId());

        ParseObject noteParseObject = new ParseObject("notes");
        noteParseObject.put(TITLE_KEY, note.getTitle());
        noteParseObject.put(TEXT_KEY, note.getText());
        noteParseObject.put(ID_KEY,note.getId());
        noteParseObject.saveInBackground();

        note.setParseObjectID(noteParseObject.getObjectId());


    }

    @Override
    public Note readNote(final Note note) {
        return null;
    }

    public ArrayList<Note> readAllNotes(final Note note) {

        final ArrayList<Note> notes = new ArrayList<Note>();

        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("notes");
        query.findInBackground(new FindCallback<ParseObject>(){
            public void done(List<ParseObject> remoteNotes, ParseException e) {
                if (e == null)
                    for(ParseObject parseObject : remoteNotes) {
                        Note note = new Note(parseObject.getString(TITLE_KEY), parseObject.getString(TEXT_KEY));
                        note.setId(parseObject.getString(ID_KEY));

                        notes.add(note);
                    }

                 else
                    throw new RuntimeException();
                    // handle Parse Exception here

            }
        });

        return notes;
    }

    @Override
    public Note readNoteById(String id) {
        return null;
    }

    @Override
    public void getAllNotes(NoteArrayListCallback c) {

    }

    @Override
    public void deleteNote(final Note note) {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("notes");
        query.getInBackground(note.getParseObjectID(), new GetCallback<ParseObject>() {
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    object.deleteInBackground();
                } else {
                    // something went wrong
                }
            }
        });
    }

    @Override
    public String[] getIdList() {
        return new String[0];
    }

    //this function is designed to create the IDs of all the files by randomizing chars into 20 chars long Strings.
    private String generateRandomId(){

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
