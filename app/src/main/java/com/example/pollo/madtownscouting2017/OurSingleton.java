package com.example.pollo.madtownscouting2017;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Pollo on 4/3/2017.
 */
public class OurSingleton {
    private static OurSingleton mOurSingletonInstance;
    private RequestQueue mRequestQueue;
    private static Context mContext;

    private OurSingleton(Context context){
        // Specify the application context
        mContext = context;
        // Get the request queue
        mRequestQueue = getRequestQueue();
    }

    public static synchronized OurSingleton getInstance(Context context){
        // If Instance is null then initialize new Instance
        if(mOurSingletonInstance == null){
           mOurSingletonInstance = new OurSingleton(context);
        }
        // Return MySingleton new Instance
        return mOurSingletonInstance;
    }

    public RequestQueue getRequestQueue(){
        // If RequestQueue is null the initialize new RequestQueue
        if(mRequestQueue == null){
            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }

        // Return RequestQueue
        return mRequestQueue;
    }

    public<T> void addToRequestQueue(Request<T> req, String tag){
        // Add the specified request to the request queue
        req.setTag(tag);
        getRequestQueue().add(req);
    }



}
