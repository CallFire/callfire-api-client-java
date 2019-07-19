package com.callfire.api.client.api.account.model;

import java.math.BigDecimal;
import java.util.Date;

import com.callfire.api.client.api.common.model.CallfireModel;

import lombok.Getter;

/**
 * Object represents Credits usage statistics
 */
@Getter
public class CreditsUsage extends CallfireModel {
    /**
     * Gets beginning of usage period
     *
     * @return beginning of usage period
     */
    private Date intervalBegin;

    /**
     * Gets end of usage period
     *
     * @return end of usage period
     */
    private Date intervalEnd;

    /**
     * Gets sum of calls duration rounded to nearest whole minute
     *
     * @return sum of calls duration rounded to nearest whole minute
     */
    private Integer callsDurationMinutes;

    /**
     * Gets number of texts sent
     *
     * @return number of texts sent
     */
    private Integer textsSent;

    /**
     * Gets total credits used by textsSent and callsDurationMinutes
     *
     * @return total credits used by textsSent and callsDurationMinutes
     */
    private BigDecimal creditsUsed;
}
