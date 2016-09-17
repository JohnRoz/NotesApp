package com.example.user1.notesapp;

import android.content.Context;

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

/**
 * Created by USER1 on 15/09/2016.
 */
public class LocalService implements NoteService {

    private Context context;

    public LocalService(Context context) {
        this.context = context;
    }


    @Override
    public void editNote(String id, String newText, String newTitle) {
        try{

            //The name of the file:
            String fileName = id + ".txt";

            //create a new file with the id given. (if there is already a file using this id, it pulls it from the memory and uses it).
            FileOutputStream fos=context.openFileOutput(fileName, Context.MODE_PRIVATE);

            //wrap the FileInputStream file with a PrintWriter to work in a more high level way.
            PrintWriter printWriter =new PrintWriter(fos);

            //inserts the given newTitle to the file.
            printWriter.println(newTitle);

            //inserts the given newText to the file.
            printWriter.print(newText);

            printWriter.close();

        } catch(FileNotFoundException ex){ex.printStackTrace();}


    }

    @Override
    public void createNewNote(String text, String title) {

        //create a new id for the new file.
        String fileName = generateRandomId() + ".txt";

        try{
            //create a new file with the id generated.
            FileOutputStream fos=context.openFileOutput(fileName, Context.MODE_PRIVATE);

            //wrap the FileOutputStream file with a PrintWriter to work in a more high level way.
            PrintWriter printWriter =new PrintWriter(fos);

            //inserts the given title to the file.
            printWriter.println(title);

            //inserts the given text to the file.
            printWriter.print(text);

            printWriter.close();

        } catch(FileNotFoundException ex){ex.printStackTrace();}

    }

    @Override
    public void deleteNote(String id) {
        String fileName = id + ".txt";
        context.deleteFile(fileName);
    }

    @Override
    public String getNoteText(String id) {
        String fileName = id + ".txt";
        String text="";
        try {
            //gets the file with the specified id from the memory. if it doesn't exist, it throws a FileNotFoundException.
          FileInputStream fis = context.openFileInput(fileName);

            //wrap the FileInputStream file with a BufferedReader to work in a more high level way.
          BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fis));

            bufferedReader.readLine(); // jump one line down to skip the title

            //gives the line of the text in the file a shorter name and checks if it is not null in every run of the loop.
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                //insert all the text in the file inside a String
                text = line;
            }
      } catch(IOException ex){
            ex.printStackTrace();
            text = "ERROR: FILE NOT FOUND";
        }

        //return the String that contains the text content of the file
        return text;

    }

    @Override
    public String GetNoteTitle(String id) {
        String fileName=id + ".txt";
        String title="";
        try{
            //gets the file with the specified id from the memory. if it doesn't exist, it throws a FileNotFoundException.
            FileInputStream fis = context.openFileInput(fileName);

            //wrap the FileInputStream file with a BufferedReader to work in a more high level way.
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fis));

            title=bufferedReader.readLine();

        }
        catch(IOException ex){ex.printStackTrace();}

        return title;
    }

    @Override
    public Date getDateModified(String id) {
        String fileName = id + ".txt";

        //creates an array of all the files.
        File[] files = context.getFilesDir().listFiles();

        /*for each file, check:
        *is the 'name' of the file is the same as the given id?
        * if it is, return the "lastModified" date of that file
        * if the loop ends and no file matched the given id, the function *!* -returns null- *!*
        */
        for (File file : files)
            if (file.getName().equals(fileName))
                return new Date(file.lastModified());
        return null;
    }

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
