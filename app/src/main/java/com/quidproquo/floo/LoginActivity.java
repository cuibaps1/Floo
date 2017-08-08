package com.quidproquo.floo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextUsername;
    private EditText editTextPassword;
    private TextView textViewSignin;
    private TextView textViewSignup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUsername = (EditText) findViewById(R.id.username);
        editTextPassword = (EditText) findViewById(R.id.password);

        textViewSignin = (TextView) findViewById(R.id.signin);
        textViewSignup = (TextView) findViewById(R.id.signup);

        textViewSignin.setOnClickListener(this);
        textViewSignup.setOnClickListener(this);
    }

    private void registerUser(){

    }

    @Override
    public void onClick(View v) {
        if(v == textViewSignup){
            Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
            startActivity(intent);
        }

        if(v == textViewSignin){
            //open login activity
        }


    }
}
