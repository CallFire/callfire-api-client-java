package com.callfire.api.client.api.contacts.model.request;

import com.callfire.api.client.api.common.model.request.GetRequest;
import com.callfire.api.client.api.common.model.request.QueryParamIgnore;

/**
 * Request object for GET /contacts/do-not-calls/lists/{id}/items which incapsulates
 * different query pairs
 */
// TODO vmikhailov move all requests with only one id property to CommonGetRequest class
public class GetDncListItemsRequest extends GetRequest {
    @QueryParamIgnore
    private Long id;

    private GetDncListItemsRequest() {
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
    public static class Builder extends GetRequestBuilder<Builder, GetDncListItemsRequest> {
        private Builder() {
            super(new GetDncListItemsRequest());
        }

        /**
         * Set DNC list id
         *
         * @param id id of DNC list
         * @return builder self-reference
         */
        public Builder id(Long id) {
            request.id = id;
            return this;
        }
    }
}
