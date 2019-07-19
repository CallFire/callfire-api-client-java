package com.callfire.api.client.api.campaigns.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.callfire.api.client.api.common.model.CallfireModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Broadcast extends CallfireModel {
    private Long id;
    private String name;
    private Status status;
    private Date lastModified;
    private String fromNumber;
    private LocalTimeRestriction localTimeRestriction;
    private List<Schedule> schedules;
    private Integer maxActive;
    private List<String> labels = new ArrayList<>();
    private Boolean resumeNextDay;

    public enum Status {
        TEST,
        SETUP,
        START_PENDING, // waiting for call batch population
        RUNNING,
        SCHEDULED, // scheduled
        STOPPED,
        SUSPENDED,
        FINISHED,
        ARCHIVED,
        VALIDATING_EMAIL,
        VALIDATING_START,
        BLOCKED_SUSPICIOUS, // campaign with suspicious word is waiting for approve or decline
        DECLINED,
        APPROVED,
        UNKNOWN
    }
}
