package com.callfire.api.client.api.callstexts.model;

import lombok.Getter;

@Getter
public class TextRecord extends ActionRecord {
    private String message;
    private TextResult textResult;

    public enum TextResult {
        SENT,
        RECEIVED,
        DNT,
        TOO_BIG,
        INTERNAL_ERROR,
        CARRIER_ERROR,
        CARRIER_TEMP_ERROR,
        UNDIALED,
    }
}
