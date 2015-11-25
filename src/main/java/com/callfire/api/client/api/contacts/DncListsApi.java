package com.callfire.api.client.api.contacts;

import com.callfire.api.client.*;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.common.model.ResourceId;
import com.callfire.api.client.api.common.model.request.GetByIdRequest;
import com.callfire.api.client.api.contacts.model.DncList;
import com.callfire.api.client.api.contacts.model.DoNotContact;
import com.callfire.api.client.api.contacts.model.UniversalDnc;
import com.callfire.api.client.api.contacts.model.request.AddDncListItemsRequest;
import com.callfire.api.client.api.contacts.model.request.FindDncListsRequest;
import org.apache.commons.lang3.Validate;
import org.apache.http.NameValuePair;

import java.util.ArrayList;
import java.util.List;

import static com.callfire.api.client.ClientConstants.PLACEHOLDER;
import static com.callfire.api.client.ClientUtils.addQueryParamIfSet;
import static com.callfire.api.client.ModelType.*;

/**
 * Represents rest endpoint /contacts/dncs/lists
 *
 * @since 1.0
 */
public class DncListsApi {
    private static final String DNC_LISTS_PATH = "/contacts/dncs/lists";
    private static final String DNC_LISTS_UNIVERSAL_PATH = "/contacts/dncs/lists/universal/{}";
    private static final String DNC_LISTS_LIST_PATH = "/contacts/dncs/lists/{}";
    private static final String DNC_LISTS_LIST_ITEMS_PATH = "/contacts/dncs/lists/{}/items";
    private static final String DNC_LISTS_LIST_ITEMS_NUMBER_PATH = "/contacts/dncs/lists/{}/items/{}";

    private RestApiClient client;

    public DncListsApi(RestApiClient client) {
        this.client = client;
    }

    /**
     * Find do not contact (DNC) lists
     *
     * @param request request with properties to find
     * @return Page of DNC lists
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public Page<DncList> find(FindDncListsRequest request) {
        return client.get(DNC_LISTS_PATH, pageOf(DncList.class), request);
    }

    /**
     * Create do not contact (DNC) list.
     *
     * @param dncList list to create
     * @return ResourceId with id of created list
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public ResourceId create(DncList dncList) {
        return client.post(DNC_LISTS_PATH, of(ResourceId.class), dncList);
    }

    /**
     * Search Universal Do Not Contact by number
     *
     * @param toNumber Phone Number in Do Not Contact list
     * @return list of universal DNC contacts
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public List<UniversalDnc> getUniversalDncNumber(String toNumber) {
        return getUniversalDncNumber(toNumber, null, null);
    }

    /**
     * Search Universal Do Not Contact by number
     *
     * @param toNumber   Phone Number in Do Not Contact list
     * @param fromNumber Searches for entries where fromNumber is communicating with toNumber, or vice versa.
     * @param fields     Limit fields returned. Example fields=limit,offset,items(id,name)
     * @return list of universal DNC contacts
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public List<UniversalDnc> getUniversalDncNumber(String toNumber, String fromNumber, String fields) {
        Validate.notBlank(toNumber, "toNumber cannot be blank");
        String path = DNC_LISTS_UNIVERSAL_PATH.replaceFirst(PLACEHOLDER, toNumber);
        List<NameValuePair> queryParams = new ArrayList<>(2);
        addQueryParamIfSet("fromNumber", fromNumber, queryParams);
        addQueryParamIfSet("fields", fields, queryParams);
        return client.get(path, listHolderOf(UniversalDnc.class), queryParams).getItems();
    }

    /**
     * Get DNC list by id
     *
     * @param id id of DNC list
     * @return DNC list object
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public DncList get(Long id) {
        return get(id, null);
    }

    /**
     * Get DNC list by id
     *
     * @param id     id of contact list
     * @param fields limit fields returned. Example fields=name,status
     * @return DNC list object
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public DncList get(Long id, String fields) {
        Validate.notNull(id, "id cannot be null");
        List<NameValuePair> queryParams = new ArrayList<>(1);
        addQueryParamIfSet("fields", fields, queryParams);
        return client.get(DNC_LISTS_LIST_PATH.replaceFirst(PLACEHOLDER, id.toString()), of(DncList.class), queryParams);
    }

    /**
     * Delete DNC list
     *
     * @param id DNC list id
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
        client.delete(DNC_LISTS_LIST_PATH.replaceFirst(PLACEHOLDER, id.toString()));
    }

    /**
     * Get DNC list items
     * Property <b>request.id</b> required
     *
     * @param request request object with properties to filter
     * @return Page filled with DNC items
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public Page<DoNotContact> getListItems(GetByIdRequest request) {
        Validate.notNull(request.getId(), "request.id cannot be null");
        String path = DNC_LISTS_LIST_ITEMS_PATH.replaceFirst(PLACEHOLDER, request.getId().toString());
        return client.get(path, pageOf(DoNotContact.class), request);
    }

    /**
     * Add DNC list items to list
     *
     * @param request request object with DNC items to add
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public void addListItems(AddDncListItemsRequest request) {
        Validate.notNull(request.getContactListId(), "request.contactListId cannot be null");
        String path = DNC_LISTS_LIST_ITEMS_PATH.replaceFirst(PLACEHOLDER, request.getContactListId().toString());
        client.post(path, null, request.getContacts());
    }

    /**
     * Delete single DNC list contact by number
     *
     * @param id     id of DNC list
     * @param number number to remove
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public void removeListItem(Long id, String number) {
        Validate.notNull(id, "id cannot be null");
        Validate.notBlank(number, "number cannot be blank");
        String path = DNC_LISTS_LIST_ITEMS_NUMBER_PATH.replaceFirst(PLACEHOLDER, id.toString())
            .replaceFirst(PLACEHOLDER, number);
        client.delete(path);
    }

    /**
     * Delete DNC list items
     *
     * @param id      id of DNC list
     * @param numbers numbers to remove
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public void removeListItems(Long id, List<String> numbers) {
        Validate.notNull(id, "contactListId cannot be null");
        List<NameValuePair> queryParams = new ArrayList<>(1);
        addQueryParamIfSet("number", numbers, queryParams);
        client.delete(DNC_LISTS_LIST_ITEMS_PATH.replaceFirst(PLACEHOLDER, id.toString()), queryParams);
    }
}
