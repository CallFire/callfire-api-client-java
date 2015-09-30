package com.callfire.api.client.api.campaigns.model;

import com.callfire.api.client.api.common.model.CallfireModel;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

public class CampaignSound extends CallfireModel {
    private Long id;
    private String name;
    private Date created;
    private Integer lengthInSeconds;
    private Status status;

    public enum Status {
        UPLOAD, RECORDING, ACTIVE, FAILED, ARCHIVED,
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreated() {
        return created;
    }

    public Integer getLengthInSeconds() {
        return lengthInSeconds;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("id", id)
            .append("name", name)
            .append("created", created)
            .append("lengthInSeconds", lengthInSeconds)
            .append("status", status)
            .toString();
    }
}
