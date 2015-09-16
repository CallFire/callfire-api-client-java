package com.callfire.api.client.model.request;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Request object for GET /call-sounds which incapsulates
 * different query pairs
 */
public class FindSoundsRequest extends FindRequest {
    private String filter;

    private FindSoundsRequest() {
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
            .append("baseRequest", super.toString())
            .toString();
    }

    /**
     * Builder class for request
     */
    public static class FindSoundsRequestBuilder extends FindRequestBuilder<FindSoundsRequestBuilder> {
        private FindSoundsRequest request;

        private FindSoundsRequestBuilder() {
            request = new FindSoundsRequest();
        }

        public static FindSoundsRequestBuilder create() {
            return new FindSoundsRequestBuilder();
        }

        public FindSoundsRequestBuilder setFilter(String filter) {
            request.filter = filter;
            return this;
        }

        @Override
        public FindSoundsRequest build() {
            return request;
        }

        @Override
        protected FindSoundsRequestBuilder getChild() {
            return this;
        }

        @Override
        protected FindSoundsRequest getRequest() {
            return request;
        }
    }
}
