package com.callfire.api.client.api.campaigns.model;

import com.callfire.api.client.api.common.model.CallfireModel;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Set;

/**
 * Campaign Schedule
 */
public class Schedule extends CallfireModel {
    private Long id;
    private Long campaignId;
    private LocalDate startDate;
    private LocalTime startTimeOfDay;
    private LocalDate stopDate;
    private LocalTime stopTimeOfDay;
    private String timeZone;
    private Set<DayOfWeek> daysOfWeek;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Long campaignId) {
        this.campaignId = campaignId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalTime getStartTimeOfDay() {
        return startTimeOfDay;
    }

    public void setStartTimeOfDay(LocalTime startTimeOfDay) {
        this.startTimeOfDay = startTimeOfDay;
    }

    public LocalDate getStopDate() {
        return stopDate;
    }

    public void setStopDate(LocalDate stopDate) {
        this.stopDate = stopDate;
    }

    public LocalTime getStopTimeOfDay() {
        return stopTimeOfDay;
    }

    public void setStopTimeOfDay(LocalTime stopTimeOfDay) {
        this.stopTimeOfDay = stopTimeOfDay;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public Set<DayOfWeek> getDaysOfWeek() {
        return daysOfWeek;
    }

    public void setDaysOfWeek(Set<DayOfWeek> daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("id", id)
            .append("campaignId", campaignId)
            .append("startDate", startDate)
            .append("startTimeOfDay", startTimeOfDay)
            .append("stopDate", stopDate)
            .append("stopTimeOfDay", stopTimeOfDay)
            .append("timeZone", timeZone)
            .append("daysOfWeek", daysOfWeek)
            .toString();
    }
}
