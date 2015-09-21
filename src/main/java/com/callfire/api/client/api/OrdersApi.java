package com.callfire.api.client.api;

import com.callfire.api.client.CallfireApiException;
import com.callfire.api.client.CallfireClientException;
import com.callfire.api.client.RestApiClient;
import com.callfire.api.client.model.NumberOrder;
import com.callfire.api.client.api.common.model.ResourceId;
import com.callfire.api.client.api.keywords.model.request.KeywordPurchaseRequest;
import com.callfire.api.client.api.numbers.model.request.NumberPurchaseRequest;
import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.commons.lang3.Validate;
import org.apache.http.NameValuePair;

import java.util.ArrayList;
import java.util.List;

import static com.callfire.api.client.ClientConstants.PLACEHOLDER;
import static com.callfire.api.client.ClientConstants.Type.RESOURCE_ID_TYPE;
import static com.callfire.api.client.ClientUtils.addQueryParamIfSet;

/**
 * Represents rest endpoint /orders
 */
public class OrdersApi {
    private static final String ORDERS_KEYWORDS = "/orders/keywords";
    private static final String ORDERS_NUMBERS = "/orders/numbers";
    private static final String ORDERS_GET_ORDER = "/orders/{}/{}";

    private static final TypeReference<NumberOrder> NUMBER_ORDER_TYPE = new TypeReference<NumberOrder>() {
    };

    private RestApiClient client;

    public OrdersApi(RestApiClient client) {
        this.client = client;
    }

    /**
     * Order keywords by list
     *
     * @param request request payload
     * @return ResourceId with id of created order
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public ResourceId orderKeywords(KeywordPurchaseRequest request) {
        return client.post(ORDERS_KEYWORDS, RESOURCE_ID_TYPE, request);
    }

    /**
     * Order numbers by list of number or by a query
     *
     * @param request request payload
     * @return ResourceId with id of created order
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public ResourceId orderNumbers(NumberPurchaseRequest request) {
        return client.post(ORDERS_NUMBERS, RESOURCE_ID_TYPE, request);
    }

    /**
     * Get order for keyword and/or number orders
     *
     * @param id id of order
     * @return NumberOrder
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public NumberOrder getKeywordOrder(Long id) {
        return getKeywordOrder(id, null);
    }

    /**
     * Get order for keyword and/or number orders
     *
     * @param id     id of order
     * @param fields limit fields returned. Example fields=id,name
     * @return NumberOrder
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public NumberOrder getKeywordOrder(Long id, String fields) {
        Validate.notNull(id, "id cannot be null");
        List<NameValuePair> queryParams = new ArrayList<>();
        addQueryParamIfSet("fields", fields, queryParams);
        String path = ORDERS_GET_ORDER.replaceFirst(PLACEHOLDER, "keyword").replaceFirst(PLACEHOLDER, id.toString());
        return client.get(path, NUMBER_ORDER_TYPE, queryParams);
    }

    /**
     * Get order for keyword and/or number orders
     *
     * @param id id of order
     * @return NumberOrder
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public NumberOrder getNumberOrder(Long id) {
        return getNumberOrder(id, null);
    }

    /**
     * Get order for keyword and/or number orders
     *
     * @param id     id of order
     * @param fields limit fields returned. Example fields=id,name
     * @return NumberOrder
     * @throws CallfireApiException    in case API cannot be queried for some reason and server returned error
     * @throws CallfireClientException in case error has occurred in client
     */
    public NumberOrder getNumberOrder(Long id, String fields) {
        Validate.notNull(id, "id cannot be null");
        List<NameValuePair> queryParams = new ArrayList<>();
        addQueryParamIfSet("fields", fields, queryParams);
        String path = ORDERS_GET_ORDER.replaceFirst(PLACEHOLDER, "number").replaceFirst(PLACEHOLDER, id.toString());
        return client.get(path, NUMBER_ORDER_TYPE, queryParams);
    }
}
