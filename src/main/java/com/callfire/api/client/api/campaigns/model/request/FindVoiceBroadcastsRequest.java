package com.callfire.api.client.api.campaigns.model.request;

import static lombok.AccessLevel.PRIVATE;

import lombok.NoArgsConstructor;

/**
 * Request object for searching voice broadcasts
 *
 * @deprecated use com.callfire.api.client.api.campaigns.model.request.FindCallBroadcastsRequest
 */
@Deprecated
@NoArgsConstructor(access = PRIVATE)
public class FindVoiceBroadcastsRequest extends FindBroadcastsRequest {

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
    public static class Builder extends FindBroadcastsBuilder<Builder, FindVoiceBroadcastsRequest> {
        protected Builder() {
            super(new FindVoiceBroadcastsRequest());
        }
    }
}
