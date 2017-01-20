package com.example.pollo.madtownscouting2017;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class ScoutingMenu extends AppCompatActivity {
    EditText teamNumberEditText;
    EditText matchNumberEditText;
    Button startScoutingButton;
    CheckBox redCheckBox;
    CheckBox blueCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scouting_menu);
        teamNumberEditText = (EditText)findViewById(R.id.teamNumberEditText);
        matchNumberEditText = (EditText)findViewById(R.id.matchNumberEditText);
        startScoutingButton = (Button)findViewById(R.id.startScoutingButton);
        redCheckBox = (CheckBox) findViewById(R.id.redCheckBox);
        blueCheckBox = (CheckBox) findViewById(R.id.blueCheckBox);
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
                                autoIntent.putExtra("teamColor", "red");
                            }else{
                                autoIntent.putExtra("teamColor", "blue");
                            }
                            startActivity(autoIntent);
                        }else{
                            Toast.makeText(getApplicationContext(), "Select a team color.", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(),"Enter a match number.", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Enter a valid team number por favor", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
