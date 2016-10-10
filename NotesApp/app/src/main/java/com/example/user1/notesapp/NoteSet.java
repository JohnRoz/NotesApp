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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_set);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        note = (Note)getIntent().getSerializableExtra("note");

        //The EditText of the title of the note
        noteTitle = (EditText) findViewById(R.id.noteTitle);

        //If the note has a title in it (it's not a new note), set 'noteTitle' to contain that text.
        noteTitle.setText(note.getTitle());

        //The EditText of the text of the note
        noteText = (EditText) findViewById(R.id.noteText);

        //If the note has text in it (it's not a new note), set 'noteText' to contain that text
        noteText.setText(note.getText());




        //Creating a new LocalService so I can build the .txt file in the internal memory
        localService = new LocalService(this); //MainActivity.localService;

        FloatingActionButton saveFab = (FloatingActionButton) findViewById(R.id.saveFab);
        saveFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                note.setTitle(noteTitle.getText().toString());
                note.setText(noteText.getText().toString());

                localService.updateNote(note);

                Intent resultIntent = new Intent();
                resultIntent.putExtra("note",note);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();


            }
        });
    }

}
