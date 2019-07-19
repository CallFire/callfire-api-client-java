package com.callfire.api.client.api.campaigns.model.request;

import static lombok.AccessLevel.PRIVATE;

import lombok.NoArgsConstructor;

/**
 * Request object for searching call broadcasts
 */
@NoArgsConstructor(access = PRIVATE)
public class FindCallBroadcastsRequest extends FindBroadcastsRequest {

    /**
     * Create request builder
     *
     * @return request build
     */
    public static Builder create() {
        return new Builder();
    }

    /**
     * Builder class for request
     */
    public static class Builder extends FindBroadcastsBuilder<Builder, FindCallBroadcastsRequest> {
        protected Builder() {
            super(new FindCallBroadcastsRequest());
        }
    }
}
