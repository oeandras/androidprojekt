package com.chtv.korsoapp.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by tk95s on 2017. 04. 19..
 */

public class Player implements Parcelable {
    List<PlayerResult> playerResults;
    String name;
    ContestSession contestSession;
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

    public void setPlayerResults(List<PlayerResult> playerResults) {
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
        playerResults = new ArrayList<PlayerResult>();
        this.playerId = playerId;
    }

    public Player(ContestSession session, String name){
        this(session, name, UUID.randomUUID().toString());
    }
    //endregion

    //region Parcelable implementation
    protected Player(Parcel in) {
        name = in.readString();
        contestSession = (ContestSession) in.readValue(ContestSession.class.getClassLoader());
        playerId = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeValue(contestSession);
        dest.writeString(playerId);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Player> CREATOR = new Parcelable.Creator<Player>() {
        @Override
        public Player createFromParcel(Parcel in) {
            return new Player(in);
        }

        @Override
        public Player[] newArray(int size) {
            return new Player[size];
        }
    };
    //endregion
}
