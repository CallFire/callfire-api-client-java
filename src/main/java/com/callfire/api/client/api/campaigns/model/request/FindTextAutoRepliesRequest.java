package com.callfire.api.client.api.campaigns.model.request;

import static lombok.AccessLevel.PRIVATE;

import com.callfire.api.client.api.common.model.request.FindRequest;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Request object for GET /campaigns/text-auto-replys which incapsulates
 * different query pairs
 */
@Getter
@NoArgsConstructor(access = PRIVATE)
public class FindTextAutoRepliesRequest extends FindRequest {

    /**
     * E.164 11 digit number
     *
     * @return phone number to filter
     */
    private String number;

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
    public static class Builder extends FindRequestBuilder<Builder, FindTextAutoRepliesRequest> {
        private Builder() {
            super(new FindTextAutoRepliesRequest());
        }

        /**
         * Set phone number to filter
         *
         * @param number E.164 11 digit number to filter
         * @return builder object
         */
        public Builder number(String number) {
            request.number = number;
            return this;
        }
    }
}
