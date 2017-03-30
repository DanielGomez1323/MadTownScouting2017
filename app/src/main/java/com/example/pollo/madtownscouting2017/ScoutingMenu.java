package com.example.pollo.madtownscouting2017;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.reflect.Array;

public class ScoutingMenu extends AppCompatActivity {
    EditText teamNumberEditText;
    EditText matchNumberEditText;
    Button startScoutingButton;
    CheckBox redCheckBox;
    CheckBox blueCheckBox;
    AutoCompleteTextView autoCompTeamNumberTextView;


    String [] teamNumber = {"1323", "254", "1678", "1671", "199", "100", "3313", "5012", "973", "971", "514" };

    SQLiteDatabase myDB = null;
    Cursor c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scouting_menu);
        teamNumberEditText = (EditText)findViewById(R.id.teamNumberEditText);
        matchNumberEditText = (EditText)findViewById(R.id.matchNumberEditText);
        startScoutingButton = (Button)findViewById(R.id.startScoutingButton);
        redCheckBox = (CheckBox) findViewById(R.id.redCheckBox);
        blueCheckBox = (CheckBox) findViewById(R.id.blueCheckBox);
        autoCompTeamNumberTextView = (AutoCompleteTextView) findViewById(R.id.autoCompTeamNumberTextView);

        ArrayAdapter adapter = new
                ArrayAdapter(this, android.R.layout.simple_list_item_1, teamNumber);

        autoCompTeamNumberTextView.setAdapter(adapter);
        autoCompTeamNumberTextView.setThreshold(1);

        myDB = openOrCreateDatabase("FRC", MODE_PRIVATE, null);
        c = myDB.rawQuery("SELECT * FROM SteamWorks ORDER BY _id DESC LIMIT 1", null);
        if (c.getCount()> 0){
            c.moveToFirst();
            int matchNumber = Integer.parseInt(c.getString(c.getColumnIndex("matchNumber"))) + 1;
            matchNumberEditText.setText(String.valueOf(matchNumber));
        }else{
            matchNumberEditText.setText("1");
        }
        redCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(blueCheckBox.isChecked()){
                    blueCheckBox.toggle();
                }
            }
        });

        blueCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(redCheckBox.isChecked()){
                    redCheckBox.toggle();
                }
            }
        });
        startScoutingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(teamNumberEditText.getText().toString().length() <=4){
                    if(matchNumberEditText.getText().toString().length() > 0){
                        if((redCheckBox.isChecked() || blueCheckBox.isChecked()) && !(redCheckBox.isChecked() && blueCheckBox.isChecked())){
                            Intent autoIntent = new Intent(getApplicationContext(), TabbedScouting.class);
                            autoIntent.putExtra("teamNumber", teamNumberEditText.getText().toString());
                            autoIntent.putExtra("matchNumber", matchNumberEditText.getText().toString());
                            if(redCheckBox.isChecked()){
                                autoIntent.putExtra("teamColor", "0");
                            }else{
                                autoIntent.putExtra("teamColor", "1");
                            }
                            startActivity(autoIntent);
                        }else{
                            Toast.makeText(getApplicationContext(), "Select a team color dude.", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(),"Enter a match number hombre.", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Enter a valid team number por favor", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
