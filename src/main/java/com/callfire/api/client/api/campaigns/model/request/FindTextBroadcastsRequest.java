package com.callfire.api.client.api.campaigns.model.request;

/**
 * Request object for searching text broadcasts
 */
public class FindTextBroadcastsRequest extends FindBroadcastsRequest {

    private FindTextBroadcastsRequest() {
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
    public static class Builder extends FindBroadcastsBuilder<Builder, FindTextBroadcastsRequest> {
        protected Builder() {
            super(new FindTextBroadcastsRequest());
        }
    }
}
