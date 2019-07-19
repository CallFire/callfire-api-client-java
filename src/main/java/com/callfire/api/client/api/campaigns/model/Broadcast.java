package com.callfire.api.client.api.campaigns.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.callfire.api.client.api.common.model.CallfireModel;

public abstract class Broadcast extends CallfireModel {
    private Long id;
    private String name;
    private Status status;
    private Date lastModified;
    private String fromNumber;
    private LocalTimeRestriction localTimeRestriction;
    private List<Schedule> schedules;
    private Integer maxActive;
    private List<String> labels = new ArrayList<>();
    private Boolean resumeNextDay;

    public enum Status {
        TEST,
        SETUP,
        START_PENDING, // waiting for call batch population
        RUNNING,
        SCHEDULED, // scheduled
        STOPPED,
        SUSPENDED,
        FINISHED,
        ARCHIVED,
        VALIDATING_EMAIL,
        VALIDATING_START,
        BLOCKED_SUSPICIOUS, // campaign with suspicious word is waiting for approve or decline
        DECLINED,
        APPROVED,
        UNKNOWN
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public String getFromNumber() {
        return fromNumber;
    }

    public void setFromNumber(String fromNumber) {
        this.fromNumber = fromNumber;
    }

    public LocalTimeRestriction getLocalTimeRestriction() {
        return localTimeRestriction;
    }

    public void setLocalTimeRestriction(LocalTimeRestriction localTimeRestriction) {
        this.localTimeRestriction = localTimeRestriction;
    }

    public Integer getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(Integer maxActive) {
        this.maxActive = maxActive;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public Boolean getResumeNextDay() {
        return resumeNextDay;
    }

    public void setResumeNextDay(Boolean resumeNextDay) {
        this.resumeNextDay = resumeNextDay;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("id", id)
            .append("name", name)
            .append("status", status)
            .append("lastModified", lastModified)
            .append("fromNumber", fromNumber)
            .append("localTimeRestriction", localTimeRestriction)
            .append("schedules", schedules)
            .append("maxActive", maxActive)
            .append("labels", labels)
            .append("resumeNextDay", resumeNextDay)
            .toString();
    }
}
