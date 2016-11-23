package com.callfire.api.client.api.account.model;

import com.callfire.api.client.api.common.model.CallfireModel;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Object represents Credits usage statistics
 */
public class CreditsUsage extends CallfireModel {
    private Date intervalBegin;
    private Date intervalEnd;
    private Integer callsDurationMinutes;
    private Integer textsSent;
    private BigDecimal creditsUsed;

    /**
     * Gets beginning of usage period
     *
     * @return beginning of usage period
     */
    public Date getIntervalBegin() {
        return intervalBegin;
    }

    /**
     * Gets end of usage period
     *
     * @return end of usage period
     */
    public Date getIntervalEnd() {
        return intervalEnd;
    }

    /**
     * Gets sum of calls duration rounded to nearest whole minute
     *
     * @return sum of calls duration rounded to nearest whole minute
     */
    public Integer getCallsDurationMinutes() {
        return callsDurationMinutes;
    }

    /**
     * Gets number of texts sent
     *
     * @return number of texts sent
     */
    public Integer getTextsSent() {
        return textsSent;
    }

    /**
     * Gets total credits used by textsSent and callsDurationMinutes
     *
     * @return total credits used by textsSent and callsDurationMinutes
     */
    public BigDecimal getCreditsUsed() {
        return creditsUsed;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("intervalBegin", intervalBegin)
            .append("intervalEnd", intervalEnd)
            .append("callsDurationMinutes", callsDurationMinutes)
            .append("textsSent", textsSent)
            .append("creditsUsed", creditsUsed)
            .toString();
    }
}
