package com.callfire.api.client.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class CampaignSound extends BaseModel {
    private Long id;
    private String name;
    private Long created;
    private Integer lengthInSeconds;
    private StatusEnum status;

    public enum StatusEnum {
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

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public Integer getLengthInSeconds() {
        return lengthInSeconds;
    }

    public void setLengthInSeconds(Integer lengthInSeconds) {
        this.lengthInSeconds = lengthInSeconds;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
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
