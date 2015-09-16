package com.callfire.api.client.model.request;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Request object for GET /campaigns/text-auto-replys which incapsulates
 * different query pairs
 */
public class FindTextAutoRepliesRequest extends FindRequest {
    private String number;

    private FindTextAutoRepliesRequest() {
    }

    /**
     * Get E.164 11 digit number
     *
     * @return phone number to filter
     */
    public String getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .appendSuper(super.toString())
            .append("number", number)
            .append("baseRequest", super.toString())
            .toString();
    }

    /**
     * Builder class for request
     */
    public static class FindTextAutoRepliesBuilder extends FindRequestBuilder<FindTextAutoRepliesBuilder> {
        private FindTextAutoRepliesRequest request;

        private FindTextAutoRepliesBuilder() {
            request = new FindTextAutoRepliesRequest();
        }

        public static FindTextAutoRepliesBuilder create() {
            return new FindTextAutoRepliesBuilder();
        }

        /**
         * Set phone number to filter
         *
         * @param number E.164 11 digit number to filter
         * @return builder object
         */
        public FindTextAutoRepliesBuilder setNumber(String number) {
            request.number = number;
            return this;
        }

        @Override
        public FindTextAutoRepliesRequest build() {
            return request;
        }

        @Override
        protected FindTextAutoRepliesBuilder getChild() {
            return this;
        }

        @Override
        protected FindTextAutoRepliesRequest getRequest() {
            return request;
        }
    }
}
