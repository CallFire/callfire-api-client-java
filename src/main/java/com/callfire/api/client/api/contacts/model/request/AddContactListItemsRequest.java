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

    /**
     * Get type of phone number (homePhone, workPhone, mobilePhone)
     *
     * @return type of phone number (homePhone, workPhone, mobilePhone)
     */
    public String getContactNumbersField() {
        return contactNumbersField;
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
    public static class Builder<T> extends AddContactsBuilder<T, AddContactListItemsRequest<T>> {
        private Builder() {
            super(new AddContactListItemsRequest<T>());
        }

        public Builder<T> contactListId(Long contactListId) {
            request.contactListId = contactListId;
            return this;
        }

        /**
         * Set type of phone number (homePhone, workPhone, mobilePhone)
         *
         * @param contactNumbersField type of phone number (homePhone, workPhone, mobilePhone)
         * @return builder self reference
         */
        public Builder<T> contactNumbersField(String contactNumbersField) {
            request.contactNumbersField = contactNumbersField;
            return this;
        }
    }
}
