package com.example.pollo.madtownscouting2017;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
    SQLiteDatabase myDB = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_menu);
        searchMenuSearchBox = (EditText) findViewById(R.id.searchMenuSearchBox);
        searchButton = (Button) findViewById(R.id.searchButton);
        scoutButton = (Button) findViewById(R.id.scoutButton);
        uploadData = (Button) findViewById(R.id.uploadData);
        teamsView = (Button) findViewById(R.id.teamsView);
        addPhoto = (Button) findViewById(R.id.addPhoto);
        viewPhoto = (Button) findViewById(R.id.viewPhoto);

        createPicturesDatabase();
        createDatabase();

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
    public void createDatabase(){
        try{
            myDB = openOrCreateDatabase("FRC", MODE_PRIVATE, null);
            myDB.execSQL("CREATE TABLE IF NOT EXISTS SteamWorks ( _id INTEGER PRIMARY KEY AUTOINCREMENT, teamNumber int, matchNumber int, teamColor int, autoGearAttempt int, autoGearSuccess int, autoHighScored int, autoLowScored int, autoHighMissed int, autoLowMissed int, baseLine int, gearsPickedUp int, gearsHung int, highShootSpeed int, highShotsMissed int, lowShootSpeed int, lowShotsMissed int, hopperIntake int, climbTime int, climbSuccess int, gearsDropped int, tbh varchar, rank int)");
            if (myDB != null)
                myDB.close();
        } catch (SQLException e) {
            Log.e("Error", "Error", e);
        }
    }
    public void createPicturesDatabase(){
        try{
            myDB = openOrCreateDatabase("FRC", MODE_PRIVATE, null);
            myDB.execSQL("CREATE TABLE IF NOT EXISTS TeamPictures ( _id INTEGER PRIMARY KEY AUTOINCREMENT, teamNumber int, pic1 VARCHAR)");
            if (myDB != null)
                myDB.close();
        } catch (SQLException e) {
            Log.e("Error", "Error", e);
        }
    }
}
