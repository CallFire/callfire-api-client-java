package com.callfire.api.client.api.account.model;

import java.math.BigDecimal;
import java.util.Date;

import com.callfire.api.client.api.common.model.CallfireModel;

import lombok.Getter;

/**
 * Object represents Plan usage statistics
 */
@Getter
public class BillingPlanUsage extends CallfireModel {
    /**
     * Gets start of usage period
     *
     * @return start of usage period
     */
    private Date intervalStart;

    /**
     * Gets end of usage period
     *
     * @return end of usage period
     */
    private Date intervalEnd;

    /**
     * Gets remaining plan credits rounded to nearest whole value.
     *
     * @return remaining plan credits rounded to nearest whole value.
     */
    private BigDecimal remainingPlanCredits;

    /**
     * Gets remaining pay as you go credits rounded to nearest whole value.
     *
     * @return remaining pay as you go credits rounded to nearest whole value.
     */
    private BigDecimal remainingPayAsYouGoCredits;

    /**
     * Gets total remaining credits (remainingPlanCredits + remainingPayAsYouGoCredits)
     *
     * @return total remaining credits (remainingPlanCredits + remainingPayAsYouGoCredits)
     */
    private BigDecimal totalRemainingCredits;
}
