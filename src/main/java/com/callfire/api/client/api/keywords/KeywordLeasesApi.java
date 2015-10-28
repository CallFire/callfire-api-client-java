package com.callfire.api.client.api.keywords;

import com.callfire.api.client.*;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.common.model.request.CommonFindRequest;
import com.callfire.api.client.api.keywords.model.KeywordLease;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.commons.lang3.Validate;
import org.apache.http.NameValuePair;

import java.util.ArrayList;
import java.util.List;

import static com.callfire.api.client.ClientConstants.PLACEHOLDER;
import static com.callfire.api.client.ClientConstants.Type.VOID_TYPE;
import static com.callfire.api.client.ClientUtils.addQueryParamIfSet;

/**
 * Represents rest endpoint /keywords/leases
 *
 * @since 1.0
 */
public class KeywordLeasesApi {
    private static final String KEYWORD_LEASES_PATH = "/keywords/leases";
    private static final String KEYWORD_LEASES_ITEM_PATH = "/keywords/leases/{}";

    private static final TypeReference<Page<KeywordLease>> PAGE_OF_KEYWORD_LEASES_TYPE = new TypeReference<Page<KeywordLease>>() {
    };
    private static final TypeReference<KeywordLease> KEYWORD_LEASE_TYPE = new TypeReference<KeywordLease>() {
    };

    private RestApiClient client;

    public KeywordLeasesApi(RestApiClient client) {
        this.client = client;
    }

    /**
     * Find all owned keyword leases for a user. A keyword lease is the ownership information involving a keyword.
     *
     * @param request request payload
     * @return page of keyword leases
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public Page<KeywordLease> find(CommonFindRequest request) {
        return client.get(KEYWORD_LEASES_PATH, PAGE_OF_KEYWORD_LEASES_TYPE, request);
    }

    /**
     * Get keyword lease by keyword
     *
     * @param keyword leased keyword
     * @return object which represents keyword lease
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public KeywordLease get(String keyword) {
        return get(keyword, null);
    }

    /**
     * Get keyword lease by keyword
     *
     * @param keyword leased keyword
     * @param fields  Limit fields returned. Example fields=id,name
     * @return object which represents keyword lease
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public KeywordLease get(String keyword, String fields) {
        Validate.notBlank(keyword, "keyword cannot be blank");
        List<NameValuePair> queryParams = new ArrayList<>(1);
        addQueryParamIfSet("fields", fields, queryParams);
        return client.get(KEYWORD_LEASES_ITEM_PATH.replaceFirst(PLACEHOLDER, keyword), KEYWORD_LEASE_TYPE, queryParams);
    }

    /**
     * Update keyword lease
     *
     * @param lease keyword lease payload
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public void update(KeywordLease lease) {
        Validate.notBlank(lease.getKeyword(), "lease.keyword cannot be blank");
        client.put(KEYWORD_LEASES_ITEM_PATH.replaceFirst(PLACEHOLDER, lease.getKeyword()), VOID_TYPE, lease);
    }
}
