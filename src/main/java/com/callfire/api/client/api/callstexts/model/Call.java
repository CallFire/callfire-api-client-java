package com.callfire.api.client.api.callstexts.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Inbound/Outbound Call Action
 */
public class Call extends Action<CallRecord> {
    private CallResult finalCallResult;
    private Boolean agentCall;
    private List<Note> notes = new ArrayList<>();

    public enum CallResult {
        LA, AM, BUSY, DNC, XFER, NO_ANS, XFER_LEG, INTERNAL_ERROR, CARRIER_ERROR, CARRIER_TEMP_ERROR,
        UNDIALED, SD, POSTPONED, ABANDONED, SKIPPED,
    }

    public CallResult getFinalCallResult() {
        return finalCallResult;
    }

    public Boolean getAgentCall() {
        return agentCall;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("finalCallResult", finalCallResult)
            .append("agentCall", agentCall)
            .append("notes", notes)
            .toString();
    }
}
