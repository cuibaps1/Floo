package com.quidproquo.floo.RegisterActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.quidproquo.floo.R;

public class InformationActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView buttonNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        buttonNext = (TextView) findViewById(R.id.Next);

        buttonNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == buttonNext);
        Intent intent = new Intent(getApplicationContext(),LanguagueActivity.class);
        startActivity(intent);
    }
}
