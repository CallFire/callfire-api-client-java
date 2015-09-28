package com.callfire.api.client.api.campaigns.model.request;

import com.callfire.api.client.api.common.model.request.FindRequest;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Request object for GET /campaigns/sounds which incapsulates
 * different query pairs
 */
public class FindSoundsRequest extends FindRequest {
    private String filter;

    private FindSoundsRequest() {
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
     * Get search field for fileName/name
     *
     * @return search field for fileName/name
     */
    public String getFilter() {
        return filter;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("filter", filter)
            .toString();
    }

    /**
     * Builder class for request
     */
    public static class Builder extends FindRequestBuilder<Builder, FindSoundsRequest> {
        private Builder() {
            super(new FindSoundsRequest());
        }

        public Builder filter(String filter) {
            request.filter = filter;
            return this;
        }
    }
}
