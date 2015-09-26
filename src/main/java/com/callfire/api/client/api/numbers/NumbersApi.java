package com.callfire.api.client.api.numbers;

import com.callfire.api.client.CallfireApiException;
import com.callfire.api.client.CallfireClientException;
import com.callfire.api.client.RestApiClient;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.common.model.request.CommonGetRequest;
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
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public List<Number> findNumbersLocal(FindNumbersLocalRequest request) {
        return client.get(NUMBERS_LOCAL_PATH, NUMBERS_LIST_TYPE, request);
    }

    /**
     * Get region data for numbers by prefix, zipcode, etc...
     *
     * @param request request payload
     * @return page of region objects
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public Page<Region> findNumberRegions(FindNumberRegionsRequest request) {
        return client.get(NUMBERS_REGIONS_PATH, PAGE_OF_REGIONS_TYPE, request);
    }

    /**
     * Find numbers in tollfree catalog
     *
     * @param request request payload
     * @return list of {@link Number}
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public List<Number> findNumbersTollfree(CommonGetRequest request) {
        return client.get(NUMBERS_TOLLFREE_PATH, NUMBERS_LIST_TYPE, request);
    }
}
