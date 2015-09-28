package com.callfire.api.client.api.callstexts;

import com.callfire.api.client.CallfireApiException;
import com.callfire.api.client.CallfireClientException;
import com.callfire.api.client.RestApiClient;
import com.callfire.api.client.api.callstexts.model.Call;
import com.callfire.api.client.api.callstexts.model.CallRecipient;
import com.callfire.api.client.api.callstexts.model.request.FindCallsRequest;
import com.callfire.api.client.api.common.model.ListHolder;
import com.callfire.api.client.api.common.model.Page;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.commons.lang3.Validate;
import org.apache.http.NameValuePair;

import java.util.ArrayList;
import java.util.List;

import static com.callfire.api.client.ClientConstants.PLACEHOLDER;
import static com.callfire.api.client.ClientUtils.addQueryParamIfSet;

/**
 * Represents rest endpoint /calls
 *
 * @since 1.0
 */
public class CallsApi {
    private static final String CALLS_PATH = "/calls";
    private static final String CALLS_ITEM_PATH = "/calls/{}";
    private static final TypeReference<Call> CALL_TYPE = new TypeReference<Call>() {
    };
    public static final TypeReference<Page<Call>> PAGE_OF_CALLS_TYPE = new TypeReference<Page<Call>>() {
    };
    public static final TypeReference<ListHolder<Call>> LIST_OF_CALLS_TYPE = new TypeReference<ListHolder<Call>>() {
    };

    private RestApiClient client;

    public CallsApi(RestApiClient client) {
        this.client = client;
    }

    /**
     * Find calls by different properties, broadcast id, toNumber, fromNumber, label, state, etc.
     *
     * @param request request object with different fields to filter
     * @return Page with @{Call} objects
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public Page<Call> find(FindCallsRequest request) {
        return client.get(CALLS_PATH, PAGE_OF_CALLS_TYPE, request);
    }

    /**
     * Get call by id
     *
     * @param id id of call
     * @return Call object
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public Call get(Long id) {
        return get(id, null);
    }

    /**
     * Get call by id
     *
     * @param id     id of call
     * @param fields limit fields returned. Example fields=id,name
     * @return Call object
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public Call get(Long id, String fields) {
        Validate.notNull(id, "id cannot be null");
        List<NameValuePair> queryParams = new ArrayList<>(1);
        addQueryParamIfSet("fields", fields, queryParams);
        String path = CALLS_ITEM_PATH.replaceFirst(PLACEHOLDER, id.toString());
        return client.get(path, CALL_TYPE, queryParams);
    }

    /**
     * Send calls to recipients through default campaign
     *
     * @param recipients call recipients
     * @return list of {@link Call}
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public List<Call> send(List<CallRecipient> recipients) {
        return send(recipients, null, null);
    }

    /**
     * Send call to recipients through existing campaign, if null default campaign will be used
     *
     * @param recipients text recipients
     * @param campaignId id of outbound campaign
     * @param fields     limit fields returned. Example fields=id,name
     * @return list of {@link Call}
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public List<Call> send(List<CallRecipient> recipients, Long campaignId, String fields) {
        List<NameValuePair> queryParams = new ArrayList<>(2);
        addQueryParamIfSet("campaignId", campaignId, queryParams);
        addQueryParamIfSet("fields", fields, queryParams);
        return client.post(CALLS_PATH, LIST_OF_CALLS_TYPE, recipients, queryParams).getItems();
    }
}
