package com.callfire.api.client.api.callstexts.model;

import java.util.ArrayList;
import java.util.List;

import com.callfire.api.client.api.campaigns.model.CallRecording;

import lombok.Getter;

@Getter
public class CallRecord extends ActionRecord {
    private CallResult result;
    private Long originateTime;
    private Long answerTime;
    private Long duration;
    private List<Note> notes = new ArrayList<>();
    private List<CallRecording> recordings = new ArrayList<>();
    private List<QuestionResponse> questionResponses = new ArrayList<>();

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
        SKIPPED
    }
}
