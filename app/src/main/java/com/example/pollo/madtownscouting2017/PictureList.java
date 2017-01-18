package com.example.pollo.madtownscouting2017;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class PictureList extends AppCompatActivity {

    ListView picList;
    SQLiteDatabase myDB = null;
    Cursor c;
    String teamNumber;
    PictureListAdapter picAdapter;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_list);
        picList = (ListView) findViewById(R.id.pictureListView);
        Intent intent = getIntent();
        teamNumber = intent.getStringExtra("TEAM_NUMBER");
        myDB = openOrCreateDatabase("FRC", MODE_PRIVATE, null);
        c = myDB.rawQuery("SELECT * FROM TeamPictures WHERE teamNumber = " + teamNumber, null);
        int i = c.getCount();
        if(i > 0){
            try{
                picAdapter = new PictureListAdapter(PictureList.this, c, 0);
                picList.setAdapter(picAdapter);
                picList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) {
                        Intent uploadIntent = new Intent(getApplicationContext(), UploadPicture.class);
                        Cursor cur = (Cursor) picAdapter.getItem(position);
                        cur.moveToPosition(position);
                        id = cur.getString(cur.getColumnIndex("_id"));
                        uploadIntent.putExtra("id", id);
                        startActivity(uploadIntent);
                    }
                });
            } catch (Exception e) {
                Log.d("ERROR", e.toString());
            }
        }
    }
}
