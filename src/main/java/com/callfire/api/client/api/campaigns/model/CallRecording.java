package com.callfire.api.client.api.campaigns.model;

import com.callfire.api.client.api.common.model.CallfireModel;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

/**
 * CallRecording
 */
public class CallRecording extends CallfireModel {
    private Long id;
    private Long callId;
    private Long campaignId;
    private String name;
    private Date created;
    private Long lengthInBytes;
    private Integer lengthInSeconds;
    private String hash;
    private String mp3Url;
    private CallRecordingState state;

    public enum CallRecordingState {
        RECORDING, READY, ERROR
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCallId() {
        return callId;
    }

    public void setCallId(Long callId) {
        this.callId = callId;
    }

    public Long getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Long campaignId) {
        this.campaignId = campaignId;
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

    public void setCreated(Date created) {
        this.created = created;
    }

    public Long getLengthInBytes() {
        return lengthInBytes;
    }

    public void setLengthInBytes(Long lengthInBytes) {
        this.lengthInBytes = lengthInBytes;
    }

    public Integer getLengthInSeconds() {
        return lengthInSeconds;
    }

    public void setLengthInSeconds(Integer lengthInSeconds) {
        this.lengthInSeconds = lengthInSeconds;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getMp3Url() {
        return mp3Url;
    }

    public void setMp3Url(String mp3Url) {
        this.mp3Url = mp3Url;
    }

    public CallRecordingState getState() {
        return state;
    }

    public void setState(CallRecordingState state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("id", id)
            .append("callId", callId)
            .append("campaignId", campaignId)
            .append("name", name)
            .append("created", created)
            .append("lengthInBytes", lengthInBytes)
            .append("lengthInSeconds", lengthInSeconds)
            .append("hash", hash)
            .append("mp3Url", mp3Url)
            .append("state", state)
            .toString();
    }
}
