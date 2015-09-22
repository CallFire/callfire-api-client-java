package com.callfire.api.client.api.contacts.model.request;

import com.callfire.api.client.api.common.model.CallfireModel;
import com.callfire.api.client.api.common.model.request.AbstractBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Request object for for creation contact lists or adding contacts to existing one
 */
public abstract class AddContactsRequest<T> extends CallfireModel {
    protected List<T> contacts = new ArrayList<>();

    protected AddContactsRequest() {
    }

    /**
     * Get associated contacts with request, possible types are list of Contact objects, contact ids,
     * contact numbers
     *
     * @return name or partial name of contact list
     */
    public List<T> getContacts() {
        return contacts;
    }

    /**
     * Builder class for find method
     */
    public static abstract class AddContactsBuilder<T, R extends AddContactsRequest> extends AbstractBuilder<R> {
        protected AddContactsBuilder(R request) {
            super(request);
        }

        /**
         * Set contacts, possible types are list of Contact objects, contact ids,
         * contact numbers
         *
         * @param contacts contacts to add
         * @return builder self-reference
         */
        public AddContactsBuilder<T, R> contacts(List<T> contacts) {
            request.contacts = contacts;
            return this;
        }
    }
}
