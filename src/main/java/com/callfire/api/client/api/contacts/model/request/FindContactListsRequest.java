package com.callfire.api.client.api.contacts.model.request;

import com.callfire.api.client.api.common.model.request.GetRequest;

/**
 * Request object for GET /contacts which incapsulates
 * different query pairs
 */
public class FindContactListsRequest extends GetRequest {
    private String name;

    private FindContactListsRequest() {
    }

    /**
     * Get name or partial name of contact list
     *
     * @return name or partial name of contact list
     */
    public String getName() {
        return name;
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
    public static class Builder extends GetRequestBuilder<Builder, FindContactListsRequest> {
        private Builder() {
            super(new FindContactListsRequest());
        }

        /**
         * Set name or partial name of contact list
         *
         * @param name name or partial name of contact list
         * @return builder self-reference
         */
        public Builder name(String name) {
            request.name = name;
            return this;
        }
    }
}
