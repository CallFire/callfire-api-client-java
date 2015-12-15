package com.callfire.api.client.api.campaigns.model.request;

/**
 * Request object for searching voice broadcasts
 *
 * @deprecated use com.callfire.api.client.api.campaigns.model.request.FindCallBroadcastsRequest
 */
@Deprecated
public class FindVoiceBroadcastsRequest extends FindBroadcastsRequest {

    private FindVoiceBroadcastsRequest() {
    }

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
