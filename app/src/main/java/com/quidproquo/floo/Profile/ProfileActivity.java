package com.quidproquo.floo.Profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.quidproquo.floo.Login.LoginActivity;
import com.quidproquo.floo.R;
import com.quidproquo.floo.Utils.BottomNavigationViewHelper;

/**
 * Created by Phuoc on 1/13/2018.
 */

public class ProfileActivity extends AppCompatActivity{
    private static final String TAG = "ProfileActivity";
    private static final int ACTIVITY_NUM = 2;

    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private DatabaseReference databaseReference;
    private String userID;

    ListView profileSettingListView;
    ListView privacyListView;
    ListView accountListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Log.d(TAG, "onCreate: started.");

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        setupProfileSettingListView();
        setupPrivacyListView();
        setupAccountListView();
        setupBottomNavigationView();
        setupToolbar();

    }

    private void setupToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.profileToolBar);
        setSupportActionBar(toolbar);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Log.d(TAG, "onMenuItemClick: clicked menu item: " + item);

                switch (item.getItemId()){
                    case R.id.addContactMenu:
                        Log.d(TAG, "onMenuItemClick: Navigating to add Contact");
                        break;
                }

                return false;
            }
        });
    }

    private void setupProfileSettingListView(){
        profileSettingListView = (ListView) findViewById(R.id.profileSettingListView);
        ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(ProfileActivity.this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray((R.array.profile_setting)));
        profileSettingListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Intent intent = new Intent(ProfileActivity.this, AboutMeActivity.class);
                        intent.putExtra("AboutMeSetting", profileSettingListView.getItemAtPosition(0).toString());
                        startActivity(intent);
                        break;
                    case 1:
                        Log.d(TAG, "language");
                        Intent intent1 = new Intent(ProfileActivity.this, LanguageActivity.class);
                        intent1.putExtra("AboutMeSetting", profileSettingListView.getItemAtPosition(1).toString());
                        startActivity(intent1);
                        break;
                    case 2:
                        Log.d(TAG, "preview");
                        Intent intent2 = new Intent(ProfileActivity.this, PreviewProfileActivity.class);
                        intent2.putExtra("AboutMeSetting", profileSettingListView.getItemAtPosition(2).toString());
                        startActivity(intent2);
                        break;
                }
            }
        });
        profileSettingListView.setAdapter(mAdapter);
    }

    private void setupPrivacyListView(){
        privacyListView = (ListView) findViewById(R.id.privacyListView);
        ArrayAdapter<String> mAdapter1 = new ArrayAdapter<String>(ProfileActivity.this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray((R.array.profile_privacy)));
        privacyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Intent intent = new Intent(ProfileActivity.this, AboutMeActivity.class);
                        intent.putExtra("AboutMeSetting", privacyListView.getItemAtPosition(0).toString());
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intent1 = new Intent(ProfileActivity.this, LanguageActivity.class);
                        intent1.putExtra("AboutMeSetting", privacyListView.getItemAtPosition(1).toString());
                        startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2 = new Intent(ProfileActivity.this, PreviewProfileActivity.class);
                        intent2.putExtra("AboutMeSetting", privacyListView.getItemAtPosition(2).toString());
                        startActivity(intent2);
                        break;
                }
            }
        });
        privacyListView.setAdapter(mAdapter1);
    }

    private void setupAccountListView(){
        accountListView = (ListView) findViewById(R.id.accountListView);
        ArrayAdapter<String> mAdapter2 = new ArrayAdapter<String>(ProfileActivity.this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray((R.array.profile_account)));
        accountListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Intent intent = new Intent(ProfileActivity.this, AboutMeActivity.class);
                        intent.putExtra("AboutMeSetting", accountListView.getItemAtPosition(0).toString());
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intent2 = new Intent(ProfileActivity.this, LoginActivity.class);
                        firebaseAuth.signOut();
                        finish();
                        startActivity(intent2);
                        break;
                }
            }
        });
        accountListView.setAdapter(mAdapter2);
    }

    /**
     * BottomNavigationView setup
     */
    private void setupBottomNavigationView(){
        Log.d(TAG, "setupBottomNavigationView: setting up BottomNavigationView");
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);

        BottomNavigationViewHelper.enableNavigation(ProfileActivity.this, bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);

    }

}
