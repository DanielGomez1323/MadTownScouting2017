package com.example.pollo.madtownscouting2017;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by 19IsaacD740 on 1/13/2017.
 */
public class AutoFragment extends android.support.v4.app.Fragment {

    CheckBox baselineCheckBox;
    CheckBox gearAttemptCheckBox;
    CheckBox gearSuccessCheckBox;
    CheckBox highCheckBox;
    CheckBox lowCheckBox;
    SeekBar autoBallsSeekBar;
    TextView ballCountTextView;
    int autoBallsMade = 0;

    public static AutoFragment newInstance() {
        AutoFragment fragment = new AutoFragment();
        return fragment;
    }

    public AutoFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.auto_period_fragment, container, false);

        baselineCheckBox = (CheckBox) rootView.findViewById(R.id.baselineCheckBox);
        gearAttemptCheckBox = (CheckBox) rootView.findViewById(R.id.gearAttemptCheckBox);
        gearSuccessCheckBox = (CheckBox) rootView.findViewById(R.id.gearSuccessCheckBox);
        highCheckBox = (CheckBox) rootView.findViewById(R.id.highCheckBox);
        lowCheckBox = (CheckBox) rootView.findViewById(R.id.lowCheckBox);
        autoBallsSeekBar = (SeekBar) rootView.findViewById(R.id.autoBallsSeekBar);
        ballCountTextView = (TextView) rootView.findViewById(R.id.ballCountTextView);

        autoBallsSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ballCountTextView.setText("Balls made: " + progress);
                autoBallsMade = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        return rootView;
    }

    public Bundle getData(){
        Bundle b = new Bundle();
        if(baselineCheckBox.isChecked()){
            b.putString("baseLine", "1");
        }else{
            b.putString("baseLine", "0");
        }
        if(gearAttemptCheckBox.isChecked()){
            b.putString("autoGearAttempt", "1");
        }else{
            b.putString("autoGearAttempt", "0");
        }
        if(gearSuccessCheckBox.isChecked()){
            b.putString("autoGearSuccess", "1");
        }else{
            b.putString("autoGearSuccess", "0");
        }
        if (highCheckBox.isChecked()){
            b.putString("autoHighScored", String.valueOf(autoBallsMade));
            b.putString("autoLowScored", "0");
            b.putString("autoHighMissed", String.valueOf(10 - autoBallsMade));
            b.putString("autoLowMissed", "0");
        }else if (lowCheckBox.isChecked()){
            b.putString("autoHighScored", "0");
            b.putString("autoLowScored", String.valueOf(autoBallsMade));
            b.putString("autoHighMissed", "0");
            b.putString("autoLowMissed", String.valueOf(10 - autoBallsMade));
        }else{
            b.putString("autoHighScored", "0");
            b.putString("autoLowScored", "0");
            b.putString("autoHighMissed", "0");
            b.putString("autoLowMissed", "0");
        }
        return b;
    }
}