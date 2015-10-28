package com.callfire.api.client.api.numbers;

import com.callfire.api.client.*;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.common.model.request.CommonFindRequest;
import com.callfire.api.client.api.numbers.model.Number;
import com.callfire.api.client.api.numbers.model.Region;
import com.callfire.api.client.api.numbers.model.request.FindNumberRegionsRequest;
import com.callfire.api.client.api.numbers.model.request.FindNumbersLocalRequest;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

/**
 * Represents rest endpoint /numbers
 *
 * @since 1.0
 */
public class NumbersApi {
    private static final String NUMBERS_LOCAL_PATH = "/numbers/local";
    private static final String NUMBERS_REGIONS_PATH = "/numbers/regions";
    private static final String NUMBERS_TOLLFREE_PATH = "/numbers/tollfree";

    private static final TypeReference<List<Number>> NUMBERS_LIST_TYPE = new TypeReference<List<Number>>() {
    };
    private static final TypeReference<Page<Region>> PAGE_OF_REGIONS_TYPE = new TypeReference<Page<Region>>() {
    };

    private RestApiClient client;

    public NumbersApi(RestApiClient client) {
        this.client = client;
    }

    /**
     * Find number in local catalog by prefix, zipcode, etc...
     *
     * @param request request payload
     * @return available numbers in catalog
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public List<Number> findNumbersLocal(FindNumbersLocalRequest request) {
        return client.get(NUMBERS_LOCAL_PATH, NUMBERS_LIST_TYPE, request);
    }

    /**
     * Find number region information. Use this API to obtain detailed region information that
     * can then be used to query for more specific phone numbers than a general query.
     *
     * @param request request payload
     * @return page of region objects
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public Page<Region> findNumberRegions(FindNumberRegionsRequest request) {
        return client.get(NUMBERS_REGIONS_PATH, PAGE_OF_REGIONS_TYPE, request);
    }

    /**
     * Find numbers in the CallFire tollfree numbers catalog that are available for purchase.
     *
     * @param request request payload
     * @return list of {@link Number}
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public List<Number> findNumbersTollfree(CommonFindRequest request) {
        return client.get(NUMBERS_TOLLFREE_PATH, NUMBERS_LIST_TYPE, request);
    }
}
