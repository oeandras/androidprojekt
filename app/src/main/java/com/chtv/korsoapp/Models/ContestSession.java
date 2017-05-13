package com.chtv.korsoapp.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by tk95s on 2017. 04. 19..
 */

public class ContestSession extends RealmObject {
    RealmList<Player> players;
    RealmList<Scoreboard> scoreboards;

    @Ignore
    ContestEvent contestEvent;

    String name;

    @PrimaryKey
    String contestSessionId;

    //region Getters Setters
    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(RealmList<Player> players) {
        this.players = players;
    }

    public List<Scoreboard> getScoreboards() {
        return scoreboards;
    }

    public void setScoreboards(RealmList<Scoreboard> scoreboards) {
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

    public String getContestSessionId() {
        return contestSessionId;
    }

    public void setContestSessionId(String contestSessionId) {
        this.contestSessionId = contestSessionId;
    }
    //endregion

    //region Constructors
    public ContestSession(ContestEvent contestEvent, String name, String contestSessionId) {
        this.contestEvent = contestEvent;
        this.name = contestEvent.getName() + ": " + name;
        players = new RealmList<Player>();
        scoreboards = new RealmList<Scoreboard>();
        this.contestSessionId = contestSessionId;
    }

    public ContestSession(ContestEvent contestEvent, String name) {
        this(contestEvent, name, UUID.randomUUID().toString());
    }

    //empty constructor for realm
    public ContestSession(){}

    //endregion

}
