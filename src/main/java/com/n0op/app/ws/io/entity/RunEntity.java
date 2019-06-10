package com.n0op.app.ws.io.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @author DanM
 */
@Entity(name="Runs")
public class RunEntity implements Serializable {

    private static final long serialVersionUID = -9125032499088820841L;



    @Id
    @GeneratedValue
    private long id;

    private String runId;
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

    public String getRunId() {
        return runId;
    }

    public void setRunId(String runId) {
        this.runId = runId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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



}
