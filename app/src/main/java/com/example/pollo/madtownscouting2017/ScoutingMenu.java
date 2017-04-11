package com.example.pollo.madtownscouting2017;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;

public class ScoutingMenu extends AppCompatActivity {
    EditText teamNumberEditText;
    EditText matchNumberEditText;
    Button startScoutingButton;
    Button importScheduleButton;
    CheckBox redCheckBox;
    CheckBox blueCheckBox;
    String teamColor;

    SQLiteDatabase myDB = null;
    Cursor c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scouting_menu);
        teamNumberEditText = (EditText)findViewById(R.id.teamNumberEditText);
        matchNumberEditText = (EditText)findViewById(R.id.matchNumberEditText);
        startScoutingButton = (Button)findViewById(R.id.startScoutingButton);
        importScheduleButton = (Button)findViewById(R.id.importScheduleButton);
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
        Cursor c = myDB.rawQuery("SELECT * FROM MatchSchedule WHERE teamColor = " + String.valueOf(teamColor), null);
        if (c.getCount()> 0){
            c.moveToFirst();
            int teamNumber = c.getColumnIndex("teamNumber");
            teamNumberEditText.setText(String.valueOf(teamNumber));

        }else{

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
        importScheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pullSchedule();
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
    public void pullSchedule(){
        String url = "http://gorohi.com/1323/test/index.php";
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray matches = response.getJSONArray("matches");
                            for (int i = 0; i < matches.length(); i++) {
                                JSONObject m = matches.getJSONObject(i);
                                int matchNumber = m.getInt("matchNumber");
                                JSONArray teams = m.getJSONArray("teams");
                                for (int j = 0; j < teams.length(); j++) {
                                    JSONObject t = teams.getJSONObject(j);
                                    int teamNumber = t.getInt("teamNumber");
                                    int teamColor = t.getInt("teamColor");


                                    ContentValues c = new ContentValues();
                                    myDB = openOrCreateDatabase("FRC", MODE_PRIVATE, null);
                                        try {
                                            myDB.insert("MatchSchedule", null, c);
                                        }catch (SQLException s){
                                            Toast.makeText(getApplication(), "Error saving", Toast.LENGTH_SHORT).show();
                                        }
                                    if (myDB != null){
                                        myDB.close();
                                    }
                                }

                            }
                    } catch (final JSONException e) {
                                    Log.d("ERROR", e.toString());
                                }
                            }
                        }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // TODO Auto-generated method stub
                                error.printStackTrace();
                            }
                        });
                        Volley.newRequestQueue(this).add(jsObjRequest);
                }

}

