package com.chtv.korsoapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.chtv.korsoapp.Models.ContestEvent;
import com.chtv.korsoapp.Models.ContestSession;
import com.chtv.korsoapp.Models.Scoreboard;
import com.chtv.korsoapp.R;
import com.chtv.korsoapp.adapters.SessionListAdapter;
import com.chtv.korsoapp.events.ContestSessionSelected;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

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

    String playerId;

    private Realm realm;

    private RecyclerView sessionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.realm = Realm.getDefaultInstance();
        Bundle b = getIntent().getExtras();
        if(b != null) {
            playerId = b.getString("player");
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
        TextView eventNameTV = (TextView) findViewById(R.id.event_text);
        eventNameTV.setText(contestEvent.getName());

        sessionList = (RecyclerView) findViewById(R.id.session_list);
        SessionListAdapter adapter = new SessionListAdapter(contestSessions);
        sessionList.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onSessionSelected(ContestSessionSelected session){
        Intent intent = new Intent(this, SessionActivity.class);
        intent.putExtra("event", contestEvent.getContestEventId());
        intent.putExtra("session", session.getContestSession().getContestSessionId());
        intent.putExtra("player", playerId);
        startActivity(intent);
    }
}
