package com.n0op.app.ws.shared.dto;

import java.io.Serializable;
import java.util.Date;

public class RunDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private long id;

    private String name;
    private Date dateAndTimeOfRun;
    private String distance;
    private String calories;
    private String averageHeartRate;
    private String cadence;
    private String location;
    private String averagePace;
    private String highestSpeed;
    private String lowestSpeed;
    private String runId;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateAndTimeOfRun() {
        return dateAndTimeOfRun;
    }

    public void setDateAndTimeOfRun(Date dateAndTimeOfRun) {
        this.dateAndTimeOfRun = dateAndTimeOfRun;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public String getAverageHeartRate() {
        return averageHeartRate;
    }

    public void setAverageHeartRate(String averageHeartRate) {
        this.averageHeartRate = averageHeartRate;
    }

    public String getCadence() {
        return cadence;
    }

    public void setCadence(String cadence) {
        this.cadence = cadence;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAveragePace() {
        return averagePace;
    }

    public void setAveragePace(String averagePace) {
        this.averagePace = averagePace;
    }

    public String getHighestSpeed() {
        return highestSpeed;
    }

    public void setHighestSpeed(String highestSpeed) {
        this.highestSpeed = highestSpeed;
    }

    public String getLowestSpeed() {
        return lowestSpeed;
    }

    public void setLowestSpeed(String lowestSpeed) {
        this.lowestSpeed = lowestSpeed;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRunId() {
        return runId;
    }

    public void setRunId(String runId) {
        this.runId = runId;
    }
}
