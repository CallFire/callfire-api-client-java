package com.callfire.api.client.api.contacts.model.request;

/**
 * Request object for POST /contacts/lists which incapsulates
 * different query pairs
 */
public class CreateContactListRequest<T> extends AddContactsRequest<T> {
    private String name;

    private CreateContactListRequest() {
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
     * @param <T> type of passed contacts to create
     * @return request build
     */
    public static <T> Builder<T> create() {
        return new Builder<>();
    }

    /**
     * Builder class for find method
     */
    public static class Builder<T> extends AddContactsBuilder<T, CreateContactListRequest<T>> {
        private Builder() {
            super(new CreateContactListRequest<T>());
        }

        /**
         * Set name or partial name of contact list
         *
         * @param name name or partial name of contact list
         * @return builder self-reference
         */
        public Builder<T> name(String name) {
            request.name = name;
            return this;
        }
    }
}
