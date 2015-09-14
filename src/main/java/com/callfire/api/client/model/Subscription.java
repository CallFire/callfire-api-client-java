package com.callfire.api.client.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Subscription extends BaseModel {
    private Long id;
    private Boolean enabled;
    private String endpoint;
    private NotificationFormat notificationFormat;
    private TriggerEvent triggerEvent;
    private Long broadcastId;
    private Long batchId;
    private String fromNumber;
    private String toNumber;

    public enum NotificationFormat {
        JSON, XML, SOAP, EMAIL, LEGACY,
    }

    public enum TriggerEvent {
        UNDEFINED_EVENT, INBOUND_CALL_FINISHED, INBOUND_TEXT_FINISHED, OUTBOUND_CALL_FINISHED,
        OUTBOUND_TEXT_FINISHED, CAMPAIGN_STARTED, CAMPAIGN_STOPPED, CAMPAIGN_FINISHED,
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public NotificationFormat getNotificationFormat() {
        return notificationFormat;
    }

    public void setNotificationFormat(
        NotificationFormat notificationFormat) {
        this.notificationFormat = notificationFormat;
    }

    public TriggerEvent getTriggerEvent() {
        return triggerEvent;
    }

    public void setTriggerEvent(TriggerEvent triggerEvent) {
        this.triggerEvent = triggerEvent;
    }

    public Long getBroadcastId() {
        return broadcastId;
    }

    public void setBroadcastId(Long broadcastId) {
        this.broadcastId = broadcastId;
    }

    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    public String getFromNumber() {
        return fromNumber;
    }

    public void setFromNumber(String fromNumber) {
        this.fromNumber = fromNumber;
    }

    public String getToNumber() {
        return toNumber;
    }

    public void setToNumber(String toNumber) {
        this.toNumber = toNumber;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("id", id)
            .append("enabled", enabled)
            .append("endpoint", endpoint)
            .append("notificationFormat", notificationFormat)
            .append("triggerEvent", triggerEvent)
            .append("broadcastId", broadcastId)
            .append("batchId", batchId)
            .append("fromNumber", fromNumber)
            .append("toNumber", toNumber)
            .toString();
    }
}
