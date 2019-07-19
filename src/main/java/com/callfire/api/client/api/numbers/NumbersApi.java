package com.callfire.api.client.api.numbers;

import static com.callfire.api.client.ModelType.listHolderOf;
import static com.callfire.api.client.ModelType.pageOf;

import java.util.List;

import com.callfire.api.client.AccessForbiddenException;
import com.callfire.api.client.BadRequestException;
import com.callfire.api.client.CallfireApiException;
import com.callfire.api.client.CallfireClientException;
import com.callfire.api.client.InternalServerErrorException;
import com.callfire.api.client.ResourceNotFoundException;
import com.callfire.api.client.RestApiClient;
import com.callfire.api.client.UnauthorizedException;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.common.model.request.CommonFindRequest;
import com.callfire.api.client.api.numbers.model.Number;
import com.callfire.api.client.api.numbers.model.Region;
import com.callfire.api.client.api.numbers.model.request.FindNumberRegionsRequest;
import com.callfire.api.client.api.numbers.model.request.FindNumbersLocalRequest;
import com.callfire.api.client.api.numbers.model.request.FindTollfreeNumbersRequest;

/**
 * Represents rest endpoint /numbers
 *
 * @since 1.0
 */
public class NumbersApi {
    private static final String NUMBERS_LOCAL_PATH = "/numbers/local";
    private static final String NUMBERS_REGIONS_PATH = "/numbers/regions";
    private static final String NUMBERS_TOLLFREE_PATH = "/numbers/tollfree";

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
        return client.get(NUMBERS_LOCAL_PATH, listHolderOf(Number.class), request).getItems();
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
        return client.get(NUMBERS_REGIONS_PATH, pageOf(Region.class), request);
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
     * @deprecated this method doesn't work with offset parameter in request, please use findNumbersTollfree method with FindTollfreeNumbersRequest instead
     */
    @Deprecated
    public List<Number> findNumbersTollfree(CommonFindRequest request) {
        return client.get(NUMBERS_TOLLFREE_PATH, listHolderOf(Number.class), request).getItems();
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
    public List<Number> findNumbersTollfree(FindTollfreeNumbersRequest request) {
        return client.get(NUMBERS_TOLLFREE_PATH, listHolderOf(Number.class), request).getItems();
    }
}
