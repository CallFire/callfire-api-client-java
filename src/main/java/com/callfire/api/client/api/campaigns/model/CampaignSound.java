package com.callfire.api.client.api.campaigns.model;

import java.util.Date;

import com.callfire.api.client.api.common.model.CallfireModel;

import lombok.Getter;

@Getter
public class CampaignSound extends CallfireModel {
    private Long id;
    private String name;
    private Date created;
    private Integer lengthInSeconds;
    private Status status;
    private Boolean duplicate;

    public enum Status {
        UPLOAD,
        RECORDING,
        ACTIVE,
        FAILED,
        ARCHIVED
    }
}
