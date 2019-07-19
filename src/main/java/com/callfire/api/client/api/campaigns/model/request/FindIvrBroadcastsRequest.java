package com.callfire.api.client.api.campaigns.model.request;

import static lombok.AccessLevel.PRIVATE;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Request object for searching ivr broadcasts
 */
@Deprecated
@Getter
@NoArgsConstructor(access = PRIVATE)
public class FindIvrBroadcastsRequest extends FindBroadcastsRequest {
    private Boolean inbound;

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
    public static class Builder extends FindBroadcastsBuilder<Builder, FindIvrBroadcastsRequest> {
        protected Builder() {
            super(new FindIvrBroadcastsRequest());
        }

        public Builder inbound(Boolean inbound) {
            request.inbound = inbound;
            return this;
        }
    }
}
