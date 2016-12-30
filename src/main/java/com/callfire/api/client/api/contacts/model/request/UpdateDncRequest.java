package com.callfire.api.client.api.contacts.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.Validate;

/**
 * Request object for
 *
 * PUT /contacts/dncs/{number}
 */
public class UpdateDncRequest extends CallsTextsRequest {
    @JsonIgnore
    private String number;
    private Boolean call;
    private Boolean text;

    private UpdateDncRequest() {
    }

    /**
     * Get number of Dnc
     *
     * @return Number of Dnc
     */
    public String getNumber() {
        return number;
    }

    public static Builder create() {
        return new Builder();
    }

    /**
     * Builder class for request
     */
    public static class Builder extends CallsTextsBuilder<Builder, UpdateDncRequest> {

        /**
         * Set Dnc number
         *
         * @param number dnc number to update
         * @return builder self-reference
         */
        public Builder number(String number) {
            request.number = number;
            return this;
        }

        private Builder() {
            super(new UpdateDncRequest());
        }

        @Override
        public UpdateDncRequest build() {
            Validate.notNull(request.number, "number cannot be null");
            return super.build();
        }
    }
}
