package com.example.user1.notesapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final static int START_ACTIVITY_FOR_RESULT = 100;

    private Note sentNote;
    Toolbar toolbar;
    FloatingActionButton fab;

    GridView gridView;
    LocalService localService;
    ArrayList<Note> notes;
    ArrayAdapter<Note> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        localService = new LocalService(this);

        //example note for debugging:
        //*******************************************************
        Note exNote1 = new Note("Title", "TEXT");
        localService.createNewNote(exNote1);
        //*******************************************************

        //loading the notes from the memory to the ArrayAdapter
        //***** THIS CODE BLOCK ISN'T RUNNING FOR SOME REASON! *****
        localService.getAllNotes(new NoteService.NoteArrayListCallback() {
            @Override
            public void returnArrayList(ArrayList<Note> notesArrayList) {
                if (notesArrayList != null)
                    notes = notesArrayList;
                else
                    notes = new ArrayList<>();
            }
        });



        /*******************************************************
        Note exNote1 = new Note("Title", "TEXT");
        localService.createNewNote(exNote1);
        Note exNote2 = new Note("ABC", "XYZ");
        localService.createNewNote(exNote2);

        notes.add(exNote1);
        notes.add(exNote2);
        //*******************************************************/


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        gridView=(GridView) findViewById(R.id.gridView);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notes);

        gridView.setAdapter(adapter);


        //when pressing on the 'Add Note' button
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();*/


                Note newNote = new Note("","");
                localService.createNewNote(newNote);

                adapter.add(newNote);
                Intent intent = new Intent(MainActivity.this, NoteSet.class);
                intent.putExtra("note", newNote);

                sentNote=newNote;

                startActivityForResult(intent, START_ACTIVITY_FOR_RESULT);
            }
        });

        //when pressing on a note from the GridView
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Note note = adapter.getItem(position);

                Intent intent = new Intent(MainActivity.this, NoteSet.class);
                intent.putExtra("note", note);

                sentNote=note;
                startActivityForResult(intent, START_ACTIVITY_FOR_RESULT);
            }
        });


        //DELETE NOTE FUNCTION:
        //ON LONG PRESS DELETE NOTE
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Note note = adapter.getItem(position);
                localService.deleteNote(note);
                adapter.remove(note);
                Toast.makeText(getApplicationContext(),"Note is being deleted", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Note note = (Note) data.getSerializableExtra("note");

        //If the user pressed the 'back' button in NoteSet && the note is a new note,
        //it will be deleted from the memory and from the adapter.
        if(note.getText().equals("") && note.getTitle().equals("")) {
            localService.deleteNote(note);
            adapter.remove(sentNote);
        }

        else {
            sentNote.setTitle(note.getTitle());
            sentNote.setText(note.getText());
        }

        adapter.notifyDataSetChanged();
    }


}
