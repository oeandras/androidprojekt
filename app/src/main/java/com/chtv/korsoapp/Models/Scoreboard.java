package com.chtv.korsoapp.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by tk95s on 2017. 04. 19..
 */

public class Scoreboard implements Parcelable {
    ContestSession contestSession;
    List<PlayerResult> playerResults;
    String name;
    String scoreboardId;

    //region Getters-Setters
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
        this.playerResults = new ArrayList<PlayerResult>();
        this.scoreboardId=scoreboardId;
    }

    public Scoreboard(ContestSession contestSession, String name){
        this(contestSession, name, UUID.randomUUID().toString());
    }
    //endregion

    //region Parcelable iplementation
    protected Scoreboard(Parcel in) {
        contestSession = (ContestSession) in.readValue(ContestSession.class.getClassLoader());
        name = in.readString();
        scoreboardId = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(contestSession);
        dest.writeString(name);
        dest.writeString(scoreboardId);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Scoreboard> CREATOR = new Parcelable.Creator<Scoreboard>() {
        @Override
        public Scoreboard createFromParcel(Parcel in) {
            return new Scoreboard(in);
        }

        @Override
        public Scoreboard[] newArray(int size) {
            return new Scoreboard[size];
        }
    };
    //endregion
}
