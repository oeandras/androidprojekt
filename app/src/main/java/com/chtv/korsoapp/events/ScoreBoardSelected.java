package com.chtv.korsoapp.events;

import com.chtv.korsoapp.Models.Scoreboard;

/**
 * Created by cregz on 2017.05.14..
 */

public class ScoreBoardSelected {
    private Scoreboard scoreboard;

    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    public ScoreBoardSelected(Scoreboard scoreboard) {
        this.scoreboard = scoreboard;
    }
}
