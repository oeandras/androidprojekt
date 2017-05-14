package com.chtv.korsoapp.events;

import com.chtv.korsoapp.Models.ContestSession;

/**
 * Created by cregz on 2017.05.14..
 */

public class ContestSessionSelected {
    private ContestSession contestSession;

    public ContestSession getContestSession() {
        return contestSession;
    }

    public ContestSessionSelected(ContestSession contestSession) {
        this.contestSession = contestSession;
    }
}
