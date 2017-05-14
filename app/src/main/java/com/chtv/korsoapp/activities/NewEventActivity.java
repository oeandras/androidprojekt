package com.chtv.korsoapp.activities;

import android.app.DatePickerDialog;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.chtv.korsoapp.Models.ContestEvent;
import com.chtv.korsoapp.Models.ContestSession;
import com.chtv.korsoapp.R;
import com.chtv.korsoapp.fragments.NewEventFragment;
import com.chtv.korsoapp.fragments.NewSessionFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmList;

public class NewEventActivity extends AppCompatActivity implements NewEventFragment.OnFragmentInteractionListener, NewSessionFragment.OnFragmentInteractionListener{

    private Realm realm;

    private String eventName;
    private Date from;
    private Date until;

    private ArrayList<ContestSession> sessions;

    private ContestEvent event;
    private ContestSession session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.sessions = new ArrayList<>();
        setContentView(R.layout.activity_new_event);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        NewEventFragment fragment = NewEventFragment.newInstance();
        transaction.add(R.id.fragment_container, fragment, "event");
        transaction.commit();


    }

    @Override
    protected void onResume() {
        super.onResume();
        this.realm = Realm.getDefaultInstance();
        this.event = new ContestEvent();
        this.session = new ContestSession();
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.realm.close();
    }

    @Override
    public void onSetName(String eventName) {
        this.eventName = eventName;
        this.event.setName(eventName);
    }

    @Override
    public void onSetFrom(Date from) {
        this.from = from;
        this.event.setFrom(from);
    }

    @Override
    public void onSetUntil(Date until) {
        this.until = until;
        this.event.setUntil(until);
    }

    @Override
    public void onAddSessionClick() {
        this.session = new ContestSession();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        NewSessionFragment fragment = NewSessionFragment.newInstance(eventName);
        transaction.replace(R.id.fragment_container, fragment).addToBackStack("session");
        transaction.commit();
    }

    @Override
    public void onSaveClick() {
        //todo: validation
        if(this.event.getContestEventId() == null) {
            this.event.setContestEventId(UUID.randomUUID().toString());
            if(this.event.getContestSessions() == null){
                this.event.setContestSessions(new RealmList<ContestSession>(this.sessions.toArray(new ContestSession[this.sessions.size()])));
            }
            realm.beginTransaction();
            realm.copyToRealm(this.event);
            realm.commitTransaction();
        }
        this.finish();
    }

    @Override
    public ArrayList<ContestSession> getSessions() {
        return sessions;
    }

    @Override
    public void onSaveSessionClick(String sessionName) {
        //todo: validation
        this.session.setName(sessionName);
        this.session.setContestSessionId(UUID.randomUUID().toString());
        sessions.add(this.session);
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void onCancelCLick() {
        getSupportFragmentManager().popBackStack();
    }
}
