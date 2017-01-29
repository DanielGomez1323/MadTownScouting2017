package com.example.pollo.madtownscouting2017;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class TeamRoster extends AppCompatActivity {
    ListView teamrosterlistView;
    SQLiteDatabase myDB = null;
    Cursor c;
    TeamListAdapter teamAdapter;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_roster);
        teamrosterlistView = (ListView)findViewById(R.id.teamrosterlistView);
        intent = new Intent(this, DataUpload.class);
        myDB = openOrCreateDatabase("FRC", MODE_PRIVATE, null);
        c = myDB.rawQuery("SELECT teamNumber,_id, count(teamNumber) AS matchCount FROM SteamWorks GROUP BY teamNumber ORDER BY teamNumber asc", null);
        int i = c.getCount();
        if (i > 0) {
            try {
                teamrosterlistView = (ListView) findViewById(R.id.teamrosterlistView);
                teamAdapter = new TeamListAdapter(TeamRoster.this, c, 0, 0);
                teamrosterlistView.setAdapter(teamAdapter);
                teamrosterlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) {
                        String tNumber;
                        Cursor cur = (Cursor) teamAdapter.getItem(position);
                        cur.moveToPosition(position);
                        // Identifies the match number of the list component that you clicked, and prepares
                        // the values of the ScoutingData instance with the data from that list component
                        // so that when you click the send data button it will send the data from the match that you have last clicked on
                        tNumber = cur.getString(cur.getColumnIndexOrThrow("teamNumber"));
                        intent.putExtra("search", "t" + tNumber);
                        goToSendData();
                    }
                });
            } catch (Exception e) {
                Log.d("ERROR", e.toString());
            }
        }
    }
    public void goToSendData(){
        startActivity(intent);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(Integer.parseInt(android.os.Build.VERSION.SDK)> 5
                && keyCode == KeyEvent.KEYCODE_BACK){
            c.close();
            myDB.close();
        }
        return super.onKeyDown(keyCode, event);
    }
}
