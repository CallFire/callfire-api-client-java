package com.callfire.api.client.api.contacts.model.request;

import com.callfire.api.client.api.common.model.CallfireModel;
import com.callfire.api.client.api.common.model.request.AbstractBuilder;
import com.callfire.api.client.api.contacts.model.Contact;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Request object for for creation contact lists or adding contacts to existing one
 */
public abstract class AddContactsRequest<T> extends CallfireModel {
    public static final String FIELD_CONTACT_IDS = "contactIds";
    public static final String FIELD_CONTACT_NUMBERS = "contactNumbers";
    public static final String FIELD_CONTACTS = "contacts";

    @JsonIgnore
    protected List<T> contacts = new ArrayList<>();

    protected AddContactsRequest() {
    }

    /**
     * Used for internal dynamic property name mapping in Jackson
     */
    @JsonAnyGetter
    public Map<String, Object> getProperties() {
        Map<String, Object> fields = new HashMap<>(1);
        if (!contacts.isEmpty()) {
            Object item = contacts.get(0);
            if (item instanceof Long) {
                fields.put(FIELD_CONTACT_IDS, contacts);
            } else if (item instanceof String) {
                fields.put(FIELD_CONTACT_NUMBERS, contacts);
            } else if (item instanceof Contact) {
                fields.put(FIELD_CONTACTS, contacts);
            } else {
                throw new IllegalStateException("Type " + item.getClass().getName() +
                    " isn't supported to create contacts. Use Long, String or Contact types instead.");
            }
        }
        return fields;
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
