package com.chtv.korsoapp.Models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by tk95s on 2017. 04. 19..
 */

public class ContestEvent {
    List<ContestSession> contestSessions;
    String name;
    Date from;
    Date until;

    public ContestEvent(String name, Date from, Date until) {
        this.name = name;
        this.from = from;
        this.until = until;
        contestSessions = new ArrayList<ContestSession>();
    }

    public List<ContestSession> getContestSessions() {
        return contestSessions;
    }

    public void setContestSessions(List<ContestSession> contestSessions) {
        this.contestSessions = contestSessions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getUntil() {
        return until;
    }

    public void setUntil(Date until) {
        this.until = until;
    }
}
