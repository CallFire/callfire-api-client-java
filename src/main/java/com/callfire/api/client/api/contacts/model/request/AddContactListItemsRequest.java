package com.callfire.api.client.api.contacts.model.request;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Request object for POST /contacts/lists/{id}/items which incapsulates
 * different query pairs
 */
public class AddContactListItemsRequest<T> extends AddContactsRequest<T> {
    @JsonIgnore
    private Long contactListId;
    private String contactNumbersField;

    private AddContactListItemsRequest() {
    }

    public Long getContactListId() {
        return contactListId;
    }

    public String getContactNumbersField() {
        return contactNumbersField;
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
    public static class Builder<T> extends AddContactsBuilder<T, AddContactListItemsRequest<T>> {
        private Builder() {
            super(new AddContactListItemsRequest<T>());
        }

        public Builder<T> contactListId(Long contactListId) {
            request.contactListId = contactListId;
            return this;
        }

        public Builder<T> contactNumbersField(String contactNumbersField) {
            request.contactNumbersField = contactNumbersField;
            return this;
        }
    }
}
