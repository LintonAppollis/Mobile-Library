package com.devhub.mobi_library;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import org.json.JSONObject;

import java.util.Calendar;

public class signup extends AppCompatActivity{

    EditText email,password,confirmpass,name;
    Button signup,btn_home;
    private DatabaseHelper databaseHelper;
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        this.setTitle("Mobi Library");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        databaseHelper = new DatabaseHelper(this);
        user = new User();
        name = (EditText)findViewById(R.id.username);
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.pass);
        confirmpass = (EditText)findViewById(R.id.cpass);
        btn_home = (Button) findViewById(R.id.btn_home);
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        signup = (Button) findViewById(R.id.btn_signup);
           //  android_id = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
        signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String username = email.getText().toString();
                String pass = password.getText().toString();
                String cpass = confirmpass.getText().toString();
                if (pass.equals(cpass)) {
                    try {

                        postDataToSQLite();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else
                    Toast.makeText(getBaseContext(), "Password & Confirm Password not Matched!", Toast.LENGTH_SHORT).show();
            }

        });




    }


    private void postDataToSQLite() {


        if (!databaseHelper.checkUser(email.getText().toString().trim())) {

            user.setName(name.getText().toString().trim());
            user.setEmail(email.getText().toString().trim());
            user.setPassword(password.getText().toString().trim());

            databaseHelper.addUser(user);

            // Snack Bar to show success message that record saved successfully
            Toast.makeText(signup.this, "Account Successfully Created!", Toast.LENGTH_SHORT).show();
            name.setText("");
            email.setText("");
            password.setText("");
            confirmpass.setText("");
            name.requestFocus();



        } else {
            Toast.makeText(signup.this, "Email Already Registered!", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
          finish();

                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}