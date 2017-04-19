package com.chtv.korsoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.chtv.korsoapp.Models.ContestEvent;
import com.chtv.korsoapp.Models.ContestSession;
import com.chtv.korsoapp.Models.Scoreboard;

import java.util.ArrayList;
import java.util.List;

public class EventActivity extends AppCompatActivity {
    ContestEvent contestEvent;
    List<ContestSession> contestSessions;
    ContestSession selectedSession;
    List<Scoreboard> scoreboards;
    String sessionName;
    String scoreboardName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sessionName = "";
        scoreboardName = "";
        selectedSession = null;
        scoreboards = new ArrayList<Scoreboard>();
        contestSessions = new ArrayList<ContestSession>();
        contestEvent = null; //TODO: intent

        setContentView(R.layout.activity_event);
    }
}
