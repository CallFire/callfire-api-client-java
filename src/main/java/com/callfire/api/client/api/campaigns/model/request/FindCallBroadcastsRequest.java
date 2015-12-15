package com.callfire.api.client.api.campaigns.model.request;

/**
 * Request object for searching call broadcasts
 */
public class FindCallBroadcastsRequest extends FindBroadcastsRequest {

    private FindCallBroadcastsRequest() {
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
    public static class Builder extends FindBroadcastsBuilder<Builder, FindCallBroadcastsRequest> {
        protected Builder() {
            super(new FindCallBroadcastsRequest());
        }
    }
}
