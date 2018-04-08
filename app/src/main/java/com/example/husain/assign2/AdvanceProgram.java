package com.example.husain.assign2;

import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdvanceProgram extends ListFragment implements AdapterView.OnItemClickListener {

    String degreeName="";
    public AdvanceProgram() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        degreeName = ((ProgramSelector)getActivity()).getDegree();
        if (degreeName != null) {
            Log.i("test_state", degreeName);
            ArrayList<String> programList = new ArrayList<String>();
           try {
                InputStream programFile = getActivity().getAssets().open(degreeName);
                BufferedReader in = new BufferedReader(new InputStreamReader(programFile));
                String program;
                while ((program = in.readLine()) != null) {
                    programList.add(program);
                }
                ArrayAdapter<String> adapter =
                        new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, programList);
                setListAdapter(adapter);

            } catch (IOException e) {
               e.printStackTrace();
           }
        }
        getListView().setOnItemClickListener(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_advance_program, container, false);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        String programName = parent.getItemAtPosition(position).toString();
        ((ProgramSelector)getActivity()).setProgram(programName);
        ((ProgramSelector)getActivity()).goBack();
    }

    public interface OnProgramListener
    {
        public void setProgram (String name);
    }

}
