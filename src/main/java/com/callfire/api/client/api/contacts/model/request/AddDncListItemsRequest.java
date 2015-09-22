package com.callfire.api.client.api.contacts.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Request object for POST /contacts/do-not-calls/lists/{id}/items which incapsulates
 * different query pairs
 */
public class AddDncListItemsRequest<T> extends AddContactsRequest<T> {
    @JsonIgnore
    private Long contactListId;

    private AddDncListItemsRequest() {
    }

    public Long getContactListId() {
        return contactListId;
    }

    /**
     * Create request builder
     *
     * @return request build
     */
    public static <T> Builder<T> create() {
        return new Builder<>();
    }

    /**
     * Builder class for find method
     */
    public static class Builder<T> extends AddContactsBuilder<T, AddDncListItemsRequest<T>> {
        private Builder() {
            super(new AddDncListItemsRequest<T>());
        }

        public Builder<T> contactListId(Long contactListId) {
            request.contactListId = contactListId;
            return this;
        }
    }
}
