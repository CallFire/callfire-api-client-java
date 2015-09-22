package com.callfire.api.client.api.contacts.model.request;

import com.callfire.api.client.api.common.model.CallfireModel;
import com.callfire.api.client.api.common.model.request.AbstractBuilder;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Request object for
 * <p/>
 * PUT /contacts/lists/{id}
 */
public class UpdateContactListRequest extends CallfireModel {
    private String name;
    @JsonIgnore
    private Long id;

    private UpdateContactListRequest() {
    }

    /**
     * Get id of contact list
     *
     * @return id of contact list
     */
    public Long getId() {
        return id;
    }

    /**
     * Get contact list's name
     *
     * @return contact list's name
     */
    public String getName() {
        return name;
    }

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
