package com.callfire.api.client.api.campaigns.model.request;

/**
 * Request object for searching ccc broadcasts
 */
public class FindCccBroadcastsRequest extends FindBroadcastsRequest {

    private FindCccBroadcastsRequest() {
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
    public static class Builder extends FindBroadcastsBuilder<Builder, FindCccBroadcastsRequest> {
        protected Builder() {
            super(new FindCccBroadcastsRequest());
        }
    }
}
