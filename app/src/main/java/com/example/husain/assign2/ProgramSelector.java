package com.example.husain.assign2;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class ProgramSelector extends AppCompatActivity implements AdvanceDegree.OnDegreeListener, AdvanceProgram.OnProgramListener {

    public String programName = "";
    public String degreeName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_selector);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        AdvanceDegree advanceDegreeFragment = new AdvanceDegree();
        fragmentTransaction.replace(R.id.activity_selector,advanceDegreeFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void setProgram(String name) {
        programName = name;
    }

    @Override
    public void setDegree(String name) {
        degreeName = name;
        AdvanceProgram advanceProgramFragment  = new AdvanceProgram();
        getSupportFragmentManager().beginTransaction().replace(R.id.activity_selector, advanceProgramFragment).commit();
    }

    public String getDegree(){
        return degreeName;
    }

    public void goBack()
    {
        if(degreeName!= null)
        {
            if (programName !=null)
            {
                Log.i("came------------------","inside");
                Intent programData = getIntent();
                programData.putExtra("Degree", degreeName);
                programData.putExtra("Program", programName);
                setResult(RESULT_OK,programData);
                finish();
            }
            else{
                Toast.makeText(this,"Select Program",Toast.LENGTH_LONG ).show();
            }
        }
        else {
            Toast.makeText(this,"Select Degree",Toast.LENGTH_LONG ).show();

        }
    }

    public void cancel(View v) {
        finish();
    }

}
