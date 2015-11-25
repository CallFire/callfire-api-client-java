package com.callfire.api.client.api.numbers;

import com.callfire.api.client.*;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.numbers.model.NumberConfig;
import com.callfire.api.client.api.numbers.model.NumberLease;
import com.callfire.api.client.api.numbers.model.request.FindNumberLeaseConfigsRequest;
import com.callfire.api.client.api.numbers.model.request.FindNumberLeasesRequest;
import org.apache.commons.lang3.Validate;
import org.apache.http.NameValuePair;

import java.util.ArrayList;
import java.util.List;

import static com.callfire.api.client.ClientConstants.PLACEHOLDER;
import static com.callfire.api.client.ClientUtils.addQueryParamIfSet;
import static com.callfire.api.client.ModelType.of;
import static com.callfire.api.client.ModelType.pageOf;

/**
 * Represents rest endpoint /numbers/leases
 *
 * @since 1.0
 */
public class NumberLeasesApi {
    private static final String NUMBER_LEASES_PATH = "/numbers/leases";
    private static final String NUMBER_LEASES_ITEM_PATH = "/numbers/leases/{}";
    private static final String NUMBER_CONFIGS_PATH = "/numbers/leases/configs";
    private static final String NUMBER_CONFIGS_ITEM_PATH = "/numbers/leases/configs/{}";

    private RestApiClient client;

    public NumberLeasesApi(RestApiClient client) {
        this.client = client;
    }

    /**
     * Find number leases by prefix, zipcode, etc...
     * This API is useful for finding all numbers currently owned by an account.
     *
     * @param request request payload
     * @return page of number leases
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public Page<NumberLease> find(FindNumberLeasesRequest request) {
        return client.get(NUMBER_LEASES_PATH, pageOf(NumberLease.class), request);
    }

    /**
     * Get number lease by number
     *
     * @param number leased phone number
     * @return object which represents number lease
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public NumberLease get(String number) {
        return get(number, null);
    }

    /**
     * Get number lease by number
     *
     * @param number leased phone number
     * @param fields Limit fields returned. Example fields=id,name
     * @return object which represents number lease
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public NumberLease get(String number, String fields) {
        Validate.notBlank(number, "number cannot be blank");
        List<NameValuePair> queryParams = new ArrayList<>();
        addQueryParamIfSet("fields", fields, queryParams);
        String path = NUMBER_LEASES_ITEM_PATH.replaceFirst(PLACEHOLDER, number);
        return client.get(path, of(NumberLease.class), queryParams);
    }

    /**
     * Update number lease
     *
     * @param lease update payload
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public void update(NumberLease lease) {
        Validate.notBlank(lease.getNumber(), "lease.number cannot be blank");
        client.put(NUMBER_LEASES_ITEM_PATH.replaceFirst(PLACEHOLDER, lease.getNumber()), null, lease);
    }

    /**
     * Find all number lease configs for the user.
     *
     * @param request request payload
     * @return page of number leases configs
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public Page<NumberConfig> findConfigs(FindNumberLeaseConfigsRequest request) {
        return client.get(NUMBER_CONFIGS_PATH, pageOf(NumberConfig.class), request);
    }

    /**
     * Get number lease config
     *
     * @param number leased phone number
     * @return object which represents number lease config
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public NumberConfig getConfig(String number) {
        return getConfig(number, null);
    }

    /**
     * Get number lease config
     *
     * @param number leased phone number
     * @param fields Limit fields returned. Example fields=id,name
     * @return object which represents number lease config
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public NumberConfig getConfig(String number, String fields) {
        Validate.notBlank(number, "number cannot be blank");
        List<NameValuePair> queryParams = new ArrayList<>();
        addQueryParamIfSet("fields", fields, queryParams);
        String path = NUMBER_CONFIGS_ITEM_PATH.replaceFirst(PLACEHOLDER, number);
        return client.get(path, of(NumberConfig.class), queryParams);
    }

    /**
     * Update number lease config
     *
     * @param config update payload
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public void updateConfig(NumberConfig config) {
        Validate.notBlank(config.getNumber(), "config.number cannot be blank");
        client.put(NUMBER_CONFIGS_ITEM_PATH.replaceFirst(PLACEHOLDER, config.getNumber()), null, config);
    }

}
