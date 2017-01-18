package com.example.pollo.madtownscouting2017;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by 19IsaacD740 on 1/17/2017.
 */
public class TeamListAdapter extends CursorAdapter{
    private LayoutInflater cursorInflater;
    private Context mContext;
    String precursor;
    public TeamListAdapter(Context context, Cursor c, int flags, int pics){
        super(context, c, flags);
        mContext = context;
        if(pics == 0){
            precursor = "Matches Played: ";
        }else {
            precursor = "Pictures: ";
        }
    }



    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView teamNumberTextView = (TextView) view.findViewById(R.id.teamNumberText);
        teamNumberTextView.setText("Team " + cursor.getString(cursor.getColumnIndex("teamNumber")));
        TextView matchesPlayedTextView = (TextView) view.findViewById(R.id.matchesPlayedText);
        matchesPlayedTextView.setText(precursor + cursor.getString(cursor.getColumnIndex("matchCount")));
    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // R.layout.list_row is your xml layout for each row
        cursorInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = cursorInflater.inflate(R.layout.activity_teams_view, parent, false);
        bindView(v, context, cursor);
        return v;
    }
}
