package com.example.user1.notesapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    FloatingActionButton fab;
    GridView noteListGV;
    LocalService LService;
    ArrayList<Note> notes;
    GridViewAdapter gvNotesAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/


        LService=new LocalService(this);

        noteListGV = (GridView) findViewById(R.id.gridview);
       /* notes = new ArrayList<>();
        GridViewAdapter gridViewAdapter = new GridViewAdapter(notes);
        noteListGV.setAdapter(gridViewAdapter);*/

        GvNoteListAdapter gvNoteListAdapter =new GvNoteListAdapter(this); //<=WRONG BUILT ADAPTER
        noteListGV.setAdapter(gvNoteListAdapter);

        /*noteListGV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(getApplicationContext(), "" + position,
                        Toast.LENGTH_SHORT).show();
            }
        });*/











    }


    class GridViewAdapter extends BaseAdapter {
        ArrayList<Note> notes;


        public GridViewAdapter(ArrayList<Note> notes) {
            this.notes = notes;
        }

        @Override
        public int getCount() {
            if (notes!=null)
                return notes.size();
            return 0;
        }

        @Override
        public Object getItem(int i) {
            return notes.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = getLayoutInflater().inflate(R.layout.gridview_item, viewGroup, false);
            TextView noteText = (TextView) view.findViewById(R.id.noteText);
            TextView noteTitle = (TextView) view.findViewById(R.id.noteTitle);
            Note tempNote = notes.get(i);

            noteText.setText(tempNote.getText());
            noteTitle.setText(tempNote.getTitle());

            return view;

        }

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
}
