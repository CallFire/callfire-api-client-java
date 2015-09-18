package com.callfire.api.client.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Text extends Action {
    private State state;
    private String message;
    private FinalTextResult finalTextResult;
    private List<TextRecord> records = new ArrayList<>();
    private Map<String, String> attributes = new HashMap<>();

    public enum State {
        READY, SELECTED, CALLBACK, FINISHED, DISABLED, DNC, DUP, INVALID, TIMEOUT, PERIOD_LIMIT,
    }

    public enum FinalTextResult {
        SENT, RECEIVED, DNT, TOO_BIG, INTERNAL_ERROR, CARRIER_ERROR, CARRIER_TEMP_ERROR, UNDIALED,
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
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
            .append("state", state)
            .append("message", message)
            .append("finalTextResult", finalTextResult)
            .append("records", records)
            .append("attributes", attributes)
            .toString();
    }
}
