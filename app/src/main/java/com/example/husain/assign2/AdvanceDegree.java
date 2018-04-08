package com.example.husain.assign2;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdvanceDegree extends ListFragment implements AdapterView.OnItemClickListener {

    OnDegreeListener degreeListener;

    public AdvanceDegree() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_advance_degree, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String [] degreeList = getResources().getStringArray(R.array.degreelist);
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, degreeList);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        String programName = parent.getItemAtPosition(position).toString();
        degreeListener.setDegree(programName);
        Log.i("rew","position of CLick" + programName) ;
        Log.i("rew","id of Click" + id);
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        Activity activity = (Activity) context;
        try{
            degreeListener = (OnDegreeListener) activity;
        }catch(Exception e) {}
    }

    public interface OnDegreeListener
    {
        public void setDegree (String name);
    }

}