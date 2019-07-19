package com.callfire.api.client.api.campaigns.model;

import com.callfire.api.client.api.common.model.LocalDate;
import com.callfire.api.client.api.common.model.WeeklySchedule;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Campaign Schedule
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Schedule extends WeeklySchedule {
    private Long id;
    private Long campaignId;
    private LocalDate startDate;
    private LocalDate stopDate;
}
