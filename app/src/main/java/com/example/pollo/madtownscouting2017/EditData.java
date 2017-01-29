package com.example.pollo.madtownscouting2017;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class EditData extends AppCompatActivity {
    EditText teamNumberEnterText;
    EditText matchNumberEnterText;
    EditText teamColorEnterText;
    EditText baselineCrossedEnterText;
    EditText autoGearAttemptEnterText;
    EditText autoGearSuccessEnterText;
    EditText autoHighScoredEnterText;
    EditText autoHighMissedEnterText;
    EditText autoLowScoredEnterText;
    EditText autoLowMissedEnterText;
    EditText gearsPickedUpEnterText;
    EditText gearsHungEnterText;
    EditText gearsDroppedEnterText;
    EditText highShootSpeedEnterText;
    EditText highShotsMissedEnterText;
    EditText lowShootSpeedEnterText;
    EditText lowShotsMissedEnterText;
    EditText hopperIntakeEnterText;
    EditText climbTimeEnterText;
    EditText climbSuccessEnterText;
    EditText tbhEnterText;

    Button confirmEditsButton;

    String tNumber;
    String mNumber;

    SQLiteDatabase myDB = null;
    Cursor c;
    int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);
        teamNumberEnterText = (EditText)findViewById(R.id.teamNumberEnterText);
        matchNumberEnterText = (EditText)findViewById(R.id.matchNumberEnterText);
        teamColorEnterText = (EditText)findViewById(R.id.teamColorEnterText);
        baselineCrossedEnterText = (EditText)findViewById(R.id.baselineCrossedEnterText);
        autoGearAttemptEnterText = (EditText)findViewById(R.id.autoGearAttemptEnterText);
        autoGearSuccessEnterText = (EditText)findViewById(R.id.autoGearSuccessEnterText);
        autoHighScoredEnterText = (EditText)findViewById(R.id.autoHighScoredEnterText);
        autoHighMissedEnterText = (EditText)findViewById(R.id.autoHighMissedEnterText);
        autoLowScoredEnterText = (EditText)findViewById(R.id.autoLowScoredEnterText);
        autoLowMissedEnterText = (EditText)findViewById(R.id.autoLowMissedEnterText);
        gearsPickedUpEnterText = (EditText)findViewById(R.id.gearsPickedUpEnterText);
        gearsHungEnterText = (EditText)findViewById(R.id.gearsHungEnterText);
        gearsDroppedEnterText = (EditText)findViewById(R.id.gearsDroppedEnterText);
        highShootSpeedEnterText = (EditText)findViewById(R.id.highShootSpeedEnterText);
        highShotsMissedEnterText = (EditText)findViewById(R.id.highShotsMissedEnterText);
        lowShootSpeedEnterText = (EditText)findViewById(R.id.lowShootSpeedEnterText);
        lowShotsMissedEnterText = (EditText)findViewById(R.id.lowShotsMissedEnterText);
        hopperIntakeEnterText = (EditText)findViewById(R.id.hopperIntakeEnterText);
        climbTimeEnterText = (EditText) findViewById(R.id.climbTimeEnterText);
        climbSuccessEnterText = (EditText)findViewById(R.id.climbSuccessEnterText);
        tbhEnterText = (EditText)findViewById(R.id.tbhEnterText);
        confirmEditsButton = (Button)findViewById(R.id.confirmEditsButton);

        Intent intent = getIntent();
        id = intent.getIntExtra("ID", 0);
        myDB = openOrCreateDatabase("FRC", MODE_PRIVATE, null);
        c = myDB.rawQuery("SELECT * FROM SteamWorks WHERE _id =" + id, null);
        c.moveToFirst();
        int i = c.getCount();
        if(i > 0){
            teamNumberEnterText.setText(c.getString(c.getColumnIndex("teamNumber")), TextView.BufferType.EDITABLE);
            matchNumberEnterText.setText(c.getString(c.getColumnIndex("matchNumber")), TextView.BufferType.EDITABLE);
            teamColorEnterText.setText(c.getString(c.getColumnIndex("teamColor")), TextView.BufferType.EDITABLE);
            baselineCrossedEnterText.setText(c.getString(c.getColumnIndex("baseLine")), TextView.BufferType.EDITABLE);
            autoGearAttemptEnterText.setText(c.getString(c.getColumnIndex("autoGearAttempt")), TextView.BufferType.EDITABLE);
            autoGearSuccessEnterText.setText(c.getString(c.getColumnIndex("autoGearSuccess")), TextView.BufferType.EDITABLE);
            autoHighScoredEnterText.setText(c.getString(c.getColumnIndex("autoHighScored")), TextView.BufferType.EDITABLE);
            autoHighMissedEnterText.setText(c.getString(c.getColumnIndex("autoHighMissed")), TextView.BufferType.EDITABLE);
            autoLowScoredEnterText.setText(c.getString(c.getColumnIndex("autoLowScored")), TextView.BufferType.EDITABLE);
            autoLowMissedEnterText.setText(c.getString(c.getColumnIndex("autoLowMissed")), TextView.BufferType.EDITABLE);
            gearsPickedUpEnterText.setText(c.getString(c.getColumnIndex("gearsPickedUp")), TextView.BufferType.EDITABLE);
            gearsHungEnterText.setText(c.getString(c.getColumnIndex("gearsHung")), TextView.BufferType.EDITABLE);
            gearsDroppedEnterText.setText(c.getString(c.getColumnIndex("gearsDropped")), TextView.BufferType.EDITABLE);
            highShootSpeedEnterText.setText(c.getString(c.getColumnIndex("highShootSpeed")), TextView.BufferType.EDITABLE);
            highShotsMissedEnterText.setText(c.getString(c.getColumnIndex("highShotsMissed")), TextView.BufferType.EDITABLE);
            lowShootSpeedEnterText.setText(c.getString(c.getColumnIndex("lowShootSpeed")), TextView.BufferType.EDITABLE);
            lowShotsMissedEnterText.setText(c.getString(c.getColumnIndex("lowShotsMissed")), TextView.BufferType.EDITABLE);
            hopperIntakeEnterText.setText(c.getString(c.getColumnIndex("hopperIntake")), TextView.BufferType.EDITABLE);
            climbTimeEnterText.setText(c.getString(c.getColumnIndex("climbTime")), TextView.BufferType.EDITABLE);
            climbSuccessEnterText.setText(c.getString(c.getColumnIndex("climbSuccess")), TextView.BufferType.EDITABLE);
            tbhEnterText.setText(c.getString(c.getColumnIndex("tbh")), TextView.BufferType.EDITABLE);
        }
        c.close();
        myDB.close();
        confirmEditsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeData();
            }
        });
    }
    public void changeData(){
        try{
            myDB = openOrCreateDatabase("FRC", MODE_PRIVATE, null);
            String tbh = tbhEnterText.getText().toString();
            tbh = tbh.replace("'","*");
            myDB.execSQL("UPDATE SteamWorks SET teamNumber = " + teamNumberEnterText.getText().toString() +
            ", matchNumber = " + matchNumberEnterText.getText().toString() + ", teamColor = " +
            teamColorEnterText.getText().toString() + ", baseLine = " + baselineCrossedEnterText.getText().toString() +
            ", autoGearAttempt = " + autoGearAttemptEnterText.getText().toString() + ", autoGearSuccess = " +
            autoGearSuccessEnterText.getText().toString() + ", autoHighScored = " + autoHighScoredEnterText.getText().toString() +
            ", autoHighMissed = " + autoHighMissedEnterText.getText().toString() + ", autoLowScored = " +
            autoLowScoredEnterText.getText().toString() + ", autoLowMissed = " + autoLowMissedEnterText.getText().toString() +
            ", gearsPickedUp = " + gearsPickedUpEnterText.getText().toString() + ", gearsHung = " +
            gearsHungEnterText.getText().toString() + ", gearsDropped = " + gearsDroppedEnterText.getText().toString() +
            ", highShootSpeed = " + highShootSpeedEnterText.getText().toString() + ", highShotsMissed = " +
            highShotsMissedEnterText.getText().toString() + ", lowShootSpeed = " + lowShootSpeedEnterText.getText().toString() +
            ", lowShotsMissed = " + lowShotsMissedEnterText.getText().toString() + ", hopperIntake = " +
            hopperIntakeEnterText.getText().toString() + ", climbTime = " + climbTimeEnterText.getText().toString() +
            ", climbSuccess = " + climbSuccessEnterText.getText().toString() + ", tbh = " + tbh + "' WHERE _id = " + id);
        }catch (SQLException e){
            System.out.print(e);
        }
        Cursor cur = myDB.rawQuery("SELECT * FROM SteamWorks Where _id =" + id, null);
        cur.moveToFirst();
        tNumber = cur.getString(cur.getColumnIndex("teamNumber"));
        mNumber = cur.getString(cur.getColumnIndex("teamColor"));
        cur.close();
        myDB.close();;
        Intent returnIntent = new Intent();
        returnIntent.putExtra("TEAM_NUMBER", tNumber);
        returnIntent.putExtra("TEAM_COLOR",  mNumber);
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }
}
