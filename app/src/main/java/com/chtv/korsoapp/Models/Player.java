package com.chtv.korsoapp.Models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tk95s on 2017. 04. 19..
 */

public class Player {
    List<PlayerResult> playerResults;
    String name;
    ContestSession contestSession;

    public Player(ContestSession session, String name) {
        this.contestSession = session;
        this.name = name;
        playerResults = new ArrayList<PlayerResult>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ContestSession getContestSession() {
        return contestSession;
    }

    public void setContestSession(ContestSession contestSession) {
        this.contestSession = contestSession;
    }

    public List<PlayerResult> getPlayerResults() {
        return playerResults;
    }

    public void setPlayerResults(List<PlayerResult> playerResults) {
        this.playerResults = playerResults;
    }
}
