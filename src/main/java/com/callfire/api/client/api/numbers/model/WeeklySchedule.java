package com.callfire.api.client.api.numbers.model;

import com.callfire.api.client.api.common.model.CallfireModel;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Set;

public class WeeklySchedule extends CallfireModel {
    private LocalTime startTimeOfDay;
    private LocalTime stopTimeOfDay;
    private Set<DayOfWeek> daysOfWeek;
    private String timeZone;

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public LocalTime getStartTimeOfDay() {
        return startTimeOfDay;
    }

    public void setStartTimeOfDay(LocalTime startTimeOfDay) {
        this.startTimeOfDay = startTimeOfDay;
    }

    public LocalTime getStopTimeOfDay() {
        return stopTimeOfDay;
    }

    public void setStopTimeOfDay(LocalTime stopTimeOfDay) {
        this.stopTimeOfDay = stopTimeOfDay;
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
            .append("startTimeOfDay", startTimeOfDay)
            .append("stopTimeOfDay", stopTimeOfDay)
            .append("daysOfWeek", daysOfWeek)
            .append("timeZone", timeZone)
            .toString();
    }
}
