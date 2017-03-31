package com.example.pollo.madtownscouting2017;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


public class threevthreeAdapter extends AppCompatActivity {

    DefaultHttpClient   httpclient = new DefaultHttpClient(new BasicHttpParams());
    HttpPost httppost = new HttpPost(www.gorohi.com/1323/2017/silicon/teamdata.php);
// Depends on your web service
            httppost.setHeader("Content-type", "application/json");

    InputStream inputStream = null;
    String result = null;
    try {
        HttpResponse response = httpclient.execute(httppost);
        HttpEntity entity = response.getEntity();

        inputStream = entity.getContent();
        // json is UTF-8 by default
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8);
        StringBuilder sb = new StringBuilder();

        String line = null;
        while ((line = reader.readLine()) != null)
        {
            sb.append(line + "\n");
        }
        result = sb.toString();
    } catch (Exception e) {
        // Oops
    }
    finally {
        try{if(inputStream != null)inputStream.close();}catch(Exception squish){}
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threevthree_adapter);


        for (int i=0; i < jArray.length(); i++)
        {
            try {
                JSONObject oneObject = jArray.getJSONObject(i);
                
    }
}
