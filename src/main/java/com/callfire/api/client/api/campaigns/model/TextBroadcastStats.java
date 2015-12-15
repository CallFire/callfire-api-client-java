package com.callfire.api.client.api.campaigns.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class TextBroadcastStats extends BroadcastStats {
    private Integer sentCount;
    private Integer unsentCount;
    // property with typo
    @JsonProperty("recievedCount")
    private Integer receivedCount;
    private Integer doNotTextCount;
    private Integer tooBigCount;
    private Integer errorCount;

    public Integer getSentCount() {
        return sentCount;
    }

    public Integer getUnsentCount() {
        return unsentCount;
    }

    public Integer getReceivedCount() {
        return receivedCount;
    }

    public Integer getDoNotTextCount() {
        return doNotTextCount;
    }

    public Integer getTooBigCount() {
        return tooBigCount;
    }

    public Integer getErrorCount() {
        return errorCount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("sentCount", sentCount)
            .append("unsentCount", unsentCount)
            .append("receivedCount", receivedCount)
            .append("doNotTextCount", doNotTextCount)
            .append("tooBigCount", tooBigCount)
            .append("errorCount", errorCount)
            .toString();
    }
}
