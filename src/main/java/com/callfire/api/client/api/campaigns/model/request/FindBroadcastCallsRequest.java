package com.callfire.api.client.api.campaigns.model.request;

import static lombok.AccessLevel.PRIVATE;

import lombok.NoArgsConstructor;

/**
 * Find broadcast's calls request
 */
@NoArgsConstructor(access = PRIVATE)
public class FindBroadcastCallsRequest extends FindBroadcastCallsTextsRequest {

    /**
     * Create request builder
     *
     * @return request build
     */
    public static Builder create() {
        return new Builder();
    }

    /**
     * Builder class
     */
    public static class Builder extends FindBroadcastCallsTextsBuilder<Builder, FindBroadcastCallsRequest> {
        private Builder() {
            super(new FindBroadcastCallsRequest());
        }
    }
}
