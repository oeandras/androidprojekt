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

public class Player extends RealmObject {
    RealmList<PlayerResult> playerResults;
    String name;

    @Ignore
    ContestSession contestSession;

    @PrimaryKey
    String playerId;

    //region Getters Setters
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

    public void setPlayerResults(RealmList<PlayerResult> playerResults) {
        this.playerResults = playerResults;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }
    //endregion


    //region Contructors
    public Player(ContestSession session, String name, String playerId) {
        this.contestSession = session;
        this.name = name;
        playerResults = new RealmList<PlayerResult>();
        this.playerId = playerId;
    }

    public Player(ContestSession session, String name){
        this(session, name, UUID.randomUUID().toString());
    }

    public Player(){}
    //endregion
}
