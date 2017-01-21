package com.example.pollo.madtownscouting2017;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

public class DataUpload extends AppCompatActivity {
SQLiteDatabase myDB = null;
    ListView lv;
    Button uploadButton;
    Button deleteButton;
    Button scrollUpButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_upload);
        lv = (ListView) findViewById(R.id.webListView);
        uploadButton = (Button) findViewById(R.id.sendToWebButton);
        deleteButton = (Button) findViewById(R.id.deleteButton);
        scrollUpButton = (Button) findViewById(R.id.ToTopButton);


    }

    public class Match{
        String teamNumber;
        String matchNumber;
        String teamColor;

        String autoGearAttempt;
        String autoGearSuccess;
        String autoHighScored;
        String autoLowScored;
        String autoHighMissed;
        String autoLowMissed;
        String baseLine;
        String gearsPickedUp;
        String gearsHung;
        String highShootSpeed;
        String highShotsMissed;
        String lowShootSpeed;
        String lowShotsMissed;
        String hopperIntake;
        String climbTime;
        String climbSuccess;
        String gearsDropped;
        String tbh;
        String rank;

        public void loadDatabase(int id){
            myDB = openOrCreateDatabase("FRC", MODE_PRIVATE, null);
            Cursor c = myDB.rawQuery("SELECT * FROM SteamWorks WHERE _id = " + String.valueOf(id), null);
            c.moveToFirst();
            if (c.getCount() > 0){
                teamNumber = c.getString(c.getColumnIndex("teamNumber"));
                matchNumber = c.getString(c.getColumnIndex("matchNumber"));
                teamColor = c.getString(c.getColumnIndex("teamColor"));
                autoGearAttempt = c.getString(c.getColumnIndex("autoGearAttempt"));
                autoGearSuccess = c.getString(c.getColumnIndex("autoGearSuccess"));
                autoHighScored = c.getString(c.getColumnIndex("autoHighScored"));
                autoLowScored = c.getString(c.getColumnIndex("autoLowScored"));
                autoHighMissed = c.getString(c.getColumnIndex("autoHighMissed"));
                autoLowMissed = c.getString(c.getColumnIndex("autoLowMissed"));
                baseLine = c.getString(c.getColumnIndex("baseLine"));
                gearsPickedUp = c.getString(c.getColumnIndex("gearsPickedUp"));
                gearsHung = c.getString(c.getColumnIndex("gearsHung"));
                highShootSpeed = c.getString(c.getColumnIndex("highShootSpeed"));
                highShotsMissed = c.getString(c.getColumnIndex("highShotsMissed"));
                lowShootSpeed = c.getString(c.getColumnIndex("lowShootSpeed"));
                lowShotsMissed = c.getString(c.getColumnIndex("lowShotsMissed"));
                hopperIntake = c.getString(c.getColumnIndex("hopperIntake"));
                climbTime = c.getString(c.getColumnIndex("climbTime"));
                climbSuccess = c.getString(c.getColumnIndex("climbSuccess"));
                gearsDropped = c.getString(c.getColumnIndex("gearsDropped"));
                tbh = c.getString(c.getColumnIndex("tbh"));
                rank = c.getString(c.getColumnIndex("rank"));
            }
        }
    }

}
