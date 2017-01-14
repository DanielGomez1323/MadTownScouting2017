package com.example.pollo.madtownscouting2017;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 19IsaacD740 on 1/13/2017.
 */
public class TeleopFragment extends android.support.v4.app.Fragment{



    public static TeleopFragment newInstance() {
        TeleopFragment fragment = new TeleopFragment();
        return fragment;
    }
    public TeleopFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.teleop_fragment, container, false);

        return rootView;
    }

}
