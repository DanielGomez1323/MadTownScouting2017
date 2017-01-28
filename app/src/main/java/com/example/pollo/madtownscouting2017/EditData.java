package com.example.pollo.madtownscouting2017;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class EditData extends AppCompatActivity {
    TextView teamNumberTV;
    EditText teamNumberEnterText;
    TextView matchNumberTV;
    EditText matchNumberEnterText;
    TextView teamColorTV;
    EditText teamColorEnterText;
    TextView crossedBaselineTV;
    EditText baselineCrossedEnterText;
    TextView autoGearAttemptTV;
    EditText autoGearAttemptEnterText;
    TextView autoGearSuccessTV;
    EditText autoGearSuccessEnterText;
    TextView autoHighScoredTV;
    EditText autoHighScoredEnterText;
    TextView autoHighMissedTV;
    EditText autoHighMissedEnterText;
    TextView autoLowScoredTV;
    EditText autoLowScoredEnterText;
    TextView autoLowMissedTV;
    EditText autoLowMissedEnterText;
    TextView gearsPickedUpTV;
    EditText gearsPickedUpEnterText;
    TextView gearsHungTV;
    EditText gearsHungEnterText;
    TextView gearsDroppedTV;
    EditText gearsDroppedEnterText;
    TextView highShootSpeedTV;
    EditText highShootSpeedEnterText;
    TextView highShotsMissedTV;
    EditText highShotsMissedEnterText;
    TextView lowShootSpeedTV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);
        teamNumberTV = (TextView)findViewById(R.id.teamNumberTV);
        teamNumberEnterText = (EditText)findViewById(R.id.teamNumberEnterText);
        matchNumberTV = (TextView)findViewById(R.id.matchNumberTV);
        matchNumberEnterText = (EditText)findViewById(R.id.matchNumberEnterText);
        teamColorTV = (TextView)findViewById(R.id.teamColorTV);
        teamColorEnterText = (EditText)findViewById(R.id.teamColorEnterText);
        crossedBaselineTV = (TextView)findViewById(R.id.crossedBaselineTV);
        baselineCrossedEnterText = (EditText)findViewById(R.id.baselineCrossedEnterText);
        autoGearAttemptTV = (TextView)findViewById(R.id.autoGearAttemptTV);
        autoGearAttemptEnterText = (EditText)findViewById(R.id.autoGearAttemptEnterText);
        autoGearSuccessTV = (TextView)findViewById(R.id.autoGearSuccessTV);
        autoGearSuccessEnterText = (EditText)findViewById(R.id.autoGearSuccessEnterText);
        autoHighScoredTV = (TextView)findViewById(R.id.autoHighScoredTV);
        autoHighScoredEnterText = (EditText)findViewById(R.id.autoHighScoredEnterText);
        autoHighMissedTV = (TextView)findViewById(R.id.autoHighMissedTV);
        autoHighMissedEnterText = (EditText)findViewById(R.id.autoHighMissedEnterText);
        autoLowScoredTV = (TextView)findViewById(R.id.autoLowScoredTV);
        autoLowScoredEnterText = (EditText)findViewById(R.id.autoLowScoredEnterText);
        autoLowMissedTV = (TextView)findViewById(R.id.autoLowMissedTV);
        autoLowMissedEnterText = (EditText)findViewById(R.id.autoLowMissedEnterText);
        gearsPickedUpTV = (TextView)findViewById(R.id.gearsPickedUpTV);
        gearsPickedUpEnterText = (EditText)findViewById(R.id.gearsPickedUpEnterText);
        gearsHungTV = (TextView)findViewById(R.id.gearsHungTV);
        gearsHungEnterText = (EditText)findViewById(R.id.gearsHungEnterText);
        gearsDroppedTV = (TextView)findViewById(R.id.gearsDroppedTV);
        gearsDroppedEnterText = (EditText)findViewById(R.id.gearsDroppedEnterText);
        highShootSpeedTV = (TextView)findViewById(R.id.highShootSpeedTV);
        highShootSpeedEnterText = (EditText)findViewById(R.id.highShootSpeedEnterText);
        highShotsMissedTV = (TextView)findViewById(R.id.highShotsMissedEnterText);
        highShotsMissedEnterText = (EditText)findViewById(R.id.highShotsMissedEnterText);
        lowShootSpeedTV = (TextView)findViewById(R.id.lowShootSpeedTV);

    }
}
