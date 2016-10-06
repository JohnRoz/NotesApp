package com.example.user1.notesapp;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.Callable;

/**
 * Created by USER1 on 15/09/2016.
 */
public class LocalService implements NoteService {

    private Context context;

    public LocalService(Context context) {
        this.context = context;
    }


    @Override
    public void updateNote(final Note note) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                try{


                    //The name of the file:
                    String fileName = note.getId() + ".txt";

                    //create a new file with the id given. (if there is already a file using this id, it pulls it from the memory and uses it).
                    FileOutputStream fos=context.openFileOutput(fileName, Context.MODE_PRIVATE);

                    //wrap the FileInputStream file with a PrintWriter to work in a more high level way.
                    PrintWriter printWriter =new PrintWriter(fos);

                    //inserts the given newTitle to the file.
                    printWriter.println(note.getTitle());

                    //inserts the given newText to the file.
                    printWriter.print(note.getText());

                    printWriter.close();

                } catch(FileNotFoundException ex){ex.printStackTrace();}

                return null;
            }
        }.execute();


    }

    @Override
    public void createNewNote(final Note note) {

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                //create a new id for the new file.
                note.setId(generateRandomId());
                String fileName = note.getId() + ".txt";


                try{
                    //create a new file with the id generated.
                    FileOutputStream fos=context.openFileOutput(fileName, Context.MODE_PRIVATE);

                    //wrap the FileOutputStream file with a PrintWriter to work in a more high level way.
                    PrintWriter printWriter =new PrintWriter(fos);

                    //inserts the given title to the file.
                    printWriter.println(note.getTitle());

                    //inserts the given text to the file.
                    printWriter.print(note.getText());

                    printWriter.close();

                } catch(FileNotFoundException ex){ex.printStackTrace();}
                return null;
            }
        }.execute();


    }

    @Override
    public void deleteNote(final Note note) {
        new AsyncTask<Void/*(params)*/, Void/*(progress)*/, Void/*(result)*/>() {
            @Override
            protected Void doInBackground(Void... params) {

                String fileName = note.getId() + ".txt";
                context.deleteFile(fileName);

                return null;
            }
        }.execute();

    }





    /*@Override
    public Date getDateModified(Note note) {
        String fileName = note.getId() + ".txt";

        //creates an array of all the files.
        File[] files = context.getFilesDir().listFiles();

        /*for each file, check:
        *is the 'name' of the file is the same as the given id?
        * if it is, return the "lastModified" date of that file
        * if the loop ends and no file matched the given id, the function *!* -returns null- *!*
        //

        for (File file : files)
            if (file.getName().equals(fileName))
                return new Date(file.lastModified());
        return null;
    }*/

    @Override
    public String[] getIdList() {
        //creates an array of all the files.
        File[] files = context.getFilesDir().listFiles();

        //creates an array to describe all the IDs of the files.
        String[] ids = new String[files.length];

        //the loop goes over every file and inserts its ID into the ids array.
        for (int i=0; i<files.length;i++)
            ids[i]=files[i].getName().replace(".txt","");

        //the function returns the list of the IDs of the files.
        return ids;

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

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

}
