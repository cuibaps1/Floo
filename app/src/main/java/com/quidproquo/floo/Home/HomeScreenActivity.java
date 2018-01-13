package com.quidproquo.floo.Home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.quidproquo.floo.Login.LoginActivity;
import com.quidproquo.floo.R;
import com.quidproquo.floo.Utils.BottomNavigationViewHelper;

public class HomeScreenActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAGD = "ViewDatabase";
    private static final String TAG = "HomeActivity";

    private static final int ACTIVITY_NUM = 0;

    private TextView textViewUserEmail;
    private TextView textViewName;
    private Button buttonLogout;

    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private DatabaseReference databaseReference;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        Log.d(TAG, "onCreate: starting");

        setupBottomNavigationView();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        if(firebaseAuth.getCurrentUser() == null){
            finish();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

        FirebaseUser user = firebaseAuth.getCurrentUser();
        userID = user.getUid();

        authStateListener= new FirebaseAuth.AuthStateListener(){
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null){
                    Log.d(TAGD, "onAuthStateChanged:signed_in:" + user.getUid());
                    toastMessage("Successfully signed in with: " +user.getEmail());
                } else {
                    Log.d(TAGD, "onAuthStateChanged:signed_out");
                    toastMessage("Successfully signed out");
                }
            }
        };

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
         //       showData(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

      //  textViewUserEmail = (TextView) findViewById(R.id.userEmail);
       // textViewUserEmail.setText("Welcome " + user.getEmail());

       // textViewName = (TextView) findViewById(R.id.name);


      //  buttonLogout = (Button) findViewById(R.id.buttonLogOut);

      //  buttonLogout.setOnClickListener(this);
    }

  /*  private void showData(DataSnapshot dataSnapshot) {
        for(DataSnapshot ds : dataSnapshot.getChildren()){
            UserInformation userInfo = new UserInformation();
            userInfo.setFirstName(ds.child(userID).getValue(UserInformation.class).getFirstName());
            userInfo.setLastName(ds.child(userID).getValue(UserInformation.class).getLastName());
            userInfo.setMonthOfBirth(ds.child(userID).getValue(UserInformation.class).getMonthOfBirth());
            userInfo.setYearOfBirth(ds.child(userID).getValue(UserInformation.class).getYearOfBirth());
        }
    }*/

    /**
     * BottomNavigationView setup
     */
  private void setupBottomNavigationView(){
      Log.d(TAG, "setupBottomNavigationView: setting up BottomNavigationView");
      BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);
      BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);

      BottomNavigationViewHelper.enableNavigation(HomeScreenActivity.this, bottomNavigationViewEx);
      Menu menu = bottomNavigationViewEx.getMenu();
      MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
      menuItem.setChecked(true);

  }

    public void onStart(){
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    public void onStop(){
        super.onStop();
        if (authStateListener != null){
            firebaseAuth.removeAuthStateListener(authStateListener);
        }
    }

    private void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        if(v == buttonLogout){
            firebaseAuth.signOut();
            finish();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }
}
