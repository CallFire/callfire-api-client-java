package com.callfire.api.client.api.contacts.model.request;

import static lombok.AccessLevel.PRIVATE;

import org.apache.commons.lang3.Validate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Request object for
 * <p>
 * PUT /contacts/dncs/{number}
 */
@Getter
@NoArgsConstructor(access = PRIVATE)
public class UpdateDncRequest extends CallsTextsRequest {

    /**
     * Number of Dnc
     *
     * @return Number of Dnc
     */
    @JsonIgnore private String number;

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
