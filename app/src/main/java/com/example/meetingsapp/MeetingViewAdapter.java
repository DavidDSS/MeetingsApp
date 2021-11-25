package com.example.meetingsapp;

import android.content.Context;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MeetingViewAdapter extends ArrayAdapter {

    public MeetingViewAdapter(Context context, ArrayList<Meeting> arrayList) {
        super(context, 0, arrayList);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        View meetingView = view;

        if (meetingView == null) meetingView = LayoutInflater.from(getContext()).inflate(R.layout.meetings_custom_list, parent, false);
        Meeting meetingViewPos = (Meeting) getItem(position);

        TextView meetingName = meetingView.findViewById(R.id.meetingName);
        TextView meetingLocation = meetingView.findViewById(R.id.meetingLocation);
        TextView meetingDate = meetingView.findViewById(R.id.meetingDate);
        TextView meetingTime = meetingView.findViewById(R.id.meetingTime);

        SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yyyy");
        String dateString= simpleDate.format(meetingViewPos.getDate());

        meetingName.setText(meetingViewPos.getTitle());
        meetingLocation.setText(meetingViewPos.getLocation());
        meetingDate.setText(dateString);
        meetingTime.setText(meetingViewPos.getTime().toString());
        return meetingView;
    }
}