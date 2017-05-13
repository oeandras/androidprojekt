package com.chtv.korsoapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;

import com.chtv.korsoapp.Models.ContestEvent;
import com.chtv.korsoapp.Models.ContestSession;
import com.chtv.korsoapp.Models.Scoreboard;
import com.chtv.korsoapp.R;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class EventActivity extends AppCompatActivity {
    ContestEvent contestEvent;
    RealmList<ContestSession> contestSessions;
    ContestSession selectedSession;
    RealmList<Scoreboard> scoreboards;
    String sessionName;
    String scoreboardName;

    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.realm = Realm.getDefaultInstance();
        Bundle b = getIntent().getExtras();
        if(b != null) {
            String contestEventId = b.getString("contestEvent");
            RealmResults<ContestEvent> results = realm.where(ContestEvent.class).equalTo("contestEventId", contestEventId).findAll();
            this.contestEvent = results.first();

            if(contestEvent.getContestSessions().size()>0) {
                this.contestSessions = new RealmList<>(contestEvent.getContestSessions().toArray(new ContestSession[contestEvent.getContestSessions().size()]));
                this.scoreboards = new RealmList<>(contestSessions.first().getScoreboards().toArray(new Scoreboard[contestSessions.first().getScoreboards().size()]));
            }
            else{
                contestSessions = new RealmList<>();
                scoreboards = new RealmList<>();
            }

        }

        setContentView(R.layout.activity_event);
        AppCompatTextView eventNameTV = (AppCompatTextView) findViewById(R.id.event_name);
        eventNameTV.setText(contestEvent.getName());
    }
}
