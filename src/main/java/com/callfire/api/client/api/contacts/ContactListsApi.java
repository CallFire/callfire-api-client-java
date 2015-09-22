package com.callfire.api.client.api.contacts;

import com.callfire.api.client.CallfireApiException;
import com.callfire.api.client.CallfireClientException;
import com.callfire.api.client.RestApiClient;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.common.model.ResourceId;
import com.callfire.api.client.api.contacts.model.Contact;
import com.callfire.api.client.api.contacts.model.ContactList;
import com.callfire.api.client.api.contacts.model.request.*;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.commons.lang3.Validate;
import org.apache.http.NameValuePair;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.callfire.api.client.ClientConstants.PLACEHOLDER;
import static com.callfire.api.client.ClientConstants.Type.RESOURCE_ID_TYPE;
import static com.callfire.api.client.ClientConstants.Type.VOID_TYPE;
import static com.callfire.api.client.ClientUtils.addQueryParamIfSet;
import static com.callfire.api.client.api.contacts.ContactsApi.PAGE_OF_CONTACT_TYPE;

/**
 * Represents rest endpoint /contacts/lists
 */
public class ContactListsApi {
    private static final String LISTS_PATH = "/contacts/lists";
    private static final String LISTS_ITEM_PATH = "/contacts/lists/{}";
    private static final String LISTS_UPLOAD_PATH = "/contacts/lists/upload";
    private static final String LISTS_ITEMS_PATH = "/contacts/lists/{}/items";
    private static final String LISTS_ITEMS_CONTACT_PATH = "/contacts/lists/{}/items/{}";
    private static final TypeReference<Page<ContactList>> PAGE_OF_CONTACT_LIST_TYPE = new TypeReference<Page<ContactList>>() {
    };
    private static final TypeReference<ContactList> CONTACT_LIST_TYPE = new TypeReference<ContactList>() {
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
    public Page<ContactList> findContactLists(FindContactListsRequest request) {
        return client.get(LISTS_PATH, PAGE_OF_CONTACT_LIST_TYPE, request);
    }

    /**
     * Create contact lists
     *
     * @param request request object with provided contacts, list name and other values
     * @return ResourceId with id of contact list
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public ResourceId createContactList(CreateContactListRequest request) {
        return client.post(LISTS_PATH, RESOURCE_ID_TYPE, request);
    }

    /**
     * Upload contact lists from CSV file
     * Create contact list which includes list of contacts by file.
     *
     * @param name contact list name
     * @param file CSV file with contacts to upload
     * @return ResourceId of created contact list
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public ResourceId createContactListFromFile(String name, File file) {
        Map<String, Object> params = new HashMap<>(2);
        params.put("file", file);
        params.put("name", name);
        return client.postFile(LISTS_UPLOAD_PATH, RESOURCE_ID_TYPE, params);
    }

    /**
     * Get contact list by id
     *
     * @param id id of contact list
     * @return ContactList object
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public ContactList getContactList(Long id) {
        return getContactList(id, null);
    }

    /**
     * Get contact list by id
     *
     * @param id     id of contact list
     * @param fields limit fields returned. Example fields=name,status
     * @return ContactList object
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public ContactList getContactList(Long id, String fields) {
        Validate.notNull(id, "id cannot be null");
        List<NameValuePair> queryParams = new ArrayList<>(1);
        addQueryParamIfSet("fields", fields, queryParams);
        return client.get(LISTS_ITEM_PATH.replaceFirst(PLACEHOLDER, id.toString()), CONTACT_LIST_TYPE, queryParams);
    }

    /**
     * Update contact list
     *
     * @param request object contains properties to update
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public void updateContactList(UpdateContactListRequest request) {
        // TODO vmikhailov add validation interceptor to all public api methods
        Validate.notNull(request.getId(), "request.id cannot be null");
        client.put(LISTS_ITEM_PATH.replaceFirst(PLACEHOLDER, request.getId().toString()), VOID_TYPE, request);
    }

    /**
     * Delete contact list
     *
     * @param id contact list id
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public void deleteContactList(Long id) {
        Validate.notNull(id, "id cannot be null");
        client.delete(LISTS_ITEM_PATH.replaceFirst(PLACEHOLDER, id.toString()));
    }

    /**
     * Get contact list items
     * Property <b>request.id</b> required
     *
     * @param request request object with properties to filter
     * @return Page filled with contacts
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public Page<Contact> getContactListItems(GetContactListItemsRequest request) {
        Validate.notNull(request.getId(), "request.id cannot be null");
        String path = LISTS_ITEMS_PATH.replaceFirst(PLACEHOLDER, request.getId().toString());
        return client.get(path, PAGE_OF_CONTACT_TYPE, request);
    }

    /**
     * Add contact list items to list
     *
     * @param request request object with contacts to add
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public void addContactListItems(AddContactListItemsRequest request) {
        Validate.notNull(request.getContactListId(), "request.contactListId cannot be null");
        String path = LISTS_ITEMS_PATH.replaceFirst(PLACEHOLDER, request.getContactListId().toString());
        client.post(path, VOID_TYPE, request);
    }

    /**
     * Delete single contact list contact by id
     *
     * @param listId    id of contact list
     * @param contactId id of item to remove
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public void removeContactListItem(Long listId, Long contactId) {
        Validate.notNull(listId, "listId cannot be null");
        Validate.notNull(contactId, "contactId cannot be null");
        String path = LISTS_ITEMS_CONTACT_PATH.replaceFirst(PLACEHOLDER, listId.toString())
            .replaceFirst(PLACEHOLDER, contactId.toString());
        client.delete(path);
    }

    /**
     * Delete contact list items
     *
     * @param contactListId id of contact list
     * @param contactIds    ids of items to remove
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public void removeContactListItems(Long contactListId, List<Long> contactIds) {
        Validate.notNull(contactListId, "contactListId cannot be null");
        List<NameValuePair> queryParams = new ArrayList<>(1);
        addQueryParamIfSet("id", contactIds, queryParams);
        String path = LISTS_ITEMS_PATH.replaceFirst(PLACEHOLDER, contactListId.toString());
        client.delete(path, queryParams);
    }
}
