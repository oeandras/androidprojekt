package com.chtv.korsoapp.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by tk95s on 2017. 04. 19..
 */

public class ContestEvent extends RealmObject {
    RealmList<ContestSession> contestSessions;
    String name;
    Date from;
    Date until;

    @PrimaryKey
    String contestEventId;

    //region Getter Setters
    public RealmList<ContestSession> getContestSessions() {
        return contestSessions;
    }

    public void setContestSessions(RealmList<ContestSession> contestSessions) {
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

    public String getContestEventId() {
        return contestEventId;
    }

    public void setContestEventId(String contestEventId) {
        this.contestEventId = contestEventId;
    }
    //endregion

    public ContestEvent(String name, Date from, Date until, String contestEventId) {
        this.name = name;
        this.from = from;
        this.until = until;
        contestSessions = new RealmList<ContestSession>();
        this.contestEventId = contestEventId;
    }

    public ContestEvent(String name, Date from, Date until) {
        this(name, from, until, UUID.randomUUID().toString());
    }

    //empty constructor for realm
    public ContestEvent(){

    }
}
