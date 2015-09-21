package com.callfire.api.client.model;

import com.callfire.api.client.api.common.model.CallfireModel;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

/**
 * Object represents Plan usage statistics
 */
public class BillingPlanUsage extends CallfireModel {
    //@JsonFormat(pattern = DATE_FORMAT_PATTERN)
    private Date intervalStart;
   // @JsonFormat(pattern = DATE_FORMAT_PATTERN)
    private Date intervalEnd;
    private Double remainingPlanCredits;
    private Double remainingPayAsYouGoCredits;
    private Double totalRemainingCredits;

    /**
     * Gets start of usage period
     *
     * @return start of usage period
     */
    public Date getIntervalStart() {
        return intervalStart;
    }

    /**
     * Sets start of usage period
     *
     * @param intervalStart start of usage period
     */
    public void setIntervalStart(Date intervalStart) {
        this.intervalStart = intervalStart;
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
     * Sets end of usage period
     *
     * @param intervalEnd end of usage period
     */
    public void setIntervalEnd(Date intervalEnd) {
        this.intervalEnd = intervalEnd;
    }

    /**
     * Gets remaining plan credits rounded to nearest whole value.
     *
     * @return remaining plan credits rounded to nearest whole value.
     */
    public Double getRemainingPlanCredits() {
        return remainingPlanCredits;
    }

    /**
     * Sets remaining plan credits rounded to nearest whole value.
     *
     * @param remainingPlanCredits remaining plan credits rounded to nearest whole value.
     */
    public void setRemainingPlanCredits(Double remainingPlanCredits) {
        this.remainingPlanCredits = remainingPlanCredits;
    }

    /**
     * Gets remaining pay as you go credits rounded to nearest whole value.
     *
     * @return remaining pay as you go credits rounded to nearest whole value.
     */
    public Double getRemainingPayAsYouGoCredits() {
        return remainingPayAsYouGoCredits;
    }

    /**
     * Sets remaining pay as you go credits rounded to nearest whole value.
     *
     * @param remainingPayAsYouGoCredits remaining pay as you go credits rounded to nearest whole value.
     */
    public void setRemainingPayAsYouGoCredits(Double remainingPayAsYouGoCredits) {
        this.remainingPayAsYouGoCredits = remainingPayAsYouGoCredits;
    }

    /**
     * Gets total remaining credits (remainingPlanCredits + remainingPayAsYouGoCredits)
     *
     * @return total remaining credits (remainingPlanCredits + remainingPayAsYouGoCredits)
     */
    public Double getTotalRemainingCredits() {
        return totalRemainingCredits;
    }

    /**
     * Sets total remaining credits (remainingPlanCredits + remainingPayAsYouGoCredits)
     *
     * @param totalRemainingCredits total remaining credits (remainingPlanCredits + remainingPayAsYouGoCredits)
     */
    public void setTotalRemainingCredits(Double totalRemainingCredits) {
        this.totalRemainingCredits = totalRemainingCredits;
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
