package com.callfire.api.client.api.contacts.model.request;

import com.callfire.api.client.api.common.model.CallfireModel;
import com.callfire.api.client.api.common.model.request.AbstractBuilder;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.Validate;

/**
 * Request object for
 *
 * POST /contacts/dncs/
 */
public class FindUniversalDncsRequest extends CallfireModel {
    @JsonIgnore
    private String toNumber;
    private String fromNumber;
    private String fields;

    private FindUniversalDncsRequest() {
    }

    /**
     * Get toNumber filter
     *
     * @return toNumber string
     */
    public String getToNumber() {
        return toNumber;
    }

    /**
     * Get fromNumber filter
     *
     * @return fromNumber string
     */
    public String getFromNumber() {
        return fromNumber;
    }

    /**
     * Get limit fields returned. Example fields=id,items(name,agents(id))
     *
     * @return field to return
     */
    public String getFields() {
        return fields;
    }

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
