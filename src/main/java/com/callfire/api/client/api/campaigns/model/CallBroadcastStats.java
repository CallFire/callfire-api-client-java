package com.callfire.api.client.api.campaigns.model;

import lombok.Getter;

/**
 * Statistics for a CallBroadcast.
 */
@Getter
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
}
