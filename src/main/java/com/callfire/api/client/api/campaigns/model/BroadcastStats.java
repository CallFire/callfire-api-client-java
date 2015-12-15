package com.callfire.api.client.api.campaigns.model;

import com.callfire.api.client.api.common.model.CallfireModel;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;

/**
 * Statistics for a Broadcast (Call or Text).
 */
public abstract class BroadcastStats extends CallfireModel {
    private Integer totalOutboundCount;
    private Integer remainingOutboundCount;
    private BigDecimal billedAmount;

    public Integer getTotalOutboundCount() {
        return totalOutboundCount;
    }

    public Integer getRemainingOutboundCount() {
        return remainingOutboundCount;
    }

    public BigDecimal getBilledAmount() {
        return billedAmount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("totalOutboundCount", totalOutboundCount)
            .append("remainingOutboundCount", remainingOutboundCount)
            .append("billedAmount", billedAmount)
            .toString();
    }
}

