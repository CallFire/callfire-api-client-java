package com.callfire.api.client.api.campaigns.model.request;

/**
 * Find broadcast's calls request
 */
public class FindBroadcastCallsRequest extends FindBroadcastCallsTextsRequest {
    private FindBroadcastCallsRequest() {
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
     * Builder class
     */
    public static class Builder extends FindBroadcastCallsTextsBuilder<Builder, FindBroadcastCallsRequest> {
        private Builder() {
            super(new FindBroadcastCallsRequest());
        }
    }
}
