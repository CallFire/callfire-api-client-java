package com.callfire.api.client.api.contacts.model.request;

import static lombok.AccessLevel.PRIVATE;

import org.apache.commons.lang3.Validate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.callfire.api.client.api.common.model.CallfireModel;
import com.callfire.api.client.api.common.model.request.AbstractBuilder;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Request object for
 * <p>
 * POST /contacts/dncs/
 */
@Getter
@NoArgsConstructor(access = PRIVATE)
public class FindUniversalDncsRequest extends CallfireModel {

    /**
     * toNumber filter
     *
     * @return toNumber string
     */
    @JsonIgnore private String toNumber;

    /**
     * fromNumber filter
     *
     * @return fromNumber string
     */
    private String fromNumber;

    /**
     * Limit fields returned. Example fields=id,items(name,agents(id))
     *
     * @return field to return
     */
    private String fields;

    public static Builder create() {
        return new Builder();
    }

    /**
     * Builder class for request
     */
    public static class Builder extends AbstractBuilder<FindUniversalDncsRequest> {

        /**
         * Set ToNumber filter
         *
         * @param toNumber ToNumber filter
         * @return builder self-reference
         */
        public Builder toNumber(String toNumber) {
            request.toNumber = toNumber;
            return this;
        }

        /**
         * Set FromNumber filter
         *
         * @param fromNumber FromNumber filter
         * @return builder self-reference
         */
        public Builder fromNumber(String fromNumber) {
            request.fromNumber = fromNumber;
            return this;
        }

        /**
         * Set limit fields returned. Example fields=id,items(name,agents(id))
         *
         * @param fields fields to return
         * @return builder object
         */
        public Builder fields(String fields) {
            request.fields = fields;
            return this;
        }

        private Builder() {
            super(new FindUniversalDncsRequest());
        }

        @Override
        public FindUniversalDncsRequest build() {
            Validate.notNull(request.toNumber, "toNumber cannot be null");
            return super.build();
        }
    }
}
