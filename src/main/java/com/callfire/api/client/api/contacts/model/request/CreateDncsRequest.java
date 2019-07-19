package com.callfire.api.client.api.contacts.model.request;

import static lombok.AccessLevel.PRIVATE;

import java.util.List;

import org.apache.commons.lang3.Validate;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Request object for
 * <p>
 * POST /contacts/dncs/
 */
@Getter
@NoArgsConstructor(access = PRIVATE)
public class CreateDncsRequest extends CallsTextsRequest {

    /**
     * Source property
     *
     * @return Dnc Source
     */
    private String source;

    /**
     * Numbers list property
     *
     * @return Dnc numbers list
     */
    private List<String> numbers;

    public static Builder create() {
        return new Builder();
    }

    /**
     * Builder class for request
     */
    public static class Builder extends CallsTextsBuilder<Builder, CreateDncsRequest> {

        /**
         * Set Source for Dnc
         *
         * @param source Dnc source
         * @return builder self-reference
         */
        public Builder source(String source) {
            request.source = source;
            return this;
        }

        /**
         * Set Numbers to create dncs for
         *
         * @param numbers Numbers list
         * @return builder self-reference
         */
        public Builder numbers(List<String> numbers) {
            request.numbers = numbers;
            return this;
        }

        private Builder() {
            super(new CreateDncsRequest());
        }

        @Override
        public CreateDncsRequest build() {
            Validate.notEmpty(request.numbers, "numbers cannot be empty");
            return super.build();
        }
    }
}
