package com.example.meetingsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import java.util.ArrayList;

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

        //Initialize Variables
        ArrayList<Meeting> meetingsList= new ArrayList<Meeting>();
        ListView meetingsListView= findViewById(R.id.meetingsListView);

        //Initialize "Add Meeting" Btn
        Button addMeetingBtn= findViewById(R.id.addMeetingBtn);
        addMeetingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                View popView = inflater.inflate(R.layout.add_meeting_window, null);

                PopupWindow addMeetingPopUp = new PopupWindow(popView, LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT, true);

                addMeetingPopUp.showAtLocation(view, Gravity.CENTER, 0, 0);

                popView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        addMeetingPopUp.dismiss();
                        return true;
                    }
                });
            }
        });
    }

    public void selectDate(View v) {
        DateSelectionFragment newFragment = new DateSelectionFragment();
        newFragment.show(getSupportFragmentManager(), "chooseDate");
    }
}