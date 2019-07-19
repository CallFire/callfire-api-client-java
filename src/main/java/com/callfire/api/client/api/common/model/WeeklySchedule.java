package com.callfire.api.client.api.common.model;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WeeklySchedule extends CallfireModel {
    private LocalTime startTimeOfDay;
    private LocalTime stopTimeOfDay;
    private Set<DayOfWeek> daysOfWeek = new HashSet<>();
    private String timeZone;
}
