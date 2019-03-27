package com.se.pranita.termproject.model;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Pranita on 21/4/16.
 */
public class Reservation {
    private String resourceName;
    private String netID;
    private Date slot_date;
    private String slot_time_range;

    private Map<String, String> time_slot_map = new HashMap<String, String>();


    public Reservation() {
        initMap();
    }

    private void initMap() {
        time_slot_map.put("9-10", "9am - 10am");
        time_slot_map.put("10-11", "10am - 11am");
        time_slot_map.put("11-12", "11am - 12pm");
        time_slot_map.put("12-1", "12pm - 1pm");
        time_slot_map.put("1-2", "1pm - 2pm");
        time_slot_map.put("2-3", "2pm - 3pm");
        time_slot_map.put("3-4", "3pm - 4pm");
        time_slot_map.put("4-5", "4pm - 5pm");
        time_slot_map.put("5-6", "5pm - 6pm");
        time_slot_map.put("6-7", "6pm - 7pm");
    }

    public Reservation(String resourceName, String netID, Date slot_date, String slot_time_range) {
        initMap();
        this.resourceName = resourceName;
        this.netID = netID;
        this.slot_date = slot_date;
        this.slot_time_range = slot_time_range;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "resourceName='" + resourceName + '\'' +
                ", netID='" + netID + '\'' +
                ", slot_date=" + slot_date +
                ", slot_time_range='" + slot_time_range + '\'' +
                '}';
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getNetID() {
        return netID;
    }

    public void setNetID(String netID) {
        this.netID = netID;
    }

    public Date getSlot_date() {
        return slot_date;
    }

    public void setSlot_date(Date slot_date) {
        this.slot_date = slot_date;
    }

    public String getSlot_time_range() {
        return slot_time_range;
    }

    public void setSlot_time_range(String slot_time_range) {
        this.slot_time_range = slot_time_range;
    }

    public String getTimeSlotVal() {
        return time_slot_map.get(this.getSlot_time_range());
    }
}
