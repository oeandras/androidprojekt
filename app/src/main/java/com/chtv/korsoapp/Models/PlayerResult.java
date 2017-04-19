package com.chtv.korsoapp.Models;

import javax.xml.datatype.Duration;

/**
 * Created by tk95s on 2017. 04. 19..
 */

public class PlayerResult {
    String name;
    int time; //TODO: Time format
    Player player;
    Scoreboard scoreboard;

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
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

    public PlayerResult(Player player, Scoreboard scoreboard, int time) {
        this.player = player;
        this.time = time;
        this.scoreboard = scoreboard;
        this.name = scoreboard.getName() + ": " + player.getName();
    }
}
