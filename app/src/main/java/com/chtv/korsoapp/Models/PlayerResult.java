package com.chtv.korsoapp.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

import javax.xml.datatype.Duration;

/**
 * Created by tk95s on 2017. 04. 19..
 */

public class PlayerResult implements Parcelable {
    String name;
    Date time; //TODO: Time format
    Player player;
    Scoreboard scoreboard;

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    public void setScoreboard(Scoreboard scoreboard) {
        this.scoreboard = scoreboard;
    }

    public PlayerResult(Player player, Scoreboard scoreboard, Date time) {
        this.player = player;
        this.time = time;
        this.scoreboard = scoreboard;
        this.name = scoreboard.getName() + ": " + player.getName();
    }

    protected PlayerResult(Parcel in) {
        name = in.readString();
        time = new Date(in.readLong());
        player = (Player) in.readValue(Player.class.getClassLoader());
        scoreboard = (Scoreboard) in.readValue(Scoreboard.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeLong(time.getTime());
        dest.writeValue(player);
        dest.writeValue(scoreboard);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<PlayerResult> CREATOR = new Parcelable.Creator<PlayerResult>() {
        @Override
        public PlayerResult createFromParcel(Parcel in) {
            return new PlayerResult(in);
        }

        @Override
        public PlayerResult[] newArray(int size) {
            return new PlayerResult[size];
        }
    };
}