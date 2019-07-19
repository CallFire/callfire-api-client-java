package com.callfire.api.client.api.callstexts.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Inbound/Outbound Text Action
 */
@Getter
@NoArgsConstructor
public class Text extends Action<TextRecord> {
    private String message;
    private TextResult finalTextResult;
    private List<Media> media = new ArrayList<>();

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
