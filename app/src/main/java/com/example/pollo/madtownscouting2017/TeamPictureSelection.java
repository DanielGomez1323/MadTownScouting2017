package com.example.pollo.madtownscouting2017;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Created by 19IsaacD740 on 1/17/2017.
 */
public class TeamPictureSelection extends AppCompatActivity {
    ListView teamsLV;
    SQLiteDatabase myDB = null;
    Cursor c;
    TeamListAdapter teamListAdapter;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_list);
        teamsLV = (ListView) findViewById(R.id.pictureListView);
        intent = new Intent(this, PictureList.class);
        myDB = openOrCreateDatabase("FRC", MODE_PRIVATE, null);
        c = myDB.rawQuery("SELECT teamNumber,_id, count(teamNumber) AS matchCount FROM TeamPictures GROUP BY teamNumber ORDER BY teamNumber asc", null);
        c.moveToFirst();
        int i = c.getCount();
        if (i > 0) {
            try {
                teamListAdapter = new TeamListAdapter(TeamPictureSelection.this, c, 0, 1);
                teamsLV.setAdapter(teamListAdapter);
                teamsLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) {
                        String tNumber;
                        Cursor cur = (Cursor) teamListAdapter.getItem(position);
                        cur.moveToPosition(position);
                        tNumber = cur.getString(cur.getColumnIndexOrThrow("teamNumber"));
                        intent.putExtra("TEAM_NUMBER", tNumber);
                        startActivity(intent);
                    }
                });
            } catch (Exception e) {
                Log.d("ERROR", e.toString());
            }
        }
    }

}
