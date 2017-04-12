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
import android.view.WindowManager;
import android.widget.Button;
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

public class StartMenu extends AppCompatActivity {
    EditText searchMenuSearchBox;
    Button searchButton;
    Button scoutButton;
    Button uploadData;
    Button teamsView;
    Button addPhoto;
    Button viewPhoto;
    Button importScheduleButton;
//    Button threeVThree;
    SQLiteDatabase myDB = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_menu);
        searchMenuSearchBox = (EditText) findViewById(R.id.searchMenuSearchBox);
        searchButton = (Button) findViewById(R.id.searchButton);
        scoutButton = (Button) findViewById(R.id.scoutButton);
        uploadData = (Button) findViewById(R.id.uploadData);
        teamsView = (Button) findViewById(R.id.teamsView);
        addPhoto = (Button) findViewById(R.id.addPhoto);
        viewPhoto = (Button) findViewById(R.id.viewPhoto);
        importScheduleButton = (Button)findViewById(R.id.importScheduleButton);
//        threeVThree = (Button)findViewById(R.id.threeVThree);

        createPicturesDatabase();
        createDatabase();
        createMatchDatabase();

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent searchIntent = new Intent(getApplicationContext(), DataUpload.class);
                searchIntent.putExtra("search", searchMenuSearchBox.getText());
                startActivity(searchIntent);
            }
        });
        scoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent scoutIntent = new Intent(getApplicationContext(), ScoutingMenu.class);
                startActivity(scoutIntent);
            }
        });
        uploadData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent uploadIntent = new Intent(getApplicationContext(), DataUpload.class);
                startActivity(uploadIntent);
            }
        });
        teamsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent teamsIntent = new Intent(getApplicationContext(), TeamRoster.class);
                startActivity(teamsIntent);
            }
        });
        addPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addPhotoIntent = new Intent(getApplicationContext(), addPhoto.class);
                startActivity(addPhotoIntent);
            }
        });
        viewPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewPhotoIntent = new Intent(getApplicationContext(), TeamPictureSelection.class);
                startActivity(viewPhotoIntent);
            }
        });
/*        threeVThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent threevthreeAdapterIntent = new Intent(getApplicationContext(), threevthreeAdapter.class);
                startActivity(threevthreeAdapterIntent);
            }
        });*/
        importScheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pullSchedule();
            }
        });
    }
    public void createDatabase(){
        try{
            myDB = openOrCreateDatabase("FRC", MODE_PRIVATE, null);
            myDB.execSQL("CREATE TABLE IF NOT EXISTS SteamWorks ( _id INTEGER PRIMARY KEY AUTOINCREMENT, teamNumber int, matchNumber int, teamColor int, robotPosition int, autoGearSuccess int, autoHighScored int, gearsHung int, climbTime int, climbSuccess int, tbh varchar)");
            if (myDB != null)
                myDB.close();
        } catch (SQLException e) {
            Log.e("Error", "Error", e);
        }
    }
    public void createPicturesDatabase(){
        try{
            myDB = openOrCreateDatabase("FRC", MODE_PRIVATE, null);
            myDB.execSQL("CREATE TABLE IF NOT EXISTS TeamPictures ( _id INTEGER PRIMARY KEY AUTOINCREMENT, teamNumber int, pic1 VARCHAR)");
            if (myDB != null)
                myDB.close();
        } catch (SQLException e) {
            Log.e("Error", "Error", e);
        }
    }
    public void createMatchDatabase(){
        try{
            myDB = openOrCreateDatabase("FRC", MODE_PRIVATE, null);
            myDB.execSQL("CREATE TABLE IF NOT EXISTS MatchSchedule (_id INTEGER PRIMARY KEY AUTOINCREMENT, matchNumber int, teamNumber int)");
            if (myDB != null)
                myDB.close();
        }   catch (SQLException e) {
            Log.e("Error", "Error", e);
        }
    }
    public void pullSchedule(){
        String url = "http://gorohi.com/1323/api/matchSchedule.php?compLevel=qm";
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


                                    ContentValues c = new ContentValues();
                                    c.put("matchNumber", matchNumber);
                                    c.put("teamNumber", teamNumber);
                                    myDB = openOrCreateDatabase("FRC", MODE_PRIVATE, null);
                                        try {
                                            myDB.insertOrThrow("MatchSchedule", null, c);
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
