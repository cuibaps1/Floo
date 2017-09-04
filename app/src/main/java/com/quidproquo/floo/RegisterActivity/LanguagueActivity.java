package com.quidproquo.floo.RegisterActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.quidproquo.floo.HomeScreen;
import com.quidproquo.floo.R;

public class LanguagueActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView finishButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_languague);

        finishButton = (TextView) findViewById(R.id.Finish);

        finishButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == finishButton){
            Intent intent = new Intent(this, HomeScreen.class);
            startActivity(intent);
        }
    }
}
