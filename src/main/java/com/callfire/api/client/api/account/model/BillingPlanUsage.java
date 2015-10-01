package com.callfire.api.client.api.account.model;

import com.callfire.api.client.api.common.model.CallfireModel;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Object represents Plan usage statistics
 */
public class BillingPlanUsage extends CallfireModel {
    private Date intervalStart;
    private Date intervalEnd;
    private BigDecimal remainingPlanCredits;
    private BigDecimal remainingPayAsYouGoCredits;
    private BigDecimal totalRemainingCredits;

    /**
     * Gets start of usage period
     *
     * @return start of usage period
     */
    public Date getIntervalStart() {
        return intervalStart;
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
     * Gets remaining plan credits rounded to nearest whole value.
     *
     * @return remaining plan credits rounded to nearest whole value.
     */
    public BigDecimal getRemainingPlanCredits() {
        return remainingPlanCredits;
    }

    /**
     * Gets remaining pay as you go credits rounded to nearest whole value.
     *
     * @return remaining pay as you go credits rounded to nearest whole value.
     */
    public BigDecimal getRemainingPayAsYouGoCredits() {
        return remainingPayAsYouGoCredits;
    }

    /**
     * Gets total remaining credits (remainingPlanCredits + remainingPayAsYouGoCredits)
     *
     * @return total remaining credits (remainingPlanCredits + remainingPayAsYouGoCredits)
     */
    public BigDecimal getTotalRemainingCredits() {
        return totalRemainingCredits;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("intervalStart", intervalStart)
            .append("intervalEnd", intervalEnd)
            .append("remainingPlanCredits", remainingPlanCredits)
            .append("remainingPayAsYouGoCredits", remainingPayAsYouGoCredits)
            .append("totalRemainingCredits", totalRemainingCredits)
            .toString();
    }
}
