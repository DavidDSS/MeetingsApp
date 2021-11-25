package com.example.meetingsapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    ActivityResultLauncher<Intent> newMeetingResult;
    ListView todayListView;
    ListView tomorrowListView;
    ListView otherListView;
    ArrayList<Meeting> todayMeetingsList;
    ArrayList<Meeting> tomorrowMeetingsList;
    ArrayList<Meeting> otherMeetingsList;
    MeetingViewAdapter meetingListAdapter;

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
        todayMeetingsList= new ArrayList<Meeting>();
        tomorrowMeetingsList= new ArrayList<Meeting>();
        otherMeetingsList= new ArrayList<Meeting>();
        todayListView= findViewById(R.id.todayListView);
        tomorrowListView= findViewById(R.id.tomorrowListView);
        otherListView= findViewById(R.id.otherListView);

        //Initialize Adapter
        meetingListAdapter = new MeetingViewAdapter(this, todayMeetingsList);

        //Set Adapter
        todayListView.setAdapter(meetingListAdapter);
        tomorrowListView.setAdapter(meetingListAdapter);
        otherListView.setAdapter(meetingListAdapter);

        TextView emptyToday= findViewById(R.id.emptyToday);
        todayListView.setEmptyView(emptyToday);

        TextView emptyTomorrow= findViewById(R.id.emptyTomorrow);
        tomorrowListView.setEmptyView(emptyTomorrow);

        TextView emptyOther= findViewById(R.id.emptyOther);
        otherListView.setEmptyView(emptyOther);

        //Initialize "Add Meeting" Btn
        Button createMeetingBtn= findViewById(R.id.createMeetingBtn);
        createMeetingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createMeeting(view);
            }
        });

        newMeetingResult = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent intent = result.getData();
                            Meeting meetingAdded= (Meeting) intent.getSerializableExtra("meetingObject");
                            todayMeetingsList.add(meetingAdded);
                            meetingListAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }


    public void createMeeting(View view) {
        Intent intent = new Intent(MainActivity.this, AddMeetingActivity.class);
        newMeetingResult.launch(intent);
    }

    public void removeMeeting(View view) {
        int position = todayListView.getPositionForView(view);
        todayMeetingsList.remove(position);
        meetingListAdapter.notifyDataSetChanged();
    }
}