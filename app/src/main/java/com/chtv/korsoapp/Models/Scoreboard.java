package com.chtv.korsoapp.Models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tk95s on 2017. 04. 19..
 */

public class Scoreboard {
    ContestSession contestSession;
    List<PlayerResult> playerResults;
    String name;

    public Scoreboard(ContestSession contestSession, String name) {
        this.contestSession = contestSession;
        this.name = contestSession.getName() + ": " + name;
        this.playerResults = new ArrayList<PlayerResult>();
    }

    public List<PlayerResult> getPlayerResults() {
        return playerResults;
    }

    public void setPlayerResults(List<PlayerResult> playerResults) {
        this.playerResults = playerResults;
    }

    public ContestSession getContestSession() {
        return contestSession;
    }

    public void setContestSession(ContestSession contestSession) {
        this.contestSession = contestSession;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
