package com.example.pollo.madtownscouting2017;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;

public class AutoPeriod extends AppCompatActivity {
    CheckBox baselineCheckBox;
    CheckBox gearAttemptCheckBox;
    CheckBox gearSuccessCheckBox;
    CheckBox highCheckBox;
    CheckBox lowCheckBox;
    SeekBar autoBallsSeekBar;
    TextView ballCountTextView;
    Button continueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_period);
        baselineCheckBox = (CheckBox)findViewById(R.id.baselineCheckBox);
        gearAttemptCheckBox = (CheckBox)findViewById(R.id.gearAttemptCheckBox);
        gearSuccessCheckBox = (CheckBox)findViewById(R.id.gearSuccessCheckBox);
        highCheckBox = (CheckBox)findViewById(R.id.highCheckBox);
        lowCheckBox = (CheckBox)findViewById(R.id.lowCheckBox);
        autoBallsSeekBar = (SeekBar)findViewById(R.id.autoBallsSeekBar);
        ballCountTextView = (TextView)findViewById(R.id.ballCountTextView);
        continueButton = (Button)findViewById(R.id.continueButton);

        autoBallsSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ballCountTextView.setText("Balls made: " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent teleIntent = new Intent(getApplicationContext(), TeleopPeriod.class);
                Intent previousIntent = getIntent();
                teleIntent.putExtra("teamNumber", previousIntent.getStringExtra("teamNumber"));
                teleIntent.putExtra("matchNumber", previousIntent.getStringExtra("matchNumber"));
                teleIntent.putExtra("teamColor", previousIntent.getStringExtra("teamColor"));
                startActivity(teleIntent);
            }
        });
    }
}
