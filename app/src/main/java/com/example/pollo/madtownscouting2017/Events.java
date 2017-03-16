package com.example.pollo.madtownscouting2017;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class Events extends AppCompatActivity {

    CheckBox CVRButton;
    CheckBox davisButton;
    CheckBox houstonButton;
    Button saveButton;
    SQLiteDatabase myDB = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        CVRButton = (CheckBox) findViewById(R.id.CVRcheckBox);
        davisButton = (CheckBox) findViewById(R.id.davisCheckBox);
        houstonButton = (CheckBox) findViewById(R.id.houstonCheckBox);
        saveButton = (Button) findViewById(R.id.saveButton);
        CVRButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(davisButton.isChecked()){
                    davisButton.toggle();
                }
                if(houstonButton.isChecked()){
                    houstonButton.toggle();
                }
            }
        });
        davisButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CVRButton.isChecked()){
                    CVRButton.toggle();
                }
                if(houstonButton.isChecked()){
                    houstonButton.toggle();
                }
            }
        });
        houstonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(davisButton.isChecked()){
                    davisButton.toggle();
                }
                if(CVRButton.isChecked()){
                    CVRButton.toggle();
                }
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDB = openOrCreateDatabase("FRC", MODE_PRIVATE, null);
                if(CVRButton.isChecked()){
                    myDB.execSQL("UPDATE Events SET event = 'CVR' WHERE _id = 1");
                }
                if(davisButton.isChecked()){
                    myDB.execSQL("UPDATE Events SET event = 'Davis' WHERE _id = 1");
                }
                if(houstonButton.isChecked()){
                    myDB.execSQL("UPDATE Events SET event = 'Houston' WHERE _id = 1");
                }
                myDB.close();
                Intent intent = new Intent(getApplicationContext(), StartMenu.class);
                startActivity(intent);
            }
        });
    }
}
