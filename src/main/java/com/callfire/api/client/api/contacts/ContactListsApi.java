package com.callfire.api.client.api.contacts;

import com.callfire.api.client.CallfireApiException;
import com.callfire.api.client.CallfireClientException;
import com.callfire.api.client.RestApiClient;
import com.callfire.api.client.api.contacts.model.Contact;
import com.callfire.api.client.api.contacts.model.ContactHistory;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.common.model.ResourceId;
import com.callfire.api.client.api.contacts.model.request.FindContactListsRequest;
import com.callfire.api.client.api.contacts.model.request.FindContactsRequest;
import com.fasterxml.jackson.core.type.TypeReference;

/**
 * Represents rest endpoint /contacts/lists
 */
public class ContactListsApi {
    private static final String CONTACT_LISTS_PATH = "/contacts/lists";
    private static final String CONTACT_LISTS_ITEM_PATH = "/contacts/lists/{}";
    private static final String CONTACT_LISTS_UPLOAD_PATH = "/contacts/lists/upload";
    private static final String CONTACT_LISTS_ITEMS_PATH = "/contacts/lists/{}/items";
    private static final String CONTACT_LISTS_ITEMS_CONTACT_PATH = "/contacts/lists/{}/items/{}";
    private static final TypeReference<Page<Contact>> PAGE_OF_CONTACT_TYPE = new TypeReference<Page<Contact>>() {
    };
    private static final TypeReference<Contact> CONTACT_TYPE = new TypeReference<Contact>() {
    };
    private static final TypeReference<ContactHistory> CONTACT_HISTORY_TYPE = new TypeReference<ContactHistory>() {
    };

    private RestApiClient client;

    public ContactListsApi(RestApiClient client) {
        this.client = client;
    }

    /**
     * Find contact lists by id, name, number, etc...
     *
     * @param request request object with fields to filter
     * @return page with contacts
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public Page<Contact> findContactLists(FindContactListsRequest request) {
        return client.get(CONTACT_LISTS_PATH, PAGE_OF_CONTACT_TYPE, request);
    }

    /**
     * Create contact lists
     *
     * @param body ~
     * @return ResourceId with id of contact list
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public ResourceId createContactList() {
        return null;
    }

}
