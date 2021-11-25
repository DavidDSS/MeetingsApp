package com.example.meetingsapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class AddMeetingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_meeting_activity);

        //Set Toolbar
        Toolbar appBar = (Toolbar) findViewById(R.id.meetingsToolBar);
        appBar.setBackgroundColor(getResources().getColor(R.color.design_default_color_primary));
        appBar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(appBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //Initialize Buttons
        Button addMeetingBtn= findViewById(R.id.addMeetingButton);

        addMeetingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });
    }
}
