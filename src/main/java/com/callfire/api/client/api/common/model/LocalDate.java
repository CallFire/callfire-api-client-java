package com.callfire.api.client.api.common.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Dto to represent date (year, month, day)
 * 1 based date, example (1964, 12, 25)
 */
public class LocalDate extends CallfireModel {
    protected Integer year;
    protected Integer month;
    protected Integer day;

    public LocalDate() {
    }

    public LocalDate(Integer year, Integer month, Integer day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("year", year)
            .append("month", month)
            .append("day", day)
            .toString();
    }
}
