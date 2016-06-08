package com.ad340.nsc.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // For saving state
    static final String FIRST_NAME = "FIRST_NAME";
    static final String LAST_NAME = "LAST_NAME";
    static final String PHONE = "PHONE";
    static final String STREET = "STREET";
    static final String CITY = "CITY";
    static final String STATE = "STATE";
    static final String ZIP = "ZIP";

    EditText first_name;
    EditText last_name;
    EditText phone;
    EditText street;
    EditText city;
    EditText state;
    EditText zip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        first_name = (EditText) findViewById(R.id.edit_text_first_name);
        last_name = (EditText) findViewById(R.id.edit_text_last_name);
        phone = (EditText) findViewById(R.id.edit_text_phone);
        street = (EditText) findViewById(R.id.edit_text_street);
        city = (EditText) findViewById(R.id.edit_text_city);
        state = (EditText) findViewById(R.id.edit_text_state);
        zip = (EditText) findViewById(R.id.edit_text_zip);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's form entries
        savedInstanceState.putString(FIRST_NAME, first_name.getText().toString());
        savedInstanceState.putString(LAST_NAME, last_name.getText().toString());
        savedInstanceState.putString(PHONE, phone.getText().toString());
        savedInstanceState.putString(STREET, street.getText().toString());
        savedInstanceState.putString(CITY, city.getText().toString());
        savedInstanceState.putString(STATE, state.getText().toString());
        savedInstanceState.putString(ZIP, zip.getText().toString());

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);

        // Restore state members from saved instance
        String firstNameText = savedInstanceState.getString(FIRST_NAME);
        String lastNameText = savedInstanceState.getString(LAST_NAME);
        String phoneText = savedInstanceState.getString(PHONE);
        String streetText = savedInstanceState.getString(STREET);
        String cityText = savedInstanceState.getString(CITY);
        String stateText = savedInstanceState.getString(STATE);
        String zipText = savedInstanceState.getString(ZIP);

        first_name.setText(firstNameText);
        last_name.setText(lastNameText);
        phone.setText(phoneText);
        street.setText(streetText);
        city.setText(cityText);
        state.setText(stateText);
        zip.setText(zipText);
    }

    public void saveAddress(View view) {
        first_name.setText("");
        last_name.setText("");
        phone.setText("");
        street.setText("");
        city.setText("");
        state.setText("");
        zip.setText("");
        Toast.makeText(getApplicationContext(), "Address Saved", Toast.LENGTH_SHORT).show();
        finish();
    }
}
