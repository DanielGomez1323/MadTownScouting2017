package com.example.pollo.madtownscouting2017;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

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
    Button startClimb;
    TextView climbChronometer;
    long startTime;
    long climbTime;

    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            long millis = System.currentTimeMillis() - startTime;
            int seconds = (int) (millis/1000);
            climbTime = millis/1000;
            int minutes = seconds / 60;
            seconds = seconds % 60;
            climbChronometer.setText(String.format("%d:%02d", minutes, seconds));
        }
    };

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
        startClimb = (Button) rootView.findViewById(R.id.startclimbButton);
        climbChronometer = (TextView) rootView.findViewById(R.id.climbChronometer);
        startClimb.setText("Start");
        startClimb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                if (b.getText().equals("stop")) {
                    timerHandler.removeCallbacks(timerRunnable);
                    b.setText("Start");
                } else {
                    startTime = System.currentTimeMillis();
                    timerHandler.postDelayed(timerRunnable, 0);
                    b.setText("Stop");
                }
            }
        });
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
                drops ++;
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
        return rootView;
    }
    @Override
    public void onPause() {
        super.onPause();
        timerHandler.removeCallbacks(timerRunnable);
        startClimb.setText("Start");
    }
    public Bundle getData(){
        Bundle b = new Bundle();
        b.putString("gearsPickedUp", String.valueOf(pickUps));
        b.putString("gearsDropped", String.valueOf(drops));
        b.putString("gearsPlaced", String.valueOf(placed));

        return b;
    }
}
