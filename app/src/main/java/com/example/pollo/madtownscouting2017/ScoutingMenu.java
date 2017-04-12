package com.example.pollo.madtownscouting2017;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class ScoutingMenu extends AppCompatActivity {
    //EditText teamNumberEditText;
    AutoCompleteTextView teamNumberAutoEditText;
    EditText matchNumberEditText;
    Button startScoutingButton;
    CheckBox redCheckBox;
    CheckBox blueCheckBox;

    SQLiteDatabase myDB = null;
    Cursor c;
    //String [] teamNumbers = {""};
    String[] teamNumbers = new String[6];
    int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scouting_menu);
        //teamNumberEditText = (EditText)findViewById(R.id.teamNumberEditText);
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(this,android.R.layout.select_dialog_item, teamNumbers);
        teamNumberAutoEditText = (AutoCompleteTextView)findViewById(R.id.teamNumberAutoEditText);
        teamNumberAutoEditText.setThreshold(1);//will start working from first character
        teamNumberAutoEditText.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
        matchNumberEditText = (EditText)findViewById(R.id.matchNumberEditText);
        startScoutingButton = (Button)findViewById(R.id.startScoutingButton);
        redCheckBox = (CheckBox) findViewById(R.id.redCheckBox);
        blueCheckBox = (CheckBox) findViewById(R.id.blueCheckBox);

        myDB = openOrCreateDatabase("FRC", MODE_PRIVATE, null);
        c = myDB.rawQuery("SELECT * FROM SteamWorks ORDER BY _id DESC LIMIT 1", null);
        if (c.getCount()> 0){
            c.moveToFirst();
            int matchNumber = Integer.parseInt(c.getString(c.getColumnIndex("matchNumber"))) + 1;
            matchNumberEditText.setText(String.valueOf(matchNumber));
        }else{
            matchNumberEditText.setText("1");
        }
        myDB = openOrCreateDatabase("FRC", MODE_PRIVATE, null);
        c = myDB.rawQuery("SELECT * FROM MatchSchedule WHERE matchNumber = " + matchNumberEditText.getText(), null);
        if (c.getCount()> 0) {
            c.moveToFirst();
            /*while(c.moveToNext()) {
                String teamNumber = c.getString(c.getColumnIndex("teamNumber"));
                teamNumbers[i] = teamNumber;
                i++;*/
            for(int i = 0 ; i < 6 ; i++) {
                String teamNumber = c.getString(c.getColumnIndex("teamNumber"));
                teamNumbers[i] = teamNumber;
                c.moveToNext();
            }
        }else {

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
                if(teamNumberAutoEditText.getText().toString().length() <=4/*teamNumberEditText.getText().toString().length() <=4*/){
                    if(matchNumberEditText.getText().toString().length() > 0){
                        if((redCheckBox.isChecked() || blueCheckBox.isChecked()) && !(redCheckBox.isChecked() && blueCheckBox.isChecked())){
                            Intent autoIntent = new Intent(getApplicationContext(), TabbedScouting.class);
                            autoIntent.putExtra("teamNumber", teamNumberAutoEditText.getText().toString()/*teamNumberEditText.getText().toString()*/);
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_tabbed_scouting, menu);
        return true;
    }

}

