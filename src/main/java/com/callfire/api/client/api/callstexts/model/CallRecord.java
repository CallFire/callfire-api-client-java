package com.callfire.api.client.api.callstexts.model;

import com.callfire.api.client.api.campaigns.model.CallRecording;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

public class CallRecord extends ActionRecord {
    private CallResult result;
    private List<Note> notes = new ArrayList<>();
    private List<CallRecording> recordings = new ArrayList<>();
    private List<QuestionResponse> questionResponses = new ArrayList<>();

    public enum CallResult {
        LA, AM, BUSY, DNC, XFER, NO_ANS, XFER_LEG, INTERNAL_ERROR, CARRIER_ERROR, CARRIER_TEMP_ERROR, UNDIALED, SD,
        POSTPONED, ABANDONED, SKIPPED,
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public CallResult getResult() {
        return result;
    }

    public List<CallRecording> getRecordings() {
        return recordings;
    }

    public List<QuestionResponse> getQuestionResponses() {
        return questionResponses;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("result", result)
            .append("notes", notes)
            .append("recordings", recordings)
            .append("questionResponses", questionResponses)
            .toString();
    }
}
