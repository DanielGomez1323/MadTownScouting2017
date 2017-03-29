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
    EditText robotPositionEditText;
    EditText autoGearSuccessEnterText;
    EditText autoHighScoredEnterText;
    EditText gearsHungEnterText;
    EditText climbTimeEnterText;
    EditText climbSuccessEnterText;
    EditText tbhEnterText;

    Button confirmEditsButton;

    String tNumber;
    String mNumber;

    SQLiteDatabase myDB = null;
    Cursor c;
    String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);
        teamNumberEnterText = (EditText)findViewById(R.id.teamNumberEnterText);
        matchNumberEnterText = (EditText)findViewById(R.id.matchNumberEnterText);
        teamColorEnterText = (EditText)findViewById(R.id.teamColorEnterText);
        robotPositionEditText = (EditText)findViewById(R.id.robotPositionEditText);
        autoGearSuccessEnterText = (EditText)findViewById(R.id.autoGearSuccessEnterText);
        gearsHungEnterText = (EditText)findViewById(R.id.gearsHungEnterText);
        climbTimeEnterText = (EditText) findViewById(R.id.climbTimeEnterText);
        climbSuccessEnterText = (EditText)findViewById(R.id.climbSuccessEnterText);
        tbhEnterText = (EditText)findViewById(R.id.tbhEnterText);
        confirmEditsButton = (Button)findViewById(R.id.confirmEditsButton);

        Intent intent = getIntent();
        id = intent.getStringExtra("ID");
        myDB = openOrCreateDatabase("FRC", MODE_PRIVATE, null);
        c = myDB.rawQuery("SELECT * FROM SteamWorks WHERE _id =" + id, null);
        c.moveToFirst();
        int i = c.getCount();
        if(i > 0){
            teamNumberEnterText.setText(c.getString(c.getColumnIndex("teamNumber")), TextView.BufferType.EDITABLE);
            matchNumberEnterText.setText(c.getString(c.getColumnIndex("matchNumber")), TextView.BufferType.EDITABLE);
            teamColorEnterText.setText(c.getString(c.getColumnIndex("teamColor")), TextView.BufferType.EDITABLE);
            //robotPositionEditText.setText(c.getString(c.getColumnIndex("robotPosition")), TextView.BufferType.EDITABLE);
            autoGearSuccessEnterText.setText(c.getString(c.getColumnIndex("autoGearSuccess")), TextView.BufferType.EDITABLE);
            autoHighScoredEnterText.setText(c.getString(c.getColumnIndex("autoHighScored")), TextView.BufferType.EDITABLE);
            gearsHungEnterText.setText(c.getString(c.getColumnIndex("gearsHung")), TextView.BufferType.EDITABLE);
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
            teamColorEnterText.getText().toString() + /*", robotPosition = " + robotPositionEditText.getText().toString() + */", autoGearSuccess = " + autoGearSuccessEnterText.getText().toString() +
            ", autoHighScored = " + autoHighScoredEnterText.getText().toString() + ", gearsHung = " +
            gearsHungEnterText.getText().toString() + ", climbTime = " + climbTimeEnterText.getText().toString() +
            ", climbSuccess = " + climbSuccessEnterText.getText().toString() + ", tbh = '" + tbh + "' WHERE _id = " + id);
        }catch (SQLException e){
            System.out.print(e);
        }
        Cursor cur = myDB.rawQuery("SELECT * FROM SteamWorks Where _id =" + id, null);
        cur.moveToFirst();
        tNumber = cur.getString(cur.getColumnIndex("teamNumber"));
        mNumber = cur.getString(cur.getColumnIndex("matchNumber"));
        cur.close();
        myDB.close();;
        Intent returnIntent = new Intent();
        returnIntent.putExtra("TEAM_NUMBER", tNumber);
        returnIntent.putExtra("MATCH_NUMBER",  mNumber);
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }
}
