package com.callfire.api.client.api.callstexts.model.request;

import static lombok.AccessLevel.PRIVATE;

import com.callfire.api.client.api.common.model.request.FindRequest;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * <p>
 * Request object used to find all media files which was uploaded by the user.
 * </p>
 *
 * @see <a href="https://developers.callfire.com/results-responses-errors.html">call states and results</a>
 */
@Getter
@NoArgsConstructor(access = PRIVATE)
public class FindMediaRequest extends FindRequest {

    /**
     * Media file filter
     *
     * @return media file filter
     * @see <a href="https://developers.callfire.com/results-responses-errors.html">call states and results</a>
     */
    private String filter;

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
