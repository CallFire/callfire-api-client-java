package com.callfire.api.client.api.campaigns.model;

import com.callfire.api.client.api.common.model.LocalDate;
import com.callfire.api.client.api.common.model.WeeklySchedule;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Campaign Schedule
 */
public class Schedule extends WeeklySchedule {
    private Long id;
    private Long campaignId;
    private LocalDate startDate;
    private LocalDate stopDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Long campaignId) {
        this.campaignId = campaignId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getStopDate() {
        return stopDate;
    }

    public void setStopDate(LocalDate stopDate) {
        this.stopDate = stopDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("id", id)
            .append("campaignId", campaignId)
            .append("startDate", startDate)
            .append("stopDate", stopDate)
            .toString();
    }
}
