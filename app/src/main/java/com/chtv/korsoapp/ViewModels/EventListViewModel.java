package com.chtv.korsoapp.ViewModels;

import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.chtv.korsoapp.Models.ContestEvent;
import com.chtv.korsoapp.events.ContestEventSelectedEvent;
import com.chtv.korsoapp.events.ShowToastEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import io.realm.RealmList;

/**
 * Created by cregz on 2017.04.25..
 */

public class EventListViewModel extends BaseObservable {
    private RealmList<ContestEvent> contestEvents;

    public RealmList<ContestEvent> getContestEvents() {
        return contestEvents;
    }

    public EventListViewModel(RealmList<ContestEvent> contestEvents) {
        this.contestEvents = contestEvents;
    }

    public void onCreateNewEventClick(){
        contestEvents.add(contestEvents.get(0));
    }

    public void onItemClick(ContestEvent contestEvent){
        EventBus.getDefault().post(new ContestEventSelectedEvent(contestEvent));
    }
}
