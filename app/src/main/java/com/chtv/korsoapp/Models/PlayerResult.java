package com.chtv.korsoapp.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.UUID;

import javax.xml.datatype.Duration;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by tk95s on 2017. 04. 19..
 */

public class PlayerResult extends RealmObject {
    String name;
    Date time; //TODO: Time format

    @Ignore
    Player player;

    @Ignore
    Scoreboard scoreboard;

    @PrimaryKey
    String playerResultId;

    //region Getters Setters
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

    public String getPlayerResultId() {
        return playerResultId;
    }

    public void setPlayerResultId(String playerResultId) {
        this.playerResultId = playerResultId;
    }
    //endregion

    //region Constructors
    public PlayerResult(Player player, Scoreboard scoreboard, Date time, String playerResultId) {
        this.player = player;
        this.time = time;
        this.scoreboard = scoreboard;
        this.name = scoreboard.getName() + ": " + player.getName();
        this.playerResultId=playerResultId;
    }

    public PlayerResult(Player player, Scoreboard scoreboard, Date time) {
        this(player, scoreboard, time, UUID.randomUUID().toString());
    }

    public PlayerResult(){}
    //endregion
}