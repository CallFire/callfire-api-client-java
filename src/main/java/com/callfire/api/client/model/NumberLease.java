package com.callfire.api.client.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

public class NumberLease extends BaseModel {
    private String number;
    private String nationalFormat;
    private Boolean tollFree;
    private Region region;
    private Date leaseBegin;
    private Date leaseEnd;
    private Boolean autoRenew;
    private Status status;
    private CallFeatureStatus callFeatureStatus;
    private TextFeatureStatus textFeatureStatus;

    public enum Status {
        PENDING, ACTIVE, RELEASED, UNAVAILABLE
    }

    public enum CallFeatureStatus {
        UNSUPPORTED, PENDING, DISABLED, ENABLED
    }

    public enum TextFeatureStatus {
        UNSUPPORTED, PENDING, DISABLED, ENABLED
    }

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

    public Boolean getTollFree() {
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

    public Date getLeaseBegin() {
        return leaseBegin;
    }

    public void setLeaseBegin(Date leaseBegin) {
        this.leaseBegin = leaseBegin;
    }

    public Date getLeaseEnd() {
        return leaseEnd;
    }

    public void setLeaseEnd(Date leaseEnd) {
        this.leaseEnd = leaseEnd;
    }

    public Boolean getAutoRenew() {
        return autoRenew;
    }

    public void setAutoRenew(Boolean autoRenew) {
        this.autoRenew = autoRenew;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public CallFeatureStatus getCallFeatureStatus() {
        return callFeatureStatus;
    }

    public void setCallFeatureStatus(CallFeatureStatus callFeatureStatus) {
        this.callFeatureStatus = callFeatureStatus;
    }

    public TextFeatureStatus getTextFeatureStatus() {
        return textFeatureStatus;
    }

    public void setTextFeatureStatus(TextFeatureStatus textFeatureStatus) {
        this.textFeatureStatus = textFeatureStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("number", number)
            .append("nationalFormat", nationalFormat)
            .append("tollFree", tollFree)
            .append("region", region)
            .append("leaseBegin", leaseBegin)
            .append("leaseEnd", leaseEnd)
            .append("autoRenew", autoRenew)
            .append("status", status)
            .append("callFeatureStatus", callFeatureStatus)
            .append("textFeatureStatus", textFeatureStatus)
            .toString();
    }
}
