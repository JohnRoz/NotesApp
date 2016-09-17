package com.example.user1.notesapp;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by USER1 on 16/09/2016.
 */
public class GvNoteListAdapter extends BaseAdapter {
    private Context mContext;

    private ArrayList<Note> notes = new ArrayList<Note>();


    public GvNoteListAdapter(Context c, ArrayList<Note> notes) {
        mContext = c;
        this.notes=notes;
    }


    @Override
    public int getCount() {
        if(notes!=null)
            return notes.size();
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    // create a new TextView for each item referenced by the Adapter
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            textView = new TextView(mContext);
            textView.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            textView.setGravity(View.TEXT_ALIGNMENT_CENTER);
            textView.setPadding(8, 0, 8, 0);
        }

        else
            textView = (TextView) convertView;


        textView.setBackgroundColor(Color.parseColor("#FFF8BB"));
        textView.setText(notes.get(position).getTitle()+"\n"+notes.get(position).getText());
        return textView;




    }

}
