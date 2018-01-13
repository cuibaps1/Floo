package com.quidproquo.floo.Utils;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.quidproquo.floo.Chat.ChatActivity;
import com.quidproquo.floo.Home.HomeScreenActivity;
import com.quidproquo.floo.Profile.ProfileActivity;
import com.quidproquo.floo.R;


/**
 * Created by Phuoc on 1/13/2018.
 */

public class BottomNavigationViewHelper {
    private static final String TAG = "BottomNavigationViewHelper";

    public static void setupBottomNavigationView(BottomNavigationViewEx bottomNavigationViewEx){
        bottomNavigationViewEx.enableAnimation(false);
        bottomNavigationViewEx.enableItemShiftingMode(false);
        bottomNavigationViewEx.enableShiftingMode(false);
        bottomNavigationViewEx.setTextVisibility(false);
    }

    public static void enableNavigation(final Context context, BottomNavigationViewEx view){
        view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.ic_home:
                        Intent intent1 = new Intent(context, HomeScreenActivity.class); //ACTIVITY_NUM = 0
                        context.startActivity(intent1);
                        break;
                    case R.id.ic_chat:
                        Intent intent2 = new Intent(context, ChatActivity.class); //ACTIVITY_NUM = 1
                        context.startActivity(intent2);
                        break;
                    case R.id.ic_profile:
                        Intent intent3 = new Intent(context, ProfileActivity.class); //ACTIVITY_NUM = 2
                        context.startActivity(intent3);
                        break;
                }

                return false;
            }
        });
    }
}
