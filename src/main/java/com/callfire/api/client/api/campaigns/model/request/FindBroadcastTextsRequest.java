package com.callfire.api.client.api.campaigns.model.request;

/**
 * Find broadcast's texts request
 */
public class FindBroadcastTextsRequest extends FindBroadcastCallsTextsRequest {
    private FindBroadcastTextsRequest() {
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
    public static class Builder extends FindBroadcastCallsTextsBuilder<Builder, FindBroadcastTextsRequest> {
        private Builder() {
            super(new FindBroadcastTextsRequest());
        }
    }
}
