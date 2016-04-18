package com.ad340.nsc.mynotes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class NewNote extends AppCompatActivity {

    // For saving state
    static final String NOTE_TITLE = "NOTE_TITLE";
    static final String NOTE_BODY = "NOTE_BODY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's notes
        EditText noteTitleEditText = (EditText) findViewById(R.id.noteTitle);
        String noteTitleText = noteTitleEditText.getText().toString();
        EditText noteBodyEditText = (EditText) findViewById(R.id.noteBody);
        String noteBodyText = noteBodyEditText.getText().toString();

        savedInstanceState.putString(NOTE_TITLE, noteTitleText);
        savedInstanceState.putString(NOTE_BODY, noteBodyText);

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);

        // Restore state members from saved instance
        String noteTitleText = savedInstanceState.getString(NOTE_TITLE);
        String noteBodyText = savedInstanceState.getString(NOTE_BODY);

        EditText noteTitleEditText = (EditText) findViewById(R.id.noteTitle);
        noteTitleEditText.setText(noteTitleText);
        EditText noteBodyEditText = (EditText) findViewById(R.id.noteBody);
        noteBodyEditText.setText(noteBodyText);
    }

    public void saveNote(View view) {
        // Send note content to main activity
        EditText noteTitleEditText = (EditText) findViewById(R.id.noteTitle);
        String noteTitleText = noteTitleEditText.getText().toString();
        EditText noteBodyEditText = (EditText) findViewById(R.id.noteBody);
        String noteBodyText = noteBodyEditText.getText().toString();
        Intent intent = new Intent();
        intent.putExtra(MainActivity.NEW_NOTE_TITLE, noteTitleText);
        intent.putExtra(MainActivity.NEW_NOTE_BODY, noteBodyText);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void cancelNote(View view) {
        // Cancel action
        Intent intent = new Intent();
        setResult(RESULT_CANCELED, intent);
        finish();
    }
}
