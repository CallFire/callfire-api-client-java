package com.callfire.api.client.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Call extends BaseModel {
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
    private FinalCallResult finalCallResult;
    private Boolean agentCall;
    private List<Note> notes = new ArrayList<Note>();
    private List<String> labels = new ArrayList<String>();
    private Map<String, String> attributes = new HashMap<>();
    private List<CallRecord> records = new ArrayList<CallRecord>();

    public enum State {
        READY, SELECTED, CALLBACK, FINISHED, DISABLED, DNC, DUP, INVALID, TIMEOUT, PERIOD_LIMIT,
    }

    public enum FinalCallResult {
        LA, AM, BUSY, DNC, XFER, NO_ANS, XFER_LEG, INTERNAL_ERROR, CARRIER_ERROR, CARRIER_TEMP_ERROR,
        UNDIALED, SD, POSTPONED, ABANDONED, SKIPPED,
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

    public FinalCallResult getFinalCallResult() {
        return finalCallResult;
    }

    public void setFinalCallResult(FinalCallResult finalCallResult) {
        this.finalCallResult = finalCallResult;
    }

    public Boolean getAgentCall() {
        return agentCall;
    }

    public void setAgentCall(Boolean agentCall) {
        this.agentCall = agentCall;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    public List<CallRecord> getRecords() {
        return records;
    }

    public void setRecords(List<CallRecord> records) {
        this.records = records;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
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
            .append("finalCallResult", finalCallResult)
            .append("agentCall", agentCall)
            .append("notes", notes)
            .append("labels", labels)
            .append("attributes", attributes)
            .append("records", records)
            .toString();
    }
}
