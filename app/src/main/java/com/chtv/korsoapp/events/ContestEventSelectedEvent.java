package com.chtv.korsoapp.events;

import com.chtv.korsoapp.Models.ContestEvent;

/**
 * Created by cregz on 2017.05.07..
 */

public class ContestEventSelectedEvent {
    private ContestEvent contestEvent;

    public ContestEvent getContestEvent() {
        return contestEvent;
    }

    public ContestEventSelectedEvent(ContestEvent contestEvent) {
        this.contestEvent=contestEvent;
    }
}
