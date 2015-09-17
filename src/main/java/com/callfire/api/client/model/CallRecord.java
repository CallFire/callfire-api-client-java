package com.callfire.api.client.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

public class CallRecord extends BaseModel {
    private Long id;
    private Float billedAmount;
    private Long created;
    private Long finishTime;
    private CallResult callResult;
    private List<Note> notes = new ArrayList<Note>();

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

    public void setCallResult(CallResult callResult) {
        this.callResult = callResult;
    }

    public Long getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Long finishTime) {
        this.finishTime = finishTime;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public Float getBilledAmount() {
        return billedAmount;
    }

    public void setBilledAmount(Float billedAmount) {
        this.billedAmount = billedAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("id", id)
            .append("billedAmount", billedAmount)
            .append("created", created)
            .append("finishTime", finishTime)
            .append("callResult", callResult)
            .append("notes", notes)
            .toString();
    }
}
