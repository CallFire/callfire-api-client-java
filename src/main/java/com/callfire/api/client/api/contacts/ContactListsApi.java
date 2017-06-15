package com.callfire.api.client.api.contacts;

import com.callfire.api.client.*;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.common.model.ResourceId;
import com.callfire.api.client.api.common.model.request.GetByIdRequest;
import com.callfire.api.client.api.contacts.model.Contact;
import com.callfire.api.client.api.contacts.model.ContactList;
import com.callfire.api.client.api.contacts.model.request.AddContactListItemsRequest;
import com.callfire.api.client.api.contacts.model.request.CreateContactListRequest;
import com.callfire.api.client.api.contacts.model.request.FindContactListsRequest;
import com.callfire.api.client.api.contacts.model.request.UpdateContactListRequest;
import org.apache.commons.lang3.Validate;
import org.apache.http.NameValuePair;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.callfire.api.client.ClientConstants.PLACEHOLDER;
import static com.callfire.api.client.ClientUtils.addQueryParamIfSet;
import static com.callfire.api.client.ModelType.of;
import static com.callfire.api.client.ModelType.pageOf;

/**
 * Represents rest endpoint /contacts/lists
 *
 * @since 1.0
 */
public class ContactListsApi {
    private static final String LISTS_PATH = "/contacts/lists";
    private static final String LISTS_ITEM_PATH = "/contacts/lists/{}";
    private static final String LISTS_UPLOAD_PATH = "/contacts/lists/upload";
    private static final String LISTS_ITEMS_PATH = "/contacts/lists/{}/items";
    private static final String LISTS_ITEMS_CONTACT_PATH = "/contacts/lists/{}/items/{}";

    private RestApiClient client;

    public ContactListsApi(RestApiClient client) {
        this.client = client;
    }

    /**
     * Find contact lists by id, name, number, etc...
     *
     * @param request request object with fields to filter
     * @return page with contacts
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public Page<ContactList> find(FindContactListsRequest request) {
        return client.get(LISTS_PATH, pageOf(ContactList.class), request);
    }

    /**
     * Creates a contact list for use with campaigns using 1 of 3 inputs. A List of Contact objects,
     * a list of String E.164 numbers, or a list of CallFire contactIds can be used as the data source
     * for the created contact list. After staging these contacts into the CallFire system, contact lists
     * go through seven system safeguards that check the accuracy and consistency of the data. For example,
     * our system checks if a number is formatted correctly, is invalid, is duplicated in another
     * contact list, or is on a specific DNC list. The default resolution in these safeguards will be
     * to remove contacts that are against these rules. If contacts are not being added to a list,
     * this means the data needs to be properly formatted and correct before calling this API.
     * <br>
     * <b>Examples:</b>
     * <pre>
     * {@code
     * Contact c1 = new Contact();
     * c1.setName("Name");
     * Contact c2 = new Contact();
     * c2.setName("Name");
     * CreateContactListRequest request = CreateContactListRequest.<Contact>create()
     *     .name("listFromContacts")
     *     .contacts(asList(c1, c2))
     *     .build();
     * <br>
     * CreateContactListRequest request = CreateContactListRequest.<Long>create()
     *     .name("listFromContactIds")
     *     .contacts(asList(123L, 456L))
     *     .build();
     * <br>
     * CreateContactListRequest request = CreateContactListRequest.<String>create()
     *     .name("listFromNumbers")
     *     .contacts(asList("12135678881", "12135678882"))
     *     .build();
     * <br>
     * }
     * </pre>
     *
     * @param request request object with provided contacts, list name and other values
     * @return {@link ResourceId} with id of contact list
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public ResourceId create(CreateContactListRequest request) {
        return client.post(LISTS_PATH, of(ResourceId.class), request);
    }

    /**
     * Upload contact lists from CSV file
     * Create contact list which includes list of contacts by file.
     *
     * @param name contact list name
     * @param file CSV file with contacts to upload
     * @return {@link ResourceId} with id of created contact list
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public ResourceId createFromCsv(String name, File file) {
        return createFromCsv(name, file, null);
    }

    /**
     * Upload contact lists from CSV file
     * Create contact list which includes list of contacts by file.
     *
     * @param name contact list name
     * @param file CSV file with contacts to upload
     * @param useCustomFields A flag to indicate how to define property names for contacts. If true, uses the field and property names exactly as defined. If false will assign custom properties and fields to A, B, C, etc
     * @return {@link ResourceId} with id of created contact list
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public ResourceId createFromCsv(String name, File file, Boolean useCustomFields) {
        Map<String, Object> params = new HashMap<>(3);
        params.put("file", file);
        params.put("name", name);
        params.put("useCustomFields", useCustomFields);
        return client.postFile(LISTS_UPLOAD_PATH, of(ResourceId.class), params);
    }

    /**
     * Get contact list by id
     *
     * @param id id of contact list
     * @return a single ContactList instance for a given contact list id
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public ContactList get(Long id) {
        return get(id, null);
    }

    /**
     * Get contact list by id
     *
     * @param id     id of contact list
     * @param fields limit fields returned. Example fields=name,status
     * @return a single ContactList instance for a given contact list id
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public ContactList get(Long id, String fields) {
        Validate.notNull(id, "id cannot be null");
        List<NameValuePair> queryParams = new ArrayList<>(1);
        addQueryParamIfSet("fields", fields, queryParams);
        return client.get(LISTS_ITEM_PATH.replaceFirst(PLACEHOLDER, id.toString()), of(ContactList.class), queryParams);
    }

    /**
     * Update contact list
     *
     * @param request object contains properties to update
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public void update(UpdateContactListRequest request) {
        Validate.notNull(request.getId(), "request.id cannot be null");
        client.put(LISTS_ITEM_PATH.replaceFirst(PLACEHOLDER, request.getId().toString()), null, request);
    }

    /**
     * Delete contact list
     *
     * @param id contact list id
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public void delete(Long id) {
        Validate.notNull(id, "id cannot be null");
        client.delete(LISTS_ITEM_PATH.replaceFirst(PLACEHOLDER, id.toString()));
    }

    /**
     * Find all entries in a given contact list. Property <b>request.id</b> required
     *
     * @param request request object with properties to filter
     * @return paged list of Contact entries.
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public Page<Contact> getListItems(GetByIdRequest request) {
        Validate.notNull(request.getId(), "request.id cannot be null");
        String path = LISTS_ITEMS_PATH.replaceFirst(PLACEHOLDER, request.getId().toString());
        return client.get(path, pageOf(Contact.class), request);
    }

    /**
     * Add contact list items to list
     *
     * @param request request object with contacts to add
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public void addListItems(AddContactListItemsRequest request) {
        Validate.notNull(request.getContactListId(), "request.contactListId cannot be null");
        String path = LISTS_ITEMS_PATH.replaceFirst(PLACEHOLDER, request.getContactListId().toString());
        client.post(path, null, request);
    }

    /**
     * Delete single contact list contact by id
     *
     * @param listId    id of contact list
     * @param contactId id of item to remove
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public void removeListItem(Long listId, Long contactId) {
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
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public void removeListItems(Long contactListId, List<Long> contactIds) {
        Validate.notNull(contactListId, "contactListId cannot be null");
        List<NameValuePair> queryParams = new ArrayList<>(1);
        addQueryParamIfSet("contactId", contactIds, queryParams);
        String path = LISTS_ITEMS_PATH.replaceFirst(PLACEHOLDER, contactListId.toString());
        client.delete(path, queryParams);
    }
}
