package com.example.pollo.madtownscouting2017;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

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
    }
}
