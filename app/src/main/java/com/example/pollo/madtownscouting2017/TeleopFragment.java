package com.example.pollo.madtownscouting2017;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Chronometer;
import android.widget.SeekBar;
import android.widget.TextView;


/**
 * Created by 19IsaacD740 on 1/13/2017.
 */
public class TeleopFragment extends android.support.v4.app.Fragment{

    Button decreasePickUpsButton;
    Button increasePickUpsButton;
    TextView pickUpText;
    Button decreaseDropsButton;
    Button increaseDropsButton;
    TextView dropText;
    Button decreasePlacedButton;
    Button increasePlacedButton;
    TextView placedText;
    int pickUps = 0;
    int drops = 0;
    int placed = 0;
    //Button startClimb;
    //Button stopClimb;
    //private Chronometer climbChronometer;
    //TextView climbChronometer;
    //long startTime;
    CheckBox successfulClimbCHeckBox;
    SeekBar climbTimeSeekBar;
    TextView climbCountTextView;
    int climbTime = 0;
    //int climbSuccess = 0;

    /*Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            long millis = System.currentTimeMillis() - startTime;
            int seconds = (int) (millis/1000);
            climbTime = millis/1000;
            int minutes = seconds / 60;
            seconds = seconds % 60;
            climbChronometer.setText(String.valueOf(seconds));
        }
    };*/

    public static TeleopFragment newInstance() {
        TeleopFragment fragment = new TeleopFragment();
        return fragment;
    }
    public TeleopFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.teleop_fragment, container, false);
        decreasePickUpsButton = (Button) rootView.findViewById(R.id.decreaseGearsPickedUpButton);
        increasePickUpsButton = (Button) rootView.findViewById(R.id.increaseGearsPickedUpButton);
        decreaseDropsButton = (Button) rootView.findViewById(R.id.decreaseGearsDroppedButton);
        increaseDropsButton = (Button) rootView.findViewById(R.id.increaseGearsDroppedButton);
        decreasePlacedButton = (Button) rootView.findViewById(R.id.decreaseGearsPlacedButton);
        increasePlacedButton = (Button) rootView.findViewById(R.id.increaseGearsPlacedButton);
        pickUpText = (TextView) rootView.findViewById(R.id.amountGearsPickedUpTextView);
        dropText = (TextView) rootView.findViewById(R.id.amountGearsDroppedTextView);
        placedText = (TextView) rootView.findViewById(R.id.amountGearsPlacedTextView);
        //startClimb = (Button) rootView.findViewById(R.id.startclimbButton);
        //stopClimb = (Button) rootView.findViewById(R.id.stopclimbButton);
        /*climbChronometer = (Chronometer) rootView.findViewById(R.id.climbChronometer);
        climbChronometer = (Chronometer) rootView.findViewById(R.id.start).setOnClickListener(this);
        climbChronometer = (Chronometer) rootView.findViewById(R.id.stop).setOnClickListener(this);*/
        successfulClimbCHeckBox = (CheckBox) rootView.findViewById(R.id.successfulclimbcheckBox);
        climbTimeSeekBar = (SeekBar) rootView.findViewById(R.id.climbTimeSeekBar);
        climbCountTextView = (TextView) rootView.findViewById(R.id.climbCountTextView);
        decreasePickUpsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickUps --;
                pickUpText.setText(String.valueOf(pickUps));
            }
        });
        increasePickUpsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickUps ++;
                pickUpText.setText(String.valueOf(pickUps));
            }
        });
        decreaseDropsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drops --;
                dropText.setText(String.valueOf(drops));
            }
        });
        increaseDropsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drops++;
                dropText.setText(String.valueOf(drops));
            }
        });
        decreasePlacedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placed --;
                placedText.setText(String.valueOf(placed));
            }
        });
        increasePlacedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placed ++;
                placedText.setText(String.valueOf(placed));
            }
        });
        climbTimeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                climbCountTextView.setText("ClimbTime: " + progress);
                climbTime = progress;
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
    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.test, menu);
        return true;
    }
    public void onPause() {
        super.onPause();
        timerHandler.removeCallbacks(timerRunnable);
        startClimb.setText("Start");
    }*/
    public Bundle getData(){
        Bundle b = new Bundle();
        b.putString("gearsPickedUp", String.valueOf(pickUps));
        b.putString("gearsDropped", String.valueOf(drops));
        b.putString("gearsHung", String.valueOf(placed));
        b.putString("climbTime", String.valueOf(climbTime));
        if (successfulClimbCHeckBox.isChecked()){
            b.putString("climbSuccess", "1");
        }else{
            b.putString("climbSuccess", "0");
        }

        return b;
    }
    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start:
                climbChronometer.setBase(SystemClock.elapsedRealtime());
                climbChronometer.start();
                break;
            case R.id.stop:
                climbChronometer.stop();
                break;
        }
    }*/
}
