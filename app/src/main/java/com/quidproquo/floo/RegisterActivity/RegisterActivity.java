package com.quidproquo.floo.RegisterActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.quidproquo.floo.R;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView buttonNext;
    private EditText emailText;
    private EditText passwordText;
    private EditText confirmPasswordText;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        buttonNext = (TextView) findViewById(R.id.Next);

        emailText = (EditText) findViewById(R.id.email);
        passwordText = (EditText) findViewById(R.id.password);
        confirmPasswordText = (EditText) findViewById(R.id.confirmPassword);

        buttonNext.setOnClickListener(this);


    }

    private void registerUser() {
        String email = emailText.getText().toString().trim();
        String password = passwordText.getText().toString().trim();
        String confirmPassword = confirmPasswordText.getText().toString().trim();




        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
            return;
        } else if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            return;
        } else if(TextUtils.isEmpty(confirmPassword)){
            Toast.makeText(this, "Please confirm your password", Toast.LENGTH_SHORT).show();
            return;
        }

        if(confirmPassword.equals(password)) {
            // if(confirmPassword != password){
            //   Toast.makeText(this, "Cannot confirm password", Toast.LENGTH_SHORT).show();
            // return;
            //}

            progressDialog.setMessage("Registering");
            progressDialog.show();

            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        //user successfully registered and logged in
                        //start profile activity
                        Toast.makeText(RegisterActivity.this, "Register Successed", Toast.LENGTH_LONG).show();
                        signInUser();
                    } else {
                        Toast.makeText(RegisterActivity.this, "Register Failed", Toast.LENGTH_LONG).show();
                    }
                }
            });
        } else {
            Toast.makeText(this, "Please confirm your password again", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    private void signInUser(){
        String email = emailText.getText().toString().trim();
        String password = passwordText.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
            return;
        } else if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            return;
        }

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent intent = new Intent(getApplicationContext(),InformationActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == buttonNext){
            registerUser();
        }
    }


}
