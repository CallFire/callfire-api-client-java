package com.callfire.api.client.api.callstexts.model.request;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.callfire.api.client.api.common.model.request.FindRequest;

/**
 * <p>
 * Request object used to find all media files which was uploaded by the user.
 * </p>
 *
 * @see <a href="https://developers.callfire.com/results-responses-errors.html">call states and results</a>
 */
public class FindMediaRequest extends FindRequest {
    private String filter;

    private FindMediaRequest() {

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
     * Get media file filter
     *
     * @return media file filter
     * @see <a href="https://developers.callfire.com/results-responses-errors.html">call states and results</a>
     */
    public String getFilter(){
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
     * Builder class
     */
    public static class Builder extends FindRequestBuilder<Builder, FindMediaRequest> {
        private Builder() {
            super(new FindMediaRequest());
        }

        /**
         * Set media file filter
         *
         * @param filter name of a file to search for
         * @return builder self reference
         * @see <a href="https://developers.callfire.com/results-responses-errors.html">call states and results</a>
         */
        public Builder filter(String filter) {
            request.filter = filter;
            return this;
        }
    }
}
