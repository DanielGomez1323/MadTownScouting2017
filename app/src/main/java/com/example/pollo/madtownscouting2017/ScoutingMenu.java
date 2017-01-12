package com.example.pollo.madtownscouting2017;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class ScoutingMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scouting_menu);
        final EditText teamNumberEditText = (EditText)findViewById(R.id.teamNumberEditText);
        final EditText matchNumberEditText = (EditText)findViewById(R.id.matchNumberEditText);
        EditText teamColorEditTesxt = (EditText)findViewById(R.id.teamColorEditText);
        Button startScoutingButton = (Button)findViewById(R.id.startScoutingButton);
        final CheckBox redCheckBox = (CheckBox) findViewById(R.id.redCheckBox);
        final CheckBox blueCheckBox = (CheckBox) findViewById(R.id.blueCheckBox);
        startScoutingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(teamNumberEditText.getText().toString().length() <=4){
                    if(matchNumberEditText.getText().toString().length() > 0){
                        if(redCheckBox.isChecked() || blueCheckBox.isChecked()){
                            Intent autoIntent = new Intent(getApplicationContext(), AutoPeriod.class);
                            startActivity(autoIntent);
                        }
                    }
                }
            }
        });
    }
}
