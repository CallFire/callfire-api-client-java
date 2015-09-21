package com.callfire.api.client.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Number extends BaseModel {
    private String number;
    private String nationalFormat;
    private boolean tollFree;
    private Region region;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNationalFormat() {
        return nationalFormat;
    }

    public void setNationalFormat(String nationalFormat) {
        this.nationalFormat = nationalFormat;
    }

    public boolean isTollFree() {
        return tollFree;
    }

    public void setTollFree(boolean tollFree) {
        this.tollFree = tollFree;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("number", number)
            .append("nationalFormat", nationalFormat)
            .append("tollFree", tollFree)
            .append("region", region)
            .toString();
    }
}
