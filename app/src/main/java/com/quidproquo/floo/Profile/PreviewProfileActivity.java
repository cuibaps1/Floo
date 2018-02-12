package com.quidproquo.floo.Profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.quidproquo.floo.R;

/**
 * Created by Phuoc on 2/9/2018.
 */

public class PreviewProfileActivity extends AppCompatActivity {
    private static final String TAG = "PreviewProfileActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_profile);
    }
}
