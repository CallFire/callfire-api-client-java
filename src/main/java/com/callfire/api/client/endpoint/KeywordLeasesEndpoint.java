package com.callfire.api.client.endpoint;

import com.callfire.api.client.CallfireApiException;
import com.callfire.api.client.CallfireClientException;
import com.callfire.api.client.RestApiClient;
import com.callfire.api.client.model.KeywordLease;
import com.callfire.api.client.model.Page;
import com.callfire.api.client.model.request.FindKeywordLeasesRequest;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.http.NameValuePair;

import java.util.ArrayList;
import java.util.List;

import static com.callfire.api.client.ClientConstants.PLACEHOLDER;
import static com.callfire.api.client.ClientConstants.Type.VOID_TYPE;
import static com.callfire.api.client.ClientUtils.addQueryParamIfSet;

/**
 * Represents rest endpoint /keywords/leases
 */
public class KeywordLeasesEndpoint {
    private static final String KEYWORD_LEASES = "/keywords/leases";
    private static final String KEYWORD_LEASES_ITEM = "/keywords/leases/{}";

    private static final TypeReference<Page<KeywordLease>> PAGE_OF_KEYWORD_LEASE_TYPE = new TypeReference<Page<KeywordLease>>() {
    };
    private static final TypeReference<KeywordLease> KEYWORD_LEASE_TYPE = new TypeReference<KeywordLease>() {
    };

    private RestApiClient client;

    public KeywordLeasesEndpoint(RestApiClient client) {
        this.client = client;
    }

    /**
     * Find keyword leases
     *
     * @param request request payload
     * @return page of keyword leases
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public Page<KeywordLease> findKeywordLeases(FindKeywordLeasesRequest request) {
        return client.get(KEYWORD_LEASES, PAGE_OF_KEYWORD_LEASE_TYPE, request);
    }

    /**
     * Get keyword lease by keyword
     *
     * @param keyword leased keyword
     * @return object which represents keyword lease
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public KeywordLease getKeywordLease(String keyword) {
        return getKeywordLease(keyword, null);
    }

    /**
     * Get keyword lease by keyword
     *
     * @param keyword leased keyword
     * @param fields  Limit fields returned. Example fields=id,name
     * @return object which represents keyword lease
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public KeywordLease getKeywordLease(String keyword, String fields) {
        List<NameValuePair> queryParams = new ArrayList<>();
        addQueryParamIfSet("fields", fields, queryParams);
        return client.get(KEYWORD_LEASES_ITEM.replaceFirst(PLACEHOLDER, keyword), KEYWORD_LEASE_TYPE, queryParams);
    }

    /**
     * Update keyword lease
     *
     * @param keyword      leased keyword to update
     * @param keywordLease update payload
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public void updateKeywordLease(String keyword, KeywordLease keywordLease) {
        client.put(KEYWORD_LEASES_ITEM.replaceFirst(PLACEHOLDER, keyword), VOID_TYPE, keywordLease);
    }
}
