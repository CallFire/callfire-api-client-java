package com.callfire.api.client.api.campaigns.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Dto to represent date and time (year, month, day, hour, minute, second).
 * 1 based date, 0 based time
 * example (1964, 12, 25, 23, 0, 59)
 */
public class LocalDateTime extends LocalDate {
    private Integer hour;
    private Integer minute;
    private Integer second;

    public LocalDateTime() {
    }

    public LocalDateTime(Integer year, Integer month, Integer day, Integer hour, Integer minute, Integer second) {
        this.year = year;
        this.month = month;
        this.day = day;
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
