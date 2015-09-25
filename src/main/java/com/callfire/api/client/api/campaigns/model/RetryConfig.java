package com.callfire.api.client.api.campaigns.model;

import com.callfire.api.client.api.common.model.CallfireModel;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

public class RetryConfig extends CallfireModel {
    private Integer maxAttempts;
    private Integer minutesBetweenAttempts;
    private List<RetryResults> retryResults = new ArrayList<>();
    private List<RetryPhoneTypes> retryPhoneTypes = new ArrayList<>();

    public enum RetryResults {
        LA, AM, BUSY, DNC, XFER, XFER_LEG, NO_ANS, UNDIALED, SENT, RECEIVED, DNT, TOO_BIG, INTERNAL_ERROR,
        CARRIER_ERROR, CARRIER_TEMP_ERROR, SD, POSTPONED, ABANDONED, SKIPPED
    }

    public enum RetryPhoneTypes {
        FIRST_NUMBER, HOME_PHONE, WORK_PHONE, MOBILE_PHONE
    }

    public Integer getMaxAttempts() {
        return maxAttempts;
    }

    public void setMaxAttempts(Integer maxAttempts) {
        this.maxAttempts = maxAttempts;
    }

    public Integer getMinutesBetweenAttempts() {
        return minutesBetweenAttempts;
    }

    public void setMinutesBetweenAttempts(Integer minutesBetweenAttempts) {
        this.minutesBetweenAttempts = minutesBetweenAttempts;
    }

    public List<RetryResults> getRetryResults() {
        return retryResults;
    }

    public void setRetryResults(List<RetryResults> retryResults) {
        this.retryResults = retryResults;
    }

    public List<RetryPhoneTypes> getRetryPhoneTypes() {
        return retryPhoneTypes;
    }

    public void setRetryPhoneTypes(List<RetryPhoneTypes> retryPhoneTypes) {
        this.retryPhoneTypes = retryPhoneTypes;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("maxAttempts", maxAttempts)
            .append("minutesBetweenAttempts", minutesBetweenAttempts)
            .append("retryResults", retryResults)
            .append("retryPhoneTypes", retryPhoneTypes)
            .toString();
    }
}
