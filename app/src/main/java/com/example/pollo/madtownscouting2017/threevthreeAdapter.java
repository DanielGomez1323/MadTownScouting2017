package com.example.pollo.madtownscouting2017;

import android.app.DownloadManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
//import

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class threevthreeAdapter extends AppCompatActivity {

        public void main(String[] args) {
            String url = "http://www.gorohi.com/1323/2017/pullData.php";
            JsonObjectRequest jsObjRequest = new JsonObjectRequest
                    (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            List<MatchData> matchNumbers = new ArrayList<>();
                            JSONArray matches = response.names();
                            try{
                                for (int i = 0; i < response.length(); i++) {
                                    String matchNumber = matches.getString(i);
                                    JSONObject jsonMatch = response.getJSONObject("matchNumber");
                                    JSONObject jsonteamNumber = jsonMatch.getJSONObject("teamNumber");
                                    MatchData test = new MatchData(jsonMatch.getInt("matchNumber"));
                                    test.addAlliance(MatchData.teamColor.red,jsonteamNumber.getInt("teamNumber"),jsonteamNumber.getInt("teamNumber"),jsonteamNumber.getInt("teamNumber"));
                                    matchNumbers.add(i,test);
                                }

                                Collections.sort(matchNumbers);
//                                    matchNumbers.
                            } catch (final JSONException e) {
                            }
                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // TODO Auto-generated method stub

                        }
                    });


        }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threevthree_adapter);


    }
}
