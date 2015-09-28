package com.callfire.api.client.api.campaigns.model.request;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Request object for searching ivr broadcasts
 */
public class FindIvrBroadcastsRequest extends FindBroadcastsRequest {
    private Boolean inbound;

    private FindIvrBroadcastsRequest() {
    }

    /**
     * Create request builder
     *
     * @return request build
     */
    public static Builder create() {
        return new Builder();
    }

    public Boolean getInbound() {
        return inbound;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("inbound", inbound)
            .toString();
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
