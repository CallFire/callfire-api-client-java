package com.callfire.api.client.api.callstexts;

import com.callfire.api.client.CallfireApiException;
import com.callfire.api.client.CallfireClientException;
import com.callfire.api.client.RestApiClient;
import com.callfire.api.client.api.callstexts.model.Call;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.callstexts.model.request.FindCallsRequest;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.http.NameValuePair;

import java.util.ArrayList;
import java.util.List;

import static com.callfire.api.client.ClientConstants.PLACEHOLDER;
import static com.callfire.api.client.ClientUtils.addQueryParamIfSet;

/**
 * Represents rest endpoint /calls
 */
public class CallsApi {
    private static final String CALLS_PATH = "/calls";
    private static final String CALLS_ITEM_PATH = "/calls/{}";
    private static final TypeReference<Page<Call>> PAGE_OF_CALLS_TYPE = new TypeReference<Page<Call>>() {
    };
    private static final TypeReference<Call> CALL_TYPE = new TypeReference<Call>() {
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
    public Page<Call> findCalls(FindCallsRequest request) {
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
    public Call getCall(Long id) {
        return getCall(id, null);
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
    public Call getCall(Long id, String fields) {
        List<NameValuePair> queryParams = new ArrayList<>();
        addQueryParamIfSet("fields", fields, queryParams);
        String path = CALLS_ITEM_PATH.replaceFirst(PLACEHOLDER, id.toString());
        return client.get(path, CALL_TYPE, queryParams);
    }
}
