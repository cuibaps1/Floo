package com.quidproquo.floo.RegisterActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.quidproquo.floo.R;

public class InformationActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView buttonNext;

    private FirebaseAuth firebaseAuth;

    private DatabaseReference databaseReference;

    private EditText editTextFirstName;
    private EditText editTextLastName;

    private EditText editTextMonthOfBirth;
    private EditText editTextYearOfBirth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        firebaseAuth = FirebaseAuth.getInstance();

        databaseReference = FirebaseDatabase.getInstance().getReference();

        editTextFirstName = (EditText) findViewById(R.id.firstName);
        editTextLastName = (EditText) findViewById(R.id.lastName);
        editTextMonthOfBirth = (EditText) findViewById(R.id.monthOfBirth);
        editTextYearOfBirth = (EditText) findViewById(R.id.yearOfBirth);

        FirebaseUser user = firebaseAuth.getCurrentUser();

        buttonNext = (TextView) findViewById(R.id.Next);

        buttonNext.setOnClickListener(this);
    }

    private void saveUserInformation(){
        String firstName = editTextFirstName.getText().toString().trim();
        String lastName = editTextLastName.getText().toString().trim();
        int month = Integer.parseInt(editTextMonthOfBirth.getText().toString().trim());
        int year = Integer.parseInt(editTextYearOfBirth.getText().toString().trim());

        UserInformation userInformation = new UserInformation(firstName, lastName, month, year);

        FirebaseUser user = firebaseAuth.getCurrentUser();

        databaseReference.child(user.getUid()).setValue(userInformation);

        Toast.makeText(this, "Information Saved",Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onClick(View v) {
        if (v == buttonNext);
        saveUserInformation();
        Intent intent = new Intent(getApplicationContext(),LanguagueActivity.class);
        startActivity(intent);
    }
}
