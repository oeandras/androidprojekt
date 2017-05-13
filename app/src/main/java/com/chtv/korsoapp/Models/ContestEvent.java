package com.chtv.korsoapp.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by tk95s on 2017. 04. 19..
 */

public class ContestEvent implements Parcelable {
    List<ContestSession> contestSessions;
    String name;
    Date from;
    Date until;
    String contestEventId;

    //region Getter Setters
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
        contestSessions = new ArrayList<ContestSession>();
        this.contestEventId = contestEventId;
    }

    public ContestEvent(String name, Date from, Date until) {
        this(name, from, until, UUID.randomUUID().toString());
    }


    //ContestSession list is not in the parcel due to circular references
    protected ContestEvent(Parcel in) {
        name = in.readString();
        long tmpFrom = in.readLong();
        from = tmpFrom != -1 ? new Date(tmpFrom) : null;
        long tmpUntil = in.readLong();
        until = tmpUntil != -1 ? new Date(tmpUntil) : null;
        contestEventId = in.readString();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeLong(from != null ? from.getTime() : -1L);
        dest.writeLong(until != null ? until.getTime() : -1L);
        dest.writeString(contestEventId);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ContestEvent> CREATOR = new Parcelable.Creator<ContestEvent>() {
        @Override
        public ContestEvent createFromParcel(Parcel in) {
            return new ContestEvent(in);
        }

        @Override
        public ContestEvent[] newArray(int size) {
            return new ContestEvent[size];
        }
    };
}
