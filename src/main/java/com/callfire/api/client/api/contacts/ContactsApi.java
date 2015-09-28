package com.callfire.api.client.api.contacts;

import com.callfire.api.client.CallfireApiException;
import com.callfire.api.client.CallfireClientException;
import com.callfire.api.client.RestApiClient;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.common.model.ResourceId;
import com.callfire.api.client.api.common.model.request.GetByIdRequest;
import com.callfire.api.client.api.contacts.model.Contact;
import com.callfire.api.client.api.contacts.model.ContactHistory;
import com.callfire.api.client.api.contacts.model.request.FindContactsRequest;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.commons.lang3.Validate;
import org.apache.http.NameValuePair;

import java.util.ArrayList;
import java.util.List;

import static com.callfire.api.client.ClientConstants.PLACEHOLDER;
import static com.callfire.api.client.ClientConstants.Type.LIST_OF_RESOURCE_ID_TYPE;
import static com.callfire.api.client.ClientConstants.Type.VOID_TYPE;
import static com.callfire.api.client.ClientUtils.addQueryParamIfSet;

/**
 * Represents rest endpoint /contacts
 *
 * @since 1.0
 */
public class ContactsApi {
    private static final String CONTACTS_PATH = "/contacts";
    private static final String CONTACTS_ITEM_PATH = "/contacts/{}";
    private static final String CONTACTS_ITEM_HISTORY_PATH = "/contacts/{}/history";
    private static final TypeReference<Contact> CONTACT_TYPE = new TypeReference<Contact>() {
    };
    private static final TypeReference<ContactHistory> CONTACT_HISTORY_TYPE = new TypeReference<ContactHistory>() {
    };
    public static final TypeReference<Page<Contact>> PAGE_OF_CONTACT_TYPE = new TypeReference<Page<Contact>>() {
    };

    private RestApiClient client;

    public ContactsApi(RestApiClient client) {
        this.client = client;
    }

    /**
     * Find contacts by number, contact-list id, etc...
     *
     * @param request request object with different fields to filter
     * @return {@link Page} with {@link Contact} objects
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public Page<Contact> find(FindContactsRequest request) {
        return client.get(CONTACTS_PATH, PAGE_OF_CONTACT_TYPE, request);
    }

    /**
     * Create contacts by list of contact items
     *
     * @param contacts contacts to create
     * @return list of ids newly created contacts
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public List<ResourceId> create(List<Contact> contacts) {
        return client.post(CONTACTS_PATH, LIST_OF_RESOURCE_ID_TYPE, contacts).getItems();
    }

    /**
     * Get contact by id. Deleted contacts can still be retrieved but will be marked deleted
     * and will not show up when quering contacts.
     *
     * @param id id of contact
     * @return requested contact
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public Contact get(Long id) {
        return get(id, null);
    }

    /**
     * Get contact by id. Deleted contacts can still be retrieved but will be marked deleted
     * and will not show up when quering contacts.
     *
     * @param id     id of contact
     * @param fields limit fields returned. Example fields=id,name
     * @return requested contact
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public Contact get(Long id, String fields) {
        Validate.notNull(id, "id cannot be null");
        List<NameValuePair> queryParams = new ArrayList<>(1);
        addQueryParamIfSet("fields", fields, queryParams);
        return client.get(CONTACTS_ITEM_PATH.replaceFirst(PLACEHOLDER, id.toString()), CONTACT_TYPE, queryParams);
    }

    /**
     * Update contact
     *
     * @param contact contact to update
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public void update(Contact contact) {
        Validate.notNull(contact.getId(), "contact.id cannot be null");
        client.put(CONTACTS_ITEM_PATH.replaceFirst(PLACEHOLDER, contact.getId().toString()), VOID_TYPE, contact);
    }

    /**
     * Delete contact by id. This does not actually delete the contact, it just removes the contact from
     * any contact lists and marks the contact as deleted so won't show up in queries anymore.
     *
     * @param id id of contact
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public void delete(Long id) {
        Validate.notNull(id, "id cannot be null");
        client.delete(CONTACTS_ITEM_PATH.replaceFirst(PLACEHOLDER, id.toString()));
    }

    /**
     * Get contact history
     * List all calls and texts associated with a contact
     *
     * @param request request to get particular contact's history
     * @return object with sent calls/texts history
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public ContactHistory getHistory(GetByIdRequest request) {
        Validate.notNull(request.getId(), "request.id cannot be null");
        String path = CONTACTS_ITEM_HISTORY_PATH.replaceFirst(PLACEHOLDER, request.getId().toString());
        return client.get(path, CONTACT_HISTORY_TYPE, request);
    }
}
