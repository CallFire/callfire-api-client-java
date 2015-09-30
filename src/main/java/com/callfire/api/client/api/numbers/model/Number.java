package com.callfire.api.client.api.numbers.model;

import com.callfire.api.client.api.common.model.CallfireModel;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Number extends CallfireModel {
    private String number;
    private String nationalFormat;
    private Boolean tollFree;
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

    public Boolean isTollFree() {
        return tollFree;
    }

    public void setTollFree(Boolean tollFree) {
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
