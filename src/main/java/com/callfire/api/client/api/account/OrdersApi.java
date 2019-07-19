package com.callfire.api.client.api.account;

import static com.callfire.api.client.ClientConstants.PLACEHOLDER;
import static com.callfire.api.client.ClientUtils.addQueryParamIfSet;
import static com.callfire.api.client.ModelType.of;
import static com.callfire.api.client.ModelType.pageOf;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.Validate;
import org.apache.http.NameValuePair;

import com.callfire.api.client.AccessForbiddenException;
import com.callfire.api.client.BadRequestException;
import com.callfire.api.client.CallfireApiException;
import com.callfire.api.client.CallfireClientException;
import com.callfire.api.client.InternalServerErrorException;
import com.callfire.api.client.ResourceNotFoundException;
import com.callfire.api.client.RestApiClient;
import com.callfire.api.client.UnauthorizedException;
import com.callfire.api.client.api.account.model.NumberOrder;
import com.callfire.api.client.api.account.model.request.FindOrdersRequest;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.common.model.ResourceId;
import com.callfire.api.client.api.keywords.model.request.KeywordPurchaseRequest;
import com.callfire.api.client.api.numbers.model.request.NumberPurchaseRequest;

/**
 * Represents rest endpoint /orders
 */
public class OrdersApi {
    private static final String ORDERS = "/orders";
    private static final String ORDERS_KEYWORDS = ORDERS + "/keywords";
    private static final String ORDERS_NUMBERS = ORDERS + "/numbers";
    private static final String ORDERS_GET_ORDER = ORDERS + "/{}";

    private RestApiClient client;

    public OrdersApi(RestApiClient client) {
        this.client = client;
    }

    /**
     * Purchase keywords. Send a list of available keywords into this API to purchase them
     * using CallFire credits. Be sure the account has credits before trying to purchase.
     *
     * @param request request payload
     * @return ResourceId with id of created order
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public ResourceId orderKeywords(KeywordPurchaseRequest request) {
        return client.post(ORDERS_KEYWORDS, of(ResourceId.class), request);
    }

    /**
     * Purchase numbers. There are many ways to purchase a number. Set either tollFreeCount or localCount
     * along with some querying fields to purchase numbers by bulk query. Set the list of numbers
     * to purchase by list. Available numbers will be purchased using CallFire credits owned by the user.
     * Be sure the account has credits before trying to purchase.
     *
     * @param request request payload
     * @return ResourceId with id of created order
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public ResourceId orderNumbers(NumberPurchaseRequest request) {
        return client.post(ORDERS_NUMBERS, of(ResourceId.class), request);
    }

    /**
     * Find number leases by prefix, zipcode, etc...
     * This API is useful for finding all numbers currently owned by an account.
     *
     * @param request request payload
     * @return page of number orders
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public Page<NumberOrder> findOrders(FindOrdersRequest request) {
        return client.get(ORDERS, pageOf(NumberOrder.class), request);
    }

    /**
     * Get order for keyword and/or number orders
     *
     * @param id id of order
     * @return NumberOrder
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public NumberOrder getOrder(Long id) {
        return getOrder(id, null);
    }

    /**
     * Get order for keyword and/or number orders
     *
     * @param id     id of order
     * @param fields limit fields returned. Example fields=id,name
     * @return NumberOrder
     * @throws BadRequestException          in case HTTP response code is 400 - Bad request, the request was formatted improperly.
     * @throws UnauthorizedException        in case HTTP response code is 401 - Unauthorized, API Key missing or invalid.
     * @throws AccessForbiddenException     in case HTTP response code is 403 - Forbidden, insufficient permissions.
     * @throws ResourceNotFoundException    in case HTTP response code is 404 - NOT FOUND, the resource requested does not exist.
     * @throws InternalServerErrorException in case HTTP response code is 500 - Internal Server Error.
     * @throws CallfireApiException         in case HTTP response code is something different from codes listed above.
     * @throws CallfireClientException      in case error has occurred in client.
     */
    public NumberOrder getOrder(Long id, String fields) {
        Validate.notNull(id, "id cannot be null");
        List<NameValuePair> queryParams = new ArrayList<>(1);
        addQueryParamIfSet("fields", fields, queryParams);
        String path = ORDERS_GET_ORDER.replaceFirst(PLACEHOLDER, id.toString());
        return client.get(path, of(NumberOrder.class), queryParams);
    }
}
