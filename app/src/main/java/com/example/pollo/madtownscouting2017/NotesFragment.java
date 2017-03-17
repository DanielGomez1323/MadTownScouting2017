package com.example.pollo.madtownscouting2017;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by 19IsaacD740 on 1/18/2017.
 */
public class NotesFragment extends Fragment{

    EditText tbh;

    public static NotesFragment newInstance(){
        NotesFragment fragment = new NotesFragment();
        return fragment;
    }
    public NotesFragment(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_notes, container, false);

        tbh = (EditText) rootView.findViewById(R.id.tbhEditText);
        return rootView;
    }

    public Bundle getData(){
        Bundle b = new Bundle();
        b.putString("tbh", tbh.getText().toString());
        return b;
    }

}
