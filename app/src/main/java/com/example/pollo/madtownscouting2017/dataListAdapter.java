package com.example.pollo.madtownscouting2017;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by 19angelg377 on 1/21/2017.
 */
public class dataListAdapter extends CursorAdapter{
    private LayoutInflater cursorInflater;
    private Context mContext;

    public dataListAdapter(Context context, Cursor c, int flags){
        super(context, c, flags);
        mContext = context;
    }
    public void bindView(View view, Context context, Cursor cursor){
        TextView teamNumberTextView = (TextView) view.findViewById(R.id.teamNumberTextView);
        TextView matchNumberTextView = (TextView) view.findViewById(R.id.matchNumberTextView);
        TextView teamColorTextView = (TextView) view.findViewById(R.id.teamColorTextView);
        TextView robotPositionTextView = (TextView) view.findViewById(R.id.robotPositionTV);
        TextView autoGearSuccessTextView = (TextView) view.findViewById(R.id.autoGearSuccessTextView);
        TextView autoHighScoredTextView = (TextView) view.findViewById(R.id.autoHighScoredTextView);
        TextView gearsHungTextView = (TextView) view.findViewById(R.id.gearsHungTextView);
        TextView climbTimeTextView = (TextView) view.findViewById(R.id.climbTimeTextView);
        TextView climbSuccessTextView = (TextView) view.findViewById(R.id.climbSuccessTextView);
        TextView tBHTextView = (TextView) view.findViewById(R.id.tBHTextView);
        teamNumberTextView.setText(teamNumberTextView.getText() + cursor.getString(cursor.getColumnIndex("teamNumber")));
        matchNumberTextView.setText(matchNumberTextView.getText() + cursor.getString(cursor.getColumnIndex("matchNumber")));
        teamColorTextView.setText(teamColorTextView.getText() + cursor.getString(cursor.getColumnIndex("teamColor")));
        robotPositionTextView.setText(robotPositionTextView.getText() + cursor.getString(cursor.getColumnIndex("robotPosition")));
        autoGearSuccessTextView.setText(autoGearSuccessTextView.getText() + cursor.getString(cursor.getColumnIndex("autoGearSuccess")));
        autoHighScoredTextView.setText(autoHighScoredTextView.getText() + cursor.getString(cursor.getColumnIndex("autoHighScored")));
        gearsHungTextView.setText(gearsHungTextView.getText() + cursor.getString(cursor.getColumnIndex("gearsHung")));
        climbTimeTextView.setText(climbTimeTextView.getText() + cursor.getString(cursor.getColumnIndex("climbTime")));
        climbSuccessTextView.setText(climbSuccessTextView.getText() + cursor.getString(cursor.getColumnIndex("climbSuccess")));
        tBHTextView.setText(tBHTextView.getText() + cursor.getString(cursor.getColumnIndex("tbh")));
        teamNumberTextView.setText("Team " + cursor.getString(cursor.getColumnIndex("teamNumber")));
        matchNumberTextView.setText("Match " + cursor.getString(cursor.getColumnIndex("matchNumber")));
        String color = cursor.getString(cursor.getColumnIndex("teamColor"));
        switch(cursor.getString(cursor.getColumnIndex("teamColor"))){
            case "1":
                color = "Blue";
                break;
            case "0":
                color = "Red";
                break;
            default:
                break;
        }
        String robotPosition = cursor.getString(cursor.getColumnIndex("robotPosition"));
        switch(cursor.getString(cursor.getColumnIndex("robotPosition"))){
            case "0":
                robotPosition = "Left Peg";
                break;
            case "1":
                robotPosition = "Middle Peg";
                break;
            case "2":
                robotPosition = "Right Peg";
                break;
            default:
                break;
        }
        teamColorTextView.setText(color);
        robotPositionTextView.setText(robotPosition);
        autoGearSuccessTextView.setText("Auto Gear Success " + cursor.getString(cursor.getColumnIndex("autoGearSuccess")));
        autoHighScoredTextView.setText("Auto High Scored " + cursor.getString(cursor.getColumnIndex("autoHighScored")));
        gearsHungTextView.setText("Gears Hung " + cursor.getString(cursor.getColumnIndex("gearsHung")));
        climbTimeTextView.setText("Climb Time " + cursor.getString(cursor.getColumnIndex("climbTime")));
        climbSuccessTextView.setText("Climb Success " + cursor.getString(cursor.getColumnIndex("climbSuccess")));
        tBHTextView.setText("TBH " + cursor.getString(cursor.getColumnIndex("tbh")));
    }
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // R.layout.list_row is your xml layout for each row
        cursorInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = cursorInflater.inflate(R.layout.data_list, parent, false);
        bindView(v, context, cursor);
        return v;
    }
}
