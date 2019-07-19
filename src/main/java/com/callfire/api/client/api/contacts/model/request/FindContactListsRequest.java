package com.callfire.api.client.api.contacts.model.request;

import static lombok.AccessLevel.PRIVATE;

import com.callfire.api.client.api.common.model.request.FindRequest;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Request object for GET /contacts/lists which incapsulates
 * different query pairs
 */
@Getter
@NoArgsConstructor(access = PRIVATE)
public class FindContactListsRequest extends FindRequest {

    /**
     * Name or partial name of contact list
     *
     * @return name or partial name of contact list
     */
    private String name;

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
    public static class Builder extends FindRequestBuilder<Builder, FindContactListsRequest> {
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
