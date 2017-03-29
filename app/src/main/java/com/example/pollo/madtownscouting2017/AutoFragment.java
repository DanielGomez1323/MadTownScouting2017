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


    CheckBox leftStartPeg;
    CheckBox middleStartPeg;
    CheckBox rightStartPeg;
    CheckBox gearSuccessCheckBox;
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

        leftStartPeg = (CheckBox) rootView.findViewById(R.id.leftStartPeg);
        middleStartPeg = (CheckBox) rootView.findViewById(R.id.middleStartPeg);
        rightStartPeg = (CheckBox) rootView.findViewById(R.id.rightStartPeg);
        gearSuccessCheckBox = (CheckBox) rootView.findViewById(R.id.gearSuccessCheckBox);
        autoBallsSeekBar = (SeekBar) rootView.findViewById(R.id.autoBallsSeekBar);
        ballCountTextView = (TextView) rootView.findViewById(R.id.ballCountTextView);

        leftStartPeg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(leftStartPeg.isChecked()){
                    middleStartPeg.setChecked(false);
                    rightStartPeg.setChecked(false);
                }
            }
        });
        middleStartPeg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(middleStartPeg.isChecked()){
                    leftStartPeg.setChecked(false);
                    rightStartPeg.setChecked(false);
                }
            }
        });
        rightStartPeg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rightStartPeg.isChecked()){
                    leftStartPeg.setChecked(false);
                    middleStartPeg.setChecked(false);
                }
            }
        });
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
        if(leftStartPeg.isChecked()){
            b.putString("robotPosition", "0");
        }else if(middleStartPeg.isChecked()){
            b.putString("robotPosition", "1");
        }else if(rightStartPeg.isChecked()){
            b.putString("robotPosition", "2");
        }
        if(gearSuccessCheckBox.isChecked()){
            b.putString("autoGearSuccess", "1");
        }else{
            b.putString("autoGearSuccess", "0");
        }
        b.putString("autoHighScored", String.valueOf(autoBallsMade));
        return b;
    }
}