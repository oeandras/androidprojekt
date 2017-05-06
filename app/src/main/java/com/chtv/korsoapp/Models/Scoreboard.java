package com.chtv.korsoapp.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tk95s on 2017. 04. 19..
 */

public class Scoreboard implements Parcelable {
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

    protected Scoreboard(Parcel in) {
        contestSession = (ContestSession) in.readValue(ContestSession.class.getClassLoader());
        if (in.readByte() == 0x01) {
            playerResults = new ArrayList<PlayerResult>();
            in.readList(playerResults, PlayerResult.class.getClassLoader());
        } else {
            playerResults = null;
        }
        name = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(contestSession);
        if (playerResults == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(playerResults);
        }
        dest.writeString(name);
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
}
