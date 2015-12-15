package com.callfire.api.client.api.campaigns.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Statistics for a CallBroadcast.
 */
public class CallBroadcastStats extends BroadcastStats {
    // Usage Stats
    private Integer callsAttempted;
    private Integer callsPlaced;
    private Integer callsDuration;
    private Integer billedDuration;
    private Integer responseRatePercent; // (100 * CallsLiveAnswer / CallsPlaced)

    // Action Stats
    private Integer callsRemaining;
    private Integer callsAwaitingRedial;
    private Integer callsLiveAnswer;

    // Result Stats
    private Integer totalCount;
    private Integer answeringMachineCount;
    private Integer busyCount;
    private Integer dialedCount;
    private Integer doNotCallCount;
    private Integer errorCount;
    private Integer liveCount;
    private Integer miscCount;
    private Integer noAnswerCount;
    private Integer transferCount;

    public Integer getCallsAttempted() {
        return callsAttempted;
    }

    public Integer getCallsPlaced() {
        return callsPlaced;
    }

    public Integer getCallsDuration() {
        return callsDuration;
    }

    public Integer getBilledDuration() {
        return billedDuration;
    }

    public Integer getResponseRatePercent() {
        return responseRatePercent;
    }

    public Integer getCallsRemaining() {
        return callsRemaining;
    }

    public Integer getCallsAwaitingRedial() {
        return callsAwaitingRedial;
    }

    public Integer getCallsLiveAnswer() {
        return callsLiveAnswer;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public Integer getAnsweringMachineCount() {
        return answeringMachineCount;
    }

    public Integer getBusyCount() {
        return busyCount;
    }

    public Integer getDialedCount() {
        return dialedCount;
    }

    public Integer getDoNotCallCount() {
        return doNotCallCount;
    }

    public Integer getErrorCount() {
        return errorCount;
    }

    public Integer getLiveCount() {
        return liveCount;
    }

    public Integer getMiscCount() {
        return miscCount;
    }

    public Integer getNoAnswerCount() {
        return noAnswerCount;
    }

    public Integer getTransferCount() {
        return transferCount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("callsAttempted", callsAttempted)
            .append("callsPlaced", callsPlaced)
            .append("callsDuration", callsDuration)
            .append("billedDuration", billedDuration)
            .append("responseRatePercent", responseRatePercent)
            .append("callsRemaining", callsRemaining)
            .append("callsAwaitingRedial", callsAwaitingRedial)
            .append("callsLiveAnswer", callsLiveAnswer)
            .append("totalCount", totalCount)
            .append("answeringMachineCount", answeringMachineCount)
            .append("busyCount", busyCount)
            .append("dialedCount", dialedCount)
            .append("doNotCallCount", doNotCallCount)
            .append("errorCount", errorCount)
            .append("liveCount", liveCount)
            .append("miscCount", miscCount)
            .append("noAnswerCount", noAnswerCount)
            .append("transferCount", transferCount)
            .toString();
    }
}

