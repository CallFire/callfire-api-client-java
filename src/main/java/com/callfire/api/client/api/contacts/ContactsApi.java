package com.callfire.api.client.api.contacts;

import static com.callfire.api.client.ClientConstants.PLACEHOLDER;
import static com.callfire.api.client.ClientUtils.addQueryParamIfSet;
import static com.callfire.api.client.ModelType.listHolderOf;
import static com.callfire.api.client.ModelType.of;
import static com.callfire.api.client.ModelType.pageOf;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.Validate;
import org.apache.http.NameValuePair;

import com.callfire.api.client.AccessForbiddenException;
import com.callfire.api.client.BadRequestException;
import com.callfire.api.client.CallfireApiException;
import com.callfire.api.client.CallfireClientException;
import com.callfire.api.client.InternalServerErrorException;
import com.callfire.api.client.ResourceNotFoundException;
import com.callfire.api.client.RestApiClient;
import com.callfire.api.client.UnauthorizedException;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.common.model.ResourceId;
import com.callfire.api.client.api.common.model.request.GetByIdRequest;
import com.callfire.api.client.api.contacts.model.Contact;
import com.callfire.api.client.api.contacts.model.ContactHistory;
import com.callfire.api.client.api.contacts.model.DoNotContact;
import com.callfire.api.client.api.contacts.model.request.FindContactsRequest;
import com.callfire.api.client.api.contacts.model.request.FindDncNumbersRequest;

/**
 * Represents rest endpoint /contacts
 *
 * @since 1.0
 */
public class ContactsApi {
    private static final String CONTACTS_PATH = "/contacts";
    private static final String CONTACTS_ITEM_PATH = "/contacts/{}";
    private static final String CONTACTS_ITEM_HISTORY_PATH = "/contacts/{}/history";

    private RestApiClient client;

    public ContactsApi(RestApiClient client) {
        this.client = client;
    }

    /**
     * Find contacts by id, contact list, or on any property name. Returns a paged list of contacts.
     *
     * @param request request object with different fields to filter
     * @return {@link Page} with {@link Contact} objects
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public Page<Contact> find(FindContactsRequest request) {
        return client.get(CONTACTS_PATH, pageOf(Contact.class), request);
    }

    /**
     * Create contacts in the CallFire system.
     * These contacts are not validated on creation, but filtered by presence in DNC list.
     * They will be validated upon being added to a campaign.
     *
     * @param contacts contacts to create
     * @return list of ids newly created contacts
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public List<ResourceId> create(List<Contact> contacts) {
        List<Contact> filteredContacts = filterOutDnc(contacts);
        return client.post(CONTACTS_PATH, listHolderOf(ResourceId.class), filteredContacts).getItems();
    }

    private List<Contact> filterOutDnc(List<Contact> contacts) {
        Page<DoNotContact> page = new DncApi(client).find(FindDncNumbersRequest.create().build());

        List<String> dncList = new ArrayList<>();
        for (DoNotContact dnc : page.getItems()){
            dncList.add(dnc.getNumber());
        }

        List<Contact> output = new ArrayList<>();
        for (Contact contact : contacts) {
            if (dncList.contains(contact.getHomePhone()) || dncList.contains(contact.getWorkPhone())
                    || dncList.contains(contact.getMobilePhone())) {
                continue;
            }
            output.add(contact);
        }
        return output;
    }

    /**
     * Get contact by id. Deleted contacts can still be retrieved but will be marked deleted
     * and will not show up when quering contacts.
     *
     * @param id id of contact
     * @return requested contact
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
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
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public Contact get(Long id, String fields) {
        Validate.notNull(id, "id cannot be null");
        List<NameValuePair> queryParams = new ArrayList<>(1);
        addQueryParamIfSet("fields", fields, queryParams);
        return client.get(CONTACTS_ITEM_PATH.replaceFirst(PLACEHOLDER, id.toString()), of(Contact.class), queryParams);
    }

    /**
     * Update contact
     *
     * @param contact contact to update
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public void update(Contact contact) {
        Validate.notNull(contact.getId(), "contact.id cannot be null");
        client.put(CONTACTS_ITEM_PATH.replaceFirst(PLACEHOLDER, contact.getId().toString()), null, contact);
    }

    /**
     * Delete contact by id. This does not actually delete the contact, it just removes the contact from
     * any contact lists and marks the contact as deleted so won't show up in queries anymore.
     *
     * @param id id of contact
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
        client.delete(CONTACTS_ITEM_PATH.replaceFirst(PLACEHOLDER, id.toString()));
    }

    /**
     * Find all texts and calls attributed to a contact.
     *
     * @param request request to get particular contact's history
     * @return returns a list of calls and texts a contact has been involved with.
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public ContactHistory getHistory(GetByIdRequest request) {
        Validate.notNull(request.getId(), "request.id cannot be null");
        String path = CONTACTS_ITEM_HISTORY_PATH.replaceFirst(PLACEHOLDER, request.getId().toString());
        return client.get(path, of(ContactHistory.class), request);
    }

}
