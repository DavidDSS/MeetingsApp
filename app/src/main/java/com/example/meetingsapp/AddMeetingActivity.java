package com.example.meetingsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

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

        //Initialize Elements
        EditText meetingName= findViewById(R.id.meetingName);
        EditText meetingLocation= findViewById(R.id.meetingLocation);
        DatePicker meetingDate= findViewById(R.id.datePicker);
        TimePicker meetingTime= findViewById(R.id.timePicker);

        Button addMeetingBtn= findViewById(R.id.addMeetingButton);

        addMeetingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddMeetingActivity.this, MainActivity.class);

                String meetingNameString= meetingName.getText().toString();
                String meetingLocationString= meetingLocation.getText().toString();
                int year= meetingDate.getYear();
                int month= meetingDate.getMonth();
                int day= meetingDate.getDayOfMonth();
                Date meetingDateObject = new Date(year, month, day);

                int hour= meetingTime.getHour();
                int min= meetingTime.getMinute();

                Time meetingTimeObject = new Time(hour, min,0);

                Meeting meetingAdded= new Meeting(meetingNameString, meetingLocationString,meetingDateObject, meetingTimeObject);
                intent.putExtra("meetingObject", meetingAdded);
                setResult(RESULT_OK, intent);
                finish();

            }
        });
    }
}
