package com.callfire.api.client.api.campaigns.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

public class TextBroadcast extends Broadcast {
    private String message;
    private BigMessageStrategy bigMessageStrategy;
    private List<TextRecipient> recipients = new ArrayList<>();

    public enum BigMessageStrategy {
        SEND_MULTIPLE, DO_NOT_SEND, TRIM
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BigMessageStrategy getBigMessageStrategy() {
        return bigMessageStrategy;
    }

    public void setBigMessageStrategy(
        BigMessageStrategy bigMessageStrategy) {
        this.bigMessageStrategy = bigMessageStrategy;
    }

    public List<TextRecipient> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<TextRecipient> recipients) {
        this.recipients = recipients;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("message", message)
            .append("bigMessageStrategy", bigMessageStrategy)
            .append("recipients", recipients)
            .toString();
    }
}
