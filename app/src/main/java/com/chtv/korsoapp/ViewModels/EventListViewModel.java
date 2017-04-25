package com.chtv.korsoapp.ViewModels;

import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.chtv.korsoapp.Models.ContestEvent;

import java.util.List;

/**
 * Created by cregz on 2017.04.25..
 */

public class EventListViewModel extends BaseObservable {
    private ObservableList<ContestEvent> contestEvents;

    public ObservableList<ContestEvent> getContestEvents() {
        return contestEvents;
    }

    public EventListViewModel(ObservableList<ContestEvent> contestEvents) {
        this.contestEvents = contestEvents;
    }
}
