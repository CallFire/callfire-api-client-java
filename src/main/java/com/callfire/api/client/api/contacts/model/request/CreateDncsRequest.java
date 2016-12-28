package com.callfire.api.client.api.contacts.model.request;

import org.apache.commons.lang3.Validate;

import java.util.List;

/**
 * Request object for
 *
 * POST /contacts/dncs/
 */
public class CreateDncsRequest extends CallsTextsRequest {
    private String source;
    private List<String> numbers;

    private CreateDncsRequest() {
    }

    /**
     * Get source property
     *
     * @return Dnc Source
     */
    public String getSource() {
        return source;
    }

    /**
     * Get numbers list property
     *
     * @return Dnc numbers list
     */
    public List<String> getNumbers() {
        return numbers;
    }

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
