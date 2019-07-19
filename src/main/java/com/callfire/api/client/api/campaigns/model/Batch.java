package com.callfire.api.client.api.campaigns.model;

import java.util.Date;

import com.callfire.api.client.api.common.model.CallfireModel;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Batch extends CallfireModel {
    @Setter private Long id;
    private String name;
    private Status status;
    private Long broadcastId;
    private Date created;
    private Integer size;
    private Integer remaining;
    @Setter private Boolean enabled;

    public enum Status {
        NEW,
        VALIDATING,
        ERRORS,
        SOURCE_ERROR,
        ACTIVE,
    }
}
