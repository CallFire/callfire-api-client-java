package com.callfire.api.client.api.contacts.model.request;

import com.callfire.api.client.api.common.model.request.GetRequest;

/**
 * Request object for GET /contacts/{id}/history which incapsulates
 * different query pairs
 */
public class GetContactHistoryRequest extends GetRequest {
    private Long id;

    private GetContactHistoryRequest() {
    }

    public Long getId() {
        return id;
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
     * Builder class for find method
     */
    public static class Builder extends GetRequestBuilder<Builder, GetContactHistoryRequest> {
        private Builder() {
            super(new GetContactHistoryRequest());
        }

        /**
         * Set contact id
         *
         * @param id id of contact
         * @return builder self-reference
         */
        public Builder id(Long id) {
            request.id = id;
            return this;
        }
    }
}
