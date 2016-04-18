package com.ad340.nsc.mynotes;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NoteDetail.OnNoteDetailFragmentInteractionListener {

    // Used for passing extras to NewNote activity
    static final String NEW_NOTE_TITLE = "NEW_NOTE_TITLE";
    static final String NEW_NOTE_BODY = "NEW_NOTE_BODY";

    // Request code for interacting with NewNote activity
    static final int ADD_NEW_NOTE = 1;

    // Storage for notes that can be bound to a listview
    private ArrayAdapter<MyNote> myNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Create a place to store notes
        myNotes = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        myNotes.add(new MyNote("Test Title", "Test Body"));

        // Find the notes list to hook up a callback when an item is clicked
        ListView myNotesList = (ListView) findViewById(R.id.notesListView);
        myNotesList.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onNoteSelected(position);
            }
        });
        // Set the adapter
        myNotesList.setAdapter(myNotes);
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
    public void onNoteDetailInteraction(Uri uri) {
        // Not needed right now, but must exist to use the fragment
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Check which request we're responding to
        if (requestCode == ADD_NEW_NOTE) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                // Create and store the newly created note
                String noteTitle = data.getStringExtra(NEW_NOTE_TITLE);
                String noteBody = data.getStringExtra(NEW_NOTE_BODY);
                myNotes.add(new MyNote(noteTitle, noteBody));
            }
        }
    }

    // Update the fragment with the currently selected note
    public void onNoteSelected(int position) {
        NoteDetail noteDetailFrag = (NoteDetail)
                getSupportFragmentManager().findFragmentById(R.id.noteDetail);

        if (noteDetailFrag != null) {
            noteDetailFrag.setNoteContent(myNotes.getItem(position));
        }
    }

    public void newNote(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, NewNote.class);
        startActivityForResult(intent, ADD_NEW_NOTE);
    }
}

// Simple package private class to represent a note with a title and body
class MyNote {

    private String title;
    private String body;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public MyNote(String newTitle, String newBody) {
        title = newTitle;
        body = newBody;
    }

    @Override
    public String toString() {
        return title;
    }
}
