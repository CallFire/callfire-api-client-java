package com.callfire.api.client.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Call extends Action {
    private State state;
    private FinalCallResult finalCallResult;
    private Boolean agentCall;
    private List<Note> notes = new ArrayList<>();
    private Map<String, String> attributes = new HashMap<>();
    private List<CallRecord> records = new ArrayList<>();

    public enum State {
        READY, SELECTED, CALLBACK, FINISHED, DISABLED, DNC, DUP, INVALID, TIMEOUT, PERIOD_LIMIT,
    }

    public enum FinalCallResult {
        LA, AM, BUSY, DNC, XFER, NO_ANS, XFER_LEG, INTERNAL_ERROR, CARRIER_ERROR, CARRIER_TEMP_ERROR,
        UNDIALED, SD, POSTPONED, ABANDONED, SKIPPED,
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
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
            .appendSuper(super.toString())
            .append("state", state)
            .append("finalCallResult", finalCallResult)
            .append("agentCall", agentCall)
            .append("notes", notes)
            .append("attributes", attributes)
            .append("records", records)
            .toString();
    }
}
