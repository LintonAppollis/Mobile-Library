package com.devhub.mobi_library;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


public class login extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    EditText email, password;
    CheckBox checkBox;
    Button signin;
    TextView txt_link_register,forget;
    private User user;


    private void verifyFromSQLite() {

        if (databaseHelper.checkUser(email.getText().toString().trim(), password.getText().toString().trim())) {

            finish();
            Intent accountsIntent = new Intent(login.this, MainActivity.class);
            startActivity(accountsIntent);


        } else {
            Toast.makeText(login.this, "Wrong User Name or Password!", Toast.LENGTH_SHORT).show();
        }
    }



    private void verifyFromSQLite_For_Reset() {


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.setTitle("Mobi Library");
        databaseHelper = new DatabaseHelper(this);
        user = new User();

        databaseHelper = new DatabaseHelper(this);
        txt_link_register=findViewById(R.id.textViewLinkRegister);

        email = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.pass);
        signin = (Button) findViewById(R.id.btn_login);
        txt_link_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this,signup.class));
            }
        });
          signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(email.getText()) || TextUtils.isEmpty(password.getText())) {
                    Toast.makeText(getBaseContext(), "Username or Password cannot be left Blank!", Toast.LENGTH_SHORT).show();
                } else {
                    try {

                 verifyFromSQLite();

                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(login.this, "Cannot Login Technical Error!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }






}
