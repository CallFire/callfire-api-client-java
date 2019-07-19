package com.callfire.api.client.api.contacts.model.request;

import static lombok.AccessLevel.PRIVATE;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Request object for POST /contacts/lists/{id}/items which incapsulates
 * different query pairs
 */
@Getter
@NoArgsConstructor(access = PRIVATE)
public class AddContactListItemsRequest<T> extends AddContactsRequest<T> {
    @JsonIgnore private Long contactListId;

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
