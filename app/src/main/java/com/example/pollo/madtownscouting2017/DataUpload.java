package com.example.pollo.madtownscouting2017;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class DataUpload extends AppCompatActivity {
    SQLiteDatabase myDB = null;
    ListView lv;
    Cursor c;
    Button uploadButton;
    Button deleteButton;
    Button scrollUpButton;
    Match match;
    String query;
    String address = "http://www.gorohi.com/1323/2017/cvr/data.php";
    String _id;
    dataListAdapter listAdapter;

    public class Match {
        String teamNumber;
        String matchNumber;
        String teamColor;

        String autoGearAttempt;
        String autoGearSuccess;
        String autoHighScored;
        String autoLowScored;
        String autoHighMissed;
        String autoLowMissed;
        String baseLine;
        String gearsPickedUp;
        String gearsHung;
        String climbTime;
        String climbSuccess;
        String gearsDropped;
        String gearsDroppedHuman;
        String allianceKPA;
        String tbh;

        public void loadDatabase(int id) {
            myDB = openOrCreateDatabase("FRC", MODE_PRIVATE, null);
            Cursor c = myDB.rawQuery("SELECT * FROM SteamWorks WHERE _id = " + String.valueOf(id), null);
            c.moveToFirst();
            if (c.getCount() > 0) {
                teamNumber = c.getString(c.getColumnIndex("teamNumber"));
                matchNumber = c.getString(c.getColumnIndex("matchNumber"));
                teamColor = c.getString(c.getColumnIndex("teamColor"));
                autoGearAttempt = c.getString(c.getColumnIndex("autoGearAttempt"));
                autoGearSuccess = c.getString(c.getColumnIndex("autoGearSuccess"));
                autoHighScored = c.getString(c.getColumnIndex("autoHighScored"));
                autoLowScored = c.getString(c.getColumnIndex("autoLowScored"));
                autoHighMissed = c.getString(c.getColumnIndex("autoHighMissed"));
                autoLowMissed = c.getString(c.getColumnIndex("autoLowMissed"));
                baseLine = c.getString(c.getColumnIndex("baseLine"));
                gearsPickedUp = c.getString(c.getColumnIndex("gearsPickedUp"));
                gearsHung = c.getString(c.getColumnIndex("gearsHung"));
                climbTime = c.getString(c.getColumnIndex("climbTime"));
                climbSuccess = c.getString(c.getColumnIndex("climbSuccess"));
                gearsDropped = c.getString(c.getColumnIndex("gearsDropped"));
                gearsDroppedHuman = c.getString(c.getColumnIndex("gearsDroppedHuman"));
                allianceKPA = c.getString(c.getColumnIndex("allianceKPA"));
                tbh = c.getString(c.getColumnIndex("tbh"));
                Toast.makeText(getApplicationContext(), "Team " + teamNumber + ", Match " + matchNumber + " selected", Toast.LENGTH_SHORT).show();
            }
        }

        public boolean isLoaded() {
            boolean hi = false;
            if (teamNumber != null) {
                hi = true;
            } else {
                hi = false;
            }
            return hi;
        }
    }

    public static String toJSon(Match data) {
        try {
            JSONObject object = new JSONObject();
            object.put("teamNumber", data.teamNumber);
            object.put("matchNumber", data.matchNumber);
            object.put("teamColor", data.teamColor);
            object.put("baseLine", data.baseLine);
            object.put("autoGearAttempt", data.autoGearAttempt);
            object.put("autoGearSuccess", data.autoGearSuccess);
            object.put("autoHighScored", data.autoHighScored);
            object.put("autoHighMissed", data.autoHighMissed);
            object.put("autoLowScored", data.autoLowScored);
            object.put("autoLowMissed", data.autoLowMissed);
            object.put("gearsPickedUp", data.gearsPickedUp);
            object.put("gearsHung", data.gearsHung);
            object.put("gearsDropped", data.gearsDropped);
            object.put("gearsDroppedHuman", data.gearsDroppedHuman);
            object.put("climbTime", data.climbTime);
            object.put("climbSuccess", data.climbSuccess);
            object.put("allianceKPA", data.allianceKPA);
            object.put("tbh", data.tbh);
            return object.toString();
        } catch (Exception e) {
            System.out.print(e);
        }
        return null;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_upload);
        lv = (ListView) findViewById(R.id.webListView);
        uploadButton = (Button) findViewById(R.id.sendToWebButton);
        deleteButton = (Button) findViewById(R.id.deleteButton);
        scrollUpButton = (Button) findViewById(R.id.ToTopButton);
        match = new Match();
        query = "SELECT * FROM SteamWorks";
        Intent i = getIntent();
        String search = i.getStringExtra("search");
        if (search != null) {
            if (search.toLowerCase().contains("t")) {
                search = search.replaceAll("[^\\d.]", "");
                query = "SELECT * FROM SteamWorks WHERE teamNumber = " + search;
            } else if (search.toLowerCase().contains("m")) {
                search = search.replaceAll("[^\\d.]", "");
                query = "SELECT * FROM SteamWorks WHERE matchNumber = " + search;
            } else {
                search = search.replaceAll("[^\\d.]", "");
                query = "SELECT * FROM SteamWorks WHERE teamNumber = " + search + " OR matchNumber = " + search;
            }
        }
        myDB = openOrCreateDatabase("FRC", MODE_PRIVATE, null);

        Cursor cursor = myDB.rawQuery("SELECT * FROM Events WHERE _id = 1", null);
        cursor.moveToFirst();
        switch(cursor.getString(cursor.getColumnIndex("event"))){
            case "CVR":
                address = "http://www.gorohi.com/1323/2017/cvr/data.php";
                break;
            case "Davis":
                address = "http://www.gorohi.com/1323/2017/davis/data.php";
                break;
            case "Houston":
                address = "http://www.gorohi.com/1323/2017/houston/data.php";
                break;
            default:
                address = "http://www.gorohi.com/1323/2017/davis/data.php";
                break;
        }
        cursor.close();

        c = myDB.rawQuery(query, null);
        c.moveToFirst();
        if (c.getCount() > 0) {
            try {
                listAdapter = new dataListAdapter(DataUpload.this, c, 0);
                lv.setAdapter(listAdapter);
                lv.setLongClickable(true);
                lv.setSelection(listAdapter.getCount() - 1);
                c.moveToPosition(listAdapter.getCount() - 1);
                _id = c.getString(c.getColumnIndex("_id"));
                match.loadDatabase(Integer.parseInt(_id));
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Cursor cc = (Cursor) listAdapter.getItem(position);
                        cc.moveToPosition(position);
                        _id = cc.getString(cc.getColumnIndex("_id"));
                        match.loadDatabase(Integer.parseInt(_id));
                    }
                });
                lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                                   int pos, long id) {
                        // TODO Auto-generated method stub
                        Cursor cur = (Cursor) listAdapter.getItem(pos);
                        cur.moveToPosition(pos);
                        String editID = cur.getString(cur.getColumnIndex("_id"));
                        Intent editIntent = new Intent(getApplicationContext(), EditData.class);
                        editIntent.putExtra("ID", editID);
                        startActivityForResult(editIntent, 1);
                        return true;
                    }
                });
            } catch (Exception e) {
                Log.d("ERROR", e.toString());
            }


            uploadButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (match.isLoaded()) {
                        String json = toJSon(match);
                        if (json != null) {
                            new MyAsyncTask().execute(json);
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Please select a match.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        deleteButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                myDB = openOrCreateDatabase("FRC", MODE_PRIVATE, null);
                myDB.execSQL("DELETE FROM SteamWorks WHERE _id = " + _id);
                updateList();
                return true;
            }
        });
        scrollUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lv.smoothScrollToPosition(0);
            }
        });
    }

        private class MyAsyncTask extends AsyncTask<String, Integer, Double> {
            String response = "";

            @Override
            protected Double doInBackground(String... params) {
                postData(params[0]);
                return null;
            }

            protected void onPostExecute(Double result) {
                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
            }


            public void postData(String jsonString) {
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(address);
                InputStream inputStream = null;
                try {
                    List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(1);
                    nameValuePair.add(new BasicNameValuePair("data", jsonString));
                    try {
                        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));

                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    HttpResponse httpResponse = httpClient.execute(httpPost);
                    inputStream = httpResponse.getEntity().getContent();
                    if (inputStream != null) {
                        response = convertInputStreamToString(inputStream);
                    } else {
                        response = "Did not Work!";
                    }
                    inputStream.close();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

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

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {
            c.close();
            myDB.close();
            Intent i = new Intent(this, ScoutingMenu.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
            return true;
        }
        return super.onKeyLongPress(keyCode, event);
    }

        private static String convertInputStreamToString(InputStream inputStream) throws IOException{
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            String result = "";
            while((line = bufferedReader.readLine()) != null)
            {
                result += line;
            }
            inputStream.close();
            return result;
        }
    public void updateList(){
        myDB = openOrCreateDatabase("FRC", MODE_PRIVATE, null);
        Cursor c2  = myDB.rawQuery(query, null);
        listAdapter.changeCursor(c2);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == Activity.RESULT_OK){
                String match = data.getStringExtra("MATCH_NUMBER");
                String teamN = data.getStringExtra("TEAM_NUMBER");
                Toast.makeText(getApplicationContext(),"Team " + teamN + ", Match " + match + "updated.", Toast.LENGTH_SHORT).show();
                updateList();
            }
            if (resultCode == Activity.RESULT_CANCELED) {

            }
        }
    }
}
