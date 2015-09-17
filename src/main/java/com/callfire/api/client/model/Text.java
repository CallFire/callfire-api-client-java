package com.callfire.api.client.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Text extends BaseModel {
    private Long id;
    private String fromNumber;
    private String toNumber;
    private State state;
    private Long broadcastId;
    private Long batchId;
    private Contact contact;
    private Boolean inbound;
    private Long created;
    private Long modified;
    private String message;
    private FinalTextResult finalTextResult;
    private List<String> labels = new ArrayList<>();
    private List<TextRecord> records = new ArrayList<>();
    private Map<String, String> attributes = new HashMap<>();

    public enum State {
        READY, SELECTED, CALLBACK, FINISHED, DISABLED, DNC, DUP, INVALID, TIMEOUT, PERIOD_LIMIT,
    }

    public enum FinalTextResult {
        SENT, RECEIVED, DNT, TOO_BIG, INTERNAL_ERROR, CARRIER_ERROR, CARRIER_TEMP_ERROR, UNDIALED,
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
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

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Boolean getInbound() {
        return inbound;
    }

    public void setInbound(Boolean inbound) {
        this.inbound = inbound;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public Long getModified() {
        return modified;
    }

    public void setModified(Long modified) {
        this.modified = modified;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public FinalTextResult getFinalTextResult() {
        return finalTextResult;
    }

    public void setFinalTextResult(FinalTextResult finalTextResult) {
        this.finalTextResult = finalTextResult;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public List<TextRecord> getRecords() {
        return records;
    }

    public void setRecords(List<TextRecord> records) {
        this.records = records;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("id", id)
            .append("fromNumber", fromNumber)
            .append("toNumber", toNumber)
            .append("state", state)
            .append("broadcastId", broadcastId)
            .append("batchId", batchId)
            .append("contact", contact)
            .append("inbound", inbound)
            .append("created", created)
            .append("modified", modified)
            .append("message", message)
            .append("finalTextResult", finalTextResult)
            .append("labels", labels)
            .append("records", records)
            .append("attributes", attributes)
            .toString();
    }
}
