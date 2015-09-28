package com.callfire.api.client.api.keywords.model;

import com.callfire.api.client.api.common.model.CallfireModel;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

public class KeywordLease extends CallfireModel {
    private String shortCode;
    private String keyword;
    private Date leaseBegin;
    private Date leaseEnd;
    private Boolean autoRenew;
    private Status status;

    public enum Status {
        PENDING, ACTIVE, RELEASED, UNAVAILABLE
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("shortCode", shortCode)
            .append("keyword", keyword)
            .append("leaseBegin", leaseBegin)
            .append("leaseEnd", leaseEnd)
            .append("autoRenew", autoRenew)
            .append("status", status)
            .toString();
    }
}
