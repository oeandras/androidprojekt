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

public class Scoreboard extends RealmObject {
    @Ignore
    ContestSession contestSession;

    RealmList<PlayerResult> playerResults;
    String name;

    @PrimaryKey
    String scoreboardId;

    //region Getters-Setters
    public List<PlayerResult> getPlayerResults() {
        return playerResults;
    }

    public void setPlayerResults(RealmList<PlayerResult> playerResults) {
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

    public String getScoreboardId() {
        return scoreboardId;
    }

    public void setScoreboardId(String scoreboardId) {
        this.scoreboardId = scoreboardId;
    }
    //endregion


    //region Constructors
    public Scoreboard(ContestSession contestSession, String name, String scoreboardId) {
        this.contestSession = contestSession;
        this.name = contestSession.getName() + ": " + name;
        this.playerResults = new RealmList<PlayerResult>();
        this.scoreboardId=scoreboardId;
    }

    public Scoreboard(ContestSession contestSession, String name){
        this(contestSession, name, UUID.randomUUID().toString());
    }

    public Scoreboard(){}
    //endregion


}
