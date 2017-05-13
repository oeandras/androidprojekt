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

public class NewEventActivity extends AppCompatActivity implements NewEventFragment.OnFragmentInteractionListener, NewSessionFragment.OnFragmentInteractionListener{

    private Realm realm;

    private String eventName;
    private Date from;
    private Date until;

    private ArrayList<ContestSession> sessions;

    private ContestEvent event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        NewEventFragment fragment = NewEventFragment.newInstance();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
        event = new ContestEvent();

    }

    @Override
    protected void onResume() {
        super.onResume();
        this.realm = Realm.getDefaultInstance();
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
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        NewSessionFragment fragment = NewSessionFragment.newInstance(eventName);
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }

    @Override
    public void onSaveClick() {
        //todo: validation
        this.event.setContestEventId(UUID.randomUUID().toString());
        realm.beginTransaction();
        realm.copyToRealm(this.event);
        realm.commitTransaction();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
