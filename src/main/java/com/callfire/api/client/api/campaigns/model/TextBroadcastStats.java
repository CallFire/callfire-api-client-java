package com.callfire.api.client.api.campaigns.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
public class TextBroadcastStats extends BroadcastStats {
    private Integer sentCount;
    private Integer unsentCount;
    // property with typo
    @JsonProperty("recievedCount") private Integer receivedCount;
    private Integer doNotTextCount;
    private Integer tooBigCount;
    private Integer errorCount;
}
