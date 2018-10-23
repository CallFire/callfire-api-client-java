package com.callfire.api.client.api.campaigns.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.callfire.api.client.api.callstexts.model.Media;

public class TextBroadcast extends Broadcast {
    private String message;
    private BigMessageStrategy bigMessageStrategy;
    private List<TextRecipient> recipients = new ArrayList<>();
    private List<Media> media = new ArrayList<>();

    public enum BigMessageStrategy {
        SEND_MULTIPLE, DO_NOT_SEND, TRIM, MMS
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

    public void setBigMessageStrategy(BigMessageStrategy bigMessageStrategy) {
        this.bigMessageStrategy = bigMessageStrategy;
    }

    public List<TextRecipient> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<TextRecipient> recipients) {
        this.recipients = recipients;
    }

    public List<Media> getMedia() {
        return media;
    }

    public void setMedia(List<Media> media) {
        this.media = media;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("message", message)
            .append("bigMessageStrategy", bigMessageStrategy)
            .append("recipients", recipients)
            .append("media", media)
            .toString();
    }
}
