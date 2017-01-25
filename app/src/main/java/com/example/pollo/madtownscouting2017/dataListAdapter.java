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
        TextView baselineTextView = (TextView) view.findViewById(R.id.baselineTextView);
        TextView autoGearAttemptTextView = (TextView) view.findViewById(R.id.autoGearAttemptTextView);
        TextView autoGearSuccessTextView = (TextView) view.findViewById(R.id.autoGearSuccessTextView);
        TextView autoHighScoredTextView = (TextView) view.findViewById(R.id.autoHighScoredTextView);
        TextView autoHighMissedTextView = (TextView) view.findViewById(R.id.autoHighMissedTextView);
        TextView autoLowScoredTextView = (TextView) view.findViewById(R.id.autoLowScoredTextView);
        TextView autoLowMissedTextView = (TextView) view.findViewById(R.id.autoLowMissedTextView);
        TextView gearsPickedUpTextView = (TextView) view.findViewById(R.id.gearsPickedUpTextView);
        TextView gearsHungTextView = (TextView) view.findViewById(R.id.gearsHungTextView);
        TextView gearsDroppedTextView = (TextView) view.findViewById(R.id.gearsDroppedTextView);
        TextView highShootSpeedTextView = (TextView) view.findViewById(R.id.highShootSpeedTextView);
        TextView highShotsMissedTextView = (TextView) view.findViewById(R.id.highShotsMissedTextView);
        TextView lowShootSpeedTextView = (TextView) view.findViewById(R.id.lowShootSpeedTextView);
        TextView lowShotsMissedTextView = (TextView) view.findViewById(R.id.lowShotsMissedTextView);
        TextView hopperIntakeTextView = (TextView) view.findViewById(R.id.hopperIntakeTextView);
        TextView climbTimeTextView = (TextView) view.findViewById(R.id.climbTimeTextView);
        TextView climbSuccessTextView = (TextView) view.findViewById(R.id.climbSuccessTextView);
        TextView rankTextView = (TextView) view.findViewById(R.id.rankTextView);
        TextView tBHTextView = (TextView) view.findViewById(R.id.tBHTextView);
        teamNumberTextView.setText(teamNumberTextView.getText() + cursor.getString(cursor.getColumnIndex("teamNumber")));
        matchNumberTextView.setText(matchNumberTextView.getText() + cursor.getString(cursor.getColumnIndex("matchNumber")));
        teamColorTextView.setText(teamColorTextView.getText() + cursor.getString(cursor.getColumnIndex("teamColor")));
        baselineTextView.setText(baselineTextView.getText() + cursor.getString(cursor.getColumnIndex("baseLine")));
        autoGearAttemptTextView.setText(autoGearAttemptTextView.getText() + cursor.getString(cursor.getColumnIndex("autoGearAttempt")));
        autoGearSuccessTextView.setText(autoGearSuccessTextView.getText() + cursor.getString(cursor.getColumnIndex("autoGearSuccess")));
        autoHighScoredTextView.setText(autoHighScoredTextView.getText() + cursor.getString(cursor.getColumnIndex("autoHighScored")));
        autoHighMissedTextView.setText(autoHighMissedTextView.getText() + cursor.getString(cursor.getColumnIndex("autoHighMissed")));
        autoLowScoredTextView.setText(autoLowScoredTextView.getText() + cursor.getString(cursor.getColumnIndex("autoLowScored")));
        autoLowMissedTextView.setText(autoLowMissedTextView.getText() + cursor.getString(cursor.getColumnIndex("autoLowMissed")));
        gearsPickedUpTextView.setText(gearsPickedUpTextView.getText() + cursor.getString(cursor.getColumnIndex("gearsPickedUp")));
        gearsHungTextView.setText(gearsHungTextView.getText() + cursor.getString(cursor.getColumnIndex("gearsHung")));
        gearsDroppedTextView.setText(gearsDroppedTextView.getText() + cursor.getString(cursor.getColumnIndex("gearsDropped")));
        highShootSpeedTextView.setText(highShootSpeedTextView.getText() + cursor.getString(cursor.getColumnIndex("highShootSpeed")));
        highShotsMissedTextView.setText(highShotsMissedTextView.getText() + cursor.getString(cursor.getColumnIndex("highShotsMissed")));
        lowShootSpeedTextView.setText(lowShootSpeedTextView.getText() + cursor.getString(cursor.getColumnIndex("lowShootSpeed")));
        lowShotsMissedTextView.setText(lowShotsMissedTextView.getText() + cursor.getString(cursor.getColumnIndex("lowShotsMissed")));
        hopperIntakeTextView.setText(hopperIntakeTextView.getText() + cursor.getString(cursor.getColumnIndex("hopperIntake")));
        climbTimeTextView.setText(climbTimeTextView.getText() + cursor.getString(cursor.getColumnIndex("climbTime")));
        climbSuccessTextView.setText(climbSuccessTextView.getText() + cursor.getString(cursor.getColumnIndex("climbSuccess")));
        rankTextView.setText(rankTextView.getText() + cursor.getString(cursor.getColumnIndex("rank")));
        tBHTextView.setText(tBHTextView.getText() + cursor.getString(cursor.getColumnIndex("tbh")));
    }
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // R.layout.list_row is your xml layout for each row
        cursorInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = cursorInflater.inflate(R.layout.data_list, parent, false);
        bindView(v, context, cursor);
        return v;
    }
}
