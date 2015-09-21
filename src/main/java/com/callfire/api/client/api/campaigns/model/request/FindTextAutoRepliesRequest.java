package com.callfire.api.client.api.campaigns.model.request;

import com.callfire.api.client.api.common.model.request.GetRequest;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Request object for GET /campaigns/text-auto-replys which incapsulates
 * different query pairs
 */
public class FindTextAutoRepliesRequest extends GetRequest {
    private String number;

    private FindTextAutoRepliesRequest() {
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
            .toString();
    }

    /**
     * Builder class for request
     */
    public static class Builder extends AbstractBuilder<Builder, FindTextAutoRepliesRequest> {
        private Builder() {
            super(new FindTextAutoRepliesRequest());
        }

        /**
         * Set phone number to filter
         *
         * @param number E.164 11 digit number to filter
         * @return builder object
         */
        public Builder setNumber(String number) {
            request.number = number;
            return this;
        }
    }
}
