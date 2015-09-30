package com.callfire.api.client.api.callstexts.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Inbound/Outbound Text Action
 */
public class Text extends Action<TextRecord> {
    private String message;
    private TextResult finalTextResult;

    public enum TextResult {
        SENT, RECEIVED, DNT, TOO_BIG, INTERNAL_ERROR, CARRIER_ERROR, CARRIER_TEMP_ERROR, UNDIALED,
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public TextResult getFinalTextResult() {
        return finalTextResult;
    }

    public void setFinalTextResult(TextResult finalTextResult) {
        this.finalTextResult = finalTextResult;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("message", message)
            .append("finalTextResult", finalTextResult)
            .toString();
    }
}
