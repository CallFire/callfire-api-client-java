package com.callfire.api.client.api.campaigns.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class RetriableBroadcast extends Broadcast {
    private RetryConfig retryConfig;
}
