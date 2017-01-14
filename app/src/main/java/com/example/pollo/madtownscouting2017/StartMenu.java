package com.example.pollo.madtownscouting2017;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
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

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent searchIntent = new Intent(getApplicationContext(), DataUpload.class);
                startActivity(searchIntent);
            }
        });
        scoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent scoutIntent = new Intent(getApplicationContext(), ScoutingMenu.class);
                startActivity(scoutIntent);
            }
        });
        uploadData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent uploadIntent = new Intent(getApplicationContext(), DataUpload.class);
                startActivity(uploadIntent);
            }
        });
        teamsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent teamsIntent = new Intent(getApplicationContext(), teamsView.class);
                startActivity(teamsIntent);
            }
        });
        addPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addPhotoIntent = new Intent(getApplicationContext(), addPhoto.class);
                startActivity(addPhotoIntent);
            }
        });
        viewPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewPhotoIntent = new Intent(getApplicationContext(), viewPhotos.class);
                startActivity(viewPhotoIntent);
            }
        });
    }
}
