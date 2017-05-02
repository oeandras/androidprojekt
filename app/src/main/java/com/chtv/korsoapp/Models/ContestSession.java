package com.chtv.korsoapp.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tk95s on 2017. 04. 19..
 */

public class ContestSession implements Parcelable {
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

    protected ContestSession(Parcel in) {
        if (in.readByte() == 0x01) {
            players = new ArrayList<Player>();
            in.readList(players, Player.class.getClassLoader());
        } else {
            players = null;
        }
        if (in.readByte() == 0x01) {
            scoreboards = new ArrayList<Scoreboard>();
            in.readList(scoreboards, Scoreboard.class.getClassLoader());
        } else {
            scoreboards = null;
        }
        contestEvent = (ContestEvent) in.readValue(ContestEvent.class.getClassLoader());
        name = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (players == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(players);
        }
        if (scoreboards == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(scoreboards);
        }
        dest.writeValue(contestEvent);
        dest.writeString(name);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ContestSession> CREATOR = new Parcelable.Creator<ContestSession>() {
        @Override
        public ContestSession createFromParcel(Parcel in) {
            return new ContestSession(in);
        }

        @Override
        public ContestSession[] newArray(int size) {
            return new ContestSession[size];
        }
    };
}
