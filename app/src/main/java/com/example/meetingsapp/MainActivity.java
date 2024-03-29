/*Reference:
Followed instructions from Android documentation to implement things such as ArrayAdapters and Returning Objects from activities:
“Getting a Result from an Activity  :   Android Developers.” Android Developers, https://developer.android.com/training/basics/intents/result.
“Arrayadapter  :   Android Developers.” Android Developers, https://developer.android.com/reference/android/widget/ArrayAdapter.
*/

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
import android.text.format.DateUtils;
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
    MeetingViewAdapter todayMeetingListAdapter;
    MeetingViewAdapter tomorrowMeetingListAdapter;
    MeetingViewAdapter otherMeetingListAdapter;

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
        todayMeetingListAdapter = new MeetingViewAdapter(this, todayMeetingsList);
        tomorrowMeetingListAdapter = new MeetingViewAdapter(this, tomorrowMeetingsList);
        otherMeetingListAdapter = new MeetingViewAdapter(this, otherMeetingsList);

        //Set Adapters
        todayListView.setAdapter(todayMeetingListAdapter);
        tomorrowListView.setAdapter(tomorrowMeetingListAdapter);
        otherListView.setAdapter(otherMeetingListAdapter);

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

        //Wait for the result from "Create Meeting" functionality and then add it to the meeting list
        newMeetingResult = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult activityResult) {
                        if (activityResult.getResultCode() == Activity.RESULT_OK) {
                            Meeting meetingAdded= (Meeting) activityResult.getData().getSerializableExtra("meetingObject");
                            Date meetingDate= meetingAdded.getDate();

                            if(DateUtils.isToday(meetingDate.getTime())){
                                todayMeetingsList.add(meetingAdded);
                                todayMeetingListAdapter.notifyDataSetChanged();
                                Toast.makeText(MainActivity.this, "Added meeting for today", Toast.LENGTH_SHORT).show();
                            }
                            else if(DateUtils.isToday(meetingDate.getTime()-DateUtils.DAY_IN_MILLIS)){
                                tomorrowMeetingsList.add(meetingAdded);
                                tomorrowMeetingListAdapter.notifyDataSetChanged();
                                Toast.makeText(MainActivity.this, "Added meeting for tomorrow", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                otherMeetingsList.add(meetingAdded);
                                otherMeetingListAdapter.notifyDataSetChanged();
                                Toast.makeText(MainActivity.this, "Added meeting", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    //Clear all meetings scheduled for today
    public void clearToday(View view) {
        todayMeetingsList.clear();
        todayMeetingListAdapter.notifyDataSetChanged();
    }

    //Clear all meetings
    public void clearAll(View view) {
        todayMeetingsList.clear();
        todayMeetingListAdapter.notifyDataSetChanged();
        tomorrowMeetingsList.clear();
        tomorrowMeetingListAdapter.notifyDataSetChanged();
        otherMeetingsList.clear();
        otherMeetingListAdapter.notifyDataSetChanged();
    }

    //Create a new meeting in new activity
    public void createMeeting(View view) {
        Intent intent = new Intent(MainActivity.this, AddMeetingActivity.class);
        newMeetingResult.launch(intent);
    }

}