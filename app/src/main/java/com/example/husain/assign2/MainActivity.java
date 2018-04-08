package com.example.husain.assign2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    public static final String PREFERENCES = "PreferencesInfo";

    private String name;
    private String lastName;
    private String age;
    private String email;
    private String phone;
    private String major;

    EditText editTextName;
    EditText editTextLname;
    EditText editTextAge;
    EditText editTextEmail;
    EditText editTextPhone;
    EditText editTextMajor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences(PREFERENCES,0);

        editTextName = (EditText) this.findViewById(R.id.editText);
        editTextLname = (EditText) this.findViewById(R.id.editText2);
        editTextAge = (EditText) this.findViewById(R.id.editText3);
        editTextEmail = (EditText) this.findViewById(R.id.editText4);
        editTextPhone = (EditText) this.findViewById(R.id.editText5);
        editTextMajor = (EditText) this.findViewById(R.id.editTextMajor);

        name = sharedPreferences.getString("FirstName",null);
        lastName = sharedPreferences.getString("LastName",null);
        age = sharedPreferences.getString("Age",null);
        email = sharedPreferences.getString( "Email",null);
        phone = sharedPreferences.getString("Phone",null);
        major = sharedPreferences.getString("Major",null);

        editTextName.setText(name);
        editTextLname.setText(lastName);
        editTextAge.setText(age);
        editTextEmail.setText(email);
        editTextPhone.setText(phone);
        editTextMajor.setText(major);

        Button done = (Button) findViewById(R.id.done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                name = editTextName.getText().toString();
                lastName = editTextLname.getText().toString();
                age = editTextAge.getText().toString();
                email = editTextEmail.getText().toString();
                phone = editTextPhone.getText().toString();
                major = editTextMajor.getText().toString();
                editor.putString("FirstName", name );
                editor.putString("LastName", lastName);
                editor.putString("Age", age);
                editor.putString("Email", email);
                editor.putString("Phone", phone);
                editor.putString("Major", major);
                editor.commit();
            }
        });

        Button search = (Button) findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("was here","duede -----------------------");
                go();
            }
        });


    }

    @Override
    public void onActivityResult(int req, int resCode, Intent data)
    {
        if (resCode == RESULT_OK && null!=data && null!=data.getStringExtra("Degree") && null!=data.getStringExtra("Program"))
        {
             editTextMajor = (EditText) this.findViewById(R.id.editTextMajor);
             editTextMajor.setText(data.getStringExtra("Degree") + " in " + data.getStringExtra("Program"));
        }
    }

    public void go() {
        Intent intent = new Intent(this, ProgramSelector.class);
        startActivityForResult(intent, 26);
    }

}
