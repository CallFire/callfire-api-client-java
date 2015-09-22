package com.callfire.api.client.api.contacts;

import com.callfire.api.client.CallfireApiException;
import com.callfire.api.client.CallfireClientException;
import com.callfire.api.client.RestApiClient;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.contacts.model.DoNotContact;
import com.callfire.api.client.api.contacts.model.request.FindDncContactsRequest;
import com.fasterxml.jackson.core.type.TypeReference;

import static com.callfire.api.client.ClientConstants.Type.VOID_TYPE;

/**
 * Represents rest endpoint /contacts/do-not-calls
 */
public class DncApi {
    private static final String DNC_PATH = "/contacts/do-not-calls";

    private RestApiClient client;
    private DncListsApi dncListsApi;

    public static final TypeReference<Page<DoNotContact>> PAGE_OF_DNC_TYPE = new TypeReference<Page<DoNotContact>>() {
    };

    public DncApi(RestApiClient client) {
        this.client = client;
    }

    /**
     * Find do not contact numbers
     *
     * @param request find request with different properties to filter
     * @return Page with numbers which must not be contacted
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public Page<DoNotContact> findDncContacts(FindDncContactsRequest request) {
        return client.get(DNC_PATH, PAGE_OF_DNC_TYPE, request);
    }

    /**
     * Update dnc by number
     *
     * @param doNotContact DNC item to update
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public void updateDncNumber(DoNotContact doNotContact) {
        client.put(DNC_PATH, VOID_TYPE, doNotContact);
    }

    /**
     * Get /contacts/do-not-calls/lists api endpoint
     *
     * @return endpoint object
     */
    public DncListsApi getDncListsApi() {
        if (dncListsApi == null) {
            dncListsApi = new DncListsApi(client);
        }
        return dncListsApi;
    }
}
