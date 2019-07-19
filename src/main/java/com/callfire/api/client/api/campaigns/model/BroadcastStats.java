package com.callfire.api.client.api.campaigns.model;

import java.math.BigDecimal;

import com.callfire.api.client.api.common.model.CallfireModel;

import lombok.Getter;

/**
 * Statistics for a Broadcast (Call or Text).
 */
@Getter
public abstract class BroadcastStats extends CallfireModel {
    private Integer totalOutboundCount;
    private Integer remainingOutboundCount;
    private BigDecimal billedAmount;
}

