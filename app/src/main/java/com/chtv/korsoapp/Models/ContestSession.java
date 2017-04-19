package com.chtv.korsoapp.Models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tk95s on 2017. 04. 19..
 */

public class ContestSession {
    List<Player> players;
    List<Scoreboard> scoreboards;
    ContestEvent contestEvent;
    String name;

    public ContestSession(ContestEvent contestEvent, String name) {
        this.contestEvent = contestEvent;
        this.name = contestEvent.getName() + ": " + name;
        players = new ArrayList<Player>();
        scoreboards = new ArrayList<Scoreboard>();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Scoreboard> getScoreboards() {
        return scoreboards;
    }

    public void setScoreboards(List<Scoreboard> scoreboards) {
        this.scoreboards = scoreboards;
    }

    public ContestEvent getContestEvent() {
        return contestEvent;
    }

    public void setContestEvent(ContestEvent contestEvent) {
        this.contestEvent = contestEvent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
