package com.callfire.api.client.api.contacts.model.request;

import static lombok.AccessLevel.PROTECTED;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import com.callfire.api.client.api.common.model.CallfireModel;
import com.callfire.api.client.api.common.model.request.AbstractBuilder;
import com.callfire.api.client.api.contacts.model.Contact;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Request object for for creation contact lists or adding contacts to existing one
 */
@Getter
@NoArgsConstructor(access = PROTECTED)
public abstract class AddContactsRequest<T> extends CallfireModel {
    public static final String FIELD_CONTACT_IDS = "contactIds";
    public static final String FIELD_CONTACT_NUMBERS = "contactNumbers";
    public static final String FIELD_CONTACTS = "contacts";

    /**
     * Associated contacts with request, possible types are list of Contact objects, contact ids,
     * contact numbers
     *
     * @return name or partial name of contact list
     */
    @JsonIgnore protected List<T> contacts = new ArrayList<>();

    /**
     * Type of phone number (homePhone, workPhone, mobilePhone)
     *
     * @return type of phone number (homePhone, workPhone, mobilePhone)
     */
    protected String contactNumbersField;

    /**
     * Use custom fields flag
     *
     * @return use custom fields flag
     */
    protected Boolean useCustomFields;

    /**
     * Used for internal dynamic property name mapping in Jackson
     *
     * @return current instance fields with proper names
     */
    @JsonAnyGetter
    public Map<String, Object> getProperties() {
        Map<String, Object> fields = new HashMap<>(1);
        if (!contacts.isEmpty()) {
            Object item = contacts.get(0);
            if (item instanceof Long) {
                fields.put(FIELD_CONTACT_IDS, contacts);
            }
            else if (item instanceof String) {
                fields.put(FIELD_CONTACT_NUMBERS, contacts);
            }
            else if (item instanceof Contact) {
                fields.put(FIELD_CONTACTS, contacts);
            }
            else {
                throw new IllegalStateException("Type " + item.getClass().getName() +
                        " isn't supported to create contacts. Use Long, String or Contact types instead.");
            }
        }
        return fields;
    }

    /**
     * Builder class for find method
     */
    public static abstract class AddContactsBuilder<B extends AddContactsBuilder, R extends AddContactsRequest> extends AbstractBuilder<R> {
        protected AddContactsBuilder(R request) {
            super(request);
        }

        /**
         * Set contacts, possible types are list of {@link Contact} objects, contact ids {@link Long},
         * contact numbers {@link String}
         *
         * @param contacts contacts to add
         * @return builder self-reference
         */
        public B contacts(List<?> contacts) {
            request.contacts = contacts;
            return (B) this;
        }

        /**
         * Set type of phone number (homePhone, workPhone, mobilePhone)
         *
         * @param contactNumbersField type of phone number (homePhone, workPhone, mobilePhone)
         * @return builder self reference
         */
        public B contactNumbersField(String contactNumbersField) {
            request.contactNumbersField = contactNumbersField;
            return (B) this;
        }

        /**
         * Set flag to indicate how to define property names for contacts. If true, uses the field and property names exactly as defined.
         * If false will assign custom properties and fields to A, B, C, etc.
         *
         * @param useCustomFields flag to indicate how to define property names for contacts
         * @return builder self reference
         */
        public B useCustomFields(Boolean useCustomFields) {
            request.useCustomFields = useCustomFields;
            return (B) this;
        }
    }
}
