package com.callfire.api.client.api.contacts.model.request;

/**
 * Request object for POST /contacts/lists/{id}/items which incapsulates
 * different query pairs
 */
public class AddContactListItemsRequest<T> extends AddContactsRequest<T> {
    private String contactNumbersField;

    private AddContactListItemsRequest() {
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

        public Builder<T> contactNumbersField(String contactNumbersField) {
            request.contactNumbersField = contactNumbersField;
            return this;
        }
    }
}
