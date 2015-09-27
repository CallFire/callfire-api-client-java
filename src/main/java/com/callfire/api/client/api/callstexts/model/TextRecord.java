package com.callfire.api.client.api.callstexts.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class TextRecord extends ActionRecord {
    private String message;
    private TextResult textResult;

    public enum TextResult {
        SENT, RECEIVED, DNT, TOO_BIG, INTERNAL_ERROR, CARRIER_ERROR, CARRIER_TEMP_ERROR, UNDIALED,
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public TextResult getTextResult() {
        return textResult;
    }

    public void setTextResult(TextResult textResult) {
        this.textResult = textResult;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("message", message)
            .append("textResult", textResult)
            .toString();
    }
}
