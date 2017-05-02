package com.chtv.korsoapp.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tk95s on 2017. 04. 19..
 */

public class Player implements Parcelable {
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

    protected Player(Parcel in) {
        if (in.readByte() == 0x01) {
            playerResults = new ArrayList<PlayerResult>();
            in.readList(playerResults, PlayerResult.class.getClassLoader());
        } else {
            playerResults = null;
        }
        name = in.readString();
        contestSession = (ContestSession) in.readValue(ContestSession.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (playerResults == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(playerResults);
        }
        dest.writeString(name);
        dest.writeValue(contestSession);
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
}
