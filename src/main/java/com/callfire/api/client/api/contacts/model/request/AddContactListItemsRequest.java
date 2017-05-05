package com.callfire.api.client.api.contacts.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Request object for POST /contacts/lists/{id}/items which incapsulates
 * different query pairs
 */
public class AddContactListItemsRequest<T> extends AddContactsRequest<T> {
    @JsonIgnore
    private Long contactListId;

    private AddContactListItemsRequest() {
    }

    public Long getContactListId() {
        return contactListId;
    }

    /**
     * Create request builder
     *
     * @param <T> type of builder
     * @return request build
     */
    public static <T> Builder<T> create() {
        return new Builder<>();
    }

    /**
     * Builder class for find method
     */
    public static class Builder<T> extends AddContactsBuilder<Builder<T>, AddContactListItemsRequest<T>> {
        private Builder() {
            super(new AddContactListItemsRequest<T>());
        }

        public Builder<T> contactListId(Long contactListId) {
            request.contactListId = contactListId;
            return this;
        }
    }
}
