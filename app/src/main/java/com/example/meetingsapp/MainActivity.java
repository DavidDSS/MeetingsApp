package com.example.meetingsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set Toolbar
        Toolbar appBar = (Toolbar) findViewById(R.id.meetingsToolBar);
        appBar.setBackgroundColor(getResources().getColor(R.color.design_default_color_primary));
        appBar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(appBar);

        //Initialize "Add Meeting" Btn
        Button addMeetingBtn= findViewById(R.id.addMeetingBtn);
        addMeetingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

    }
}