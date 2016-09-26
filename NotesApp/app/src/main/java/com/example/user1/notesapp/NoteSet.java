package com.example.user1.notesapp;

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

    ArrayAdapter arrayAdapter;
    GridView gridView;
    ArrayList<Note> notes;
    Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_set);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        note = (Note)getIntent().getSerializableExtra("note");

        //The text of the note
        noteTitle = (EditText) findViewById(R.id.noteTitle);
        noteTitle.setText(note.getTitle());
        //The title of the note
        noteText = (EditText) findViewById(R.id.noteText);


        //The static ArrayList 'notes' from the MainActivity
        notes = MainActivity.notes;




        //Creating a new LocalService so I can build the .txt file in the internal memory
        localService = MainActivity.localService;

        FloatingActionButton saveFab = (FloatingActionButton) findViewById(R.id.saveFab);
        saveFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //The new Note that was just created
                //(that will be sent to the LocalService in order to create the .txt file in the internal memory)
                Note newNote = new Note(noteTitle.getText().toString(), noteText.getText().toString());

                //Add the new Note to the ArrayList
                notes.add(newNote);

                localService.createNewNote(newNote);
                finish();


            }
        });
    }

}
