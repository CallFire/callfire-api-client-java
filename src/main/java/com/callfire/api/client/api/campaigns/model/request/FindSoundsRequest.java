package com.callfire.api.client.api.campaigns.model.request;

import com.callfire.api.client.api.common.model.request.FindRequest;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Request object for GET /campaigns/sounds which incapsulates
 * different query pairs
 */
public class FindSoundsRequest extends FindRequest {
    private String filter;
    private Boolean includeArchived;
    private Boolean includePending;
    private Boolean includeScrubbed;


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

    /**
     * Get include archived sounds filter
     *
     * @return include archived sounds filter
     */
    public Boolean getIncludeArchived() {
        return includeArchived;
    }

    /**
     * Get include archived sounds filter
     *
     * @return include pending sounds filter
     */
    public Boolean getIncludePending() {
        return includePending;
    }

    /**
     * Get include scrubbed sounds filter
     *
     * @return include scrubbed sounds filter
     */
    public Boolean getIncludeScrubbed() {
        return includeScrubbed;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("filter", filter)
            .append("includeArchived", filter)
            .append("includePending", filter)
            .append("includeScrubbed", filter)
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

        public Builder includeScrubbed(Boolean includeScrubbed) {
            request.includeScrubbed = includeScrubbed;
            return this;
        }

        public Builder includePending(Boolean includePending) {
            request.includePending = includePending;
            return this;
        }

        public Builder includeArchived(Boolean includeArchived) {
            request.includeArchived = includeArchived;
            return this;
        }
    }
}
