package com.example.user1.notesapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;

import java.util.ArrayList;

public class NoteSet extends AppCompatActivity {

    EditText noteTitle;
    EditText noteText;
    LocalService localService;

    GridView gridView;
    ArrayAdapter<Note> arrayAdapter;
    ArrayList<Note> notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_set);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        noteTitle = (EditText) findViewById(R.id.noteTitle);
        noteText = (EditText) findViewById(R.id.noteText);

        gridView=(GridView) findViewById(R.id.gridview);
        gridView = new GridView(this);
        ArrayList<Note> notes = new ArrayList<>();
        arrayAdapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notes);


        final Note newNote = new Note(noteTitle.getText().toString(), noteText.getText().toString());
        arrayAdapter.add(newNote);

        localService = new LocalService(this);

        FloatingActionButton saveFab = (FloatingActionButton) findViewById(R.id.saveFab);
        saveFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                localService.createNewNote(newNote);



                gridView.setAdapter(arrayAdapter);
                startActivity(new Intent(NoteSet.this,MainActivity.class));
            }
        });
    }

}
