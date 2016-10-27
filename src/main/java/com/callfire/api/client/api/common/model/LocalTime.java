package com.callfire.api.client.api.common.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Dto to represent time (hour, minute, second)
 * 0 based time, example (23, 0, 59)
 */
public class LocalTime extends CallfireModel {
    private Integer hour;
    private Integer minute;
    private Integer second;

    public LocalTime() {
    }

    public LocalTime(Integer hour, Integer minute, Integer second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Integer getMinute() {
        return minute;
    }

    public void setMinute(Integer minute) {
        this.minute = minute;
    }

    public Integer getSecond() {
        return second;
    }

    public void setSecond(Integer second) {
        this.second = second;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("hour", hour)
            .append("minute", minute)
            .append("second", second)
            .toString();
    }
}
