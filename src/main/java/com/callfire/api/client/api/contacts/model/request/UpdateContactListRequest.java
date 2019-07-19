package com.callfire.api.client.api.contacts.model.request;

import static lombok.AccessLevel.PRIVATE;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.callfire.api.client.api.common.model.CallfireModel;
import com.callfire.api.client.api.common.model.request.AbstractBuilder;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Request object for
 * <p>
 * PUT /contacts/lists/{id}
 */
@Getter
@NoArgsConstructor(access = PRIVATE)
public class UpdateContactListRequest extends CallfireModel {
    /**
     * Contact list's name
     *
     * @return contact list's name
     */
    private String name;

    /**
     * ID of contact list
     *
     * @return id of contact list
     */
    @JsonIgnore private Long id;

    public static Builder create() {
        return new Builder();
    }

    /**
     * Builder class for request
     */
    public static class Builder extends AbstractBuilder<UpdateContactListRequest> {

        /**
         * Set contact list name
         *
         * @param name contact list name to update
         * @return builder self-reference
         */
        public Builder name(String name) {
            request.name = name;
            return this;
        }

        /**
         * Set contact list id
         *
         * @param id contact list id
         * @return builder self-reference
         */
        public Builder id(Long id) {
            request.id = id;
            return this;
        }

        private Builder() {
            super(new UpdateContactListRequest());
        }
    }
}
