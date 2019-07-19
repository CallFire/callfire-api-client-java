package com.callfire.api.client.api.campaigns.model.request;

import static lombok.AccessLevel.PRIVATE;

import com.callfire.api.client.api.common.model.request.FindRequest;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Request object for GET /campaigns/sounds which incapsulates
 * different query pairs
 */
@Getter
@NoArgsConstructor(access = PRIVATE)
public class FindSoundsRequest extends FindRequest {

    /**
     * Search field for fileName/name
     *
     * @return search field for fileName/name
     */
    private String filter;

    /**
     * Include archived sounds filter
     *
     * @return include archived sounds filter
     */
    private Boolean includeArchived;

    /**
     * Include archived sounds filter
     *
     * @return include pending sounds filter
     */
    private Boolean includePending;

    /**
     * Include scrubbed sounds filter
     *
     * @return include scrubbed sounds filter
     */
    private Boolean includeScrubbed;

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
