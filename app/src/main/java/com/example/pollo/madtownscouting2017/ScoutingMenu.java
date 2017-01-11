package com.example.pollo.madtownscouting2017;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class ScoutingMenu extends AppCompatActivity {
    EditText teamNumberEditText;
    EditText matchNumberEditText;
    EditText teamColorEditText;
    Button startScoutingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scouting_menu);
        EditText teamNumberEditText = (EditText)findViewById(R.id.teamNumberEditText);
        EditText matchNumberEditText = (EditText)findViewById(R.id.matchNumberEditText);
        EditText teamColorEditTesxt = (EditText)findViewById(R.id.teamColorEditText);
        Button startScoutingButton = (Button)findViewById(R.id.startScoutingButton);
    }
}
