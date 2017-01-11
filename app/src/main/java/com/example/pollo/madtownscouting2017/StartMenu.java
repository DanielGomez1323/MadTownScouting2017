package com.example.pollo.madtownscouting2017;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class StartMenu extends AppCompatActivity {

    EditText searchMenuSearchBox;
    Button searchButton;
    Button scoutButton;
    Button uploadData;
    Button teamsView;
    Button addPhoto;
    Button viewPhoto;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_menu);
        EditText searchMenuSearchBox = (EditText) findViewById(R.id.searchMenuSearchBox);
        Button searchButton = (Button) findViewById(R.id.searchButton);
        Button scoutButton = (Button) findViewById(R.id.scoutButton);
        Button uploadData = (Button) findViewById(R.id.uploadData);
        Button teamsView = (Button) findViewById(R.id.teamsView);
        Button addPhoto = (Button) findViewById(R.id.addPhoto);
        Button viewPhoto = (Button) findViewById(R.id.viewPhoto);
    }
}
