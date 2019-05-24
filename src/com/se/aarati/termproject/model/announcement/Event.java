package com.se.aarati.termproject.model.announcement;

import com.google.gson.GsonBuilder;

import java.sql.Timestamp;

/**
 * Created by aarati on 23/5/19.
 */
public class Event extends Announcement {
    private Timestamp eventDatetime;
    private String eventVenue;

    public Event(Integer id, String netID, String link, String title, String details, Timestamp createTime, Timestamp eventDatetime, String eventVenue) {
        super(id, netID, link, title, details, AnnouncementType.EVENT, createTime);
        this.eventDatetime = eventDatetime;
        this.eventVenue = eventVenue;
    }

    public Event() {
        super(AnnouncementType.EVENT);
    }

    @Override
    public String toString() {
        return new GsonBuilder().create().toJson(this);
    }

    public Timestamp getEventDatetime() {
        return eventDatetime;
    }

    public void setEventDatetime(Timestamp eventDatetime) {
        this.eventDatetime = eventDatetime;
    }

    public String getEventVenue() {
        return eventVenue;
    }

    public void setEventVenue(String eventVenue) {
        this.eventVenue = eventVenue;
    }
}
