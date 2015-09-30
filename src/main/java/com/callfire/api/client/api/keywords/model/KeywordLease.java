package com.callfire.api.client.api.keywords.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

public class KeywordLease extends Keyword {
    private Date leaseBegin;
    private Date leaseEnd;
    private Boolean autoRenew;
    private LeaseStatus status;

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

    public LeaseStatus getStatus() {
        return status;
    }

    public void setStatus(LeaseStatus leaseStatus) {
        this.status = leaseStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("leaseBegin", leaseBegin)
            .append("leaseEnd", leaseEnd)
            .append("autoRenew", autoRenew)
            .append("status", status)
            .toString();
    }
}
