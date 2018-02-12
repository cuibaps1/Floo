package com.quidproquo.floo.Profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.quidproquo.floo.R;

/**
 * Created by Phuoc on 2/9/2018.
 */

public class AboutMeActivity extends AppCompatActivity {
    private final static String TAG = "About me activity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);

        Log.d(TAG, "onCreate: started.");
    }


}
