package com.example.user1.notesapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class NoteSet extends AppCompatActivity {

    EditText noteTitle;
    EditText noteText;
    LocalService localService;
    Note note;

    //when the user presses the 'back' button the note is being sent back to 'onActivityResult'.
    //If the note is a new note it will be deleted from the memory and removed from the adapter
    @Override
    public void onBackPressed() {

        //this note is an exact copy of the note i sent through the intent
        //IT'S NOT THE SAME NOTE! IT'S A DUPLICATE!
        note = (Note)getIntent().getSerializableExtra("note");

        //Create an intent to send to the 'onActivityResult' func
        Intent resultIntent = new Intent();

        //Put the note inside the Intent as an extra
        //*** THE NOTE OBJECT IMPLEMENTS 'Serializable' WHICH MEANS
        //IT DOESN'T SEND THE ORIGINAL NOTE, BUT AN EXACT COPY OF IT! ***
        resultIntent.putExtra("note",note);

        //Setting the 'Result Code' and the 'Intent' to be sent to the 'onActivityResult' func
        setResult(Activity.RESULT_OK, resultIntent);

        //Finish (exit) the Activity
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_set);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Creating a new LocalService so I can build the .txt file in the internal memory
        localService = new LocalService(this);

        //this note is an exact copy of the note i sent through the intent
        //IT'S NOT THE SAME NOTE! IT'S A DUPLICATE!
        note = (Note)getIntent().getSerializableExtra("note");

        //The EditText of the title of the note
        noteTitle = (EditText) findViewById(R.id.noteTitle);

        //If the note has a title in it (it's not a new note), set 'noteTitle' to contain that text.
        noteTitle.setText(note.getTitle());

        //The EditText of the text of the note
        noteText = (EditText) findViewById(R.id.noteText);

        //If the note has text in it (it's not a new note), set 'noteText' to contain that text
        noteText.setText(note.getText());


        //when pressing the 'Save' button
        FloatingActionButton saveFab = (FloatingActionButton) findViewById(R.id.saveFab);
        saveFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //sets the text ad title of the note as the text of the EditTexts
                note.setTitle(noteTitle.getText().toString());
                note.setText(noteText.getText().toString());

                //Resave the note with it's current values
                localService.updateNote(note);

                //Create an intent to send to the 'onActivityResult' func
                Intent resultIntent = new Intent();

                //Put the note inside the Intent as an extra
                //*** THE NOTE OBJECT IMPLEMENTS 'Serializable' WHICH MEANS
                //IT DOESN'T SEND THE ORIGINAL NOTE, BUT AN EXACT COPY OF IT! ***
                resultIntent.putExtra("note",note);

                //Setting the 'Result Code' and the 'Intent' to be sent to the 'onActivityResult' func
                setResult(Activity.RESULT_OK, resultIntent);

                //Finish (exit) the Activity
                finish();


            }
        });



    }

}
