package com.callfire.api.client.api.callstexts.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

/**
 * Inbound/Outbound Call Action
 */
@Getter
public class Call extends Action<CallRecord> {
    private CallResult finalCallResult;
    private Boolean agentCall;
    private List<Note> notes = new ArrayList<>();

    public enum CallResult {
        LA,
        AM,
        BUSY,
        DNC,
        XFER,
        NO_ANS,
        XFER_LEG,
        INTERNAL_ERROR,
        CARRIER_ERROR,
        CARRIER_TEMP_ERROR,
        UNDIALED,
        SD,
        POSTPONED,
        ABANDONED,
        SKIPPED,
    }
}
