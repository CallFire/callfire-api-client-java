package com.callfire.api.client.api.callstexts.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

public class CallRecord extends ActionRecord {
    private CallResult callResult;
    private List<Note> notes = new ArrayList<>();

    public enum CallResult {
        LA, AM, BUSY, DNC, XFER, NO_ANS, XFER_LEG, INTERNAL_ERROR, CARRIER_ERROR, CARRIER_TEMP_ERROR, UNDIALED, SD, POSTPONED, ABANDONED, SKIPPED,
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public CallResult getCallResult() {
        return callResult;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("callResult", callResult)
            .append("notes", notes)
            .toString();
    }
}
