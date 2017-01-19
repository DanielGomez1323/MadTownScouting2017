package com.example.pollo.madtownscouting2017;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;

/**
 * Created by 19IsaacD740 on 1/18/2017.
 */
public class NotesFragment extends Fragment{

    EditText tbh;
    CheckBox firstPickCheckBox;
    CheckBox secondPickCheckBox;
    CheckBox dnpCheckBox;

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
        firstPickCheckBox = (CheckBox) rootView.findViewById(R.id.firstPickCheckBox);
        secondPickCheckBox = (CheckBox) rootView.findViewById(R.id.secondPickCheckBox);
        dnpCheckBox = (CheckBox) rootView.findViewById(R.id.dnpCheckBox);

        return rootView;
    }

    public Bundle getData(){
        Bundle b = new Bundle();
        b.putString("tbh", tbh.getText().toString());
        if(firstPickCheckBox.isChecked() && !(secondPickCheckBox.isChecked() || dnpCheckBox.isChecked())){
            b.putString("rank", "first");
        }else if (secondPickCheckBox.isChecked() && !(firstPickCheckBox.isChecked() || dnpCheckBox.isChecked())){
            b.putString("rank", "second");
        }else if(dnpCheckBox.isChecked() && !(firstPickCheckBox.isChecked() || secondPickCheckBox.isChecked())){
            b.putString("rank", "dnp");
        }else{
            b.putString("rank", "error");
        }
        return b;
    }

}
